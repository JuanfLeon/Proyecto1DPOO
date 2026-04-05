package exceptions;

@SuppressWarnings("serial")
public class EstadoInvalidoException extends Exception{
	private String objetoInvalido;

	public EstadoInvalidoException(String objetoInvalido) {
		super();
		this.objetoInvalido = objetoInvalido;
	}
	
	@Override
	public String getMessage() {
		return "El objeto" + this.objetoInvalido + " se encuentra en un objeto invalido";
	}
}
