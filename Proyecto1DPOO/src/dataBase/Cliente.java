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

	public List<JuegoDeMesaFisico> getJuegosPrestados() {
		return juegosPrestados;
	}

	public void setJuegosPrestados(List<JuegoDeMesaFisico> juegosPrestados) {
		this.juegosPrestados = juegosPrestados;
	}

	public List<JuegoDeMesaFisico> getJuegosComprados() {
		return juegosComprados;
	}

	public void setJuegosComprados(List<JuegoDeMesaFisico> juegosComprados) {
		this.juegosComprados = juegosComprados;
	}
	
	
}
