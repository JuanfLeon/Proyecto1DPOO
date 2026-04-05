package exceptions;

@SuppressWarnings("serial")
public class JuegoNoDisponibleException extends Exception{
	private String juegoNoEncontrado;

	public JuegoNoDisponibleException(String juegoNoEncontrado) {
		super();
		this.juegoNoEncontrado = juegoNoEncontrado;
	}
	
	@Override
	public String getMessage() {
		return "El juego "+ this.juegoNoEncontrado+ " no fue encontrado";
	}
}
