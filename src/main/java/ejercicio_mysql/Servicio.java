package ejercicio_mysql;

import java.sql.*;

public abstract class Servicio {
    private String USER="root";
    private String PASS="admin";
    private String DB_URL = String.format("jdbc:mysql://localhost/NEW_DB?user=%s&password=%s",USER,PASS);
    private Connection conn = null;

    public Servicio() {

    }

    private void conectar() throws DatabaseException {
        System.out.println("Connecting...");
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch (SQLException e) {
            throw new DatabaseException("Error al connectar");
        }
    }

    protected Connection getConnection() throws DatabaseException {
        this.conectar();
        return this.conn;
    }
    protected void cerrar(Connection conn, ResultSet rs, Statement stmt){
        try {
            if (!rs.isClosed()) {
                rs.close();
            }
            if (!stmt.isClosed()) {
                stmt.close();
            }
            if (!conn.isClosed()) {
                conn.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error al cerrar la conexion");
            e.printStackTrace();
        }
    }

    protected void cerrar(Connection conn, PreparedStatement stmt) {
        try {
            if (!stmt.isClosed()) {
                stmt.close();
            }
            if (!conn.isClosed()) {
                conn.close();
            }
        }
        catch (Exception e) {
            System.out.println("Error al cerrar la conexion");
            e.printStackTrace();
        }
    }
}
