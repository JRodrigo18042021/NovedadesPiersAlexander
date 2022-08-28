
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class consultarEmpleados extends Conexion{
    
     public boolean Registrar( Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="INSERT INTO empleados (dni_Empleado,Nombre_Empleado,Apellidos_Empleado,Telefono_Empleado,Direccion_Empleado,Correo_Empleado,Ciudad_Empleado) VALUES(?,?,?,?,?,?,?)";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empl.getDni());
            ps.setString(2, empl.getNombres());
            ps.setString(3, empl.getApellidos());
            ps.setString(4, empl.getTelefono());
            ps.setString(5, empl.getDireccion());
            ps.setString(6, empl.getCorreo());
            ps.setString(7, empl.getCiudad());
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
     
    public boolean buscar( Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="SELECT * FROM empleados WHERE dni_Empleado=?";
        ResultSet rs=null;
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empl.getDni());
            rs = ps.executeQuery();
            if(rs.next()){
                empl.setDni(rs.getString("dni_Empleado"));
                empl.setNombres(rs.getString("Nombre_Empleado"));
                empl.setApellidos(rs.getString("Apellidos_Empleado"));
                empl.setTelefono(rs.getString("Telefono_Empleado"));
                empl.setDireccion(rs.getString("Direccion_Empleado"));
                empl.setCorreo(rs.getString("Correo_Empleado"));
                empl.setCiudad(rs.getString("Ciudad_Empleado"));
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
     
    public boolean Modificar( Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="UPDATE empleados SET dni_Empleado=?,Nombre_Empleado=?,Apellidos_Empleado=?,Telefono_Empleado=?,Direccion_Empleado=?,Correo_Empleado=?,Ciudad_Empleado=? WHERE dni_Empleado=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empl.getDni());
            ps.setString(2, empl.getNombres());
            ps.setString(3, empl.getApellidos());
            ps.setString(4, empl.getTelefono());
            ps.setString(5, empl.getDireccion());
            ps.setString(6, empl.getCorreo());
            ps.setString(7, empl.getCiudad());
            ps.setString(8, empl.getDni());
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
     
    public boolean eliminar( Empleados empl){
        
        PreparedStatement ps=null;
        Connection con =getConexion();
        String sql="DELETE FROM empleados WHERE dni_Empleado=?";
        
        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, empl.getDni());
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
