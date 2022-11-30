package conexion;

import modelo.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CRUD_Usuario {

    public static String buscarUser(String nombre){
        Connection con = Conexion.getConexion();

        PreparedStatement ps;
        ResultSet rs;
        boolean encontrado = false;
        Usuarios u = new Usuarios();

        try{
            String sql = "SELECT * FROM usuarios WHERE nombre=?";
            ps = (PreparedStatement) con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next()) {
                encontrado = true;
                u.setPassword(rs.getString("pass"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return u.getPassword();
    }
}
