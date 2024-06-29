package services;

import core.services.MysqlDBService;
import java.util.ArrayList;
import java.util.List;
import models.EstudioAcademico;
import java.sql.ResultSet;
import java.sql.SQLException;

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

    public List listarEstudiosAcademicos(String[] columnNames, EstudioAcademico estudioAcademico) {

        List<Object[]> lista = new ArrayList<>();

        try {
            Object[] parametrosSQL_1 = new Object[1];

            querySQL_1 = "SELECT ea.* FROM estudios_academicos ea JOIN candidatos_estudios_academicos cea ON cea.id_estudio_academico = ea.id where cea.id_candidato = ?;";
            parametrosSQL_1[0] = estudioAcademico.getIdCandidato();

//            if (estado != null) {
//                querySQL_1 += " AND po.estado = ? ";
//                parametrosSQL_1[1] = estado;
//            }
            ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

            System.out.println("\n");

            while (rs.next()) {
                // Nuevo
                Object[] data = new Object[columnNames.length];

                // Llenar el arreglo de datos con los valores del ResultSet
                data[0] = rs.getString("titulo");
                data[1] = rs.getString("descripcion");
                data[2] = rs.getString("fecha_inicio");
                data[3] = rs.getString("fecha_fin");
                data[4] = rs.getString("grado");

                // Agregar el arreglo de datos a la lista de contenido de datos
                lista.add(data);
            }

            // Imprimir la tabla utilizando la lista de contenido de datos
            util.imprimirTabla(columnNames, lista.toArray(Object[][]::new));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            db.cerrarConsulta();
        }

        return lista;
    }

}
