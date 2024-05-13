package controllers;

import core.utils.UsuarioThreadLocal;
import core.utils.Util;
import models.Usuario;
import views.DialogLogin;

public class Main {

    public static Util util = new Util();

    // Ejecutar programa, mostrando la vista de Login
    public static void main(String[] args) {
//        DialogLogin dialogLogin = new DialogLogin();
//        util.centerOnScreen(dialogLogin, true);
//        dialogLogin.setVisible(true);

        testController();
    }

    public static void testLogin() {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(1);
        usuario.setNombres("Alex Quispe");
        UsuarioThreadLocal.set(usuario);
    }
    
    public static void testController() {
        testLogin();
        PostulacionController postulacionController = new PostulacionController();
        postulacionController.listarPostulaciones("postulado");
    }
}
