package models;

public class Candidato extends Persona {

    private int idCandidato;
    private int idUsuario;
    private String aptitudes;
    private String imagenPerfil;
    private ExperienciaLaboral[] experienciaLaboral;
    private String pathCV;
    private String pathCertificadoTrabajo;
    private String pathAntecedentePolicial;

    public Candidato() {
    }

    public Candidato(Candidato candidato) {
        super(candidato.getTipoDocumento(), candidato.getNroDocumento(), candidato.getNombre(), candidato.getApellidos(), candidato.getSexo(), candidato.getFechaNacimiento(), candidato.getTelefono(), candidato.getEstado(), candidato.getFechaCreado(), candidato.getFechaActualizado(), candidato.getEstadoCivil(), candidato.getIdPersona());
        this.idUsuario = candidato.getIdUsuario();
        this.aptitudes = candidato.getAptitudes();
        this.imagenPerfil = candidato.getImagenPerfil();
        this.experienciaLaboral = candidato.getExperienciaLaboral();
        this.pathCV = candidato.getPathCV();
        this.pathCertificadoTrabajo = candidato.getPathCertificadoTrabajo();
        this.pathAntecedentePolicial = candidato.getPathAntecedentePolicial();

    }

    public String getAptitudes() {
        return aptitudes;
    }

    public void setAptitudes(String aptitudes) {
        this.aptitudes = aptitudes;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
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
