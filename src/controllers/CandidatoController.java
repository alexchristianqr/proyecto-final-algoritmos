package controllers;

import core.services.ResponseService;
import core.utils.UsuarioThreadLocal;
import models.Candidato;
import models.EstudioAcademico;
import models.Usuario;
import services.CandidatoService;
import services.EstudioAcademicoService;

public class CandidatoController extends BaseController<Candidato, CandidatoService> {

    private CandidatoService candidatoService;
    private EstudioAcademicoService estudioAcademicoService;

    public CandidatoController() {
        candidatoService = new CandidatoService();
        estudioAcademicoService = new EstudioAcademicoService();
    }

    public void registrarCandidato(Candidato candidato) {
        candidatoService.registrarCandidato(candidato);
    }
    
    public ResponseService<Boolean> registrarEstudioAcademico(EstudioAcademico estudioAcademico){
        ResponseService<Boolean> response = new ResponseService<>();
        
        boolean success = estudioAcademicoService.registrarEstudioAcademico(estudioAcademico);
        
         response.setSuccess(success);
        response.setMessage("estudio academico registrado correctamente");
        response.setResult(null);

        return response;
    }

}
