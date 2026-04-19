package dataBase;

public class SolicitudCambioTurno extends SolicitudTurno{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SolicitudCambioTurno(Turno turnoi, Turno turnod, Empleado solicitante) {
		super(turnoi, turnod, solicitante);
	}
}
