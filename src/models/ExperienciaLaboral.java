package models;

public class ExperienciaLaboral {

    private String titulo;
    private String descripcion;
    private String empresa;
    private String fechaInicio;
    private String fechaFin;

    public ExperienciaLaboral() {
    }

    public ExperienciaLaboral(ExperienciaLaboral experienciaLaboral) {
        this.titulo = experienciaLaboral.getTitulo();
        this.descripcion = experienciaLaboral.getDescripcion();
        this.empresa = experienciaLaboral.getEmpresa();
        this.fechaInicio = experienciaLaboral.getFechaInicio();
        this.fechaFin = experienciaLaboral.getFechaFin();
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
