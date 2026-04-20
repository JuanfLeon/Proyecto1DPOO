package tiendaDeJuegos;

import java.io.Serializable;
import java.time.LocalDate;

public class Prestamo implements Serializable{
	private static final long serialVersionUID = 1L ;
	private String idJuego;
	private String idMesa;
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	public String getIdJuego() {
		return idJuego;
	}
	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}
	public String getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}
	public LocalDate getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public LocalDate getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}
	public Prestamo(String idJuego, String idMesa, LocalDate fechaActual, LocalDate tiempoLimite) {
		this.idJuego = idJuego;
		this.idMesa = idMesa;
		this.fechaInicio = fechaActual;
		this.fechaFin = tiempoLimite;
	}
	


}
