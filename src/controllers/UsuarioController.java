package controllers;

import core.services.ResponseService;
import core.utils.UsuarioThreadLocal;
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

    public ResponseService<Usuario> login(String username, String pwd) {
        ResponseService<Usuario> response = new ResponseService<>();
        boolean success = authService.login(username, pwd);

        response.setSuccess(success);
        response.setMessage("usuario logueado como: " + UsuarioThreadLocal.get().getRol());
        response.setResult(UsuarioThreadLocal.get());

        return response;
    }

    public ResponseService<Boolean> registrarUsuario(Usuario usuario) {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = usuarioService.registrarUsuario(usuario);

        response.setSuccess(success);
        response.setMessage("usuario registrado existosamente");

        return response;
    }

    public ResponseService<Boolean> logout() {
        ResponseService<Boolean> response = new ResponseService<>();
        boolean success = authService.logout();

        response.setSuccess(success);
        response.setMessage("usuario deslogueado");

        return response;
    }
}
