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
