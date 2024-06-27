package controllers;

import core.services.ExportService;
import core.services.MysqlDBService;
import core.services.ResponseService;
import core.utils.Util;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import models.Candidato;
import models.Empleo;
import models.EstudioAcademico;
import models.ExperienciaLaboral;
import models.FeedbackInfo;
import models.Postulacion;
import models.Reclutador;
import models.Usuario;
import services.ReporteService;
import views.Login;

public class Main {

    // ANSI escape codes for bold text
    public static final String ANSI_BOLD = "\033[1m";
    public static final String ANSI_RESET = "\033[0m";
    public static Util util = new Util();
    public List<Object[]> ListaModelo;

    // Ejecutar programa, mostrando la vista de Login
    public static void main(String[] args) throws IOException {
        //testViewLogin();
        //testLogin("maria.gonzales@utp.edu.pe", "reclutador2024");
        //testLogin("alex.quispe@gmail.com", "candidato2024");
        //testLogout();
        //testListarPostulaciones();
        //testActualizarPostulacion();
        //testRegistrarPostulacion();
        //testListarEmpleos();
        //testReporteUsuarios();
        //testRegistrarUsuario();
        //testRegistrarCandidato();
        //testRegistrarReclutador();
        //testRegistrarEmpleo();
        //testRegistrarUsuario();
        //testRegistrarCandidato();
        testRegistrarReclutador();
        //testReporte();
        //testRegistrarEstudioAcademico();
        //testRegistrarExperienciaLaboral();
        //testRegistrarFeedbackPersonalizado();
    }

    public static void testViewLogin() {
        Login login = new Login();
        util.centerOnScreen(login, true);
        login.setVisible(true);
    }

    public static void testLogin(String username, String pwd) {
        UsuarioController usuarioController = new UsuarioController();

        ResponseService<Usuario> response = usuarioController.login(username, pwd);

        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testLogout() {
        UsuarioController usuarioController = new UsuarioController();

        ResponseService<Boolean> response = usuarioController.logout();

        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testRegistrarPostulacion() {
        // INICIO DE SESION DE USUARIO CANDIDATO
        testLogin("alex.quispe@gmail.com", "candidato2024");

        PostulacionController postulacionController = new PostulacionController();

        // CREAR INSTANCIA
        Postulacion postulacion = new Postulacion();
        postulacion.setIdCandidato(1);
        postulacion.setIdEmpleo(1);
        postulacion.setEstado("postulado");

        // ACCION POSTULAR A UN EMPLEO
        ResponseService<Boolean> response = postulacionController.registrarPostulacion(postulacion);

        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testRegistrarFeedbackPersonalizado() {
        // INICIO DE SESION
        testLogin("alex.quispe@gmail.com", "candidato2024");

        PostulacionController postulacionController = new PostulacionController();

        // CREAR INSTANCIA
        Postulacion postulacion = new Postulacion();
        postulacion.setIdPostulacion(1);

        // CREAR INSTANCIA
        FeedbackInfo feedbackInfo = new FeedbackInfo();
        feedbackInfo.setPuesto("Desarrollador de Software");
        feedbackInfo.setNombreCandidato("Juan Pérez");
        feedbackInfo.setFortalezas("Trabajo en equipo, creatividad");
        feedbackInfo.setMejoras("Gestión del tiempo");
        feedbackInfo.setAdicional("Gran disposición para aprender");
        feedbackInfo.setNombreReclutador("María Gómez");
        feedbackInfo.setPuestoReclutador("Gerente de Recursos Humanos");
        feedbackInfo.setEmpresaReclutador("Tech Solutions");
        feedbackInfo.setEmailReclutador("maria.gomez@techsolutions.com");
        feedbackInfo.setTelefonoReclutador("123-456-7890");

        // ACCION ACTUALIZAR POSTULACION POR EL CAMPO "ESTADO"
        ResponseService<Boolean> response = postulacionController.registrarFeedbackPersonalizado(postulacion, feedbackInfo);

        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testListarPostulaciones() {
        // INICIO DE SESION DE USUARIO CANDIDATO
        testLogin("alex.quispe@gmail.com", "candidato2024");

        PostulacionController postulacionController = new PostulacionController();

        // MOSTRAR POSTULACIONES
        ResponseService<List<Object[]>> response = postulacionController.listarPostulaciones("postulado");
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testListarEmpleos() {

        // INICIO DE SESION DE USUARIO RECLUTADOR
        testLogin("maria.gonzales@utp.edu.pe", "reclutador2024");

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

        // Candidato:
        /*
        usuario.setNombres("Martin");
        usuario.setApellidos("Torres");
        usuario.setUsername("martin.torres@gmail.com");
        usuario.setPassword("candidato2024");
        usuario.setRol("candidato");
         */
        // Reclutador
        usuario.setNombres("Deysi");
        usuario.setApellidos("Barrios");
        usuario.setUsername("deysi.barrios@gmail.com");
        usuario.setPassword("reclutador2024");
        usuario.setRol("reclutador");

        ResponseService<String> response = usuarioController.registrarUsuario(usuario);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testRegistrarCandidato() {
        CandidatoController candidatoController = new CandidatoController();

        // Setea los atributos del candidato
        Candidato candidato = new Candidato();
        candidato.setNombre("John");
        candidato.setApellidos("Doe");
        candidato.setTipoDocumento(1);
        candidato.setNroDocumento("23456789");
        candidato.setSexo("M");
        candidato.setEdad("20");
        candidato.setTelefono("123456789");
        candidato.setEstado("activo");
        candidato.setIdUsuario(1);
        candidato.setAptitudes("Java, SQL");
        candidato.setImagenPerfil("profile.jpg");
        candidato.setPathCV("cv.pdf");
        candidato.setPathCertificadoTrabajo("certificado_trabajo.pdf");
        candidato.setPathAntecedentePolicial("antecedente_policial.pdf");

        candidatoController.registrarCandidato(candidato);

        System.out.println("Candidato creado exitosamente.");
    }

    public static void testRegistrarEmpleo() {
        EmpleoController empleoController = new EmpleoController();
        Empleo empleo = new Empleo();

        // Setea los atributos del empleo
        empleo.setIdReclutador(1); // Asume un ID de reclutador, ajustar según sea necesario
        empleo.setTitulo("Desarrollador Java");
        empleo.setEmpresa("Tech Solutions");
        empleo.setSueldo("4500.00");
        empleo.setModalidad("remoto");
        empleo.setDescripcion("Responsable del desarrollo de aplicaciones Java");
        empleo.setEstado("activo");

        ResponseService<String> response = empleoController.registrarEmpleo(empleo);

        System.out.println("Success: " + response.isSuccess());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testRegistrarReclutador() {
        ReclutadorController reclutadorController = new ReclutadorController();
        Reclutador reclutador = new Reclutador();

        reclutador.setNombre("Kevin");
        reclutador.setApellidos("Lucca");
        reclutador.setTipoDocumento(1);
        reclutador.setNroDocumento("78521630");
        reclutador.setSexo("M");
        reclutador.setEdad("23");
        reclutador.setTelefono("848514956");
        reclutador.setEstado("activo");
        //reclutador.setFechaNacimiento("");
        reclutador.setEstadoCivil("soltero");
        //reclutador.setIdUsuario(8);

        ResponseService<Boolean> response = reclutadorController.registrarReclutador(reclutador);
        System.out.println("success: "+response.isSuccess());
        System.out.println("Mensaje: "+response.getMessage());
        System.out.println("Resultado: "+response.getResult());
    }

    public static void testReporte() throws IOException {
        try {
            ReporteService.ReporteEdad();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testRegistrarEstudioAcademico() {
        CandidatoController candidatoController = new CandidatoController();

        EstudioAcademico estudioAcademico = new EstudioAcademico();
        estudioAcademico.setIdCandidato(1);
        estudioAcademico.setTitulo("IDAT");
        estudioAcademico.setDescripcion("InstitutoTecnologico Superior");
        estudioAcademico.setFechaInicio("01/2010");
        estudioAcademico.setFechaFin("06/2013");
        estudioAcademico.setGrado("tecnico");
        estudioAcademico.setOrden(1);

        ResponseService<Boolean> response = candidatoController.registrarEstudioAcademico(estudioAcademico);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }

    public static void testRegistrarExperienciaLaboral() {
        CandidatoController candidatoController = new CandidatoController();

        ExperienciaLaboral experienciaLaboral = new ExperienciaLaboral();
        experienciaLaboral.setIdCandidato(2);
        experienciaLaboral.setTitulo("Desarrollador Fontend (NodeJs - AWS)");
        experienciaLaboral.setDescripcion("Diseñar, desarrollar e implementar soluciones backend utilizando Node.js.");
        experienciaLaboral.setEmpresa("INTERBANK SAC");
        experienciaLaboral.setFechaInicio("01/2022");
        experienciaLaboral.setFechaFin("06/2024");
        experienciaLaboral.setOrden(1);

        ResponseService<Boolean> response = candidatoController.registrarExperienciaLaboral(experienciaLaboral);
        System.out.println("Success: " + response.isSuccess());
        System.out.println("Mensaje: " + response.getMessage());
        System.out.println("Resultado: " + response.getResult());
    }
}
