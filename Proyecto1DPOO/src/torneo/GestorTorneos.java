package torneo;


import java.util.ArrayList;

import dataBase.Cliente;
import dataBase.DataBase;
import dataBase.Usuario;
import exceptions.CupoNoDisponibleException;
import exceptions.RestriccionJugadoresException;
import generals.DiaSemana;
import tiendaDeJuegos.JuegoDeMesa;

public class GestorTorneos {
	private DataBase dataBase;

	public GestorTorneos(DataBase dataBase) {
		this.dataBase = dataBase;
	}

	public DataBase getDataBase() {
		return dataBase;
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}
	
	public void crearTorneoAmistoso(
			ArrayList<Inscripcion> inscripciones,
	        DiaSemana diaTorneo,
	        JuegoDeMesa juegoTorneo,
	        int cantidadParticipantes) {

	    TorneoAmistoso torneo =
	        new TorneoAmistoso(
	            inscripciones,
	            diaTorneo,
	            juegoTorneo,
	            cantidadParticipantes);

	    dataBase.getTorneos().add(torneo);
	}
	
	public void crearTorneoCompetitivo(ArrayList<Inscripcion> inscripciones, DiaSemana diaTorneo, JuegoDeMesa juegoTorneo,
			int cantidadParticipantes, int precioEntrada, double dineroRecaudado) {
		
		TorneoCompetitivo torneo= 
				new TorneoCompetitivo(inscripciones,
									  diaTorneo,
									  juegoTorneo,
									  cantidadParticipantes,
									  precioEntrada,
									  dineroRecaudado);
		dataBase.getTorneos().add(torneo);
		
	}
	
	
	public void inscribirUsuariosTorneo(ArrayList<Usuario> usuarios, Torneo torneo) throws CupoNoDisponibleException{
		try {
			Inscripcion inscripcion= new Inscripcion(usuarios);
			
			int[]cupos=contarTipoUsuarioInscrito(inscripcion, torneo);
			int cuposFavoritos=cupos[0];
			int cuposNormales=cupos[1];
			
			if (cuposFavoritos>torneo.getCantidadJuegoFavoritoDisponible()
					|| cuposNormales>torneo.getCantidadParticipantesDisponible()) {
				throw new CupoNoDisponibleException();
				
			}
			else {
				torneo.getInscripciones().add(inscripcion);
				torneo.setCantidadJuegoFavoritoDisponible(
										torneo.getCantidadJuegoFavoritoDisponible()-cuposFavoritos);
				torneo.setCantidadParticipantesDisponible(
										torneo.getCantidadParticipantesDisponible()-cuposNormales);
				if (torneo instanceof TorneoCompetitivo) {
					TorneoCompetitivo torneoCompetitivo= (TorneoCompetitivo) torneo;
					torneoCompetitivo.setDineroRecaudado(
						(cuposFavoritos+cuposNormales)*torneoCompetitivo.getPrecioEntrada());
				}
			}
			
		} catch (RestriccionJugadoresException e) {
			System.out.println(e.getMessage());;
		}
	}
	
	public void desinscribirUsuariosTorneo(Usuario usuario, Torneo torneo) {
		ArrayList<Inscripcion>inscripciones =torneo.getInscripciones();
		boolean encontrado= false;
		int i =0;
		Inscripcion delInscripcion=null;
		while(!encontrado && i<inscripciones.size()) {
			Inscripcion inscripcion= inscripciones.get(i);
			if (inscripcion.contieneUsuario(usuario)) {
				encontrado=true;
			}
			i++;
		}
		
		inscripciones.remove(delInscripcion);
		int[] cupos=contarTipoUsuarioInscrito(delInscripcion, torneo);
		torneo.setCantidadJuegoFavoritoDisponible(
								torneo.getCantidadJuegoFavoritoDisponible()-cupos[0]);
		torneo.setCantidadParticipantesDisponible(
								torneo.getCantidadParticipantesDisponible()-cupos[1]);
		
	}
	
	
	//aux inscribir desinscribir
	
	public int[] contarTipoUsuarioInscrito(Inscripcion inscripcion, Torneo torneo) {
		
		String nombreJuegoTorneo= torneo.getJuegoTorneo().getNombre();
		int cuposFavoritos= 0;
		int cuposNormales=0;
		for(Usuario u: inscripcion.getUsuariosInscripcion()) {
			ArrayList<String> juegosFavoritos= u.getJuegosFavoritos();
			
			if (juegosFavoritos.contains(nombreJuegoTorneo)) {
				cuposFavoritos++;
			}
			else {
				cuposNormales++;
			}
		}
		return new int[] {cuposFavoritos,cuposNormales};
	}
		
	
	public void asignarGanadorTorneo(Cliente usuario, Torneo torneo) {

		if (torneo instanceof TorneoAmistoso) {
			usuario.setTieneBonoTorneo(true);
		}
		else if(torneo instanceof TorneoCompetitivo) {
			TorneoCompetitivo torneoCompetitivo= (TorneoCompetitivo) torneo;
			int premio = (int) Math.round(torneoCompetitivo.getDineroRecaudado());
			usuario.setPuntosFidelidad((usuario.getPuntosFidelidad())+premio);
		}
	}
	
	
	
}
