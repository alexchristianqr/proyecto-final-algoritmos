package services;

import core.services.MysqlDBService;
import models.Reclutador;

public class ReclutadorService extends BaseService {
    
    public ReclutadorService(){
        db = new MysqlDBService();
    }

    public void crearReclutador(Reclutador reclutador) {
        querySQL_1 = "INSERT INTO personas (nombre, apellido, tipo_documento, nrodocumento, sexo, estado, edad, telefono, fecha_creado) VALUES (?,?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(),
            reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(),
            reclutador.getEdad(), reclutador.getTelefono()};
        int id_persona = db.queryInsertar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "INSERT INTO reclutadores (id_persona, id_usuario, estado, fecha_creado) VALUES (?,?,?,NOW())";
        Object[] parametrosSQL_2 = {id_persona, reclutador.getIdUsuario(), reclutador.getEstado()};
        db.queryInsertar(querySQL_2, parametrosSQL_2);

        db.cerrarConsulta();
    }

    public void actualizarReclutador(Reclutador reclutador) {
        querySQL_1 = "UPDATE INTO personas SET nombre = ?, apellido = ?, tipo_documento = ?, nrodocumento = ?, sexo = ?, estado = ?, edad = ?, telefono = ? WHERE id = ?";
        Object[] parametrosSQL_1 = {reclutador.getNombre(), reclutador.getApellidos(),
            reclutador.getTipoDocumento(), reclutador.getNroDocumento(), reclutador.getSexo(), reclutador.getEstado(),
            reclutador.getEdad(), reclutador.getTelefono(), reclutador.getIdPersona()};
        db.queryActualizar(querySQL_1, parametrosSQL_1);

        querySQL_2 = "UPDATE INTO reclutadores SET estado = ? WHERE id_persona = ?";
        Object[] parametrosSQL_2 = {reclutador.getEstado(), reclutador.getIdPersona()};

        db.queryActualizar(querySQL_2, parametrosSQL_2);
        db.cerrarConsulta();
    }

    public void eliminarReclutador(int idPersona, int idUsuario) {
        querySQL_1 = "DELETE FROM reclutadores WHERE id_persona = ?";
        Object[] parametrosSQL_1 = {idPersona};

        db.queryEliminar(querySQL_1, parametrosSQL_1);

        //para eliminar de la tabla personas
        /*querySQL_2 ="DELETE FROM reclutadores WHERE id = ?";
        Object[] parametrosSQL_2 = {idPersona};
        
        db.queryELiminar(querySQL_2, parametrosSQL_2);
        
        //para eliminar de la tabla usuarios
        String querySQL_3 = "DELETE FROM usuarios WHERE id = ?";
        Object[] parametrosSQL_3 = {idUsuario};

        db.queryEliminar(querySQL_3, parametrosSQL_3);

        db.cerrarConsulta();*/
    }
}
