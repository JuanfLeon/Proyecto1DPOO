package dataBase;

import java.io.Serializable;
import java.util.ArrayList;

import cafe.Cafe;
import cafe.Producto;
import tiendaDeJuegos.JuegoDeMesa;

public class Mesero extends Empleado implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<JuegoDeMesa> juegosDificiles;
	
	public Mesero(String login, ArrayList<String> juegosFav, String contrasenia, ArrayList<Turno> turnos, String codigo_desc, ArrayList<JuegoDeMesa> juegosDificiles) {
		super(login, juegosFav, contrasenia, turnos, codigo_desc);
		this.juegosDificiles = juegosDificiles;
	}

	public ArrayList<JuegoDeMesa> getJuegosDificiles() {
		return juegosDificiles;
	}

	public void setJuegosDificiles(ArrayList<JuegoDeMesa> juegosDificiles) {
		this.juegosDificiles = juegosDificiles;
	}
	
	public void crearMesa(Cafe cafe, String idMesa, int cantidadClientes, boolean tieneJovenes, boolean tieneNinos, boolean tieneBebidaCaliente, ArrayList<Producto> productosOrdenados, Cliente clienteTitular) {
		cafe.agregarMesa(idMesa, cantidadClientes, tieneJovenes, tieneNinos, tieneBebidaCaliente, productosOrdenados, clienteTitular);
	}

}