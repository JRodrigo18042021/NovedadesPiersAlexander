
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
import modelo.Productos;
import modelo.consultarProductos;
import vista.fmrProductos;

public class controloadorProductos implements ActionListener {
    
    Productos mod;
    consultarProductos modC;
    fmrProductos frm;

    public controloadorProductos(Productos mod, consultarProductos modC, fmrProductos frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
        this.frm.btnSalir.addActionListener(this);
        mostrarProductos();
    }
    
    public void limpiar(){
        frm.txtCodigo.setText(null);
        frm.txtNombre.setText(null);
        frm.txtMarca.setText(null);
        frm.txtColor.setText(null);
        frm.txtDescripcion.setText(null);
        frm.txtPrecio.setText(null);
        frm.txtStock.setText(null);
        frm.txtBuscar.setText(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frm.btnGuardar){
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setMarca(frm.txtMarca.getText());
            mod.setColor(frm.txtColor.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtStock.getText()));
            if(modC.Registrar(mod)){
                JOptionPane.showMessageDialog(null, "GUARDADO CORRRECTAMENTE");
                mostrarProductos();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR GUARDAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnModificar){
            mod.setCodigo(frm.txtCodigo.getText());
            mod.setNombre(frm.txtNombre.getText());
            mod.setMarca(frm.txtMarca.getText());
            mod.setColor(frm.txtColor.getText());
            mod.setDescripcion(frm.txtDescripcion.getText());
            mod.setPrecio(Double.parseDouble(frm.txtPrecio.getText()));
            mod.setCantidad(Integer.parseInt(frm.txtStock.getText()));
            if(modC.Modificar(mod)){
                JOptionPane.showMessageDialog(null, "MODFICDO CORRRECTAMENTE");
                mostrarProductos();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR MODIFICAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnEliminar){
            mod.setCodigo(frm.txtCodigo.getText());
            if(modC.eliminar(mod)){
                JOptionPane.showMessageDialog(null, "ELIMINADO CORRRECTAMENTE");
                mostrarProductos();
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR ELIMINAR");
                limpiar();
            }
        }
        
        if(e.getSource()==frm.btnBuscar){
            mod.setCodigo(frm.txtBuscar.getText());
            if(modC.buscar(mod)){
                frm.txtCodigo.setText(mod.getCodigo());
                frm.txtNombre.setText(mod.getNombre());
                frm.txtMarca.setText(mod.getMarca());
                frm.txtColor.setText(mod.getColor());
                frm.txtDescripcion.setText(mod.getDescripcion());
                frm.txtPrecio.setText(String.valueOf(mod.getPrecio()));
                frm.txtStock.setText(String.valueOf(mod.getCantidad()));
                
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
    
    public void mostrarProductos(){
        try {
            frm.setLocationRelativeTo(null);
            DefaultTableModel modelo = new DefaultTableModel();
            frm.jtEmpleados.setModel(modelo);
            
            PreparedStatement ps=null;
            ResultSet rs;
            
            Conexion conn = new Conexion();
            Connection con =conn.getConexion();
            
            String sql="SELECT * FROM productos";
            
            ps = con.prepareCall(sql);
            rs= ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColumnas = rsMd.getColumnCount();
            
            frm.jtEmpleados.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD,12));
            frm.jtEmpleados.getTableHeader().setOpaque(false);
            frm.jtEmpleados.getTableHeader().setBackground(new Color(33, 33, 33));
            frm.jtEmpleados.getTableHeader().setForeground(new Color(255, 255, 255));
            frm.jtEmpleados.setRowHeight(25);
            
            modelo.addColumn("CODIGO");
            modelo.addColumn("NOMBRE");
            modelo.addColumn("MARCA");
            modelo.addColumn("COLOR");
            modelo.addColumn("DESCRIPCION");
            modelo.addColumn("PRECIO");
            modelo.addColumn("STOCK");
            
            Object[] filas = new Object[7];
            while(rs.next()){

                filas[0] = rs.getObject(1);
                filas[1] = rs.getObject(2);
                filas[2] = rs.getObject(6);
                filas[3] = rs.getObject(5);
                filas[4] = rs.getObject(3);
                filas[5] = rs.getObject(7);
                filas[6] = rs.getObject(4);
                modelo.addRow(filas);
            }   
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    } 
    
}
