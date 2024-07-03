package models;

public class ExperienciaLaboral {

    private int idExperienciaLaboral;
    private int idCandidato;
    private String titulo;
    private String descripcion;
    private String empresa;
    private String fechaInicio;
    private String fechaFin;
    private String estado;// activo|inactivo
    private String fechaCreado;
    private String fechaActualizado;
    private String fechaEliminado;
    private int orden;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        this.idExperienciaLaboral = experienciaLaboral.getIdExperienciaLaboral();
        this.idCandidato = experienciaLaboral.getIdCandidato();
        this.titulo = experienciaLaboral.getTitulo();
        this.descripcion = experienciaLaboral.getDescripcion();
        this.empresa = experienciaLaboral.getEmpresa();
        this.fechaInicio = experienciaLaboral.getFechaInicio();
        this.fechaFin = experienciaLaboral.getFechaFin();
        this.fechaCreado = experienciaLaboral.getFechaCreado();
        this.fechaActualizado = experienciaLaboral.getFechaActualizado();
        this.fechaEliminado = experienciaLaboral.getFechaEliminado();
        this.orden = experienciaLaboral.getOrden();
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public int getIdExperienciaLaboral() {
        return idExperienciaLaboral;
    }

    public void setIdExperienciaLaboral(int idExperienciaLaboral) {
        this.idExperienciaLaboral = idExperienciaLaboral;
    }

    public int getIdCandidato() {
        return idCandidato;
    }

    public void setIdCandidato(int idCandidato) {
        this.idCandidato = idCandidato;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
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

}
