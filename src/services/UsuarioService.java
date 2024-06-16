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

            // Validación de entrada
            if (usuario == null || usuario.getFullname() == null || usuario.getRol() == null || usuario.getUsername() == null || usuario.getPassword() == null || usuario.getEstado() == null) {
                throw new IllegalArgumentException("Datos del usuario incompletos o nulos.");
            }

            querySQL_1 = "INSERT INTO usuarios (fullname, rol, username, pwd, estado) VALUES (?,?,?,?,?)";
            Object[] parametrosSQL_1 = {usuario.getFullname(), usuario.getRol(), usuario.getUsername(), usuario.getPassword(), usuario.getEstado()};
            int inserted = db.queryInsertar(querySQL_1, parametrosSQL_1);

            if (inserted > 0) {
                String rol = usuario.getRol();
                switch (rol) {
                    case "candidato" -> {
                        CandidatoService candidatoService = new CandidatoService();
                        Candidato candidato = new Candidato();
                        candidato.setNombre(usuario.getFullname());
                        candidatoService.crearCandidato(candidato);
                    }
                    case "reclutador" -> {
                        /*ReclutadorService reclutadorService = new ReclutadorService();
                        Reclutador reclutador = new Reclutador();
                        reclutador.setNombre(usuario.getFullname());
                        reclutadorService.crearReclutador(reclutador);*/
                    }
                    default ->
                        throw new AssertionError();
                }
            } else {
                throw new RuntimeException("No se pudo insertar el usuario en la base de datos.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);

        } finally {
            db.cerrarConsulta();
        }
    }
}
