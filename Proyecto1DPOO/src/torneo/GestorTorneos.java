package torneo;


import java.util.ArrayList;

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
			String nombreJuegoTorneo= torneo.getJuegoTorneo().getNombre();
			
			int cuposFavoritos= 0;
			int cuposNormales=0;
			for(Usuario u: usuarios) {
				ArrayList<String> juegosFavoritos= u.getJuegosFavoritos();
				
				if (juegosFavoritos.contains(nombreJuegoTorneo)) {
					cuposFavoritos++;
				}
				else {
					cuposNormales++;
				}
			}
			if (cuposFavoritos>torneo.getCantidadJuegoFavoritoDisponible()
					|| cuposNormales>torneo.getCantidadParticipantesDisponible()) {
				throw new CupoNoDisponibleException();
				
			}
			else {
				torneo.getInscripciones().add(inscripcion);
			}
			
		} catch (RestriccionJugadoresException e) {
			System.out.println(e.getMessage());;
		}
	}
	
	
	
	
	
}
