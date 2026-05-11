package torneo;

import java.util.ArrayList;

import generals.DiaSemana;
import tiendaDeJuegos.JuegoDeMesa;

public class TorneoAmistoso extends Torneo{

	public TorneoAmistoso(ArrayList<Inscripcion> inscripciones, DiaSemana diaTorneo, JuegoDeMesa juegoTorneo,
			int cantidadParticipantes) {
		super(inscripciones, diaTorneo, juegoTorneo, cantidadParticipantes);
	}

}
