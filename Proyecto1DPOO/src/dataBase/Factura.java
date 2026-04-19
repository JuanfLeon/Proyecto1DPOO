package dataBase;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Factura implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private LocalDate fecha;
	private double subtotal;
	private double impuestos;
	private double propina;
	private double descuento;
	private double total;
	public double getDescuento() {
		return descuento;
	}




	public void setDescuento(double descuento) {
		this.descuento = descuento;
	}

	private ArrayList<DetalleVenta> detallesDeLaVenta;
	
	public Factura(LocalDate fecha, double subtotal, double impuestos, double propina, double descuento, double total,ArrayList<DetalleVenta> detallesVenta) {
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.impuestos = impuestos;
		this.propina = propina;
		
		this.detallesDeLaVenta = detallesVenta;
	}

	


	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}




	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}




	public void setImpuestos(double impuestos) {
		this.impuestos = impuestos;
	}




	public void setPropina(double propina) {
		this.propina = propina;
	}




	public void setDetallesDeLaVenta(ArrayList<DetalleVenta> detallesDeLaVenta) {
		this.detallesDeLaVenta = detallesDeLaVenta;
	}




	public LocalDate getFecha() {
		return fecha;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public double getImpuestos() {
		return impuestos;
	}

	public double getPropina() {
		return propina;
	}

	public ArrayList<DetalleVenta> getDetallesDeLaVenta() {
		return detallesDeLaVenta;
	}




	public double getTotal() {
		return total;
	}




	public void setTotal(double total) {
		this.total = total;
	}
	
}
