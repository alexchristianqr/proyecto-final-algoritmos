package controllers;

import models.Reclutador;
import core.services.ResponseService;
import services.ReclutadorService;

public class ReclutadorController extends BaseController<Reclutador, ReclutadorService> {

    private final ReclutadorService reclutadorService;

    public ReclutadorController() {
        reclutadorService = new ReclutadorService();
    }

    public ResponseService<Boolean> registrarReclutador(Reclutador reclutador) {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reclutadorService.registrarReclutador(reclutador);

        response.setSuccess(success);
        response.setMessage("reclutador registrado de manera exitosa");

        return response;
    }

    public ResponseService<Boolean> actualizarReclutador(Reclutador reclutador) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = reclutadorService.actualizarReclutador(reclutador);

        response.setSuccess(success);
        response.setMessage("reclutador actualizado correctamente");
        response.setResult(null);

        return response;
    }
}
