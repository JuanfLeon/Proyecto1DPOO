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

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public List<String> getJuegosFavoritos() {
		return juegosFavoritos;
	}

	public void setJuegosFavoritos(List<String> juegosFavoritos) {
		this.juegosFavoritos = juegosFavoritos;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
