package services;

import core.services.MysqlDBService;
import models.ExperienciaLaboral;

public class ExperienciaLaboralService extends BaseService {

    public ExperienciaLaboralService() {
        db = new MysqlDBService();
    }

    public boolean registrarExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        boolean success = false;

        try {
            querySQL_1 = "INSERT INTO experiencias_laborales (titulo, descripcion, empresa, fecha_inicio, fecha_fin) VALUES (?,?,?,?,?);";
            Object[] parametrosSQL_1 = {experienciaLaboral.getTitulo(), experienciaLaboral.getDescripcion(), experienciaLaboral.getEmpresa(), experienciaLaboral.getFechaInicio(), experienciaLaboral.getFechaFin()};
            int id_experiencia_laboral = db.queryInsertar(querySQL_1, parametrosSQL_1);

            querySQL_2 = "INSERT INTO candidatos_experiencias_laborales (id_candidato, id_experiencia_laboral, orden) VALUES (?,?,?);";
            Object[] parametrosSQL_2 = {experienciaLaboral.getIdCandidato(), id_experiencia_laboral, experienciaLaboral.getOrden()};
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
