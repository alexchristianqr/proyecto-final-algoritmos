package controllers;

import core.services.ResponseService;
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

    public void listarPublicaciones() {
        String[] columnNames = {"Codigo", "Reclutador", "Titulo", "Empresa", "Sueldo", "Modalidad", "Total Postulados", "Fecha creado"};
        service.obtenerEmpleosPorReclutador(columnNames);
    }
}
