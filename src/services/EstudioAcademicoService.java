package services;

import core.services.MysqlDBService;
import models.EstudioAcademico;

public class EstudioAcademicoService extends BaseService {

    public EstudioAcademicoService() {
        db = new MysqlDBService();
    }

    public boolean registrarEstudioAcademico(EstudioAcademico estudioAcademico) {
        boolean success = false;

        try {
            querySQL_1 = "INSERT INTO estudios_academicos (titulo, descripcion, fecha_inicio, fecha_fin, grado) VALUES (?,?,?,?,?);";
            Object[] parametrosSQL_1 = {estudioAcademico.getTitulo(), estudioAcademico.getDescripcion(), estudioAcademico.getFechaInicio(), estudioAcademico.getFechaFin(), estudioAcademico.getGrado()};
            int id_estudio_academico = db.queryInsertar(querySQL_1, parametrosSQL_1);

            querySQL_2 = "INSERT INTO candidatos_estudios_academicos (id_candidato, id_estudio_academico, orden) VALUES (?,?,?);";
            Object[] parametrosSQL_2 = {estudioAcademico.getIdCandidato(), id_estudio_academico, estudioAcademico.getOrden()};
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
