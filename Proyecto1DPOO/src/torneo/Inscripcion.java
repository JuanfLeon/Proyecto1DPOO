package torneo;

import java.util.ArrayList;

import dataBase.Usuario;
import exceptions.EntidadNoEncontradaException;
import exceptions.RestriccionJugadoresException;

public class Inscripcion {
	private ArrayList<Usuario> usuariosInscripcion;
	
	
	public Inscripcion buscarUsuario(Usuario usuario) throws EntidadNoEncontradaException {
		if (!usuariosInscripcion.contains(usuario)) {
			throw new EntidadNoEncontradaException(usuario.toString());
		}
		return this;
	}
	
	public Inscripcion(ArrayList<Usuario> usuariosInscripcion) throws RestriccionJugadoresException{
		if(usuariosInscripcion.isEmpty()|| usuariosInscripcion.size()>3) {
			throw new RestriccionJugadoresException();
		}
		this.usuariosInscripcion=usuariosInscripcion;
	}
}
