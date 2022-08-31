
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Productos;
import modelo.consultarProductos;
import vista.fmrProductos;

public class controloadorVentas implements ActionListener {
    
    Productos mod;
    consultarProductos modC;
    fmrProductos frm;

    public controloadorVentas(Productos mod, consultarProductos modC, fmrProductos frm) {
        this.mod = mod;
        this.modC = modC;
        this.frm = frm;
        this.frm.btnGuardar.addActionListener(this);
        this.frm.btnModificar.addActionListener(this);
        this.frm.btnEliminar.addActionListener(this);
        this.frm.btnLimpiar.addActionListener(this);
        this.frm.btnBuscar.addActionListener(this);
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
    }
    
    
    
}
