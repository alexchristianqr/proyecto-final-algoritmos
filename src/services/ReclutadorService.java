package services;

import core.services.MysqlDBService;
import models.Reclutador;

public class ReclutadorService extends BaseService {

    public ReclutadorService() {
        db = new MysqlDBService();
    }

    public boolean registrarReclutador(Reclutador reclutador) {
        boolean success = false;

        try {
            querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, edad, telefono, fecha_nacimiento, estado_civil, fecha_creado) VALUES (?,?,?,?,?,?,?,?,?,?,NOW())";
            Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(), reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(), reclutador.getEdad(), reclutador.getTelefono(), reclutador.getFechaNacimiento(), reclutador.getEstadoCivil()};
            int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

            querySQL_2 = "INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) VALUES (?,?,?,NOW())";
            Object[] parametrosSQL_2 = {id_persona, reclutador.getIdUsuario(), reclutador.getEstado()};
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

}
