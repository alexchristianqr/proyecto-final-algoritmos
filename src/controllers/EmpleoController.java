package controllers;

import models.Empleo;
import services.EmpleoService;

public class EmpleoController extends BaseController<Empleo, EmpleoService> {

    public void crearEmpleo(Empleo empleo) {
        service.crearEmpleo(empleo);
    }
}
