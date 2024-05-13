package services;

import models.Empleo;

public class EmpleoService extends BaseService {

    public void crearEmpleo(Empleo empleo) {
        querySQL_1 = "INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, estado, fecha_creado) VALUES (?,?,?,?,?,?,?,NOW())";
        Object[] parametrosSQL_1 = {empleo.getIdReclutador(), empleo.getTitulo(), empleo.getEmpresa(), empleo.getSueldo(), empleo.getModalidad(), empleo.getDescripcion(), empleo.getEstado()};
        db.queryInsertar(querySQL_1, parametrosSQL_1);

        db.cerrarConsulta();
    }
}
