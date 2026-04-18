package cafe;

import java.util.ArrayList;
import java.util.List;
import dataBase.Empleado;

public class Cafe {

	//Attributes
	private int capacidadMaxClientes ;
	private List<Empleado> empleados ;
	private ArrayList<Mesa> mesas ;
	private ArrayList<Producto> catalogoPlatillos ;
	
	//Constructor
	public Cafe(int capacidadMaxClientes, List<Empleado> empleados, ArrayList<Mesa> mesas) {
		super();
		this.capacidadMaxClientes = capacidadMaxClientes;
		this.empleados = empleados;
		this.mesas = mesas;
	}
	
	//Getters y Setters 
	public int getCapacidadMaxClientes() {
		return capacidadMaxClientes;
	}
	public void setCapacidadMaxClientes(int capacidadMaxClientes) {
		this.capacidadMaxClientes = capacidadMaxClientes;
	}
	public List<Empleado> getEmpleados() {
		return empleados;
	}
	public void setEmpleados(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	public ArrayList<Mesa> getMesas() {
		return mesas;
	}
	public void setMesas(ArrayList<Mesa> mesas) {
		this.mesas = mesas;
	}

	public ArrayList<Producto> getCatalogoPlatillos() {
		return catalogoPlatillos;
	}

	public void setCatalogoPlatillos(ArrayList<Producto> catalogoPlatillos) {
		this.catalogoPlatillos = catalogoPlatillos;
	}
	
	
}

