
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
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
    }
    
    public void iniciar(){
        frm.setTitle("Empleados");
        frm.setLocationRelativeTo(null);
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
    }
    
    
    
}
