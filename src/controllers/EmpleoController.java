package controllers;

import core.services.ResponseService;
import java.util.List;
import models.Empleo;
import models.FiltroEmpleosCandidato;
import models.FiltroEmpleosReclutador;
import services.EmpleoService;

public class EmpleoController extends BaseController<Empleo, EmpleoService> {

    public EmpleoController() {
        lista.clear();
        service = new EmpleoService();
    }

    public ResponseService<Boolean> registrarEmpleo(Empleo empleo) {
        ResponseService<Boolean> response = new ResponseService<>();

        boolean success = service.registrarEmpleo(empleo);

        response.setSuccess(success);
        response.setMessage("empleo registrado correctamente");

        return response;
    }

    public ResponseService<List<Object[]>> listarEmpleos(Empleo empleo, FiltroEmpleosReclutador filtroEmpleosReclutador) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Titulo", "Empresa", "Sueldo", "Modalidad", "Descripcion", "Estado", "ID Candidato", "Candidato", "Total Postulados","Edad Min", "Edad Max", "Fecha Creado", "Fecha Actualizado"};
        List<Object[]> resultado = service.listarEmpleos(columnNames, empleo, filtroEmpleosReclutador);

        response.setSuccess(true);
        response.setMessage("listar empleos correctamente");
        response.setResult(resultado);

        return response;
    }

    public ResponseService<List<Object[]>> listarEmpleosCandidatos(FiltroEmpleosCandidato filtroEmpleosCandidato) {
        ResponseService<List<Object[]>> response = new ResponseService<>();

        String[] columnNames = {"Codigo", "Titulo", "Empresa", "Sueldo", "Modalidad", "Descripcion", "Fecha Creado"};
        List<Object[]> resultado = service.listarEmpleosCandidatos(columnNames, filtroEmpleosCandidato);

        response.setSuccess(true);
        response.setMessage("listar empleos para candidatos correctamente");
        response.setResult(resultado);

        return response;
    }
}
