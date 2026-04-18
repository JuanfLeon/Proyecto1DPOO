package dataBase;

import java.time.LocalDateTime;
import java.util.List;

public class Factura {

	private LocalDateTime fecha;
	private double subtotal;
	private double impuestos;
	private double propina;
	private List<DetalleVenta> detallesDeLaVenta;
	
	public Factura(LocalDateTime fecha, double subtotal, double impuestos, double propina, List<DetalleVenta> detallesVenta) {
		this.fecha = fecha;
		this.subtotal = subtotal;
		this.impuestos = impuestos;
		this.propina = propina;
		this.detallesDeLaVenta = detallesVenta;
	}

	public LocalDateTime getFecha() {
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

	public List<DetalleVenta> getDetallesDeLaVenta() {
		return detallesDeLaVenta;
	}

	public void setFecha(LocalDateTime fecha) {
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

	public void setDetallesDeLaVenta(List<DetalleVenta> detallesDeLaVenta) {
		this.detallesDeLaVenta = detallesDeLaVenta;
	}
	
}
