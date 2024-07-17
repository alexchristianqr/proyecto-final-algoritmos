package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.Empleo;
import models.FiltroEmpleosCandidato;
import models.FiltroEmpleosReclutador;

public class EmpleoService extends BaseService {

    public EmpleoService() {
        db = new MysqlDBService();
    }

    public boolean registrarEmpleo(Empleo empleo) {
        boolean response = false;
        try {
            querySQL_1 = "INSERT INTO empleos (id_reclutador, titulo, empresa, sueldo, modalidad, descripcion, edad_min, edad_max, estado) VALUES (?,?,?,?,?,?,?,?,?);";
            Object[] parametrosSQL_1 = {empleo.getIdReclutador(), empleo.getTitulo(), empleo.getEmpresa(), empleo.getSueldo(), empleo.getModalidad(), empleo.getDescripcion(), empleo.getEdadMin(), empleo.getEdadMax(), empleo.getEstado()};
            db.queryInsertar(querySQL_1, parametrosSQL_1);

            response = true;

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            db.cerrarConsulta();
        }

        return response;
    }

    public List listarEmpleos(String[] columnNames, Empleo empleo, FiltroEmpleosReclutador filtroEmpleosReclutador) {
        List<Object[]> lista = new ArrayList<>();

        try {
            List<Object> parametrosList = new ArrayList<>();

            querySQL_1 = "SELECT e.titulo, e.empresa, e.sueldo, e.modalidad, e.descripcion, e.estado, c.id AS 'id_candidato', pc.nombre AS 'candidato', e.edad_min, e.edad_max, e.fecha_creado, e.fecha_actualizado FROM empleos e JOIN reclutadores r ON r.id = e.id_reclutador JOIN personas pe ON pe.id = r.id_persona LEFT JOIN postulaciones po ON po.id_empleo = e.id AND po.estado NOT IN ('cancelado','rechazado','bloqueado') JOIN candidatos c ON c.id = po.id_candidato JOIN personas pc ON pc.id = c.id_persona WHERE e.id_reclutador = ? ";
            parametrosList.add(empleo.getIdReclutador());
            
            if (filtroEmpleosReclutador.getBuscar() != null) {
                querySQL_1 += " AND (e.titulo LIKE ? OR e.descripcion LIKE ? OR e.empresa LIKE ?) ";
                String buscar = "%" + filtroEmpleosReclutador.getBuscar() + "%";
                parametrosList.add(buscar);
                parametrosList.add(buscar);
                parametrosList.add(buscar);
            }
            if (filtroEmpleosReclutador.getModalidad() != null) {
                querySQL_1 += " AND e.modalidad = ? ";
                parametrosList.add(filtroEmpleosReclutador.getModalidad());
            }
            if (filtroEmpleosReclutador.getEstado() != null) {
                querySQL_1 += " AND e.estado = ? ";
                parametrosList.add(filtroEmpleosReclutador.getEstado());
            }

            querySQL_1 += " ORDER BY e.id DESC; ";

            // Convertimos la lista a un array
            Object[] parametrosSQL = parametrosList.toArray(Object[]::new);
            ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL);
            
            System.out.println("\n");

            while (rs.next()) {
                Object[] data = new Object[columnNames.length];

                data[0] = rs.getString("titulo");
                data[1] = rs.getString("empresa");
                data[2] = rs.getString("sueldo");
                data[3] = rs.getString("modalidad");
                data[4] = rs.getString("descripcion");
                data[5] = rs.getString("estado");
                data[6] = rs.getInt("id_candidato");
                data[7] = rs.getString("candidato");
                data[8] = rs.getInt("edad_min");
                data[9] = rs.getInt("edad_max");
                data[10] = rs.getString("fecha_creado");
                data[11] = rs.getString("fecha_actualizado");

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

    public List listarEmpleosCandidatos(String[] columnNames, FiltroEmpleosCandidato filtroEmpleosCandidato) {
        List<Object[]> lista = new ArrayList<>();

        try {
            List<Object> parametrosList = new ArrayList<>();

            querySQL_1 = "SELECT e.id, e.titulo, e.empresa, e.sueldo, e.modalidad, e.descripcion, e.fecha_creado FROM empleos e JOIN reclutadores r ON r.id = e.id_reclutador AND e.estado = 'disponible' JOIN personas pe ON pe.id = r.id_persona WHERE 1 = 1 ";

            if (filtroEmpleosCandidato.getBuscar() != null) {
                querySQL_1 += " AND (e.titulo LIKE ? OR e.descripcion LIKE ? OR e.empresa LIKE ?) ";
                String buscar = "%" + filtroEmpleosCandidato.getBuscar() + "%";
                parametrosList.add(buscar);
                parametrosList.add(buscar);
                parametrosList.add(buscar);
            }
            if (filtroEmpleosCandidato.getModalidad() != null) {
                querySQL_1 += " AND e.modalidad = ? ";
                parametrosList.add(filtroEmpleosCandidato.getModalidad());
            }

            querySQL_1 += " ORDER BY e.id DESC; ";

            // Convertimos la lista a un array
            Object[] parametrosSQL = parametrosList.toArray(Object[]::new);
            ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL);

            System.out.println("\n");

            while (rs.next()) {
                Object[] data = new Object[columnNames.length];

                data[0] = rs.getString("id");
                data[1] = rs.getString("titulo");
                data[2] = rs.getString("empresa");
                data[3] = rs.getString("sueldo");
                data[4] = rs.getString("modalidad");
                data[5] = rs.getString("descripcion");
                data[6] = rs.getString("fecha_creado");

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
