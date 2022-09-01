
package modelo;

public class Facturacion {
    
    private int id;
    private String CodigoProducto;
    private int IdVenta;
    private String FechaFacturacion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCodigoProducto() {
        return CodigoProducto;
    }

    public void setCodigoProducto(String CodigoProducto) {
        this.CodigoProducto = CodigoProducto;
    }

    public int getIdVenta() {
        return IdVenta;
    }

    public void setIdVenta(int IdVenta) {
        this.IdVenta = IdVenta;
    }

    public String getFechaFacturacion() {
        return FechaFacturacion;
    }

    public void setFechaFacturacion(String FechaFacturacion) {
        this.FechaFacturacion = FechaFacturacion;
    }
    
    

       
}
