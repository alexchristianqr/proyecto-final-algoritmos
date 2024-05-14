package controllers;

import core.utils.UsuarioThreadLocal;
import core.utils.Util;
import models.Postulacion;
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

    /**
     * 1: ID prueba de un Candidato
     * 1 y 2: IDs prueba de un Reclutador
     * @param idUsuario
     */
    public static void testLogin(int idUsuario) {
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(idUsuario);
        usuario.setNombres("Alex Quispe");
        UsuarioThreadLocal.set(usuario);
    }
    
    public static void testController() {
        testLogin(1);
        
        PostulacionController postulacionController = new PostulacionController();
        EmpleoController empleoController = new EmpleoController();
        CandidatoController candiController = new CandidatoController();
        
        // POSTULACION DE UN CANDIDATO
        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);
        postulacion.setEstado("postulado");
        postulacionController.actualizarEstadoPostulacion(postulacion);
        
        // MIS POSTULACIONES
        postulacionController.listarPostulaciones("postulado");
        
        testLogin(4);
        
        // MIS PUBLICACIONES
        empleoController.listarPublicaciones();


    }
}
