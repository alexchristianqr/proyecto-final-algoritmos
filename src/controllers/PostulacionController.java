package controllers;

import core.services.ResponseService;
import java.util.List;
import models.FeedbackInfo;
import models.FiltroPostulaciones;
import models.Postulacion;
import services.PostulacionService;

public class PostulacionController extends BaseController<Postulacion, PostulacionService> {

    public PostulacionController() {
        lista.clear();
        service = new PostulacionService();
    }

    public ResponseService<Boolean> registrarFeedbackPersonalizado(Postulacion postulacion, FeedbackInfo feedbackInfo) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = service.registrarFeedbackPersonalizado(postulacion, feedbackInfo);

        response.setSuccess(success);
        response.setMessage("feedback guardado correctamente");
        response.setResult(null);

        return response;
    }

    public ResponseService<String> registrarPostulacion(Postulacion postulacion) {
        ResponseService<String> response = new ResponseService<>();

        String result = service.registrarPostulacion(postulacion);

        response.setSuccess(true);
        response.setMessage("postulación creada correctamente");
        response.setResult(result);

        return response;
    }

    public ResponseService<Boolean> actualizarPostulacion(Postulacion postulacion) {
        return this.actualizarPostulacion(postulacion, "estado");
    }

    public ResponseService<Boolean> actualizarPostulacion(Postulacion postulacion, String columna) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = service.actualizarPostulacion(postulacion, columna);

        response.setSuccess(success);
        response.setMessage("postulación actualizada correctamente");
        response.setResult(null);

        return response;
    }

    public ResponseService<List<Object[]>> listarPostulaciones(Postulacion postulacion, FiltroPostulaciones filtroPostulaciones) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Descripcion", "Estado", "Feedback", "Fecha Creado"};
        List<Object[]> resultado = service.listarPostulaciones(columnNames, postulacion, filtroPostulaciones);

        response.setSuccess(true);
        response.setMessage("listado de postulaciones");
        response.setResult(resultado);

        return response;
    }
}
