package core.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MysqlDBService {

    private static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://127.0.0.1/dbpostulantes";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    public Connection conn = null;
    public PreparedStatement stmt = null;

    public MysqlDBService() {
        this.conn = conectar();
        System.out.println("[MysqlDBService]: Conectado a la BD");
    }

    private Connection conectar() {
        try {
            Class.forName(DRIVER_NAME);
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection() {
        return this.conn;
    }

    public void setConnection(Connection myConn) {
        this.conn = myConn;
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

            // System.out.println("[MysqlDBService.cerrarConsulta()]: ");
            try {
                stmt.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void commit() {
        try {
            if (conn != null) {
                conn.commit();
                System.out.println("[MysqlDBService]: Transacción confirmada");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al confirmar la transacción", e);
        }
    }

    public void rollback() {
        try {
            if (conn != null) {
                conn.rollback();
                System.out.println("[MysqlDBService]: Transacción revertida");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al revertir la transacción", e);
        }
    }

    public void setAutoCommit(boolean autoCommit) {
        try {
            if (conn != null) {
                conn.setAutoCommit(autoCommit);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al configurar auto-commit", e);
        }
    }

    public boolean getAutoCommit() {
        try {
            if (conn != null) {
                return conn.getAutoCommit();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener auto-commit", e);
        }
        return true; // Valor por defecto si no se puede obtener
    }

    public ResultSet queryConsultar(String sql, Object[] parametros) {
        try {
            conn = this.getConnection();

            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql);

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            System.out.print("[MysqlDBService.queryConsultar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            ResultSet rs = stmt.executeQuery();

            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int queryInsertar(String sql, Object[] parametros) {
        conn = this.getConnection();
        int newId = -1;

        try {
            int tamano = parametros.length;
            stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            for (int i = 0; i < tamano; i++) {
                stmt.setObject(i + 1, parametros[i]);
            }

            System.out.println("[MysqlDBService.queryInsertar()] QUERY: " + stmt.toString().replace("com.mysql.cj.jdbc.ClientPreparedStatement: ", ""));

            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                newId = rs.getInt(1);
                System.out.println("INSERT: new ID: " + newId);
            }

            rs.close();

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
