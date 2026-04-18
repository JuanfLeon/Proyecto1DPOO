package cafe;

import java.util.ArrayList;
import java.util.List;

import dataBase.Cliente;
import dataBase.Empleado;

public class Cafe {

	//Attributes
	private int capacidadMaxClientes ;
	private ArrayList<Empleado> empleados ;
	private ArrayList<Mesa> mesas ;
	private ArrayList<Producto> catalogoPlatillos ;
	
	//Constructor
	public Cafe(int capacidadMaxClientes, ArrayList<Empleado> empleados, ArrayList<Mesa> mesas) {
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
	public void setEmpleados(ArrayList<Empleado> empleados) {
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
	
	public void agregarPlatillo(Producto platillo) {
		this.catalogoPlatillos.add(platillo);
	}
	
	public void agregarMesa(String idMesa, int cantidadClientes, boolean tieneJovenes, boolean tieneNinos, boolean tieneBebidaCaliente, ArrayList<Producto> productosOrdenados, Cliente clienteTitular) {
		Mesa mesanueva = new Mesa(idMesa, cantidadClientes, tieneJovenes, tieneNinos, tieneBebidaCaliente, productosOrdenados, clienteTitular);
		this.mesas.add(mesanueva);
	}
	
}

