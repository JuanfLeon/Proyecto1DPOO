package cafe;

public class Producto {

	//Attributes 
	protected String nombre ;
	protected double precio ;
	
	//Constructor 
	public Producto(String nombre, double precio2) {
		super();
		this.nombre = nombre;
		this.precio = precio2;
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
