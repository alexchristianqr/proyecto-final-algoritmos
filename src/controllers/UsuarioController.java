package controllers;

import models.Usuario;
import services.UsuarioService;

public class UsuarioController extends BaseController<Usuario, UsuarioService> {

    public UsuarioController() {
        service = new UsuarioService();
    }

    public void login(String rol, String username, String pwd) {
        service.obtenerUsuarioPorRol(rol, username, pwd);
    }
}
