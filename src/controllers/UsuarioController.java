package controllers;

import models.Usuario;
import services.AuthService;
import services.UsuarioService;

public class UsuarioController extends BaseController<Usuario, UsuarioService> {

    private final AuthService authService;
    private final UsuarioService usuarioService;

    public UsuarioController() {
        usuarioService = new UsuarioService();
        authService = new AuthService();
    }

    public boolean login(String rol, String username, String pwd) {
        return authService.login(rol, username, pwd);
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }

    public void logout(String id_usuario) {
        authService.logout(id_usuario);
    }
}
