package services;

import core.services.MysqlDBService;
import models.Candidato;
import models.Reclutador;
import models.Usuario;

public class UsuarioService extends BaseService {

    public UsuarioService() {
        db = new MysqlDBService();
    }

    public boolean registrarUsuario(Usuario usuario) {
        boolean success = false;
        
        try {

            // Validaciones de entrada
            if (usuario == null || usuario.getNombres() == null || usuario.getApellidos() == null || usuario.getRol() == null || usuario.getUsername() == null || usuario.getPassword() == null) {
                throw new IllegalArgumentException("Datos del usuario incompletos o nulos.");
            }

            String rol = usuario.getRol();

            // Insertar usuario
            querySQL_1 = "INSERT INTO usuarios (nombres, apellidos, rol, username, pwd) VALUES (?,?,?,?,?)";
            Object[] parametrosSQL_1 = {usuario.getNombres(), usuario.getApellidos(), rol, usuario.getUsername(), usuario.getPassword()};
            int id_usuario = db.queryInsertar(querySQL_1, parametrosSQL_1);

            if (id_usuario > 0) {
                switch (rol) {
                    case "candidato" -> {
                        // Insertar candidato
                        CandidatoService candidatoService = new CandidatoService();
                        Candidato candidato = new Candidato();
                        candidato.setIdUsuario(id_usuario);
                        candidato.setNombre(usuario.getNombres());
                        candidato.setApellidos(usuario.getApellidos());
                        candidato.setEstado("activo");
                        candidatoService.registrarCandidato(candidato);
                        
                        success = true;
                        break;
                    }
                    case "reclutador" -> {
                        // Insertar reclutador
                        ReclutadorService reclutadorService = new ReclutadorService();
                        Reclutador reclutador = new Reclutador();
                        reclutador.setIdUsuario(id_usuario);
                        reclutador.setNombre(usuario.getNombres());
                        reclutador.setApellidos(usuario.getApellidos());
                        reclutadorService.registrarReclutador(reclutador);
                        
                        success = true;
                        break;
                    }
                    default ->
                        throw new AssertionError();
                }

                // db.commit();// Confirmar transacción SQL
            } else {
                throw new RuntimeException("No se pudo insertar el usuario en la base de datos.");
            }
        } catch (RuntimeException e) {
            // db.rollback();// Revertir transacción SQL
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        } finally {
            // db.setAutoCommit(originalAutoCommit); // Restaura el auto-commit
            db.cerrarConsulta();
        }
        
        return success;
    }
}
