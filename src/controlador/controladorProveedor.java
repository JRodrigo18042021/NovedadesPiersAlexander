
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Proveedores;
import modelo.ProveedoresConsultas;
import vista.frmProveedores;

public class controladorProveedor implements ActionListener{
    Proveedores modPv;
    ProveedoresConsultas provC;
    frmProveedores frm;

    public controladorProveedor(Proveedores prov, ProveedoresConsultas provC, frmProveedores frm) {
        this.modPv = prov;
        this.provC = provC;
        this.frm = frm;
        this.frm.btnRegistarProv.addActionListener(this);
        this.frm.btnActualizarProv.addActionListener(this);
        this.frm.btnEliminarProv.addActionListener(this);
        this.frm.btnConsultarProv.addActionListener(this);
    
    }
    public void iniciar(){
        frm.setTitle("Proveedores");
        frm.setLocationRelativeTo(null);
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
        frm.txtdniEmp.setText(null);
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
                JOptionPane.showMessageDialog(null, "ELIMINADO CORRECTAMENTE");
                limpiar();
            }else{
                JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR");
                limpiar();
            }
        }
    }
    
    
}
