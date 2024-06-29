
package services;

import models.Archivo;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ArchivoService {

    private Connection conn;

    public ArchivoService() {
        // Inicializar la conexión a la base de datos
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_base_de_datos", "usuario", "contraseña");
        } catch (SQLException e) {
        }
    }

    /**
     *
     * @param archivo
     */
    public void subirArchivo(Archivo archivo) {
        String sql = "INSERT INTO archivos (id_candidato, nombre, path, tipo, fecha_subida) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, archivo.getIdCandidato());
            pstmt.setString(2, archivo.getNombre());
            pstmt.setString(3, archivo.getPath());
            pstmt.setString(4, archivo.getTipo());
            pstmt.setString(5, archivo.getFechaSubida());
            pstmt.executeUpdate();
        } catch (SQLException e) {
        }
    }

    public List<Archivo> obtenerArchivosPorCandidato(int idCandidato) {
        List<Archivo> archivos = new ArrayList<>();
        String sql = "SELECT * FROM archivos WHERE id_candidato = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, idCandidato);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Archivo archivo = new Archivo();
                archivo.setId(rs.getInt("id"));
                archivo.setIdCandidato(rs.getInt("id_candidato"));
                archivo.setNombre(rs.getString("nombre"));
                archivo.setPath(rs.getString("path"));
                archivo.setTipo(rs.getString("tipo"));
                archivo.setFechaSubida(rs.getString("fecha_subida"));
                archivos.add(archivo);
            }
        } catch (SQLException e) {
        }
        return archivos;
    }
}
