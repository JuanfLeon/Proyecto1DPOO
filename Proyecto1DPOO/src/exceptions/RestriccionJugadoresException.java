package exceptions;

@SuppressWarnings("serial")
public class RestriccionJugadoresException extends Exception{
	
	public RestriccionJugadoresException() {
		super();
	}

	@Override
	public String getMessage() {
		return "No se cumple con el rango de jugadores permitido";
}
}
