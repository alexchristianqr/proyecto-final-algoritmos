package models;

public class Postulacion extends Empleo {

    private int idPostulacion;
    private int idEmpleo;
    private Empleo empleo;
    private int idCandidato;
    private Candidato candidato;
    private int idReclutador;
    private String feedback;
    private Reclutador reclutador;
    private int edad;
    private String estado;// postulado|en_proceso|contratado|cancelado|bloqueado
    private String fechaCreado;
    private String fechaActualizado;

    public Postulacion() {
    }

    public Postulacion(Postulacion postulacion) {
        this.idPostulacion = postulacion.getIdPostulacion();
        this.idEmpleo = postulacion.getIdEmpleo();
        this.empleo = postulacion.getEmpleo();
        this.idCandidato = postulacion.getIdCandidato();
        this.candidato = postulacion.getCandidato();
        this.feedback = postulacion.getFeedback();
        this.idReclutador = postulacion.getIdReclutador();
        this.reclutador = postulacion.getReclutador();
        this.edad = postulacion.getEdad();
        this.estado = postulacion.getEstado();
        this.fechaCreado = postulacion.getFechaCreado();
        this.fechaActualizado = postulacion.getFechaActualizado();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public int getIdPostulacion() {
        return idPostulacion;
    }

    public void setIdPostulacion(int idPostulacion) {
        this.idPostulacion = idPostulacion;
    }

    public Empleo getEmpleo() {
        return empleo;
    }

    public void setEmpleo(Empleo empleo) {
        this.empleo = empleo;
    }

    public Candidato getCandidato() {
        return candidato;
    }

    public void setCandidato(Candidato candidato) {
        this.candidato = candidato;
    }

    public Reclutador getReclutador() {
        return reclutador;
    }

    public void setReclutador(Reclutador reclutador) {
        this.reclutador = reclutador;
    }

    public int getIdEmpleo() {
        return idEmpleo;
    }

    public void setIdEmpleo(int idEmpleo) {
        this.idEmpleo = idEmpleo;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public int getIdReclutador() {
        return idReclutador;
    }

    public void setIdReclutador(int idReclutador) {
        this.idReclutador = idReclutador;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFechaCreado() {
        return fechaCreado;
    }

    public void setFechaCreado(String fechaCreado) {
        this.fechaCreado = fechaCreado;
    }

    public String getFechaActualizado() {
        return fechaActualizado;
    }

    public void setFechaActualizado(String fechaActualizado) {
        this.fechaActualizado = fechaActualizado;
    }

}
