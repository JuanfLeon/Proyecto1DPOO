package dataBase;

import java.util.ArrayList;

public abstract class Usuario {

	protected String login;
	protected ArrayList<String> juegosFavoritos;
	protected String contrasenia;
	
	public Usuario(String login, ArrayList<String> juegosFav, String contrasenia) {
		this.contrasenia = contrasenia;
		this.juegosFavoritos = juegosFav;
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public ArrayList<String> getJuegosFavoritos() {
		return juegosFavoritos;
	}

	public void setJuegosFavoritos(ArrayList<String> juegosFavoritos) {
		this.juegosFavoritos = juegosFavoritos;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
