package cafe;

import java.io.Serializable;

public class Bebida extends Producto implements Serializable{
	private static final long serialVersionUID = 1L;
	
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

