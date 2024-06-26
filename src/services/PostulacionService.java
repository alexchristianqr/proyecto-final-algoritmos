package services;

import core.services.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Candidato;
import models.Postulacion;

public class PostulacionService extends BaseService {

    public PostulacionService() {
        db = new MysqlDBService();
    }

    public boolean crearPostulacion(Postulacion postulacion) {
        boolean success = false;

        try {
            if (postulacion.getEstado().isEmpty() || postulacion.getEstado().isBlank()) {
                util.alertMessage("[estado] es requerido");
            }
            
            querySQL_1 = "INSERT INTO postulaciones (id_candidato, id_empleo, estado) VALUES (?,?,?)";
            Object[] parametrosSQL_1 = {postulacion.getIdCandidato(), postulacion.getIdEmpleo(), postulacion.getEstado()};
            int creado = db.queryInsertar(querySQL_1, parametrosSQL_1);

            if (creado > 0) {
                db.cerrarConsulta();
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean actualizarPostulacion(Postulacion postulacion, String columna) {
        boolean success = false;

        try {
            if (columna == null) {
                util.alertMessage("El campo columna es necesario");
            }

            querySQL_1 = String.format("UPDATE postulaciones p JOIN empleos e ON e.id = p.id_empleo AND e.estado = 'activo' SET p.%s = ? WHERE p.id = ?; ", columna);
            Object[] parametrosSQL_1 = {postulacion.getEstado(), postulacion.getIdPostulacion()};
            int actualizado = db.queryActualizar(querySQL_1, parametrosSQL_1);

            if (actualizado > 0) {
                db.cerrarConsulta();
                success = true;
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public DefaultTableModel tablaPostulaciones(DefaultTableModel modelo, Object[] data, String estado) {
        querySQL_1 = "SELECT e.id, e.titulo, e.empresa, e.sueldo, e.modalidad, po.estado, CONCAT(pe.nombre, ' ', pe.apellido) AS 'candidato', po.fecha_creado FROM empleos e JOIN postulaciones po ON po.id_empleo = e.id JOIN candidatos c ON c.id = po.id_candidato JOIN personas pe ON pe.id = c.id_persona WHERE po.id_candidato = ?;";
        Object[] parametrosSQL_1 = {auth.getIdCandidato()};
        ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            while (rs.next()) {
                data[0] = rs.getInt("id");
                data[1] = rs.getString("titulo");
                data[2] = rs.getString("empresa");
                data[3] = rs.getString("sueldo");
                data[4] = rs.getString("modalidad");
                data[5] = rs.getString("estado");
                data[6] = rs.getString("candidato");
                data[7] = rs.getString("fecha_creado");
                modelo.addRow(data);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        db.cerrarConsulta();

        return modelo;
    }

    public List listarPostulaciones(String[] columnNames, String estado) {

        List<Object[]> lista = new ArrayList<>();

        try {
            Object[] parametrosSQL_1 = new Object[2];

            querySQL_1 = "SELECT e.id, e.titulo, e.empresa, e.sueldo, e.modalidad, po.estado, CONCAT(pe.nombre, ' ', pe.apellido) AS 'candidato', po.fecha_creado FROM empleos e JOIN postulaciones po ON po.id_empleo = e.id JOIN candidatos c ON c.id = po.id_candidato JOIN personas pe ON pe.id = c.id_persona WHERE po.id_candidato = ? ";
            parametrosSQL_1[0] = auth.getIdCandidato();

            if (estado != null) {
                querySQL_1 += " AND po.estado = ? ";
                parametrosSQL_1[1] = estado;
            }

            ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

            System.out.println("\n");

            while (rs.next()) {
                // Nuevo
                Object[] data = new Object[columnNames.length];

                // Llenar el arreglo de datos con los valores del ResultSet
                data[0] = rs.getInt("id");
                data[1] = rs.getString("titulo");
                data[2] = rs.getString("empresa");
                data[3] = rs.getString("sueldo");
                data[4] = rs.getString("modalidad");
                data[5] = rs.getString("estado");
                data[6] = rs.getString("candidato");
                data[7] = rs.getString("fecha_creado");

                // Agregar el arreglo de datos a la lista de contenido de datos
                lista.add(data);
            }

            // Imprimir la tabla utilizando la lista de contenido de datos
            util.imprimirTabla(columnNames, lista.toArray(Object[][]::new));

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        db.cerrarConsulta();

        return lista;
    }
}
