package dataBase;

import java.io.Serializable;

public class SolicitudCambioTurno extends SolicitudTurno implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SolicitudCambioTurno(Turno turnoi, Turno turnod, Empleado solicitante) {
		super(turnoi, turnod, solicitante);
	}
}
