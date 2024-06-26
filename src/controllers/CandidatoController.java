package controllers;

import core.services.ResponseService;
import core.utils.UsuarioThreadLocal;
import models.Candidato;
import models.EstudioAcademico;
import models.ExperienciaLaboral;
import models.Usuario;
import services.CandidatoService;
import services.EstudioAcademicoService;
import services.ExperienciaLaboralService;

public class CandidatoController extends BaseController<Candidato, CandidatoService> {

    private CandidatoService candidatoService;
    private EstudioAcademicoService estudioAcademicoService;
    private ExperienciaLaboralService experienciaLaboralService;

    public CandidatoController() {
        candidatoService = new CandidatoService();
        estudioAcademicoService = new EstudioAcademicoService();
        experienciaLaboralService = new ExperienciaLaboralService();
    }

    public void registrarCandidato(Candidato candidato) {
        candidatoService.registrarCandidato(candidato);
    }

    public ResponseService<Boolean> registrarEstudioAcademico(EstudioAcademico estudioAcademico) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = estudioAcademicoService.registrarEstudioAcademico(estudioAcademico);

        response.setSuccess(success);
        response.setMessage("estudio academico registrado correctamente");
        response.setResult(null);

        return response;
    }

    public ResponseService<Boolean> registrarExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = experienciaLaboralService.registrarExperienciaLaboral(experienciaLaboral);

        response.setSuccess(success);
        response.setMessage("experiencia laboral registrado correctamente");
        response.setResult(null);

        return response;
    }
}
