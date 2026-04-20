package cafe;

import java.io.Serializable;
import java.util.List;

public class Pasteleria extends Producto implements Serializable{
	private static final long serialVersionUID = 1L ;
	//Attributes 
	private List<String> alergenos ;

	//Constructor
	public Pasteleria(String nombre, float precio, List<String> alergenos) {
		super(nombre, precio);
		this.alergenos = alergenos;
	}
	
	//Getters y Setters
	public List<String> getAlergenos() {
		return alergenos;
	}

	public void setAlergenos(List<String> alergenos) {
		this.alergenos = alergenos;
	}
	
}
