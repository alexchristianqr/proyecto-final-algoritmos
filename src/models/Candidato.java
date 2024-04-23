package models;

import java.io.Serializable;

public class Candidato extends Persona implements Serializable {

    private int idCandidato;
    private String imagenPerfil;
    private ExperienciaLaboral[] experienciaLaboral;
    private String pathCV;
    private String pathCertificadoTrabajo;
    private String pathAntecedentePolicial;

    public Candidato() {
    }

    public Candidato(Candidato candidato) {
        super(candidato.getTipoDocumento(), candidato.getNroDocumento(), candidato.getNombre(), candidato.getApellidos(), candidato.getSexo(), candidato.getEdad(), candidato.getTelefono(), candidato.getEstado(), candidato.getFechaCreado(), candidato.getFechaActualizado(), candidato.getIdPersona());
        this.imagenPerfil = candidato.getImagenPerfil();
        this.experienciaLaboral = candidato.getExperienciaLaboral();
        this.pathCV = candidato.getPathCV();
        this.pathCertificadoTrabajo = candidato.getPathCertificadoTrabajo();
        this.pathAntecedentePolicial = candidato.getPathAntecedentePolicial();
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getImagenPerfil() {
        return imagenPerfil;
    }

    public void setImagenPerfil(String imagenPerfil) {
        this.imagenPerfil = imagenPerfil;
    }

    public ExperienciaLaboral[] getExperienciaLaboral() {
        return experienciaLaboral;
    }

    public void setExperienciaLaboral(ExperienciaLaboral[] experienciaLaboral) {
        this.experienciaLaboral = experienciaLaboral;
    }

    public String getPathCV() {
        return pathCV;
    }

    public void setPathCV(String pathCV) {
        this.pathCV = pathCV;
    }

    public String getPathCertificadoTrabajo() {
        return pathCertificadoTrabajo;
    }

    public void setPathCertificadoTrabajo(String pathCertificadoTrabajo) {
        this.pathCertificadoTrabajo = pathCertificadoTrabajo;
    }

    public String getPathAntecedentePolicial() {
        return pathAntecedentePolicial;
    }

    public void setPathAntecedentePolicial(String pathAntecedentePolicial) {
        this.pathAntecedentePolicial = pathAntecedentePolicial;
    }

}
