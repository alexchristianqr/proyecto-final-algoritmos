package models;

public class FeedbackInfo {

    private String puesto;
    private String nombreCandidato;
    private String fortalezas;
    private String mejoras;
    private String adicional;
    private String nombreReclutador;
    private String puestoReclutador;
    private String empresaReclutador;
    private String emailReclutador;
    private String telefonoReclutador;

    public FeedbackInfo() {
    }

    // Constructor
    public FeedbackInfo(FeedbackInfo feedbackInfo) {
        this.puesto = feedbackInfo.getPuesto();
        this.nombreCandidato = feedbackInfo.getNombreCandidato();
        this.fortalezas = feedbackInfo.getFortalezas();
        this.mejoras = feedbackInfo.getMejoras();
        this.adicional = feedbackInfo.getAdicional();
        this.nombreReclutador = feedbackInfo.getNombreReclutador();
        this.puestoReclutador = feedbackInfo.getPuestoReclutador();
        this.empresaReclutador = feedbackInfo.getEmpresaReclutador();
        this.emailReclutador = feedbackInfo.getEmailReclutador();
        this.telefonoReclutador = feedbackInfo.getTelefonoReclutador();
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getNombreCandidato() {
        return nombreCandidato;
    }

    public void setNombreCandidato(String nombreCandidato) {
        this.nombreCandidato = nombreCandidato;
    }

    public String getFortalezas() {
        return fortalezas;
    }

    public void setFortalezas(String fortalezas) {
        this.fortalezas = fortalezas;
    }

    public String getMejoras() {
        return mejoras;
    }

    public void setMejoras(String mejoras) {
        this.mejoras = mejoras;
    }

    public String getAdicional() {
        return adicional;
    }

    public void setAdicional(String adicional) {
        this.adicional = adicional;
    }

    public String getNombreReclutador() {
        return nombreReclutador;
    }

    public void setNombreReclutador(String nombreReclutador) {
        this.nombreReclutador = nombreReclutador;
    }

    public String getPuestoReclutador() {
        return puestoReclutador;
    }

    public void setPuestoReclutador(String puestoReclutador) {
        this.puestoReclutador = puestoReclutador;
    }

    public String getEmpresaReclutador() {
        return empresaReclutador;
    }

    public void setEmpresaReclutador(String empresaReclutador) {
        this.empresaReclutador = empresaReclutador;
    }

    public String getEmailReclutador() {
        return emailReclutador;
    }

    public void setEmailReclutador(String emailReclutador) {
        this.emailReclutador = emailReclutador;
    }

    public String getTelefonoReclutador() {
        return telefonoReclutador;
    }

    public void setTelefonoReclutador(String telefonoReclutador) {
        this.telefonoReclutador = telefonoReclutador;
    }

}
