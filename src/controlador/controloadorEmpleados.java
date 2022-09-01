
package controlador;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Conexion;
import modelo.Empleados;
import modelo.consultarEmpleados;
import vista.frmEmpleados;

public class controloadorEmpleados implements ActionListener {
    
    Empleados mod;
    consultarEmpleados modC;
    frmEmpleados frm;

    public controloadorEmpleados(Empleados mod, consultarEmpleados modC, frmEmpleados frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);
        mostrarEmpleados();
    }
  
    
    public void limpiar(){
        frm.txtDni.setText(null);
        frm.txtNombre.setText(null);
        frm.txtApellido.setText(null);
        frm.txtTelefono.setText(null);
        frm.txtDireccion.setText(null);
        frm.txtCorreo.setText(null);
        frm.txtCiudad.setText(null);
        frm.txtBuscar.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frm.btnGuardar){
            mod.setDni(frm.txtDni.getText());
            mod.setNombres(frm.txtNombre.getText());
            mod.setApellidos(frm.txtApellido.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setDireccion(frm.txtDireccion.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            mod.setCiudad(frm.txtCiudad.getText());
            if(modC.Registrar(mod)){
                JOptionPane.showMessageDialog(null, "GUARDADO CORRRECTAMENTE");
                mostrarEmpleados();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR GUARDAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnModificar){
            mod.setDni(frm.txtDni.getText());
            mod.setNombres(frm.txtNombre.getText());
            mod.setApellidos(frm.txtApellido.getText());
            mod.setTelefono(frm.txtTelefono.getText());
            mod.setDireccion(frm.txtDireccion.getText());
            mod.setCorreo(frm.txtCorreo.getText());
            mod.setCiudad(frm.txtCiudad.getText());
            if(modC.Modificar(mod)){
                JOptionPane.showMessageDialog(null, "MODFICDO CORRRECTAMENTE");
                mostrarEmpleados();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR MODIFICAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnEliminar){
            mod.setDni(frm.txtDni.getText());
            if(modC.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "ELIMINADO CORRRECTAMENTE");
                mostrarEmpleados();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR ELIMINAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnBuscar){
            mod.setDni(frm.txtBuscar.getText());
            if(modC.buscar(mod)){
                frm.txtDni.setText(mod.getDni());
                frm.txtNombre.setText(mod.getNombres());
                frm.txtApellido.setText(mod.getApellidos());
                frm.txtTelefono.setText(mod.getTelefono());
                frm.txtDireccion.setText(mod.getDireccion());
                frm.txtCorreo.setText(mod.getCorreo());
                frm.txtCiudad.setText(mod.getCiudad());
            }else{
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRARON RESULTADOS");
                limpiar();
            }
        }
        if(e.getSource()==frm.btnLimpiar){
            limpiar();
        }
        
        if(e.getSource()==frm.btnSalir){
            frm.dispose();
        }
    }
    
    void mostrarEmpleados(){
        frm.setLocationRelativeTo(null);
        try {
            DefaultTableModel modelo = new DefaultTableModel();
            frm.jtEmpleados.setModel(modelo);
            
            PreparedStatement ps=null;
            ResultSet rs;
            
            Conexion conn = new Conexion();
            Connection con =conn.getConexion();
            
            String sql="SELECT * FROM empleados";
            
            ps = con.prepareCall(sql);
            rs= ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            frm.jtEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
            frm.jtEmpleados.getTableHeader().setOpaque(false);
            frm.jtEmpleados.getTableHeader().setBackground(new Color(33, 33, 33));
            frm.jtEmpleados.getTableHeader().setForeground(new Color(255, 255, 255));
            frm.jtEmpleados.setRowHeight(25);
            
            modelo.addColumn("DNI");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("APELLIDO");
            modelo.addColumn("TELEFONO");
            modelo.addColumn("DIRECCION");
            modelo.addColumn("CORREO");
            modelo.addColumn("CIUDAD");
            
            
            while(rs.next()){
                Object[] filas = new Object[cantidadColumnas];
                for (int i = 0; i < cantidadColumnas; i++) {
                    filas[i] = rs.getObject(i + 1);
                }
                modelo.addRow(filas);
            }   
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
}
