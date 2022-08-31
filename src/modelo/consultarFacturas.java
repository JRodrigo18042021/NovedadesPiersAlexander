
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultarFacturas extends Conexion{
    
     public boolean Registrar( Productos pro){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="INSERT INTO productos (Codigo_Producto,Nombre_Producto,Marca_Producto,Color_Producto,Descripcion_Producto,Precio_Producto,Cantidad_Producto) VALUES(?,?,?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getMarca());
            ps.setString(4, pro.getColor());
            ps.setString(5, pro.getDescripcion());
            ps.setDouble(6, pro.getPrecio());
            ps.setInt(7, pro.getCantidad());
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
     
    public boolean buscar( Productos pro){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="SELECT * FROM productos WHERE Codigo_Producto=?";
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            rs = ps.executeQuery();
            if(rs.next()){
                pro.setCodigo(rs.getString("Codigo_Producto"));
                pro.setNombre(rs.getString("Nombre_Producto"));
                pro.setMarca(rs.getString("Marca_Producto"));
                pro.setColor(rs.getString("Color_Producto"));
                pro.setDescripcion(rs.getString("Descripcion_Producto"));
                pro.setPrecio(rs.getDouble("Precio_Producto"));
                pro.setCantidad(rs.getInt("Cantidad_Producto"));
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
     
    public boolean Modificar( Productos pro){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="UPDATE productos SET Codigo_Producto =?,Nombre_Producto=?,Marca_Producto=?,Color_Producto=?,Descripcion_Producto=?,Precio_Producto=?,Cantidad_Producto=? WHERE Codigo_Producto =?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
            ps.setString(2, pro.getNombre());
            ps.setString(3, pro.getMarca());
            ps.setString(4, pro.getColor());
            ps.setString(5, pro.getDescripcion());
            ps.setDouble(6, pro.getPrecio());
            ps.setInt(7, pro.getCantidad());
            ps.setString(8, pro.getCodigo());
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
     
    public boolean eliminar( Productos pro){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="DELETE FROM empleados WHERE dni_Empleado=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, pro.getCodigo());
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
}
