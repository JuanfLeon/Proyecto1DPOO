package exceptions;

@SuppressWarnings("serial")
public class SolicitudNoValidaException extends Exception{

	public SolicitudNoValidaException() {
		super();
	}
	
	@Override 
	public String getMessage() {
		return "Los parametros ingresados son incorrectos, intente de nuevo";
	}
}
