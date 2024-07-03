package controllers;

import core.services.ResponseService;
import services.ReporteService;

public class ReporteController extends BaseController<Object, Object> {

    private final ReporteService reporteService;

    public ReporteController() {
        reporteService = new ReporteService();
    }

    public ResponseService<Boolean> reporteUsuarios() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reporteUsuarios();

        response.setSuccess(success);
        response.setMessage("reporte usuarios listo");

        return response;
    }

    public ResponseService<Boolean> reportePersonas() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reportePersonas();

        response.setSuccess(success);
        response.setMessage("reporte Personas listo");

        return response;
    }

    public ResponseService<Boolean> reporteEdad() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reporteEdad();

        response.setSuccess(success);
        response.setMessage("reporte Edad listo");

        return response;
    }

    public ResponseService<Boolean> reporteAptitudes() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reporteAptitudes();

        response.setSuccess(success);
        response.setMessage("reporte Aptitudes listo");

        return response;
    }

    public ResponseService<Boolean> reporteExperiencias() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reporteExperiencias();

        response.setSuccess(success);
        response.setMessage("reporte Experiencias listo");

        return response;
    }

    public ResponseService<Boolean> reporteReclutadores() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = reporteService.reporteReclutadores();

        response.setSuccess(success);
        response.setMessage("reporte usuarios listo");

        return response;
    }

}
