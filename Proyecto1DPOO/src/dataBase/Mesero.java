package dataBase;

import java.util.List;

import tiendaDeJuegos.JuegoDeMesa;

public class Mesero extends Empleado{


	private List<JuegoDeMesa> juegosDificiles;
	
	public Mesero(String login, List<String> juegosFav, String contrasenia, List<Turno> turnos, String codigo_desc, List<JuegoDeMesa> juegosDificiles) {
		super(login, juegosFav, contrasenia, turnos, codigo_desc);
		this.juegosDificiles = juegosDificiles;
	}
}
