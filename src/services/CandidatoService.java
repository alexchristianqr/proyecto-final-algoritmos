package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Candidato;

public class CandidatoService extends BaseService {

    public CandidatoService() {
        db = new MysqlDBService();
    }

    public boolean registrarCandidato(Candidato candidato) {
        boolean success = false;

        try {
            querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, fecha_nacimiento, telefono) VALUES (?,?,?,?,?,?,?,?)";
            Object[] parametrosSQL_1 = {candidato.getNombre(), candidato.getApellidos(), candidato.getTipoDocumento(), candidato.getNroDocumento(), candidato.getSexo(), candidato.getEstado(), candidato.getFechaNacimiento(), candidato.getTelefono()};
            int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

            querySQL_2 = "INSERT INTO candidatos (id_persona, id_usuario, aptitudes, imagen_perfil, path_curriculum_vitae, path_certificado_trabajo, path_antecendente_policial) VALUES (?,?,?,?,?,?,?)";
            Object[] parametrosSQL_2 = {id_persona, candidato.getIdUsuario(), candidato.getAptitudes(), candidato.getImagenPerfil(), candidato.getPathCV(), candidato.getPathCertificadoTrabajo(), candidato.getPathAntecedentePolicial()};
            int id_candidato = db.queryInsertar(querySQL_2, parametrosSQL_2);

            if (id_candidato > 0) {
                success = true;
            } else {
                throw new RuntimeException("No se pudo insertar en la base de datos.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al registrar el usuario: " + e.getMessage(), e);
        } finally {
            db.cerrarConsulta();
        }

        return success;
    }

    public DefaultTableModel tablaCandidatos(DefaultTableModel modelo, Object[] data) {
        querySQL_1 = "SELECT p.id_persona, p.nombre, p.apellido, p.tipo_documento, p.nrodocumento, p.sexo, p.estado, p.fecha_nacimiento, p.telefono, p.estadoCivil, c.id_usuario, c.aptitudes, c.imagen_perfil, c.path_curriculum_vitae, c.path_certificado_trabajo, c.path_antecendente_policial, c.fecha_creado FROM personas p JOIN candidatos c ON p.id_persona = c.id_persona;";
        Object[] parametrosSQL_1 = {};
        ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            while (rs.next()) {
                data[0] = rs.getInt("id_persona");
                data[1] = rs.getString("nombre");
                data[2] = rs.getString("apellido");
                data[3] = rs.getInt("tipo_documento");
                data[4] = rs.getString("nrodocumento");
                data[5] = rs.getString("sexo");
                data[6] = rs.getString("estado");
                data[7] = rs.getString("fecha_nacimiento");
                data[8] = rs.getString("telefono");
                data[9] = rs.getInt("id_usuario");
                data[10] = rs.getString("aptitudes");
                data[11] = rs.getString("imagen_perfil");
                data[12] = rs.getString("path_curriculum_vitae");
                data[13] = rs.getString("path_certificado_trabajo");
                data[14] = rs.getString("path_antecendente_policial");
                data[15] = rs.getDate("fecha_creado");
                data[16] = rs.getString("estadoCivil");
                modelo.addRow(data);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al obtener los candidatos", ex);
        }

        db.cerrarConsulta();

        return modelo;
    }

    public List<Candidato> listarCandidatos() {
        List<Candidato> candidatos = new ArrayList<>();
        querySQL_1 = "SELECT p.id_persona, p.nombre, p.apellido, p.tipo_documento, p.nrodocumento, p.sexo, p.estado, p.fecha_nacimiento, p.telefono, p.estadoCivil, c.id_usuario, c.aptitudes, c.imagen_perfil, c.path_curriculum_vitae, c.path_certificado_trabajo, c.path_antecendente_policial, c.fecha_creado FROM personas p JOIN candidatos c ON p.id_persona = c.id_persona;";
        Object[] parametrosSQL_1 = {};
        ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            while (rs.next()) {
                Candidato candidato = new Candidato();
                candidato.setIdPersona(rs.getInt("id_persona"));
                candidato.setNombre(rs.getString("nombre"));
                candidato.setApellidos(rs.getString("apellido"));
                candidato.setTipoDocumento(rs.getInt("tipo_documento"));
                candidato.setNroDocumento(rs.getString("nrodocumento"));
                candidato.setSexo(rs.getString("sexo"));
                candidato.setEstado(rs.getString("estado"));
                candidato.setFechaNacimiento(rs.getString("fecha_nacimiento"));
                candidato.setTelefono(rs.getString("telefono"));
                candidato.setIdUsuario(rs.getInt("id_usuario"));
                candidato.setAptitudes(rs.getString("aptitudes"));
                candidato.setImagenPerfil(rs.getString("imagen_perfil"));
                candidato.setPathCV(rs.getString("path_curriculum_vitae"));
                candidato.setPathCertificadoTrabajo(rs.getString("path_certificado_trabajo"));
                candidato.setPathAntecedentePolicial(rs.getString("path_antecendente_policial"));
                candidato.setFechaCreado(rs.getString("fecha_creado"));
                candidato.setEstadoCivil(rs.getString("estadoCivil"));
                candidatos.add(candidato);
            }
        } catch (SQLException ex) {
            throw new RuntimeException("Error al listar candidatos", ex);
        }

        db.cerrarConsulta();

        return candidatos;
    }

    public boolean actualizarCandidatoPorColumna(Candidato candidato, String columna) {
        boolean success = false;

        try {
            if (columna == null) {
                util.alertMessage("El campo columna es necesario");
            }

            querySQL_1 = String.format("UPDATE candidatos c SET c.%s = ? WHERE c.id = ? AND c.estado IN ('activo');", columna);
            Object[] parametrosSQL_1 = {candidato.getPathCV(), candidato.getIdCandidato()};

            int actualizado = db.queryActualizar(querySQL_1, parametrosSQL_1);

            if (actualizado > 0) {
                db.cerrarConsulta();
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean actualizarCandidato(Candidato candidato) {
        boolean success = false;

        try {

            StringBuilder querySQL_1 = new StringBuilder("UPDATE personas SET ");
            List<Object> parametrosSQL_1 = new ArrayList<>();

            // Añade solo los campos que tienen valores no nulos
            if (candidato.getNombre() != null) {
                querySQL_1.append("nombre = ?, ");
                parametrosSQL_1.add(candidato.getNombre());
            }
            if (candidato.getApellidos() != null) {
                querySQL_1.append("apellido = ?, ");
                parametrosSQL_1.add(candidato.getApellidos());
            }
            if (candidato.getTipoDocumento() > 0) {
                querySQL_1.append("tipo_documento = ?, ");
                parametrosSQL_1.add(candidato.getTipoDocumento());
            }
            if (candidato.getNroDocumento() != null) {
                querySQL_1.append("nrodocumento = ?, ");
                parametrosSQL_1.add(candidato.getNroDocumento());
            }
            if (candidato.getSexo() != null) {
                querySQL_1.append("sexo = ?, ");
                parametrosSQL_1.add(candidato.getSexo());
            }
            if (candidato.getEstadoCivil() != null) {
                querySQL_1.append("estado_civil = ?, ");
                parametrosSQL_1.add(candidato.getEstadoCivil());
            }
            if (candidato.getEstado() != null) {
                querySQL_1.append("estado = ?, ");
                parametrosSQL_1.add(candidato.getEstado());
            }
            if (candidato.getFechaNacimiento() != null) {
                querySQL_1.append("fecha_nacimiento = ?, ");
                parametrosSQL_1.add(candidato.getFechaNacimiento());
            }
            if (candidato.getTelefono() != null) {
                querySQL_1.append("telefono = ? ");
                parametrosSQL_1.add(candidato.getTelefono());
            }

            // Elimina la última coma y espacio, y añade la cláusula WHERE
            querySQL_1.setLength(querySQL_1.length() - 2);
            querySQL_1.append(" WHERE id = ?;");
            parametrosSQL_1.add(candidato.getIdPersona());

            Object[] parametrosArray = parametrosSQL_1.toArray();
            int actualizadoPersona = db.queryActualizar(querySQL_1.toString(), parametrosArray);

            if (actualizadoPersona > 0) {
                success = true;
            }

            StringBuilder querySQL_2 = new StringBuilder("UPDATE candidatos SET ");
            List<Object> parametrosSQL_2 = new ArrayList<>();

            if (candidato.getAptitudes() != null) {
                querySQL_2.append("aptitudes = ?, ");
                parametrosSQL_2.add(candidato.getAptitudes());
            }
            if (candidato.getImagenPerfil() != null) {
                querySQL_2.append("imagen_perfil = ?, ");
                parametrosSQL_2.add(candidato.getImagenPerfil());
            }
            if (candidato.getPathCV() != null) {
                querySQL_2.append("path_curriculum_vitae = ?, ");
                parametrosSQL_2.add(candidato.getPathCV());
            }
            if (candidato.getPathCertificadoTrabajo() != null) {
                querySQL_2.append("path_certificado_trabajo = ?, ");
                parametrosSQL_2.add(candidato.getPathCertificadoTrabajo());
            }
            if (candidato.getPathAntecedentePolicial() != null) {
                querySQL_2.append("path_antecendente_policial = ? ");
                parametrosSQL_2.add(candidato.getPathAntecedentePolicial());
            }

            querySQL_2.setLength(querySQL_2.length() - 2);
            querySQL_2.append(" WHERE id = ?;");
            parametrosSQL_2.add(candidato.getIdCandidato());

            Object[] parametrosArray2 = parametrosSQL_2.toArray(Object[]::new);
            int actualizadoCandaito = db.queryActualizar(querySQL_2.toString(), parametrosArray2);

            if (actualizadoCandaito > 0) {
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            db.cerrarConsulta();
        }

        return success;
    }
}
