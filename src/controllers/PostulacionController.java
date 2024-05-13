package controllers;

import models.Postulacion;
import services.PostulacionService;

public class PostulacionController extends BaseController<Postulacion, PostulacionService> {

    public void crearPostulacion(Postulacion postulacion) {
        service.crearPostulacion(postulacion);
    }

    public void actualizarEstadoPostulacion(Postulacion postulacion) {
        service.actualizarPostulacion(postulacion, "estado");
    }
}
