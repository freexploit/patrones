package ejercicio_mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario extends Servicio{

    public void insertUsuario(Usuario user) {
        Connection conn = null;
        PreparedStatement  preparedStmt = null;

        try {

            conn = this.getConnection();
            String query = "insert into usuarios (nombre,edad,correo,clave)  values (?, ?, ?, ?)";
            //Crear el Prepared Statement
            preparedStmt = conn.prepareStatement(query);
            preparedStmt.setString(1, user.getNombre());
            preparedStmt.setInt(2, user.getEdad());
            preparedStmt.setString(3, user.getCorreo());
            preparedStmt.setString(4, user.getClave());
            //Ejecutar la prepared statement
            preparedStmt.execute();
        }

        catch (Exception e) {
            System.out.println("Error al procesar usuarios: "+e.getMessage());
            e.printStackTrace();
        } finally {
            super.cerrar(conn,preparedStmt);
        }

    }

    public List<Usuario> getUsuarios () {

        Connection conn = null;
        ResultSet rs = null;
        Statement stmt = null;

        List<Usuario> usuarios = new ArrayList<Usuario>();

        try {
            conn = super.getConnection();
            System.out.println("Creating statement");
            stmt = conn.createStatement();
            String sql = "select * from usuarios";
            rs = stmt.executeQuery(sql);


            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String correo = rs.getString("correo");
                int edad = rs.getInt("edad");
                String clave = rs.getString("clave");
                Usuario usuario = new Usuario(id, nombre, edad,correo,clave);
                usuarios.add(usuario);

            }
        } catch (Exception e) {
            System.out.println("Error al procesar usuarios: "+e.getMessage());
        } finally {
            super.cerrar(conn, rs, stmt);
        }
        return usuarios;
    }


}

