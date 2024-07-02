package controllers;

import core.services.ResponseService;
import java.util.List;
import models.Candidato;
import models.EstudioAcademico;
import models.ExperienciaLaboral;
import services.CandidatoService;
import services.EstudioAcademicoService;
import services.ExperienciaLaboralService;

public class CandidatoController extends BaseController<Candidato, CandidatoService> {

    private final CandidatoService candidatoService;
    private final EstudioAcademicoService estudioAcademicoService;
    private final ExperienciaLaboralService experienciaLaboralService;

    public CandidatoController() {
        candidatoService = new CandidatoService();
        estudioAcademicoService = new EstudioAcademicoService();
        experienciaLaboralService = new ExperienciaLaboralService();
    }

    public ResponseService<Boolean> registrarCandidato(Candidato candidato) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = candidatoService.registrarCandidato(candidato);

        response.setSuccess(success);
        response.setMessage("candidato registrado correctamente");
        response.setResult(null);

        return response;
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

    public ResponseService<List<Object[]>> listarEstudiosAcademicos(EstudioAcademico estudioAcademico) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Codigo", "Titulo", "Descripcion", "Fecha inicio", "Fecha fin", "Grado", "Estado", "Fecha creado"};
        List<Object[]> resultado = estudioAcademicoService.listarEstudiosAcademicos(columnNames, estudioAcademico);

        response.setSuccess(true);
        response.setMessage("listado de postulaciones");
        response.setResult(resultado);

        return response;
    }
      
    public ResponseService<Boolean> cargarPDF(int candidatoId, String filePath) {
        ResponseService<Boolean> response = new ResponseService<>();

        try {
            candidatoService.cargarPDF(candidatoId, filePath);
            response.setSuccess(true);
            response.setMessage("PDF cargado exitosamente.");
        } catch (RuntimeException e) {
            response.setSuccess(false);
            response.setMessage("Error al cargar el PDF: " + e.getMessage());
        }

        return response;
    }
}
