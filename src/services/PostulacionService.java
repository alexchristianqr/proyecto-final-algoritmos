package services;

import core.db.MysqlDBService;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Postulacion;

public class PostulacionService extends BaseService {

    public PostulacionService() {
        db = new MysqlDBService();
    }

    public void crearPostulacion(Postulacion postulacion) {
        querySQL_1 = "INSERT INTO postulaciones (id_candidato, id_empleo, estado, fecha_creado) VALUES (?,?,?,NOW())";
        Object[] parametrosSQL_1 = {postulacion.getIdCandidato(), postulacion.getIdEmpleo(), postulacion.getEstado()};
        db.queryInsertar(querySQL_1, parametrosSQL_1);
        db.cerrarConsulta();
    }

    public void actualizarPostulacion(Postulacion postulacion, String columna) {

        if (columna == null) {
            util.alertMessage("El campo columna es necesario");
        }

        querySQL_1 = String.format("UPDATE postulaciones SET %s = ? WHERE id = ?", columna);
        Object[] parametrosSQL_1 = {postulacion.getEstado(), postulacion.getIdPostulacion()};
        db.queryActualizar(querySQL_1, parametrosSQL_1);

        db.cerrarConsulta();
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

    public List listarPostulaciones(String[] columnNames, Object[] data, String estado) {

        List<Postulacion> lista = new ArrayList<>();
        Object[] parametrosSQL_1 = new Object[2];

        querySQL_1 = "SELECT e.id, e.titulo, e.empresa, e.sueldo, e.modalidad, po.estado, CONCAT(pe.nombre, ' ', pe.apellido) AS 'candidato', po.fecha_creado FROM empleos e JOIN postulaciones po ON po.id_empleo = e.id JOIN candidatos c ON c.id = po.id_candidato JOIN personas pe ON pe.id = c.id_persona WHERE po.id_candidato = ? ";
        parametrosSQL_1[0] = auth.getIdCandidato();

        if (estado != null) {
            querySQL_1 += " AND po.estado = ? ";
            parametrosSQL_1[1] = estado;
        }

        ResultSet rs = db.queryConsultar(querySQL_1, parametrosSQL_1);

        try {
            System.out.println();
            for (int i = 0; i < columnNames.length; i++) {
                System.out.print(columnNames[i].toUpperCase() + " / ");
            }

            while (rs.next()) {
                data[0] = rs.getInt("id");
                data[1] = rs.getString("titulo");
                data[2] = rs.getString("empresa");
                data[3] = rs.getString("sueldo");
                data[4] = rs.getString("modalidad");
                data[5] = rs.getString("estado");
                data[6] = rs.getString("candidato");
                data[7] = rs.getString("fecha_creado");

                System.out.println();
                for (int i = 0; i < data.length; i++) {
                    System.out.print(data[i] + " / ");
                }
            }

            System.out.print("\n");

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        db.cerrarConsulta();

        return lista;
    }
}
