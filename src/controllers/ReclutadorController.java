package controllers;

import models.Reclutador;
import core.services.ResponseService;
import services.AuthService;
import services.ReclutadorService;

public class ReclutadorController extends BaseController<Reclutador, ReclutadorService> {
    
    private final ReclutadorService reclutadorService;
    private final AuthService authService;
    
    public ReclutadorController(){
        reclutadorService = new ReclutadorService();
        authService = new AuthService();
    }

   /* public void eliminarReclutador(int idPersona){
        service.eliminarReclutador(idPersona);
    }*/
    public ResponseService<Boolean> registrarReclutador(Reclutador reclutador) {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reclutadorService.registrarReclutador(reclutador);

        response.setSuccess(success);
        response.setMessage("reclutador registrado de manera exitosa");       

        return response;
    }
}
