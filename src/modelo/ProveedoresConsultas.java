
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Zarcrox
 */
public class ProveedoresConsultas extends Conexion{
    
    public boolean registar (Proveedores prov){
        
        PreparedStatement ps=null;
        Connection con = getConexion();
        String sql = "INSERT INTO proveedores (RUC_Proveedor,Nombre_Proveedor,Descripcion_Proveedor,Telefono_Proveedor,Correo_Proveedor,Ciudad_Proveedor,Direccion_Empleado,dni_Empleado) VALUES(?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDescripcion());
            ps.setString(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getCiudad());
            ps.setString(7, prov.getDireccion());
            ps.setString(8, prov.getDniEmpleado());
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
    
    public boolean buscar(Proveedores prov){
        
        PreparedStatement ps=null;
        Connection con = getConexion();
        String sql = "select * from  proveedores where RUC_Proveedor like ?";
        ResultSet rs=null;
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            rs = ps.executeQuery();
            if (rs.next()) {
                prov.setRuc(rs.getString("RUC_Proveedor"));
                prov.setNombre(rs.getString("Nombre_Proveedor"));
                prov.setDescripcion(rs.getString("Descripcion_Proveedor"));
                prov.setTelefono(rs.getString("Telefono_Proveedor"));
                prov.setCorreo(rs.getString("Correo_Proveedor"));
                prov.setCiudad(rs.getString("Ciudad_Proveedor"));
                prov.setDireccion(rs.getString("Direccion_Empleado"));
                prov.setDniEmpleado(rs.getString("dni_Empleado"));
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
    
    public boolean modificar (Proveedores prov){
        PreparedStatement ps=null;
        Connection con = getConexion();
        String sql = "UPDATE proveedores SET RUC_Proveedor=?,Nombre_Proveedor=?,Descripcion_Proveedor=?,Telefono_Proveedor=?,Correo_Proveedor=?,Ciudad_Proveedor=?,Direccion_Empleado=?,dni_Empleado=? WHERE RUC_Proveedor=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
            ps.setString(2, prov.getNombre());
            ps.setString(3, prov.getDescripcion());
            ps.setString(4, prov.getTelefono());
            ps.setString(5, prov.getCorreo());
            ps.setString(6, prov.getCiudad());
            ps.setString(7, prov.getDireccion());
            ps.setString(8, prov.getDniEmpleado());
            ps.setString(9, prov.getRuc());
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
    public boolean eliminar(Proveedores prov){
        PreparedStatement ps=null;
        Connection con = getConexion();
        String sql= "DELETE FROM proveedores WHERE RUC_Proveedor =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, prov.getRuc());
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
