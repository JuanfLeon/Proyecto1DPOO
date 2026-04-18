package dataBase;

import java.util.List;

import cafe.Mesa;
import tiendaDeJuegos.JuegoDeMesaFisico;

public class Cliente extends Usuario{

	public Cliente(String login, List<String> juegosFav, String contrasenia) {
		super(login, juegosFav, contrasenia);
	}

	private String idCliente;
	private double puntosFidelidad;
	private Mesa mesa;
	private List<JuegoDeMesaFisico> juegosPrestados;
	private List<JuegoDeMesaFisico> juegosComprados;
	
	public void actualizarPuntosFidelidad() {
		
	}
}
