
package controllers;

import models.Archivo;
import services.ArchivoService;

import java.util.List;

public class ArchivoController {
    private final ArchivoService archivoService;

    public ArchivoController() {
        this.archivoService = new ArchivoService();
    }

    public void subirArchivo(Archivo archivo) {
        archivoService.subirArchivo(archivo);
    }

    public List<Archivo> obtenerArchivosPorCandidato(int idCandidato) {
        return archivoService.obtenerArchivosPorCandidato(idCandidato);
    }
}