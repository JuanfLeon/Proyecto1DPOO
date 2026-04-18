package dataBase;

import java.time.LocalDateTime; //YYYY-MM-DDTHH:MM:SS

public class Informe {

	private double totalJuegos;
	private double totalPlatillos;
	private double subTotalJuegos;
	private double subTotalPlatillos;
	private double totalImpuestos;
	private double totalPropinas;
	private double totalCosto;
	private LocalDateTime fechaInicio;
	private LocalDateTime fechaFin;
	
	public Informe(double totalJuegos, double totalPlatillos, double subTotalJuegos, double subTotalPlatillos, double totalImpuestos, double totalPropinas, double totalCosto, LocalDateTime fechaInicio, LocalDateTime fechaFin) {
		
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
	public LocalDateTime getFechaInicio() {
		return fechaInicio;
	}
	public LocalDateTime getFechaFin() {
		return fechaFin;
	}
	
	
}
