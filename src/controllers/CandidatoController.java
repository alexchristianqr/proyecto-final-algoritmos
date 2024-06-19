package controllers;

import models.Candidato;
import services.CandidatoService;

public class CandidatoController extends BaseController<Candidato, CandidatoService> {

    private CandidatoService service;

    public CandidatoController() {
        this.service = new CandidatoService();
    }

    public void crearCandidato(Candidato candidato) {
        service.crearCandidato(candidato);
    }

}
