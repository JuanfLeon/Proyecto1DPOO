package exceptions;

@SuppressWarnings("serial")
public class CapacidadExcedidaException extends Exception{

public CapacidadExcedidaException() {
		super();
	}

@Override 
public String getMessage () {
	return "El café no cuenta con mesas disponibles";
}
	
}
