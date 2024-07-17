package services;

import core.services.MysqlDBService;
import java.util.ArrayList;
import java.util.List;
import models.Reclutador;

public class ReclutadorService extends BaseService {

    public ReclutadorService() {
        db = new MysqlDBService();
    }

    public boolean registrarReclutador(Reclutador reclutador) {
        boolean success = false;

        try {
            querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, edad, telefono, fecha_nacimiento, estado_civil) VALUES (?,?,?,?,?,?,?,?,?)";
            Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(), reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEdad(), reclutador.getTelefono(), reclutador.getFechaNacimiento(), reclutador.getEstadoCivil()};
            int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

            querySQL_2 = "INSERT INTO reclutadores (id_persona, id_usuario) VALUES (?,?)";
            Object[] parametrosSQL_2 = {id_persona, reclutador.getIdUsuario()};
            int creado = db.queryInsertar(querySQL_2, parametrosSQL_2);

            if (creado > 0) {
                db.cerrarConsulta();
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    public boolean actualizarReclutador(Reclutador reclutador) {
        boolean success = false;

        try {
            StringBuilder querySQL_1 = new StringBuilder("UPDATE personas SET ");
            List<Object> parametrosPersonaList = new ArrayList<>();

            // Añade solo los campos que tienen valores no nulos
            if (reclutador.getNombre() != null) {
                querySQL_1.append("nombre = ?, ");
                parametrosPersonaList.add(reclutador.getNombre());
            }
            if (reclutador.getApellidos() != null) {
                querySQL_1.append("apellido = ?, ");
                parametrosPersonaList.add(reclutador.getApellidos());
            }
            if (reclutador.getTipoDocumento() > 0) {
                querySQL_1.append("tipo_documento = ?, ");
                parametrosPersonaList.add(reclutador.getTipoDocumento());
            }
            if (reclutador.getNroDocumento() != null) {
                querySQL_1.append("nrodocumento = ?, ");
                parametrosPersonaList.add(reclutador.getNroDocumento());
            }
            if (reclutador.getSexo() != null) {
                querySQL_1.append("sexo = ?, ");
                parametrosPersonaList.add(reclutador.getSexo());
            }
            if (reclutador.getEstadoCivil() != null) {
                querySQL_1.append("estado_civil = ?, ");
                parametrosPersonaList.add(reclutador.getEstadoCivil());
            }
            if (reclutador.getEstado() != null) {
                querySQL_1.append("estado = ?, ");
                parametrosPersonaList.add(reclutador.getEstado());
            }
            if (reclutador.getFechaNacimiento() != null) {
                querySQL_1.append("fecha_nacimiento = ?, ");
                parametrosPersonaList.add(reclutador.getFechaNacimiento());
            }
            if (reclutador.getTelefono() != null) {
                querySQL_1.append("telefono = ? ");
                parametrosPersonaList.add(reclutador.getTelefono());
            }

            // Elimina la última coma y espacio, y añade la cláusula WHERE
//            querySQL_1.setLength(querySQL_1.length() - 2);
            querySQL_1.append(" WHERE id = ?;");
            parametrosPersonaList.add(reclutador.getIdPersona());

            Object[] parametrosPersonaSQL = parametrosPersonaList.toArray();
            int actualizadoPersona = db.queryActualizar(querySQL_1.toString(), parametrosPersonaSQL);

            if (actualizadoPersona > 0) {
                success = true;
            }

            int actualizadoReclutador = 0;
            if (reclutador.getEstado() != null) {
                querySQL_2 = "UPDATE reclutadores SET estado = ? WHERE id = ?;";
                Object[] parametrosReclutadorSQL = {reclutador.getEstado(), reclutador.getIdReclutador()};
                actualizadoReclutador = db.queryActualizar(querySQL_2, parametrosReclutadorSQL);
            }

            if (actualizadoReclutador > 0) {
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
