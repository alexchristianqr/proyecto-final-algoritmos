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

        boolean success = service.registrarEmpleo(empleo);

        response.setSuccess(success);
        response.setResult("empleo registrado exitosamente");

        return response;
    }

    public ResponseService<List<Object[]>> listarEmpleos(Empleo empleo) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Titulo", "Empresa", "Sueldo", "Modalidad", "Estado", "Total Postulados", "Fecha Creado", "Fecha Actualizado"};
        List<Object[]> resultado = service.listarEmpleos(columnNames, empleo);

        response.setSuccess(true);
        response.setMessage("listado de postulaciones");
        response.setResult(resultado);

        return response;
    }
}
