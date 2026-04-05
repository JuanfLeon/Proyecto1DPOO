package exceptions;

@SuppressWarnings("serial")
public class ConflictoHorarioException extends Exception{
	public ConflictoHorarioException() {
		super();
	}
	@Override 
	public String getMessage() {
		return "No se puede admitir este cambio de horario";
	}
}
