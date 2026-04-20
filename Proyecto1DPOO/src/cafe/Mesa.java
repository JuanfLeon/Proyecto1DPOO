package cafe;

import java.io.Serializable;
import java.util.ArrayList;

import dataBase.Cliente;

public class Mesa implements Serializable{

	private static final long serialVersionUID = 1L;
	//Attributes 
	private String idMesa;
	private int cantidadClientes ;
	private boolean tieneJovenes ;
	private boolean tieneNinos ;
	private boolean tieneBebidaCaliente ; 
	private ArrayList<Producto> productosOrdenados; 
	private Cliente clienteTitular;
	
	//Constructor 
	public Mesa(String idMesa, int cantidadClientes, boolean tieneJovenes, boolean tieneNinos,
			boolean tieneBebidaCaliente, ArrayList<Producto> productosOrdenados, Cliente clienteTitular) {
		super();
		this.idMesa = idMesa;
		this.cantidadClientes = cantidadClientes;
		this.tieneJovenes = tieneJovenes;
		this.tieneNinos = tieneNinos;
		this.tieneBebidaCaliente = tieneBebidaCaliente;
		this.productosOrdenados = productosOrdenados;
		this.clienteTitular = clienteTitular;
	}
	
	//Getters y Setters 
	public String getIdMesa() {
		return idMesa;
	}
	public void setIdMesa(String idMesa) {
		this.idMesa = idMesa;
	}
	public int getCantidadClientes() {
		return cantidadClientes;
	}
	public void setCantidadClientes(int cantidadClientes) {
		this.cantidadClientes = cantidadClientes;
	}
	public boolean isTieneJovenes() {
		return tieneJovenes;
	}
	public void setTieneJovenes(boolean tieneJovenes) {
		this.tieneJovenes = tieneJovenes;
	}
	public boolean isTieneNinos() {
		return tieneNinos;
	}
	public void setTieneNinos(boolean tieneNinos) {
		this.tieneNinos = tieneNinos;
	}
	public boolean isTieneBebidaCaliente() {
		return tieneBebidaCaliente;
	}
	public void setTieneBebidaCaliente(boolean tieneBebidaCaliente) {
		this.tieneBebidaCaliente = tieneBebidaCaliente;
	}
	public ArrayList<Producto> getProductosOrdenados() {
		return productosOrdenados;
	}
	public void setProductosOrdenados(ArrayList<Producto> productosOrdenados) {
		this.productosOrdenados = productosOrdenados;
	}
	public Cliente getClienteTitular() {
		return clienteTitular;
	}
	public void setClienteTitular(Cliente clienteTitular) {
		this.clienteTitular = clienteTitular;
	} 
	
	
}
