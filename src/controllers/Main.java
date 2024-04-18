package controllers;

import core.utils.Util;
import views.DialogLogin;

public class Main {

    public static Util util = new Util();

    // Ejecutar programa, mostrando la vista de Login
    public static void main(String[] args) {
        DialogLogin dialogLogin = new DialogLogin();
        util.centerOnScreen(dialogLogin, true);
        dialogLogin.setVisible(true);
    }
}
