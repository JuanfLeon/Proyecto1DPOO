package dataBase;

public abstract class Solicitud {

	protected Empleado solicitante;
	
	public Solicitud(Empleado emp_solic) {
	
		this.solicitante = emp_solic;
	}

	public Empleado getSolicitante() {
		return solicitante;
	}

	public void setSolicitante(Empleado solicitante) {
		this.solicitante = solicitante;
	}
	
}
