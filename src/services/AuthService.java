package services;

import core.services.MysqlDBService;
import core.utils.UsuarioThreadLocal;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Candidato;
import models.Reclutador;
import models.Usuario;

public class AuthService extends BaseService {

    public AuthService() {
        db = new MysqlDBService();
    }

    public boolean login(String username, String pwd) {

        boolean success = false;

        try {
            ResultSet rs_1, rs_2, rs_3, rs_4;

            querySQL_1 = "SELECT u.rol FROM usuarios u WHERE u.username = ? AND u.pwd = ? AND u.estado = 'activo' LIMIT 1;";
            Object[] parametrosSQL_1 = {username, pwd};
            rs_1 = db.queryConsultar(querySQL_1, parametrosSQL_1);

            String rol = "";

            if (rs_1.next()) {
                rol = rs_1.getString("rol");
            }

            switch (rol) {
                case "candidato" ->
                    querySQL_2 = "SELECT u.*, c.id AS 'id_candidato', c.id_persona FROM usuarios u JOIN candidatos c ON c.id_usuario = u.id AND c.estado = 'activo' WHERE u.username = ? AND u.pwd = ? AND u.estado = 'activo' LIMIT 1;";
                case "reclutador" ->
                    querySQL_2 = "SELECT u.*, r.id AS 'id_reclutador', r.id_persona FROM usuarios u JOIN reclutadores r ON r.id_usuario = u.id AND r.estado = 'activo' WHERE u.username = ? AND u.pwd = ? AND u.estado = 'activo' LIMIT 1;";
                default ->
                    throw new RuntimeException("Rol no permitido");
            }

            Object[] parametrosSQL_2 = {username, pwd};
            rs_2 = db.queryConsultar(querySQL_2, parametrosSQL_2);

            Usuario usuario = new Usuario();
            Candidato candidato;
            Reclutador reclutador;

            while (rs_2.next()) {

                usuario.setIdUsuario(rs_2.getInt("id"));
                usuario.setIdPersona(rs_2.getInt("id_persona"));
                usuario.setNombres(rs_2.getString("nombres"));
                usuario.setApellidos(rs_2.getString("apellidos"));
                usuario.setRol(rs_2.getString("rol"));
                usuario.setUsername(rs_2.getString("username"));
                usuario.setPassword(rs_2.getString("pwd"));
                usuario.setEstado(rs_2.getString("estado"));
                usuario.setFechaCreado(rs_2.getString("fecha_creado"));

                switch (rol) {
                    case "candidato" -> {
                        usuario.setIdCandidato(rs_2.getInt("id_candidato"));
                        candidato = new Candidato();

                        /* OBTENER DATOS DEL CANDIDATO */
                        querySQL_3 = "SELECT * FROM candidatos c WHERE c.id = ? LIMIT 1;";
                        Object[] parametrosSQL_3 = {usuario.getIdCandidato()};
                        rs_3 = db.queryConsultar(querySQL_3, parametrosSQL_3);

                        while (rs_3.next()) {
                            candidato.setIdCandidato(rs_3.getInt("id"));
                            candidato.setIdPersona(rs_3.getInt("id_persona"));
                            candidato.setIdUsuario(rs_3.getInt("id_usuario"));
                            candidato.setAptitudes(rs_3.getString("aptitudes"));
                            candidato.setImagenPerfil(rs_3.getString("imagen_perfil"));
                            candidato.setPathCV(rs_3.getString("path_curriculum_vitae"));
                            candidato.setPathCertificadoTrabajo(rs_3.getString("path_certificado_trabajo"));
                            candidato.setPathAntecedentePolicial(rs_3.getString("path_antecendente_policial"));
                            candidato.setEstado(rs_3.getString("estado"));
                            candidato.setFechaCreado(rs_3.getString("fecha_creado"));
                            candidato.setFechaActualizado(rs_3.getString("fecha_actualizado"));
                        }

                        /* OBTENER DATOS DE LA PERSONA */
                        querySQL_4 = "SELECT * FROM personas p WHERE p.id = ? LIMIT 1;";
                        Object[] parametrosSQL_4 = {usuario.getIdPersona()};
                        rs_4 = db.queryConsultar(querySQL_4, parametrosSQL_4);

                        while (rs_4.next()) {
                            candidato.setIdPersona(rs_4.getInt("id"));
                            candidato.setNombre(rs_4.getString("nombre"));
                            candidato.setApellidos(rs_4.getString("apellido"));
                            candidato.setTipoDocumento(rs_4.getInt("tipo_documento"));
                            candidato.setNroDocumento(rs_4.getString("nrodocumento"));
                            candidato.setSexo(rs_4.getString("sexo"));
                            candidato.setEdad(rs_4.getString("edad"));
                            candidato.setTelefono(rs_4.getString("telefono"));
                            candidato.setFechaNacimiento(rs_4.getString("fecha_nacimiento"));
                            candidato.setEstadoCivil(rs_4.getString("estado_civil"));
                            candidato.setEstado(rs_4.getString("estado"));
                            candidato.setFechaCreado(rs_4.getString("fecha_creado"));
                            candidato.setFechaActualizado(rs_4.getString("fecha_actualizado"));
                        }

                        usuario.setCandidato(candidato);
                        success = true;
                    }
                    case "reclutador" -> {
                        usuario.setIdReclutador(rs_2.getInt("id_reclutador"));
                        reclutador = new Reclutador();

                        /* OBTENER DATOS DEL RECLUTADOR */
                        querySQL_3 = "SELECT * FROM reclutadores r WHERE r.id = ? LIMIT 1;";
                        Object[] parametrosSQL_3 = {usuario.getIdReclutador()};
                        rs_3 = db.queryConsultar(querySQL_3, parametrosSQL_3);

                        while (rs_3.next()) {
                            reclutador.setIdReclutador(rs_3.getInt("id"));
                            reclutador.setIdPersona(rs_3.getInt("id_persona"));
                            reclutador.setIdUsuario(rs_3.getInt("id_usuario"));
                            reclutador.setEstado(rs_3.getString("estado"));
                            reclutador.setFechaCreado(rs_3.getString("fecha_creado"));
                            reclutador.setFechaActualizado(rs_3.getString("fecha_actualizado"));
                        }

                        /* OBTENER DATOS DE LA PERSONA */
                        querySQL_4 = "SELECT * FROM personas p WHERE p.id = ? LIMIT 1;";
                        Object[] parametrosSQL_4 = {usuario.getIdPersona()};
                        rs_4 = db.queryConsultar(querySQL_4, parametrosSQL_4);

                        while (rs_4.next()) {
                            reclutador.setIdPersona(rs_4.getInt("id"));
                            reclutador.setNombre(rs_4.getString("nombre"));
                            reclutador.setApellidos(rs_4.getString("apellido"));
                            reclutador.setTipoDocumento(rs_4.getInt("tipo_documento"));
                            reclutador.setNroDocumento(rs_4.getString("nrodocumento"));
                            reclutador.setSexo(rs_4.getString("sexo"));
                            reclutador.setEdad(rs_4.getString("edad"));
                            reclutador.setTelefono(rs_4.getString("telefono"));
                            reclutador.setFechaNacimiento(rs_4.getString("fecha_nacimiento"));
                            reclutador.setEstadoCivil(rs_4.getString("estado_civil"));
                            reclutador.setEstado(rs_4.getString("estado"));
                            reclutador.setFechaCreado(rs_4.getString("fecha_creado"));
                            reclutador.setFechaActualizado(rs_4.getString("fecha_actualizado"));
                        }

                        usuario.setReclutador(reclutador);

                        success = true;
                    }
                    default ->
                        throw new RuntimeException("Rol no permitido");
                }
            }

            // Actualizar nuevo usuario en sesión local
            UsuarioThreadLocal.set(usuario);

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            db.cerrarConsulta();
        }

        return success;
    }

    public boolean logout() {
        boolean success = false;

        try {
            var usuario = UsuarioThreadLocal.get();

            // Eliminar usuario en sesión local
            if (usuario != null) {
                UsuarioThreadLocal.remove();
                success = true;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return success;
    }
}
