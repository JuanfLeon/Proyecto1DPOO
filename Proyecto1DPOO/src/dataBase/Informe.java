package dataBase;

import java.io.Serializable;
import java.time.LocalDate; //YYYY-MM-DD

public class Informe implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double totalJuegos;
	private double totalPlatillos;
	private double subTotalJuegos;
	private double subTotalPlatillos;
	private double totalImpuestos;
	private double totalPropinas;
	private double totalCosto;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public Informe(double totalJuegos, double totalPlatillos, double subTotalJuegos, double subTotalPlatillos, double totalImpuestos, double totalPropinas, double totalCosto, LocalDate fechaInicio, LocalDate fechaFin) {
		
		this.totalJuegos = totalJuegos;
		this.totalPlatillos = totalPlatillos;
		this.subTotalJuegos = subTotalJuegos;
		this.subTotalPlatillos = subTotalPlatillos;
		this.totalImpuestos = totalImpuestos;
		this.totalPropinas = totalPropinas;
		this.totalCosto = totalCosto;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public double getTotalJuegos() {
		return totalJuegos;
	}
	public double getTotalPlatillos() {
		return totalPlatillos;
	}
	public double getSubTotalJuegos() {
		return subTotalJuegos;
	}
	public double getSubTotalPlatillos() {
		return subTotalPlatillos;
	}
	public double getTotalImpuestos() {
		return totalImpuestos;
	}
	public double getTotalPropinas() {
		return totalPropinas;
	}
	public double getTotalCosto() {
		return totalCosto;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setTotalJuegos(double totalJuegos) {
		this.totalJuegos = totalJuegos;
	}

	public void setTotalPlatillos(double totalPlatillos) {
		this.totalPlatillos = totalPlatillos;
	}

	public void setSubTotalJuegos(double subTotalJuegos) {
		this.subTotalJuegos = subTotalJuegos;
	}

	public void setSubTotalPlatillos(double subTotalPlatillos) {
		this.subTotalPlatillos = subTotalPlatillos;
	}

	public void setTotalImpuestos(double totalImpuestos) {
		this.totalImpuestos = totalImpuestos;
	}

	public void setTotalPropinas(double totalPropinas) {
		this.totalPropinas = totalPropinas;
	}

	public void setTotalCosto(double totalCosto) {
		this.totalCosto = totalCosto;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	
}
