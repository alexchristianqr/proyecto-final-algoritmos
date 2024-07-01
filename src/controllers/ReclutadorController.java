package controllers;

import models.Reclutador;
import core.services.ResponseService;
import java.util.List;
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

        boolean success = empleoService.crearEmpleo(empleo);

        response.setSuccess(success);
        response.setMessage("empleo registrado correctamente");
        response.setResult(null);

        return response;
    }
    
    public ResponseService<List<Object[]>> listarEmpleo(Empleo empleo) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Titulo", "Empresa", "Sueldo", "Modalidad", "Estado"};
        List<Object[]> resultado = empleoService.listarEmpleosPorReclutador(columnNames);

        response.setSuccess(true);
        response.setMessage("listado de empleos");
        response.setResult(resultado);

        return response;
    }
}
