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
}
