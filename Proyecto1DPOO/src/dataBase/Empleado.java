package dataBase;

import java.util.ArrayList;

public abstract class Empleado extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected ArrayList<Turno> turnoLaboral;
	protected String codigoDescuento;
	
	
	public Empleado(String login, ArrayList<String> juegosFav, String contrasenia, ArrayList<Turno> turnos, String codigo_desc) {
		super(login, juegosFav, contrasenia);
		this.codigoDescuento = codigo_desc;
		this.turnoLaboral = turnos;
	}
	
	public SolicitudCambioTurno solicitarCambioHorario(Turno turno_inicial, Turno turno_deseado) {
		return new SolicitudCambioTurno(turno_inicial, turno_deseado, this);
	}
	
	public SolicitudIntercambioTurno solicitarIntercambioHorario(Turno turno_inicial, Turno turno_deseado, Empleado empleado_otro) {
		return new SolicitudIntercambioTurno(turno_inicial, turno_deseado, this, empleado_otro);
	}
	
	public void agregarTurno(Turno turno) throws Exception {
		if (!turnoLaboral.contains(turno)) {
	            turnoLaboral.add(turno);
	        } 
		else {
	        throw new Exception("TurnoYaAsignadoAlEmpleado");
	        }
	    }

	public void eliminarTurno(Turno turno) {
	        turnoLaboral.remove(turno);
	    }

	public ArrayList<Turno> getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(ArrayList<Turno> turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}
	    
	   
}
