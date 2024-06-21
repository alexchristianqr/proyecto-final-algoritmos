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
        response.setMessage("usuario autenticado");
        response.setResult(rol);

        return response;
    }

    public ResponseService<String> registrarUsuario(Usuario usuario) {
        ResponseService<String> response = new ResponseService<>();
        boolean success = usuarioService.registrarUsuario(usuario);

        response.setSuccess(success);
        response.setMessage("usuario registrado existosamente");

        return response;
    }

    public ResponseService<Boolean> logout() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = authService.logout();

        response.setSuccess(success);
        response.setMessage("usuario no autenticado");

        return response;
    }
}
