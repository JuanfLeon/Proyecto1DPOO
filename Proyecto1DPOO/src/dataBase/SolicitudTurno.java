package dataBase;

public abstract class SolicitudTurno extends Solicitud{


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
	
	
}