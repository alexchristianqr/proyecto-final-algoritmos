package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Empleo;
import models.Postulacion;

public class EmpleoService extends BaseService {

    public EmpleoService() {
        db = new MysqlDBService();
    }

    public boolean crearEmpleo(Empleo empleo) {
        boolean response = false;
        try {
            querySQL_1 = "INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, estado, fecha_creado) VALUES (?,?,?,?,?,?,?,NOW())";
            Object[] parametrosSQL_1 = {empleo.getIdReclutador(), empleo.getTitulo(), empleo.getEmpresa(), empleo.getSueldo(), empleo.getModalidad(), empleo.getDescripcion(), empleo.getEstado()};
            db.queryInsertar(querySQL_1, parametrosSQL_1);

            db.cerrarConsulta();

            response = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public List obtenerEmpleosPorReclutador(String[] columnNames) {

        List<Postulacion> lista = new ArrayList<>();
        Object[] parametrosSQL_1 = new Object[1];

        querySQL_1 = "SELECT e.id, CONCAT(pe.nombre,' ', pe.apellido) AS 'reclutador', e.titulo, e.empresa, e.sueldo, e.modalidad, COUNT(po.id) AS 'total_candidatos_postulados', e.fecha_creado FROM empleos e JOIN reclutadores r ON r.id = e.id_reclutador JOIN personas pe ON pe.id = r.id_persona LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado') WHERE e.id_reclutador = ? GROUP BY e.id ";
        parametrosSQL_1[0] = auth.getIdReclutador();

        ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            System.out.println("\n");
            List<Object[]> dataContent = new ArrayList<>();

            while (rs.next()) {

                // Nuevo
                Object[] data = new Object[columnNames.length];

                // Llenar el arreglo de datos con los valores del ResultSet
                data[0] = rs.getInt("id");
                data[1] = rs.getString("reclutador");
                data[2] = rs.getString("titulo");
                data[3] = rs.getString("empresa");
                data[4] = rs.getString("sueldo");
                data[5] = rs.getString("modalidad");
                data[6] = rs.getInt("total_candidatos_postulados");
                data[7] = rs.getString("fecha_creado");

                // Agregar el arreglo de datos a la lista de contenido de datos
                dataContent.add(data);
            }

            // Imprimir la tabla utilizando la lista de contenido de datos
            util.imprimirTabla(columnNames, dataContent.toArray(Object[][]::new));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        db.cerrarConsulta();

        return lista;
    }
}
