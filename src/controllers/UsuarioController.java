package controllers;

import core.services.ResponseService;
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

    public ResponseService<String> login(String rol, String username, String pwd) {
        ResponseService<String> response = new ResponseService<>();
        boolean success = authService.login(rol, username, pwd);
        
        response.setSuccess(success);
        response.setResult(rol);
        
        return response;
    }

    public void registrarUsuario(Usuario usuario) {
        usuarioService.registrarUsuario(usuario);
    }

    public void logout(String id_usuario) {
        authService.logout(id_usuario);
    }
}
