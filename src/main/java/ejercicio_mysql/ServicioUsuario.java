package ejercicio_mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServicioUsuario extends Servicio{

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

