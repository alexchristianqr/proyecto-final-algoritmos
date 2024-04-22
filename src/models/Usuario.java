package models;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int idUsuario;
    private String username;
    private String password;
    private String perfil;
    private String estado;// activo|inactivo|pendiente
    private String nombres;
    private String fechaCreado;
    private String fechaActualizado;

    public Usuario() {
    }

    public Usuario(Usuario usuario) {
        this.username = usuario.getUsername();
        this.password = usuario.getPassword();
        this.perfil = usuario.getPerfil();
        this.estado = usuario.getEstado();
        this.nombres = usuario.getNombres();
        this.fechaCreado = usuario.getFechaCreado();
        this.fechaActualizado = usuario.getFechaActualizado();
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
