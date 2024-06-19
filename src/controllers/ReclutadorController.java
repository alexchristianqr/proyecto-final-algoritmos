package controllers;

import models.Reclutador;
import services.ReclutadorService;

public class ReclutadorController extends BaseController<Reclutador, ReclutadorService> {
    
    public void crearReclutador(Reclutador reclutador) {
        service.crearReclutador(reclutador);
    }
}
