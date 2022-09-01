
package modelo;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

public class consultarVentas extends Conexion{
    
    public boolean CalcularVenta(Ventas Ven, Productos pro, Empleados empl){
        try {
            buscar(Ven, pro, empl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean AplicarDescuento(Ventas Ven, Productos pro, Empleados empl){
        try {
            buscar(Ven, pro, empl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    public boolean Registrar( Ventas Ven, Productos pro, Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="INSERT INTO ventas (dni_Empleado,RUC_DNI_Cliente,Nombre_Cliente,Direccion_Cliente,Cantidad_Venta,Codigo_Producto,Nombre_Producto) VALUES(?,?,?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empl.getDni());
            ps.setString(2, Ven.getDniCliente());
            ps.setString(3, Ven.getNombreCliente());
            ps.setString(4, Ven.getDireccionCliente());
            ps.setDouble(5, Ven.getVentaTotal());
            ps.setString(6, pro.getCodigo());
            ps.setString(7, pro.getNombre());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }   
    }
     
    public boolean buscar( Ventas Ven, Productos pro, Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="SELECT * FROM ventas WHERE id_Venta =?";
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, Ven.getId());
            rs = ps.executeQuery();
            if(rs.next()){
                empl.setDni(rs.getString("dni_Empleado"));
                Ven.setDniCliente(rs.getString("RUC_DNI_Cliente"));
                Ven.setNombreCliente(rs.getString("Nombre_Cliente"));
                Ven.setDireccionCliente(rs.getString("Direccion_Cliente"));
                Ven.setVentaTotal(rs.getDouble("Cantidad_Venta"));
                pro.setCodigo(rs.getString("Codigo_Producto"));
                pro.setNombre(rs.getString("Nombre_Producto"));
                return true;
            }
            return false;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }   
    }
     
    public boolean Modificar( Ventas Ven, Productos pro, Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="UPDATE ventas SET id_Venta=? dni_Empleado=?,RUC_DNI_Cliente=?,Nombre_Cliente=?,Direccion_Cliente=?,Cantidad_Venta=?,Codigo_Producto=?,Nombre_Producto=? WHERE id_Venta=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, Ven.getId());
            ps.setString(2, empl.getDni());
            ps.setString(3, Ven.getDniCliente());
            ps.setString(4, Ven.getNombreCliente());
            ps.setString(5, Ven.getDireccionCliente());
            ps.setDouble(6, Ven.getVentaTotal());
            ps.setString(7, pro.getCodigo());
            ps.setString(8, pro.getNombre());
            ps.setInt(9, Ven.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
     
    public boolean eliminar( Ventas Ven){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="DELETE FROM ventas WHERE id_Venta=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, Ven.getId());
            ps.execute();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }   
    }
    
    public void mostrarCombo(JComboBox productos){
        String sql = "SELECT Nombre_Producto FROM productos";
        try {
            
            PreparedStatement ps= null;
            ResultSet rs;
            
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {                
                productos.addItem(rs.getString("Nombre_Producto"));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
}
