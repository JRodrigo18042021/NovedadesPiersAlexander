
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
import modelo.Empleados;
import modelo.consultarEmpleados;
import modelo.Ventas;
import modelo.consultarVentas;
import vista.fmrProductos;
import vista.fmrVentas;

public class controloadorVentas implements ActionListener {
    
    Productos modPro;
    consultarProductos modProC;
    Empleados modEmpl;
    consultarEmpleados modEmplC;
    Ventas modVent;
    consultarVentas modVentC;
    fmrVentas frmVent ;
    fmrVentas frmVent1 = new fmrVentas();

    public controloadorVentas(Productos modPro, consultarProductos modProC, Empleados modEmpl, consultarEmpleados modEmplC, Ventas modVent, consultarVentas modVentC, fmrVentas frmVent) {
        this.modPro = modPro;
        this.modProC = modProC;
        this.modEmpl = modEmpl;
        this.modEmplC = modEmplC;
        this.modVent = modVent;
        this.modVentC = modVentC;
        this.frmVent = frmVent;
        
        this.frmVent.btnVentasCalcular.addActionListener(this);
        this.frmVent.btnVentasAplicarDescuento.addActionListener(this);
        this.frmVent.btnVentasConfirmar.addActionListener(this);
        this.frmVent.btnVentasLimpiar.addActionListener(this);
        this.frmVent.btnImprimirComprobante.addActionListener(this);
        this.frmVent.btnBuscar.addActionListener(this);
        this.frmVent.btnModificarVenta.addActionListener(this);
        this.frmVent.btnEliminarVenta.addActionListener(this);
        this.frmVent.btnLimpiarCasilleroBusqueda.addActionListener(this);
        this.frmVent.btnSalir.addActionListener(this);
        mostrarVentas();
        
        
    }

    public controloadorVentas() {
        modVentC.mostrarCombo(frmVent1.cbVentasProducto);
    }
    
    
    
    public void limpiarVentas(){
        frmVent.txtVentasEmpleado.setText(null);
        frmVent.txtVentasCantidad.setText(null);
        frmVent.txtVentasCantidad.setText(null);
        frmVent.txtVentasDescuento.setText(null);
        frmVent.txtVentasTotal.setText(null);
        frmVent.txtClienteDni.setText(null);
        frmVent.txtClienteNombre.setText(null);
        frmVent.txtClienteDireccion.setText(null);
    }
    
    public void limpiarBusqueda(){
        frmVent.txtBuscarDniCliente.setText(null);
        frmVent.txtBuscarNombreCliente.setText(null);
        frmVent.txtBuscarDireccionCliente.setText(null);
        frmVent.txtBuscarCodidoVenta.setText(null);
        frmVent.txtBuscarNombreEmpleado.setText(null);
        frmVent.txtVentasTotal.setText(null);  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==frmVent.btnVentasCalcular){
            modVent.setVentaCalculada((Double.parseDouble(frmVent.txtVentasCantidad.getText()))*modPro.getPrecio());
            if(modVentC.CalcularVenta(modVent, modPro, modEmpl)){
                JOptionPane.showMessageDialog(null, "Calculado Correctamente");
                frmVent.txtVentasCalculada.setText(String.valueOf(modVent.getVentaCalculada()));
            }else{
                JOptionPane.showMessageDialog(null, "Erros al Calcular");
            }
        }
        
        if(e.getSource()==frmVent.btnVentasAplicarDescuento){
            modVent.setVentaTotal((Double.parseDouble(frmVent.txtVentasCalculada.getText()))-(Double.parseDouble(frmVent.txtVentasDescuento.getText())));
            if(modVentC.AplicarDescuento(modVent, modPro, modEmpl)){
                JOptionPane.showMessageDialog(null, "Calculado Correctamente");
                frmVent.txtVentasTotal.setText(String.valueOf(modVent.getVentaTotal()));
            }else{
                JOptionPane.showMessageDialog(null, "Erros al Calcular");
            }
        }
        
        if(e.getSource()==frmVent.btnVentasConfirmar){
            modEmpl.setDni(frmVent.txtVentasEmpleado.getText());
            modVent.setDniCliente(frmVent.txtClienteDni.getText());
            modVent.setNombreCliente(frmVent.txtClienteNombre.getText());
            modVent.setDireccionCliente(frmVent.txtClienteDireccion.getText());
            modVent.setVentaTotal(Double.parseDouble(frmVent.txtVentasTotal.getText()));
            modPro.setCodigo("001");
            if(modVentC.Registrar(modVent, modPro, modEmpl)){
                JOptionPane.showMessageDialog(null, "Venta Exitosa");
                limpiarVentas();
            }else{
                JOptionPane.showMessageDialog(null, "Venta Fallida");
                limpiarVentas();
            }
        }
        
        if(e.getSource()==frmVent.btnVentasLimpiar){
            limpiarVentas();
        }
        
        if(e.getSource()==frmVent.btnBuscar){
            modVent.setId(Integer.parseInt(frmVent.txtBuscar.getText()));
            if(modVentC.buscar(modVent, modPro, modEmpl)){
                frmVent.txtBuscarDniCliente.setText(modVent.getDniCliente());
                frmVent.txtBuscarNombreCliente.setText(modVent.getNombreCliente());
                frmVent.txtBuscarDireccionCliente.setText(modVent.getDireccionCliente());
                frmVent.txtBuscarCodidoVenta.setText(String.valueOf(modVent.getId()));
                frmVent.txtBuscarNombreEmpleado.setText(modEmpl.getNombres());
                frmVent.txtBuscarVentaTotal.setText(String.valueOf(modVent.getVentaTotal())); 
            }else{
                JOptionPane.showMessageDialog(null, "Busquda Sin Resultados");
                limpiarBusqueda();
            }
        }
        
        if(e.getSource()==frmVent.btnModificarVenta){
            modVent.setId(Integer.parseInt(frmVent.txtBuscarCodidoVenta.getText()));
            modVent.setDniCliente(frmVent.txtBuscarDniCliente.getText());
            modVent.setNombreCliente(frmVent.txtBuscarNombreCliente.getText());
            modVent.setDireccionCliente(frmVent.txtBuscarDireccionCliente.getText());
            modVent.setVentaTotal(Double.parseDouble(frmVent.txtBuscarVentaTotal.getText()));
            modEmpl.setNombres(frmVent.txtBuscarNombreEmpleado.getText());
            if(modVentC.Modificar(modVent, modPro, modEmpl)){
                JOptionPane.showMessageDialog(null, "Exito al Modificar");
                limpiarBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "Modificar Fallido");
                limpiarBusqueda();
            }
        }
        
        if(e.getSource()==frmVent.btnLimpiarCasilleroBusqueda){
            limpiarBusqueda();
        }
        
        if(e.getSource()==frmVent.btnEliminarVenta){
            modVent.setId(Integer.parseInt(frmVent.txtBuscarCodidoVenta.getText()));
            if(modVentC.eliminar(modVent)){
                JOptionPane.showMessageDialog(null, "Exito al Eliminar");
                limpiarBusqueda();
            }else{
                JOptionPane.showMessageDialog(null, "Eliminar Fallido");
                limpiarBusqueda();
            }
        }
        
        if(e.getSource()==frmVent.btnSalir){
            frmVent.dispose();
        }
    }
    
    public void mostrarVentas(){
        frmVent.setLocationRelativeTo(null);
        try {
            DefaultTableModel modeloProv = new DefaultTableModel();
            frmVent.tbVentas.setModel(modeloProv);
            
            PreparedStatement ps= null;
            ResultSet rs;
            
            Conexion conn = new Conexion();
            Connection con = conn.getConexion();
            
            String sql = "SELECT * FROM ventas";
            
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            ResultSetMetaData rsMd = rs.getMetaData();
            int cantidadColum = rsMd.getColumnCount();
            
            frmVent.tbVentas.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));
            frmVent.tbVentas.getTableHeader().setOpaque(false);
            frmVent.tbVentas.getTableHeader().setBackground(new Color(33, 33, 33));
            frmVent.tbVentas.getTableHeader().setForeground(new Color(225, 255, 255));
            frmVent.tbVentas.setRowHeight(25);
            
            modeloProv.addColumn("id_Venta");
            modeloProv.addColumn("dni_Empleado");
            modeloProv.addColumn("RUC_DNI_Cliente");
            modeloProv.addColumn("Nombre_Cliente");
            modeloProv.addColumn("Direccion_Cliente");
            modeloProv.addColumn("Cantidad_Venta");
            modeloProv.addColumn("Codigo_Producto");
            modeloProv.addColumn("Nombre_Producto");
            
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
