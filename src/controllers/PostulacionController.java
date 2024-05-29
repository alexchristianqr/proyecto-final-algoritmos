package controllers;

import javax.swing.table.DefaultTableModel;
import models.Postulacion;
import services.PostulacionService;

public class PostulacionController extends BaseController<Postulacion, PostulacionService> {

    public PostulacionController() {
        lista.clear();
        service = new PostulacionService();
    }

    public void crearPostulacion(Postulacion postulacion) {
        service.crearPostulacion(postulacion);
    }

    public void postularEmpleo(Postulacion postulacion) {
        service.postular(postulacion);
    }

    public DefaultTableModel tablaPostulaciones(String estado) {
        DefaultTableModel modelo;
        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Candidato", "Fecha creado"};
        Object[] data = new Object[columnNames.length];
        modelo = new DefaultTableModel(null, columnNames);
        modelo = service.tablaPostulaciones(modelo, data, estado);
        return modelo;
    }

    public void listarPostulaciones(String estado) {
        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Candidato", "Fecha creado"};
        service.listarPostulaciones(columnNames, estado);
    }
}
