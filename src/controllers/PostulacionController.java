package controllers;

import core.services.ResponseService;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.Postulacion;
import services.PostulacionService;

public class PostulacionController extends BaseController<Postulacion, PostulacionService> {

    public PostulacionController() {
        lista.clear();
        service = new PostulacionService();
    }

    public ResponseService<Boolean> crearPostulacion(Postulacion postulacion) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = service.crearPostulacion(postulacion);

        response.setSuccess(success);
        response.setMessage("postulación actualizada");
        response.setResult(null);

        return response;
    }

    public ResponseService<Boolean> actualizarPostulacion(Postulacion postulacion) {
        return this.actualizarPostulacion(postulacion, "estado");
    }

    public ResponseService<Boolean> actualizarPostulacion(Postulacion postulacion, String columna) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = service.actualizarPostulacion(postulacion, columna);

        response.setSuccess(success);
        response.setMessage("postulación actualizada");
        response.setResult(null);

        return response;
    }

    public DefaultTableModel tablaPostulaciones(String estado) {
        DefaultTableModel modelo;
        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Candidato", "Fecha creado"};
        Object[] data = new Object[columnNames.length];
        modelo = new DefaultTableModel(null, columnNames);
        modelo = service.tablaPostulaciones(modelo, data, estado);
        return modelo;
    }

    public ResponseService<List<Object[]>> listarPostulaciones(String estado) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Candidato", "Fecha creado"};
        List<Object[]> resultado = service.listarPostulaciones(columnNames, estado);

        response.setSuccess(true);
        response.setMessage("listado de postulaciones");
        response.setResult(resultado);

        return response;
    }
}
