package controllers;

import core.services.ResponseService;
import java.util.List;
import models.Empleo;
import services.EmpleoService;

public class EmpleoController extends BaseController<Empleo, EmpleoService> {

    public EmpleoController() {
        lista.clear();
        service = new EmpleoService();
    }

    public ResponseService<String> registrarEmpleo(Empleo empleo) {
        ResponseService<String> response = new ResponseService<>();

        boolean success = service.crearEmpleo(empleo);

        response.setSuccess(success);
        response.setResult("empleo registrado exitosamente");

        return response;
    }

    public ResponseService<List<Object[]>> listarPublicaciones() {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Codigo", "Reclutador", "Titulo", "Empresa", "Sueldo", "Modalidad", "Total Postulados", "Fecha creado"};
        List<Object[]> resultado = service.listarEmpleosPorReclutador(columnNames);

        response.setSuccess(true);
        response.setMessage("listado de postulaciones");
        response.setResult(resultado);

        return response;
    }
}
