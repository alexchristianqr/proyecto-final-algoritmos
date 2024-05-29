package controllers;

import models.Empleo;
import services.EmpleoService;

public class EmpleoController extends BaseController<Empleo, EmpleoService> {

    public EmpleoController() {
        lista.clear();
        service = new EmpleoService();
    }

    public void registrarEmpleo(Empleo empleo) {
        service.crearEmpleo(empleo);
    }

    public void listarPublicaciones() {
        String[] columnNames = {"Codigo", "Reclutador", "Titulo", "Empresa", "Sueldo", "Modalidad", "Total Postulados", "Fecha creado"};
        service.obtenerEmpleosPorReclutador(columnNames);
    }
}
