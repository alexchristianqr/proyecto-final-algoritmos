package controllers;

import core.services.ExportService;
import core.services.MysqlDBService;
import core.utils.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;
import models.Candidato;
import models.Postulacion;
import models.Usuario;
import views.Login;

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

        //testMisPostulaciones();
        //testMisPublicaciones();
        //testReporteUsuarios();
        //testRegistrarUsuario();
<<<<<<< Updated upstream
        testRegistrarCandidato();
=======
        //testRegistrarCandidato();
        //testRegistrarReclutador();
        //testRegistrarEmpleo();
        //testRegistrarUsuario();
        testRegistrarCandidato();
        //testRegistrarReclutador();
        //testReporte();
>>>>>>> Stashed changes
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

    public static void testRegistrarUsuario() {
        UsuarioController usuarioController = new UsuarioController();
        Usuario usuario = new Usuario();
        usuario.setNombres("Martin");
        usuario.setApellidos("Torres");
        usuario.setUsername("martin.torres@gmail.com");
        usuario.setPassword("candidato2024");
        usuario.setRol("candidato");
        usuarioController.registrarUsuario(usuario);
    }

    public static void testRegistrarCandidato() {
        CandidatoController candidatoController = new CandidatoController();
        Candidato candidato = new Candidato();

        // Setea los atributos del candidato
        candidato.setNombre("zen");
        candidato.setApellidos("fann");
        candidato.setTipoDocumento(1);
        candidato.setNroDocumento("23456798");
        candidato.setSexo("M");
        candidato.setFecha_nacimiento("06-21-24");
        candidato.setTelefono("223456789");
        candidato.setEstado("activo");
        candidato.setIdUsuario(1);
        candidato.setAptitudes("Java, SQL");
        candidato.setImagenPerfil("profile.jpg");
        candidato.setPathCV("cv.pdf");
        candidato.setPathCertificadoTrabajo("certificado_trabajo.pdf");
        candidato.setPathAntecedentePolicial("antecedente_policial.pdf");

        candidatoController.crearCandidato(candidato);

        System.out.println("Candidato creado exitosamente.");
    }
}
