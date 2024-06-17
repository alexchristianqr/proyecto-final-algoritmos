package controllers;

import core.services.ExportService;
import core.services.MysqlDBService;
import core.services.UploadService;
import core.utils.Util;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import models.Postulacion;
import views.DialogLogin;

public class Main {

    // ANSI escape codes for bold text
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_RESET = "\033[0m";
    public static Util util = new Util();

    // Ejecutar programa, mostrando la vista de Login
    public static void main(String[] args) throws IOException {
        /*DialogLogin dialogLogin = new DialogLogin();
        util.centerOnScreen(dialogLogin, true);
        dialogLogin.setVisible(true);*/

//        testMisPostulaciones();
//        testMisPublicaciones();
//        testReporteUsuarios();
         // Example usage:
         UploadService uploader = new UploadService();
         
//        UploadFile uploader = new UploadService.();
        String sourceFilePath = "C:\\Users\\sistemas\\OneDrive - TUI\\Documentos\\src\\src-utp\\proyecto-final-algoritmos\\src\\exports\\file.txt";
        String destinationFilePath = "C:\\Users\\sistemas\\OneDrive - TUI\\Documentos\\src\\src-utp\\proyecto-final-algoritmos\\src\\exports\\uploaded_file.txt";

        try {
            // Open an input stream to the source file
            // Open an input stream to the source file
            InputStream inputStream = new FileInputStream(sourceFilePath);
//            InputStream inputStream = UploadService.class.getResourceAsStream(sourceFilePath);

            if (inputStream == null) {
                System.out.println("File not found: " + sourceFilePath);
                return;
            }

            // Upload the file
            uploader.upload(inputStream, destinationFilePath);

            // Close the input stream
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testLogin(String rol, String username, String pwd) {
        UsuarioController usuarioController = new UsuarioController();
        usuarioController.login(rol, username, pwd);
    }

    public static void testMisPostulaciones() {

        // INICIO DE SESION DE USUARIO CANDIDATO
        testLogin("candidato", "alex.quispe@gmail.com", "candidato2024");
        // testLogin("candidato", "dante.inigo@gmail.com", "candidato2024");

        PostulacionController postulacionController = new PostulacionController();

        // POSTULAR A UN EMPLEO
        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);
        postulacion.setEstado("postulado");
        postulacionController.postularEmpleo(postulacion);

        // MOSTRAR POSTULACIONES
        postulacionController.listarPostulaciones("postulado");
    }

    public static void testMisPublicaciones() {

        // INICIO DE SESION DE USUARIO RECLUTADOR
        testLogin("reclutador", "maria.gonzales@utp.edu.pe", "reclutador2024");
        // testLogin("reclutador", "susan.torres@utp.edu.pe", "reclutador2024");

        EmpleoController empleoController = new EmpleoController();

        // MOSTRAR EMPLEOS
        empleoController.listarPublicaciones();
    }

    public static void testReporteUsuarios() throws IOException {
        try {
            String sql = "select * from usuarios";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }

}
