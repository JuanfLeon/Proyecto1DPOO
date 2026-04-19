package dataBase;

import java.io.Serializable;

public abstract class Solicitud implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
