package cafe;

public class Bebida extends Producto {

	//Attributes 
	private boolean alcoholica ;
	private boolean caliente ;
	
	//Constructor 
	public Bebida(String nombre, float precio, boolean alcoholica, boolean caliente) {
		super(nombre, precio);
		this.alcoholica = alcoholica;
		this.caliente = caliente;
	}

	//Getters y Setters 
	public boolean isAlcoholica() {
		return alcoholica;
	}

	public void setAlcoholica(boolean alcoholica) {
		this.alcoholica = alcoholica;
	}

	public boolean isCaliente() {
		return caliente;
	}

	public void setCaliente(boolean caliente) {
		this.caliente = caliente;
	}
		
}

