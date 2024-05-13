package models;

public class Empleo {

    private String titulo;
    private String empresa;
    private String sueldo;
    private String modalidad;
    private String descripcion;
    private String estado;// disponible|indisponible
    private String fechaCreado;
    private String fechaActualizado;
    private String fechaEliminado;

    public Empleo() {

    }

    public Empleo(Empleo empleo) {
        this.titulo = empleo.getTitulo();
        this.empresa = empleo.getEmpresa();
        this.estado = empleo.getEstado();
        this.sueldo = empleo.getSueldo();
        this.modalidad = empleo.getModalidad();
        this.descripcion = empleo.getDescripcion();
        this.fechaCreado = empleo.getFechaCreado();
        this.fechaActualizado = empleo.getFechaActualizado();
        this.fechaEliminado = empleo.getFechaEliminado();
    }

    public String getFechaEliminado() {
        return fechaEliminado;
    }

    public void setFechaEliminado(String fechaEliminado) {
        this.fechaEliminado = fechaEliminado;
    }
    
    

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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

}
