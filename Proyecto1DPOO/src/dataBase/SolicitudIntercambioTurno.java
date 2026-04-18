package dataBase;

public class SolicitudIntercambioTurno extends SolicitudTurno{

	private Empleado empleadoIntercambiable;

	public SolicitudIntercambioTurno(Turno turnoi, Turno turnod, Empleado solicitante, Empleado intercambiable) {
		super(turnoi, turnod, solicitante);
		empleadoIntercambiable = intercambiable;
	}

	public Empleado getEmpleadoIntercambiable() {
		return empleadoIntercambiable;
	}

	public void setEmpleadoIntercambiable(Empleado empleadoIntercambiable) {
		this.empleadoIntercambiable = empleadoIntercambiable;
	}
	
}
