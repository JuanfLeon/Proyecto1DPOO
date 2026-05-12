package torneo;

import java.util.ArrayList;

import dataBase.Usuario;

import exceptions.RestriccionJugadoresException;

public class Inscripcion {
	private ArrayList<Usuario> usuariosInscripcion;
	
	public boolean contieneUsuario(Usuario usuario){
		if (usuariosInscripcion.contains(usuario)) {
			return true;
		}
		return false;
	}
	
	public Inscripcion(ArrayList<Usuario> usuariosInscripcion) throws RestriccionJugadoresException{
		if(usuariosInscripcion.isEmpty()|| usuariosInscripcion.size()>3) {
			throw new RestriccionJugadoresException();
		}
		this.usuariosInscripcion=usuariosInscripcion;
	}

	public ArrayList<Usuario> getUsuariosInscripcion() {
		return usuariosInscripcion;
	}

	public void setUsuariosInscripcion(ArrayList<Usuario> usuariosInscripcion) {
		this.usuariosInscripcion = usuariosInscripcion;
	}
	
}
