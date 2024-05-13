package services;

import models.Postulacion;

public class PostulacionService extends BaseService {

    public void crearPostulacion(Postulacion postulacion) {
        querySQL_1 = "INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (?,?,?,NOW())";
        Object[] parametrosSQL_1 = {postulacion.getIdCandidato(), postulacion.getIdEmpleo(), postulacion.getEstado()};
        db.queryInsertar(querySQL_1, parametrosSQL_1);

        db.cerrarConsulta();
    }

    public enum COLUMNAS_TABLA_POSTULACION {
        id_candidato,
        id_empleo,
        estado,
    }

    public void actualizarPostulacion(Postulacion postulacion, String columna) {

        if(columna == null){
            util.alertMessage("El campo columna es necesario");
        }
        
        querySQL_1 = String.format("UPDATE postulaciones SET %s = ? WHERE id = ?", columna);
        Object[] parametrosSQL_1 = {postulacion.getEstado(), postulacion.getIdPostulacion()};
        db.queryActualizar(querySQL_1, parametrosSQL_1);

        db.cerrarConsulta();
    }
}
