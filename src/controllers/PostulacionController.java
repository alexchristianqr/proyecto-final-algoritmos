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

    public void actualizarEstadoPostulacion(Postulacion postulacion) {
        service.actualizarPostulacion(postulacion, "estado");
    }

    public void listarPostulaciones(String estado) {
//        DefaultTableModel modelo;
        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Candidato","Fecha creado"};
        Object[] data = new Object[columnNames.length];
//        modelo = new DefaultTableModel(null, columnNames);
        service.listarPostulaciones(columnNames,data, estado);
//        System.out.println(modelo);
//        return modelo;
    }
}
