package models;

public class EstudioAcademico {

    private int idEstudioAcademico;
    private int idCandidato;
    private String titulo;
    private String descripcion;
    private String fechaInicio;
    private String fechaFin;
    private String grado;// secundaria|tecnico|universitario|maestria|doctorado
    private String estado;// activo|inactivo
    private String fechaCreado;
    private String fechaActualizado;
    private String fechaEliminado;
    private int orden;

    public EstudioAcademico() {
    }

    public EstudioAcademico(EstudioAcademico estudioAcademico) {
        this.idEstudioAcademico = estudioAcademico.getIdEstudioAcademico();
        this.idCandidato = estudioAcademico.getIdCandidato();
        this.titulo = estudioAcademico.getTitulo();
        this.descripcion = estudioAcademico.getDescripcion();
        this.grado = estudioAcademico.getGrado();
        this.estado = estudioAcademico.getEstado();
        this.fechaInicio = estudioAcademico.getFechaInicio();
        this.fechaFin = estudioAcademico.getFechaFin();
        this.fechaCreado = estudioAcademico.getFechaCreado();
        this.fechaActualizado = estudioAcademico.getFechaActualizado();
        this.fechaEliminado = estudioAcademico.getFechaEliminado();
        this.orden = estudioAcademico.getOrden();
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getIdEstudioAcademico() {
        return idEstudioAcademico;
    }

    public void setIdEstudioAcademico(int idEstudioAcademico) {
        this.idEstudioAcademico = idEstudioAcademico;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
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

    public String getFechaEliminado() {
        return fechaEliminado;
    }

    public void setFechaEliminado(String fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }

}
