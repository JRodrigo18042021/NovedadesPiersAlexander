
package modelo;


public class Ventas {
    
    private int id;
    private int dniEmpleado;
    private String rucOdniCliente;
    private String nombreCliente;
    private String direccionCliente;
    private String cantidadVenta;
    private String codigoProducto;
    private String nombreProducto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDniEmpleado() {
        return dniEmpleado;
    }

    public void setDniEmpleado(int dniEmpleado) {
        this.dniEmpleado = dniEmpleado;
    }

    public String getRucOdniCliente() {
        return rucOdniCliente;
    }

    public void setRucOdniCliente(String rucOdniCliente) {
        this.rucOdniCliente = rucOdniCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getDireccionCliente() {
        return direccionCliente;
    }

    public void setDireccionCliente(String direccionCliente) {
        this.direccionCliente = direccionCliente;
    }

    public String getCantidadVenta() {
        return cantidadVenta;
    }

    public void setCantidadVenta(String cantidadVenta) {
        this.cantidadVenta = cantidadVenta;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }
    
    
    
}
