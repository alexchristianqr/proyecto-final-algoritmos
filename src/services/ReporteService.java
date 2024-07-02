package services;

import core.services.ExportService;
import core.services.MysqlDBService;

public class ReporteService extends BaseService {

    public ReporteService() {
        db = new MysqlDBService();
    }

    /*public static void ReporteUsuarios() throws IOException {
        try {
            String sql = "select * from usuarios";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reporteUsuarios() {
        boolean success = false;

        try {
            String sql = "select * from usuarios";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    /*public static void ReportePersonas() throws IOException {
        try {
            String sql = "select * from personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_personas_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reportePersonas() {
        boolean success = false;

        try {
            String sql = "select * from personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_personas_01");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    /*public static void ReporteEdad() throws IOException {
        try {
            String sql = "SELECT nombre, apellido, sexo, edad FROM personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Edad_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reporteEdad() {
        boolean success = false;

        try {
            String sql = "SELECT nombre, apellido, sexo, edad FROM personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Edad_01");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }
    
    /*public static void ReporteAptitudes() throws IOException {
        try {
            String sql = "SELECT aptitudes FROM candidatos";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Aptitudes_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reporteAptitudes() {
        boolean success = false;

        try {
            String sql = "SELECT aptitudes FROM candidatos";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Aptitudes_01");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    /*public static void ReporteExperiencias() throws IOException {
        try {
            String sql = "SELECT * FROM EXPERIENCIAS_LABORALES";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Experiencias-Laborales_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reporteExperiencias() {
        boolean success = false;

        try {
            String sql = "SELECT * FROM EXPERIENCIAS_LABORALES";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Experiencias-Laborales_01");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }

    /*public static void ReporteReclutadores() throws IOException {
        try {
            String sql = "SELECT p.nombre,p.apellido,r.estado\n" +
                         "FROM reclutadores r\n" +
                         "JOIN personas p ON r.id_persona = p.id;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Reclutadores_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }*/
    public boolean reporteReclutadores() {
        boolean success = false;

        try {
            String sql = "select * from reclutadores";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Reclutadores_01");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return success;
    }
    
    public static void ReporteBloqueados() throws IOException {
        try {
            String sql = "SELECT p.nombre,p.apellido,b.estado,b.fecha_creado FROM blacklist b\n" +
                         "JOIN candidatos c ON b.id_candidato = c.id\n" +
                         "JOIN personas p ON c.id_persona = p.id";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Bloqueados_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
        
    public static void ReportePostulaciones() throws IOException {
        try {
            String sql = "SELECT p.nombre, p.apellido,e.titulo AS empleo_titulo,e.empresa AS empleo_empresa\n" +
                         "FROM postulaciones po\n" +
                         "JOIN candidatos ca ON po.id_candidato = ca.id\n" +
                         "JOIN personas p ON ca.id_persona = p.id\n" +
                         "JOIN empleos e ON po.id_empleo = e.id;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Postulaciones_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
     public static void ReporteEmpleos() throws IOException {
        try {
            String sql = "SELECT p.nombre AS reclutador_nombre,p.apellido AS reclutador_apellido,e.id,e.titulo,e.empresa,e.sueldo,e.modalidad,e.descripcion,e.estado,e.fecha_creado\n" +
                         "FROM empleos e\n" +
                         "JOIN reclutadores r ON e.id_reclutador = r.id\n" +
                         "JOIN personas p ON r.id_persona = p.id;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Empleos_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
     
    public static void ReporteCandidatos() throws IOException {
        try {
            String sql = "SELECT p.nombre,p.apellido,c.*\n" +
                         "FROM candidatos c\n" +
                         "JOIN personas p ON c.id_persona = p.id;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Candidatos_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteEstudiosAcademicos() throws IOException {
        try {
            String sql = "SELECT p.nombre,p.apellido,ea.*\n" +
                         "FROM candidatos_estudios_academicos cea\n" +
                         "JOIN candidatos c ON cea.id_candidato = c.id\n" +
                         "JOIN personas p ON c.id_persona = p.id\n" +
                         "JOIN estudios_academicos ea ON cea.id_estudio_academico = ea.id;;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_EstudiosAcademicos_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteExperienciasLaborales() throws IOException {
        try {
            String sql = "SELECT p.nombre, p.apellido, el.*\n" +
                         "FROM candidatos_experiencias_laborales cel\n" +
                         "JOIN candidatos c ON cel.id_candidato = c.id\n" +
                         "JOIN personas p ON c.id_persona = p.id\n" +
                         "JOIN experiencias_laborales el ON cel.id_experiencia_laboral = el.id;";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_ExperienciasLabores_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
}
