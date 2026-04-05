package exceptions;

@SuppressWarnings("serial")
public class RestriccionEdadException extends Exception{
	private String juegoConRestriccion;
	
	public RestriccionEdadException(String juegoConRestriccion) {
		super();
		this.juegoConRestriccion = juegoConRestriccion;
	}

	@Override
	public String getMessage() {
		return "El juego " + juegoConRestriccion+ " no puede ser usado por menores de edad";
	}
}
