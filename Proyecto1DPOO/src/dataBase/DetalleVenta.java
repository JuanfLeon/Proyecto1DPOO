package dataBase;

import java.util.ArrayList;

import cafe.Producto;
import tiendaDeJuegos.JuegoDeMesaFisico;

public class DetalleVenta {

	private Producto producto;
	private int cantidad;
	private double subtotal;
	private double impuestos;
	private String tipoCompra;
	
	public DetalleVenta(Producto producto, int cantidad, double subtotal, double impuestos, String tipoCompra) {
		this.producto = producto;
		this.cantidad = cantidad;
		this.subtotal = subtotal;
		this.impuestos = impuestos;
		this.tipoCompra = tipoCompra;
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
	
	
}
