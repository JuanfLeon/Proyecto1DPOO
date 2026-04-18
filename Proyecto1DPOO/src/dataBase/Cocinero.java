package dataBase;

import java.util.ArrayList;
import java.util.List;

import cafe.Bebida;
import cafe.Mesa;
import cafe.Producto;

public class Cocinero extends Empleado{

	
	public Cocinero(String login, List<String> juegosFav, String contrasenia, List<Turno> turnos, String codigo_desc) {
		super(login, juegosFav, contrasenia, turnos, codigo_desc);
	}
	
	public void despacharProducto(Mesa mesa, Producto producto) {
		ArrayList<Producto> pp_mesa = mesa.getProductosOrdenados();
		pp_mesa.add(producto);
		mesa.setProductosOrdenados(pp_mesa);
	}
	
	public boolean checkEdad() {
		
	}
	
	public boolean checkBebidaCaliente() {
		
	}
}