package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.ExperienciaLaboral;
import static services.BaseService.util;

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
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            db.cerrarConsulta();
        }

        return success;
    }

    public List listarExperienciasLaborales(String[] columnNames, ExperienciaLaboral experienciaLaboral) {

        List<Object[]> lista = new ArrayList<>();

        try {
            Object[] parametrosSQL_1 = new Object[1];

            querySQL_1 = "SELECT el.* FROM experiencias_laborales el JOIN candidatos_experiencias_laborales cel ON cel.id_experiencia_laboral = el.id where cel.id_candidato = ?;";
            parametrosSQL_1[0] = experienciaLaboral.getIdCandidato();

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
                data[2] = rs.getString("empresa");
                data[3] = rs.getString("fecha_inicio");
                data[4] = rs.getString("fecha_fin");

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
