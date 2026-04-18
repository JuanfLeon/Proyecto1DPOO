package dataBase;

import java.util.List;

public abstract class Empleado extends Usuario{

	protected List<Turno> turnoLaboral;
	protected String codigoDescuento;
	
	
	public Empleado(String login, List<String> juegosFav, String contrasenia, List<Turno> turnos, String codigo_desc) {
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

	public List<Turno> getTurnoLaboral() {
		return turnoLaboral;
	}

	public void setTurnoLaboral(List<Turno> turnoLaboral) {
		this.turnoLaboral = turnoLaboral;
	}

	public String getCodigoDescuento() {
		return codigoDescuento;
	}

	public void setCodigoDescuento(String codigoDescuento) {
		this.codigoDescuento = codigoDescuento;
	}
	    
	   
}
