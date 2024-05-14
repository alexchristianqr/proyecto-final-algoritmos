package controllers;

import models.Empleo;
import services.EmpleoService;

public class EmpleoController extends BaseController<Empleo, EmpleoService> {

    public EmpleoController() {
        lista.clear();
        service = new EmpleoService();
    }

    public void crearEmpleo(Empleo empleo) {
        service.crearEmpleo(empleo);
    }

    public void listarPublicaciones() {
        String[] columnNames = {"Codigo", "Reclutador", "Titulo", "Empresa", "Sueldo", "Modalidad", "Total Postulados", "Fecha creado"};
        Object[] data = new Object[columnNames.length];
        service.listarEmpleos(columnNames, data);
    }
}
