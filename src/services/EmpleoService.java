package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Empleo;

public class EmpleoService extends BaseService {

    public EmpleoService() {
        db = new MysqlDBService();
    }

    public boolean registrarEmpleo(Empleo empleo) {
        boolean response = false;
        try {
            querySQL_1 = "INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, estado) VALUES (?,?,?,?,?,?,?);";
            Object[] parametrosSQL_1 = {empleo.getIdReclutador(), empleo.getTitulo(), empleo.getEmpresa(), empleo.getSueldo(), empleo.getModalidad(), empleo.getDescripcion(), empleo.getEstado()};
            db.queryInsertar(querySQL_1, parametrosSQL_1);

            db.cerrarConsulta();

            response = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return response;
    }

    public List listarEmpleos(String[] columnNames, Empleo empleo) {

        List<Object[]> lista = new ArrayList<>();

        try {
            Object[] parametrosSQL_1 = new Object[1];

            querySQL_1 = "SELECT e.titulo, e.empresa, e.sueldo, e.modalidad, e.descripcion, e.estado, COUNT(po.id) AS 'total_candidatos_postulados', e.fecha_creado, e.fecha_actualizado FROM empleos e JOIN reclutadores r ON r.id = e.id_reclutador JOIN personas pe ON pe.id = r.id_persona LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado') WHERE e.id_reclutador = ? GROUP BY e.id;";
            parametrosSQL_1[0] = empleo.getIdReclutador();

            ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

            System.out.println("\n");

            while (rs.next()) {
                Object[] data = new Object[columnNames.length];

                data[0] = rs.getString("titulo");
                data[1] = rs.getString("empresa");
                data[2] = rs.getString("sueldo");
                data[3] = rs.getString("modalidad");
                data[4] = rs.getString("descripcion");
                data[5] = rs.getString("estado");
                data[6] = rs.getInt("total_candidatos_postulados");
                data[7] = rs.getString("fecha_creado");
                data[8] = rs.getString("fecha_actualizado");

                lista.add(data);
            }

            util.imprimirTabla(columnNames, lista.toArray(Object[][]::new));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        } finally {
            db.cerrarConsulta();
        }

        return lista;
    }
}
