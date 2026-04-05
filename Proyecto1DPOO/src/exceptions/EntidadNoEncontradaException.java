package exceptions;

@SuppressWarnings("serial")
public class EntidadNoEncontradaException extends Exception {
	private String mensaje;

	public EntidadNoEncontradaException(String mensaje) {
		super();
		this.mensaje = mensaje;
	}
	
	@Override
	public String getMessage() {
		return "No se encontró el la entidad buscada: "+ this.mensaje;
	}
}
