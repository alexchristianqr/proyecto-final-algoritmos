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

    public boolean actualizarCandidato(Candidato candidato, String columna) {
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
    public void cargarPDF(int candidatoId, String filePath) {
        String query = "UPDATE candidatos SET path_curriculum_vitae = ? WHERE id= ?";
        Object[] parametros = {filePath, candidatoId};

        try {
            int filasAfectadas = db.queryActualizar(query, parametros);
            if (filasAfectadas == 0) {
                throw new RuntimeException("No se encontró el candidato con el ID especificado.");
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Error al actualizar el path del PDF: " + e.getMessage(), e);
        } finally {
            db.cerrarConsulta();
        }
    
    
}
}
