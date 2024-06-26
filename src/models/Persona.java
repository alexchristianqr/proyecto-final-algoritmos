package models;

public abstract class Persona {

    private int idPersona;
    private int tipoDocumento;// DNI|PASAPORTE
    private String nroDocumento;
    private String nombre;
    private String apellidos;
    private String sexo;
    private String fechaNacimiento;
    private String telefono;
    private String estado;// activo|inactivo
    private String fechaCreado;
    private String fechaActualizado;
    private String estadoCivil;
    private String edad;

    public Persona() {
    }

    public Persona(int tipoDocumento, String nroDocumento, String nombre, String apellidos, String sexo, String fechaNacimiento, String telefono, String estado, String fechaCreado, String fechaActualizado, String estadoCivil, int idPersona) {
        this.tipoDocumento = tipoDocumento;
        this.nroDocumento = nroDocumento;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.estado = estado;
        this.fechaCreado = fechaCreado;
        this.fechaActualizado = fechaActualizado;
        this.estadoCivil = estadoCivil;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
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

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

}
