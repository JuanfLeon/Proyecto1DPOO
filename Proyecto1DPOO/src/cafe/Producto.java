package cafe;

import java.io.Serializable;

public class Producto implements Serializable{
	private static final long serialVersionUID = 1L ;
	//Attributes 
	protected String nombre ;
	protected double precio ;
	
	//Constructor 
	public Producto(String nombre, double precio) {
		super();
		this.nombre = nombre;
		this.precio = precio;
	}
	
	//Getters y Setters 
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
}
