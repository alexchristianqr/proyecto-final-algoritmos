package models;

public abstract class Persona {

    private int idPersona;
    private int tipoDocumento;// DNI|PASAPORTE
    private String nroDocumento;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fecha_nacimiento;
    private String telefono;
    private String estado;// activo|inactivo
    private String fechaCreado;
    private String fechaActualizado;

    public Persona() {
    }

    public Persona(int tipoDocumento, String nroDocumento, String nombre, String apellidos, String sexo, String fecha_nacimiento, String telefono, String estado, String fechaCreado, String fechaActualizado, int idPersona) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fecha_nacimiento = fecha_nacimiento;
        this.telefono = telefono;
        this.estado = estado;
        this.fechaCreado = fechaCreado;
        this.fechaActualizado = fechaActualizado;
        this.idPersona = idPersona;
    }

    public int getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(int tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNroDocumento() {
        return nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

  

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
