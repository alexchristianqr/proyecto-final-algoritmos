package controllers;

import models.Reclutador;
import core.services.ResponseService;
import models.Empleo;
import services.EmpleoService;
import services.ReclutadorService;

public class ReclutadorController extends BaseController<Reclutador, ReclutadorService> {

    private final ReclutadorService reclutadorService;
    private final EmpleoService empleoService;

    public ReclutadorController() {
        reclutadorService = new ReclutadorService();
        empleoService = new EmpleoService();
    }

    public ResponseService<Boolean> registrarReclutador(Reclutador reclutador) {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reclutadorService.registrarReclutador(reclutador);

        response.setSuccess(success);
        response.setMessage("reclutador registrado de manera exitosa");

        return response;
    }
    
    public ResponseService<Boolean> registrarEmpleo(Empleo empleo) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = empleoService.registrarEmpleo(empleo);

        response.setSuccess(success);
        response.setMessage("empleo registrado correctamente");
        response.setResult(null);

        return response;
    }
}
