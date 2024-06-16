package controllers;

import models.Usuario;
import services.AuthService;
import services.UsuarioService;

public class UsuarioController extends BaseController<Usuario, UsuarioService> {

    private AuthService authService;
    private UsuarioService usuarioService;
    
    public UsuarioController() {
        usuarioService = new UsuarioService();
        authService = new AuthService();
    }

    public void signIn(String rol, String username, String pwd) {
        authService.login(rol, username, pwd);
    }
    
    public void register(Usuario usuario) {
        usuarioService.crearRegistro(usuario);
    }
}
