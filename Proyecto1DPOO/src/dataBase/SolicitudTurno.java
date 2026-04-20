package dataBase;

import java.io.Serializable;

public abstract class SolicitudTurno extends Solicitud implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Turno turnoInicial;
	protected Turno turnoDeseado;
	
	public SolicitudTurno(Turno turnoi, Turno turnod, Empleado solicitante) {
		super(solicitante);
		turnoInicial = turnoi;
		turnoDeseado = turnod;
	}

	public Turno getTurnoInicial() {
		return turnoInicial;
	}

	public Turno getTurnoDeseado() {
		return turnoDeseado;
	}

	public void setTurnoInicial(Turno turnoInicial) {
		this.turnoInicial = turnoInicial;
	}

	public void setTurnoDeseado(Turno turnoDeseado) {
		this.turnoDeseado = turnoDeseado;
	}
	
}