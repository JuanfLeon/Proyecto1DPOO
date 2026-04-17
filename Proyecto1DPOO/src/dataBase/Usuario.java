package dataBase;

import java.util.List;

public abstract class Usuario {

	protected String login;
	protected List<String> juegosFavoritos;
	protected String contrasenia;
	
	public Usuario(String login, List<String> juegosFav, String contrasenia) {
		this.contrasenia = contrasenia;
		this.juegosFavoritos = juegosFav;
		this.login = login;
	}
}
