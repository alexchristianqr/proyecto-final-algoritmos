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
            String sql = "WITH UsuariosDetallados AS (SELECT u.id, u.nombres, u.apellidos, u.rol, u.username, u.estado, COUNT(DISTINCT c.id) AS total_postulaciones, COUNT(DISTINCT r.id) AS total_reclutajes FROM usuarios u LEFT JOIN candidatos c ON u.id = c.id_usuario LEFT JOIN reclutadores r ON u.id = r.id_usuario GROUP BY u.id, u.nombres, u.apellidos, u.rol, u.username, u.estado) SELECT ud.id, ud.nombres, ud.apellidos, ud.rol, ud.username, ud.estado, COALESCE(ud.total_postulaciones, 0) AS total_postulaciones, COALESCE(ud.total_reclutajes, 0) AS total_reclutajes FROM UsuariosDetallados ud;";
            db.stmt = db.conn.prepareStatement(sql);
            ExportService.exportToExcel(db.stmt, "reporte_de_usuarios_01");
            success = true;
        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteEdad() {
        boolean success = false;

        try {
            String sql = "WITH EdadPromedio AS (SELECT ROUND(AVG(CAST(edad AS DECIMAL)), 2) AS edad_promedio FROM personas) SELECT p.id, p.nombre, p.apellido, p.tipo_documento, p.nrodocumento, p.sexo, p.edad, ep.edad_promedio FROM personas p, EdadPromedio ep;";
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
            String sql = "WITH EmpleosContratados AS (SELECT r.id, p.nombre AS nombre_reclutador, p.apellido AS apellido_reclutador, COUNT(DISTINCT e.id) AS total_empleos_contratados, SUM(CASE WHEN e.estado = 'finalizado' THEN 1 ELSE 0 END) AS total_empleos_finalizados FROM reclutadores r JOIN personas p ON r.id_persona = p.id LEFT JOIN empleos e ON r.id = e.id_reclutador GROUP BY r.id, p.nombre, p.apellido) SELECT ec.id, ec.nombre_reclutador, ec.apellido_reclutador, ec.total_empleos_contratados, ec.total_empleos_finalizados, CASE WHEN ec.total_empleos_contratados > 0 THEN ROUND((ec.total_empleos_finalizados / ec.total_empleos_contratados) * 100, 2) ELSE 0 END AS porcentaje_finalizados FROM EmpleosContratados ec;";
            db.stmt = db.conn.prepareStatement(sql);
            ExportService.exportToExcel(db.stmt, "reporte_de_Aptitudes_01");
            success = true;

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteExperiencias() {
        boolean success = false;

        try {
            String sql = "WITH ExperienciaLaboralCandidatos AS (SELECT c.id AS candidato_id, AVG(DATEDIFF(el.fecha_fin, el.fecha_inicio)) AS experiencia_promedio_anios FROM candidatos c JOIN candidatos_experiencias_laborales cel ON c.id = cel.id_candidato JOIN experiencias_laborales el ON cel.id_experiencia_laboral = el.id GROUP BY c.id) SELECT p.nombre, p.apellido, elc.experiencia_promedio_anios FROM personas p JOIN candidatos c ON p.id = c.id_persona JOIN ExperienciaLaboralCandidatos elc ON c.id = elc.candidato_id;";
            db.stmt = db.conn.prepareStatement(sql);
            ExportService.exportToExcel(db.stmt, "reporte_de_Experiencias-Laborales_01");
            success = true;

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }

    public boolean reporteReclutadores() {
        boolean success = false;

        try {
            String sql = "WITH SalariosReclutador AS (SELECT r.id AS reclutador_id, p.nombre AS reclutador_nombre, AVG(e.sueldo) AS salario_promedio FROM reclutadores r JOIN personas p ON r.id_persona = p.id JOIN empleos e ON r.id = e.id_reclutador GROUP BY r.id, p.nombre), SalarioPromedioGlobal AS (SELECT AVG(sueldo) AS salario_promedio_global FROM empleos) SELECT sr.reclutador_id, sr.reclutador_nombre, sr.salario_promedio, spg.salario_promedio_global FROM SalariosReclutador sr, SalarioPromedioGlobal spg ORDER BY sr.salario_promedio DESC;";
            db.stmt = db.conn.prepareStatement(sql);
            ExportService.exportToExcel(db.stmt, "reporte_de_Reclutadores_01");
            success = true;

        } catch (IOException | InterruptedException | SQLException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return success;
    }
}
