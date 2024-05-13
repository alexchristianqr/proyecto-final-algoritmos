package core.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDBService {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/dbhotel";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private Connection conn = null;
    private PreparedStatement stmt = null;

    public MysqlDBService() {
        this.conn = conectar();
    }

    private Connection conectar() {
        try {
            System.out.println("[MysqlDBService.conectar()]: ");

            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void desconectar() {
        if (conn != null) {

            System.out.println("[MysqlDBService.desconectar()]: ");

            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void cerrarConsulta() {
        if (stmt != null) {

            System.out.println("[MysqlDBService.cerrarConsulta()]: ");

            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public ResultSet queryConsultar(String sql, Object[] parametros) {
        try {
            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql);

            System.out.println("[MysqlDBService.queryConsultar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }
            ResultSet rs = stmt.executeQuery();

            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int queryInsertar(String sql, Object[] parametros) {
        try {
            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            System.out.println("[MysqlDBService.queryInsertar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            int newId = -1;

            if (rs.next()) {
                newId = rs.getInt(1);
                System.out.println("INSERT: new ID: " + newId);
            }

            return newId;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int queryActualizar(String sql, Object[] parametros) {
        try {
            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql, tamano);

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            System.out.println("[MysqlDBService.queryActualizar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int queryEliminar(String sql, Object[] parametros) {
        try {
            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            System.out.println("[MysqlDBService.queryEliminar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            return stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
