package torneo;

import java.util.ArrayList;

import generals.DiaSemana;
import tiendaDeJuegos.JuegoDeMesa;

public class TorneoCompetitivo extends Torneo{
	private final int precioEntrada;
	private double dineroRecaudado;

	public TorneoCompetitivo(ArrayList<Inscripcion> inscripciones, DiaSemana diaTorneo, JuegoDeMesa juegoTorneo,
			int cantidadParticipantes, int precioEntrada, double dineroRecaudado) {
		
		super(inscripciones, diaTorneo, juegoTorneo, cantidadParticipantes);
		this.precioEntrada = precioEntrada;
		this.dineroRecaudado = dineroRecaudado;
	}
	public double getDineroRecaudado() {
		return dineroRecaudado;
	}
	public void setDineroRecaudado(double dineroRecaudado) {
		this.dineroRecaudado = dineroRecaudado;
	}
	public int getPrecioEntrada() {
		return precioEntrada;
	}
	
	
	
	
	
}
