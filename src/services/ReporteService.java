package services;

import core.services.ExportService;
import core.services.MysqlDBService;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

public class ReporteService extends BaseService {

    public ReporteService() {
        db = new MysqlDBService();
    }

    public boolean reporteUsuarios() {
        boolean success = false;

        try {
            String sql = "select * from usuarios";
            db.stmt = db.conn.prepareStatement(sql);
            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");
            success = true;
        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reportePersonas() {
        boolean success = false;

        try {
            String sql = "select * from personas";
            db.stmt = db.conn.prepareStatement(sql);
            success = true;
            ExportService.exportToExcel(db.stmt, "reporte_de_personas_01");

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteEdad() {
        boolean success = false;

        try {
            String sql = "SELECT nombre, apellido, sexo, edad FROM personas";
            db.stmt = db.conn.prepareStatement(sql);
            success = true;
            ExportService.exportToExcel(db.stmt, "reporte_de_Edad_01");

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteAptitudes() {
        boolean success = false;

        try {
            String sql = "SELECT aptitudes FROM candidatos";
            db.stmt = db.conn.prepareStatement(sql);
            success = true;
            ExportService.exportToExcel(db.stmt, "reporte_de_Aptitudes_01");

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteExperiencias() {
        boolean success = false;

        try {
            String sql = "SELECT * FROM EXPERIENCIAS_LABORALES";

            MysqlDBService db = new MysqlDBService();
            db.stmt = db.conn.prepareStatement(sql);

            ExportService.exportToExcel(db.stmt, "reporte_de_Experiencias-Laborales_01");

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteReclutadores() {
        boolean success = false;

        try {
            String sql = "SELECT p.nombre,p.apellido,r.estado FROM reclutadores r JOIN personas p ON r.id_persona = p.id;";
            db.stmt = db.conn.prepareStatement(sql);
            success = true;
            ExportService.exportToExcel(db.stmt, "reporte_de_Reclutadores_01");

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }
}
