package conexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import modelo.Alertas;
import modelo.Perros;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CRUD_Perro {

    //METODO DE INSERCION DE PERROS
    public static void insertarPerro(Perros p1) {
        String sqlPerro;
        PreparedStatement ps1;
        Connection con = Conexion.getConexion();

        //Introduce en la tabla coche
        sqlPerro = "insert into perro(nombre,raza,peso) values(?,?,?)";
        try
        {
            ps1 = con.prepareStatement(sqlPerro);
            ps1.setString(1, p1.getNombre());
            ps1.setString(2, p1.getRaza());
            ps1.setInt(3, p1.getPeso());

            ps1.executeUpdate();

        } catch (SQLException ex)
        {
            Logger.getLogger(CRUD_Perro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //METODO PARA DESPUES LLENAR EL TABLEVIEW
    public static List<Perros> getPerros() {
        Connection con=Conexion.getConexion();
        List <Perros> listaPerros = new ArrayList<>();


        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from perro");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Perros perro = new Perros();
                perro.setId(rs.getInt(1));
                perro.setNombre(rs.getString(2));
                perro.setRaza(rs.getString(3));
                perro.setPeso(rs.getInt(4));

                listaPerros.add(perro);
            }
        } catch (SQLException ex) {
            Alertas.crearAlertaError("Fallor de insercion");
        }

        return listaPerros;
    }
    public static List<Perros> getPerrosFiltro(String raza) {
        Connection con=Conexion.getConexion();
        List <Perros> listaPerros = new ArrayList<>();


        try {
            PreparedStatement ps = con.prepareStatement("SELECT * from perro WHERE raza LIKE '%"+raza+"%'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                Perros perro = new Perros();
                perro.setId(rs.getInt(1));
                perro.setNombre(rs.getString(2));
                perro.setRaza(rs.getString(3));
                perro.setPeso(rs.getInt(4));

                listaPerros.add(perro);
            }
        } catch (SQLException ex) {
            Alertas.crearAlertaError("No encontrado...");
        }

        return listaPerros;
    }
    //Aqui hacemos una transaccion
    public static void modificarPerro(Perros p1) {
        Connection con = Conexion.getConexion();

        try {
            con.setAutoCommit(false);
            String valueNombre = p1.getNombre();
            String valueRaza = p1.getRaza();
            int valuePeso = p1.getPeso();
            int id = p1.getId();

            String sentenciaSql = "UPDATE perro SET nombre = ?,raza = ?,peso=? WHERE id=?";
            PreparedStatement sentencia = null;
            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setString(1, valueNombre);
            sentencia.setString(2, valueRaza);
            sentencia.setInt(3, valuePeso);
            sentencia.setInt(4, id);
            sentencia.executeUpdate();
            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
    public static void eliminarPerro(int id){
        Connection con = Conexion.getConexion();

        String sentenciaSql = "DELETE  from  perro WHERE id = ?";
        PreparedStatement sentencia = null;
        try {
            con.setAutoCommit(false);
            sentencia = con.prepareStatement(sentenciaSql);
            sentencia.setInt(1, id);
            sentencia.execute();

            con.commit();
            con.setAutoCommit(true);
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
