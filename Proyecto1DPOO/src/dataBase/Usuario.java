package dataBase;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
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
