package models;

public class FiltroPostulaciones {

    private String buscar;
    private String estado;
    private String fechaCreado;
    private String modalidad;
    private String sueldoMin;
    private String sueldoMax;
    private String sueldo;

    public FiltroPostulaciones() {
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

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getSueldoMin() {
        return sueldoMin;
    }

    public void setSueldoMin(String sueldoMin) {
        this.sueldoMin = sueldoMin;
    }

    public String getSueldoMax() {
        return sueldoMax;
    }

    public void setSueldoMax(String sueldoMax) {
        this.sueldoMax = sueldoMax;
    }

    public String getSueldo() {
        return sueldo;
    }

    public void setSueldo(String sueldo) {
        this.sueldo = sueldo;
    }

    public String getBuscar() {
        return buscar;
    }

    public void setBuscar(String buscar) {
        this.buscar = buscar;
    }

}
