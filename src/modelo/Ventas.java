
package modelo;


public class Ventas {
    
    private int Id;
    private int ProductoCantidad;
    private double VentaCantidad;
    private double VentaCalculada;
    private double Descuento;
    private double VentaTotal;
    private String DniCliente;
    private String NombreCliente;
    private String DireccionCliente;

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getProductoCantidad() {
        return ProductoCantidad;
    }

    public void setProductoCantidad(int ProductoCantidad) {
        this.ProductoCantidad = ProductoCantidad;
    }

    public double getVentaCantidad() {
        return VentaCantidad;
    }
    
    public void setVentaCantidad(double VentaCantidad) {
        this.VentaCantidad = VentaCantidad;
    }

    public double getVentaCalculada() {
        return VentaCalculada;
    }

    public void setVentaCalculada(double VentaCalculada) {
        this.VentaCalculada = VentaCalculada;
    }

    public double getDescuento() {
        return Descuento;
    }

    public void setDescuento(double Descuento) {
        this.Descuento = Descuento;
    }

    public double getVentaTotal() {
        return VentaTotal;
    }

    public void setVentaTotal(double VentaTotal) {
        this.VentaTotal = VentaTotal;
    }

    public String getDniCliente() {
        return DniCliente;
    }

    public void setDniCliente(String DniCliente) {
        this.DniCliente = DniCliente;
    }

    public String getNombreCliente() {
        return NombreCliente;
    }

    public void setNombreCliente(String NombreCliente) {
        this.NombreCliente = NombreCliente;
    }

    public String getDireccionCliente() {
        return DireccionCliente;
    }

    public void setDireccionCliente(String DireccionCliente) {
        this.DireccionCliente = DireccionCliente;
    }

}
