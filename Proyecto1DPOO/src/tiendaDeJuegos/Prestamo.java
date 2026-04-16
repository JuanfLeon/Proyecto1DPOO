package tiendaDeJuegos;

import java.util.Date;

public class Prestamo {
	private String idJuego;
	private String idMesa;
	private Date fechaInicio;
	private Date fechaFin;
	
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
	public Date getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Date getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

}
