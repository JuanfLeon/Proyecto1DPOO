package usuarios;

import java.util.ArrayList;

import cafe.Mesa;
import tiendaDeJuegos.JuegoDeMesa;

public class Cliente extends Usuario{
	private String idUsuario;
	private ArrayList<JuegoDeMesa> juegosComprados;
	private ArrayList<JuegoDeMesa> juegosFavoritos;
	private ArrayList<JuegoDeMesa> juegosReservados;
	private int puntosDeFidelidad;
	private Mesa mesa;
	
	public Cliente(String idUsuario, ArrayList<JuegoDeMesa> juegosComprados, ArrayList<JuegoDeMesa> juegosFavoritos,
			ArrayList<JuegoDeMesa> juegosReservados, int puntosDeFidelidad, Mesa mesa) {
		super();
		this.idUsuario = idUsuario;
		this.juegosComprados = new ArrayList<JuegoDeMesa>();
		this.juegosFavoritos = new ArrayList<JuegoDeMesa>();
		this.juegosReservados = new ArrayList<JuegoDeMesa>();
		this.puntosDeFidelidad = puntosDeFidelidad;
		this.mesa = mesa;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public double getPuntosDeFidelidad() {
		return puntosDeFidelidad;
	}

	public void setPuntosDeFidelidad(int puntosDeFidelidad) {
		this.puntosDeFidelidad = puntosDeFidelidad;
	}

	public Mesa getMesa() {
		return mesa;
	}

	public void setMesa(Mesa mesa) {
		this.mesa = mesa;
	}
	
	
	
}
