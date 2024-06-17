package services;

import core.services.MysqlDBService;
import core.utils.UsuarioThreadLocal;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Candidato;
import models.Reclutador;
import models.Usuario;

public class UsuarioService extends BaseService {

    public UsuarioService() {
        db = new MysqlDBService();
    }

    public void registrarUsuario(Usuario usuario) {
        try {

            // Validaciones de entrada
            if (usuario == null || usuario.getNombres() == null || usuario.getApellidos() == null || usuario.getRol() == null || usuario.getUsername() == null || usuario.getPassword() == null) {
                throw new IllegalArgumentException("Datos del usuario incompletos o nulos.");
            }

            String rol = usuario.getRol();

            // Guarda el estado original de auto-commit y desactiva el auto-commit
            originalAutoCommit = db.getAutoCommit();
            db.setAutoCommit(false);

            // Insertar usuario
            querySQL_1 = "INSERT INTO usuarios (nombres, apellidos, rol, username, pwd) VALUES (?,?,?,?,?)";
            Object[] parametrosSQL_1 = {usuario.getNombres(), usuario.getApellidos(), rol, usuario.getUsername(), usuario.getPassword()};
            int inserted = db.queryInsertar(querySQL_1, parametrosSQL_1);

            if (inserted > 0) {
                switch (rol) {
                    case "candidato" -> {
                        // Insertar candidato
                        /*CandidatoService candidatoService = new CandidatoService();
                        Candidato candidato = new Candidato();
                        candidato.setNombre(usuario.getNombres());
                        candidato.setApellidos(usuario.getApellidos());
                        candidato.setEstado("activo");
                        candidatoService.crearCandidato(candidato, true);*/
                    }
                    case "reclutador" -> {
                        /*ReclutadorService reclutadorService = new ReclutadorService();
                        Reclutador reclutador = new Reclutador();
                        reclutador.setNombre(usuario.getNombres());
                        reclutador.setApellidos(usuario.getApellidos());
                        reclutadorService.crearReclutador(reclutador);*/
                    }
                    default ->
                        throw new AssertionError();
                }
                
                db.commit();// Confirmar transacción SQL
                
            } else {
                throw new RuntimeException("No se pudo insertar el usuario en la base de datos.");
            }
        } catch (RuntimeException e) {
            
            db.rollback();// Revertir transacción SQL
            
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        } finally {
            db.setAutoCommit(originalAutoCommit); // Restaura el auto-commit
            db.cerrarConsulta();
        }
    }
}
