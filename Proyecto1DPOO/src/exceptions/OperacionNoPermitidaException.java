package exceptions;

@SuppressWarnings("serial")
public class OperacionNoPermitidaException extends Exception{

	public OperacionNoPermitidaException () {
		super();
	}
	@Override
	public String getMessage() {
		return "No cuenta con permisos para realizar esta operacíon";
	}
}	
