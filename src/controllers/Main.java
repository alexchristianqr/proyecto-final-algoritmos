package controllers;

import core.services.ExportService;
import core.services.MysqlDBService;
import core.utils.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import models.Postulacion;
import views.DialogLogin;

public class Main {

    public static Util util = new Util();

    // Ejecutar programa, mostrando la vista de Login
    public static void main(String[] args) throws IOException {
        /*DialogLogin dialogLogin = new DialogLogin();
        util.centerOnScreen(dialogLogin, true);
        dialogLogin.setVisible(true);*/

        // testController();
        // testController2();
        // testReporte();

    }

    public static void testLogin(String rol, String username, String pwd) {
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.login(rol, username, pwd);
    }

    public static void testMisPostulaciones() {
        // INCIO DE SESION DE USUARIO CANDIDATO
        testLogin("candidato", "alex.quispe@gmail.com", "candidato2024");
        // testLogin("candidato", "dante.inigo@gmail.com", "candidato2024");

        PostulacionController postulacionController = new PostulacionController();

        // POSTULACION DE UN CANDIDATO A UN EMPLEO
        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);
        postulacion.setEstado("postulado");
        postulacionController.postularEmpleo(postulacion);

        // MIS POSTULACIONES
        postulacionController.listarPostulaciones("postulado");
    }

    public static void testMisPublicaciones() {
        // INCIO DE SESION DE USUARIO RECLUTADOR
        testLogin("reclutador", "maria.gonzales@utp.edu.pe", "reclutador2024");
        // testLogin("reclutador", "susan.torres@utp.edu.pe", "reclutador2024");

        EmpleoController empleoController = new EmpleoController();

        // MIS PUBLICACIONES
        empleoController.listarPublicaciones();
    }

    public static void testReporte() throws IOException {
        try {
            String sql = "select * from usuarios";
            
            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);
            
            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
}
