
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
import modelo.Proveedores;
import modelo.ProveedoresConsultas;
import vista.frmProveedores;
import vista.fmrLogin;

public class controladorProveedor implements ActionListener{
    Proveedores modPv;
    ProveedoresConsultas provC;
    frmProveedores frm;
    fmrLogin EMPLEADO;
    

    public controladorProveedor(Proveedores prov, ProveedoresConsultas provC, frmProveedores frm) {
        this.modPv = prov;
        this.provC = provC;
        this.frm = frm;
        this.frm.btnRegistarProv.addActionListener(this);
        this.frm.btnActualizarProv.addActionListener(this);
        this.frm.btnEliminarProv.addActionListener(this);
        this.frm.btnConsultarProv.addActionListener(this);
        this.frm.btnRegresar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        mostrarProveedores();
    }
    
    public void limpiar(){
        frm.txtbuscarProveedor.setText(null);
        frm.txtruc.setText(null);
        frm.txtnombre.setText(null);
        frm.txtdescripcion.setText(null);
        frm.txttelefono.setText(null);
        frm.txtcorreo.setText(null);
        frm.txtciudad.setText(null);
        frm.txtdireccion.setText(null);
        frm.txtrucEliminado.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frm.btnRegistarProv){
            modPv.setRuc(frm.txtruc.getText());
            modPv.setNombre(frm.txtnombre.getText());
            modPv.setDescripcion(frm.txtdescripcion.getText());
            modPv.setTelefono(frm.txttelefono.getText());
            modPv.setCorreo(frm.txtcorreo.getText());
            modPv.setCiudad(frm.txtciudad.getText());
            modPv.setDireccion(frm.txtdireccion.getText());
            modPv.setDniEmpleado(frm.txtdniEmp.getText());
            if (provC.registar(modPv)) {
                JOptionPane.showMessageDialog(null, "REGISTRADO CORRECTAMENTE");
                mostrarProveedores();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL REGISTRAR");
                limpiar();
            }
        }
        if (e.getSource()==frm.btnActualizarProv) {
            modPv.setRuc(frm.txtruc.getText());
            modPv.setNombre(frm.txtnombre.getText());
            modPv.setDescripcion(frm.txtdescripcion.getText());
            modPv.setTelefono(frm.txttelefono.getText());
            modPv.setCorreo(frm.txtcorreo.getText());
            modPv.setCiudad(frm.txtciudad.getText());
            modPv.setDireccion(frm.txtdireccion.getText());
            modPv.setDniEmpleado(frm.txtdniEmp.getText());
            if (provC.modificar(modPv)) {
                JOptionPane.showMessageDialog(null, "ACTUALIZACION COMPTETA");
                mostrarProveedores();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL ACTUALIZAR");
                limpiar();
            }
        }
        
        if (e.getSource()==frm.btnBuscarProv) {
            modPv.setRuc(frm.txtbuscarProveedor.getText());
             if (provC.buscar(modPv)) {
                 frm.txtruc.setText(modPv.getRuc());
                 frm.txtnombre.setText(modPv.getNombre());
                 frm.txtdescripcion.setText(modPv.getDescripcion());
                 frm.txttelefono.setText(modPv.getTelefono());
                 frm.txtcorreo.setText(modPv.getCorreo());
                 frm.txtciudad.setText(modPv.getCiudad());
                 frm.txtdireccion.setText(modPv.getDireccion());
                 frm.txtdniEmp.setText(modPv.getDniEmpleado());  
                JOptionPane.showMessageDialog(null, "BUSQUEDA COMPLETA");
            }else{
                JOptionPane.showMessageDialog(null, "NO SE ENCONTRO PROVEEDORES");
                limpiar();
            }
        }
        
        if (e.getSource()==frm.btnEliminarProv) {
            modPv.setRuc(frm.txtrucEliminado.getText());
            if (provC.eliminar(modPv)) {
                mostrarProveedores();
                JOptionPane.showMessageDialog(null, "ELIMINADO CORRECTAMENTE");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
                limpiar();
            }
        }
        
        if (e.getSource()==frm.btnRegresar) {
            frm.dispose();
        }
        
        if (e.getSource()==frm.btnLimpiar) {
            limpiar();
        }
    }
    
    public void mostrarProveedores(){
        frm.setLocationRelativeTo(null);
        try {
            DefaultTableModel modeloProv = new DefaultTableModel();
            frm.tbproveedor.setModel(modeloProv);
            
            PreparedStatement ps= null;
            ResultSet rs;
            
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            
            String sql = "SELECT * FROM proveedores";
            
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColum = rsMd.getColumnCount();
            
            frm.tbproveedor.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            frm.tbproveedor.getTableHeader().setOpaque(false);
            frm.tbproveedor.getTableHeader().setBackground(new Color(33, 33, 33));
            frm.tbproveedor.getTableHeader().setForeground(new Color(225, 255, 255));
            frm.tbproveedor.setRowHeight(25);
            
            modeloProv.addColumn("RUC");
            modeloProv.addColumn("NOMBRE");
            modeloProv.addColumn("DESCRIPCION");
            modeloProv.addColumn("TELEFONO");
            modeloProv.addColumn("CORREO");
            modeloProv.addColumn("CIUDAD");
            modeloProv.addColumn("DIRECCION");
            modeloProv.addColumn("DNI_EMPLEADO");
            
            while (rs.next()) {                
                Object[] filas = new Object[cantidadColum];
                for (int i = 0; i < cantidadColum; i++) {
                    filas[i] = rs.getObject(i+1);
                }
                modeloProv.addRow(filas);
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    
}
