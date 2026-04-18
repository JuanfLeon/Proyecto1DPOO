package dataBase;



import cafe.Producto;
import tiendaDeJuegos.TipoVenta;

public class DetalleVenta {

	private Producto producto;
	private int cantidad;
	private double subtotal;
	private double impuestos;
	private String tipoCompra;
	private TipoVenta tipoVenta;
	
	public DetalleVenta(Producto producto, int cantidad2, double subtotal2, double impuestos2, TipoVenta tipoVenta) {
	this.producto=producto;
	this.cantidad=cantidad2;
	this.subtotal=subtotal2;
	this.impuestos=impuestos2;
	this.tipoVenta=tipoVenta;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}

	public String getTipoCompra() {
		return tipoCompra;
	}

	public void setTipoCompra(String tipoCompra) {
		this.tipoCompra = tipoCompra;
	}

	public TipoVenta getTipoVenta() {
		return tipoVenta;
	}

	public void setTipoVenta(TipoVenta tipoVenta) {
		this.tipoVenta = tipoVenta;
	}
		
}
