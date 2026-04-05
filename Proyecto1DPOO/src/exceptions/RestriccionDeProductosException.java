package exceptions;

@SuppressWarnings("serial")
public class RestriccionDeProductosException extends Exception{
	private String productoConflicto;
	
	
	public RestriccionDeProductosException(String productoConflicto) {
		super();
		this.productoConflicto = productoConflicto;
	}


	@Override
	public String getMessage() {
		return "El producto solicitado" + this.productoConflicto + " tiene conflicto con los productos en la mesa"; 
	}
}
