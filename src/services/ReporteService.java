
package services;

import core.services.ExportService;
import core.services.MysqlDBService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class ReporteService {
    
     public static void ReporteUsuarios() throws IOException {
        try {
            String sql = "select * from usuarios";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
     
    public static void ReportePersonas() throws IOException {
        try {
            String sql = "select * from personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_personas_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteEdad() throws IOException {
        try {
            String sql = "SELECT nombre, apellido, sexo, edad FROM personas";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Edad_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteAptitudes() throws IOException {
        try {
            String sql = "SELECT aptitudes FROM candidatos";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Aptitudes_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteExperiencias() throws IOException {
        try {
            String sql = "SELECT * FROM EXPERIENCIAS_LABORALES";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Experiencias-Laborales_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
    
    public static void ReporteReclutadores() throws IOException {
        try {
            String sql = "select * from reclutadores";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Reclutadores_01");

        } catch (InterruptedException | SQLException | ExecutionException e) {
        }
    }
}
