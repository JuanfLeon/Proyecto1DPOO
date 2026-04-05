package exceptions;

@SuppressWarnings("serial")
public class InventarioInsuficienteException extends Exception{

	private String juegoIsuficiente;

	public InventarioInsuficienteException(String juegoIsuficiente) {
		super();
		this.juegoIsuficiente = juegoIsuficiente;
	}
	
	@Override
	public String getMessage() {
		return "El juego " + this.juegoIsuficiente + " no cuenta con suficientes unidades para realizar la operación";
	}
}
