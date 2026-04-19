package dataBase;

import java.util.ArrayList;

import cafe.Mesa;
import tiendaDeJuegos.JuegoDeMesaFisico;

public class Cliente extends Usuario{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Cliente(String login, ArrayList<String> juegosFav, String contrasenia) {
		super(login, juegosFav, contrasenia);
	}

	private String idCliente;
	private double puntosFidelidad;
	private Mesa mesa;
	private ArrayList<JuegoDeMesaFisico> juegosPrestados;
	private ArrayList<JuegoDeMesaFisico> juegosComprados;
	
	public void actualizarPuntosFidelidad() {
		
	}

	public String getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(String idCliente) {
		this.idCliente = idCliente;
	}

	public double getPuntosFidelidad() {
		return puntosFidelidad;
	}

	public void setPuntosFidelidad(double puntosFidelidad) {
		this.puntosFidelidad = puntosFidelidad;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}

	public ArrayList<JuegoDeMesaFisico> getJuegosPrestados() {
		return juegosPrestados;
	}

	public void setJuegosPrestados(ArrayList<JuegoDeMesaFisico> juegosPrestados) {
		this.juegosPrestados = juegosPrestados;
	}

	public ArrayList<JuegoDeMesaFisico> getJuegosComprados() {
		return juegosComprados;
	}

	public void setJuegosComprados(ArrayList<JuegoDeMesaFisico> juegosComprados) {
		this.juegosComprados = juegosComprados;
	}
	
	
}
