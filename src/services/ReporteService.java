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
            String sql = "select * from reclutadores";

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
}
