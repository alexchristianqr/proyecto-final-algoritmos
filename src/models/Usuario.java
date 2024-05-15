package models;

public class Usuario {

    private int idUsuario;
    private int idCandidato;
    private int idReclutador;
    private String username;
    private String password;
    private String perfil;
    private String estado;// activo|inactivo|pendiente
    private String fullname;
    private String nombres;
    private String apellidos;
    private String rol;
    private String fechaCreado;
    private String fechaActualizado;

    public Usuario() {
    }

    public Usuario(Usuario usuario) {
        this.idUsuario = usuario.getIdUsuario();
        this.idCandidato = usuario.getIdCandidato();
        this.idReclutador = usuario.getIdReclutador();
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.password = usuario.getNombres();
        this.perfil = usuario.getPerfil();
        this.estado = usuario.getEstado();
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.fullname = usuario.getFullname();
        this.rol = usuario.getRol();
        this.fechaCreado = usuario.getFechaCreado();
        this.fechaActualizado = usuario.getFechaActualizado();
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
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
