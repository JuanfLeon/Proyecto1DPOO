package dataBase;

import java.io.Serializable;
import java.util.ArrayList;
import cafe.Bebida;
import cafe.Mesa;
import cafe.Producto;
import tiendaDeJuegos.JuegoDeMesa;
import tiendaDeJuegos.TipoDeJuego;

public class Cocinero extends Empleado implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cocinero(String login, ArrayList<String> juegosFav, String contrasenia, ArrayList<Turno> turnos, String codigo_desc) {
		super(login, juegosFav, contrasenia, turnos, codigo_desc);
	}
	
	public Producto despacharProducto(Mesa mesa, Producto producto) throws Exception{
		
		if (producto instanceof Bebida) {
			if(((Bebida) producto).isAlcoholica()) {
				if (mesa.isTieneNinos()) {
					throw new Exception("HayNiniosEnLaMesa");
				}
				else {
					return producto;
				}
			}
			if(((Bebida) producto).isCaliente()) {
				for(Object i:mesa.getProductosOrdenados()) {
					if(i instanceof JuegoDeMesa && ((JuegoDeMesa) i).getTipoDeJuego() == TipoDeJuego.ACCION) {
						throw new Exception("JuegoDeAccionEnLaMesa");
					}
				}
			}
			else {
				return producto;
			}
		}
		else {
			return producto;
		}
		return producto;
	}
	
	public boolean checkEdad(Mesa mesa) {
		if (mesa.isTieneNinos()) {
			return true;
		}
		return false;
	}
	
	public boolean checkBebidaCaliente(Mesa mesa) {
		if (mesa.isTieneBebidaCaliente()) {
			return true;
		}
		return false;
	}
}
