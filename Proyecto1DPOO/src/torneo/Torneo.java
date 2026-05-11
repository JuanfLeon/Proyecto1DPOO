package torneo;

import java.util.ArrayList;

import dataBase.Usuario;
import generals.DiaSemana;
import tiendaDeJuegos.JuegoDeMesa;

public abstract class Torneo {

	protected ArrayList<Inscripcion> inscripciones;
	protected DiaSemana diaTorneo;
	protected JuegoDeMesa juegoTorneo;
	protected int cantidadParticipantesDisponible;
	protected int cantidadJuegoFavoritoDisponible;
	protected Usuario ganador;
	
	
	public Torneo(ArrayList<Inscripcion> inscripciones, DiaSemana diaTorneo, JuegoDeMesa juegoTorneo,
			int cantidadParticipantes) {
		this.inscripciones = inscripciones;
		this.diaTorneo = diaTorneo;
		this.juegoTorneo = juegoTorneo;
		this.cantidadJuegoFavoritoDisponible= (int) Math.ceil(cantidadParticipantes*0.2);
		this.cantidadParticipantesDisponible = cantidadParticipantes-cantidadJuegoFavoritoDisponible;
		this.ganador=null;
	}


	public ArrayList<Inscripcion> getInscripciones() {
		return inscripciones;
	}


	public void setInscripciones(ArrayList<Inscripcion> inscripciones) {
		this.inscripciones = inscripciones;
	}


	public DiaSemana getDiaTorneo() {
		return diaTorneo;
	}


	public void setDiaTorneo(DiaSemana diaTorneo) {
		this.diaTorneo = diaTorneo;
	}


	public JuegoDeMesa getJuegoTorneo() {
		return juegoTorneo;
	}


	public void setJuegoTorneo(JuegoDeMesa juegoTorneo) {
		this.juegoTorneo = juegoTorneo;
	}


	public int getCantidadParticipantesDisponible() {
		return cantidadParticipantesDisponible;
	}


	public void setCantidadParticipantesDisponible(int cantidadParticipantesDisponible) {
		this.cantidadParticipantesDisponible = cantidadParticipantesDisponible;
	}


	public int getCantidadJuegoFavoritoDisponible() {
		return cantidadJuegoFavoritoDisponible;
	}


	public void setCantidadJuegoFavoritoDisponible(int cantidadJuegoFavoritoDisponible) {
		this.cantidadJuegoFavoritoDisponible = cantidadJuegoFavoritoDisponible;
	}


	public Usuario getGanador() {
		return ganador;
	}


	public void setGanador(Usuario ganador) {
		this.ganador = ganador;
	}
	
	
}
