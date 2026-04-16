package tiendaDeJuegos;

import java.util.Date;

public class JuegoDeMesaFisico extends JuegoDeMesa{
	private String estadoDelJuego;
	private boolean prestado;
	
	public String getEstadoDelJuego() {
		return estadoDelJuego;
	}
	public void setEstadoDelJuego(String estadoDelJuego) {
		this.estadoDelJuego = estadoDelJuego;
	}
	public boolean isPrestado() {
		return prestado;
	}
	public void setPrestado(boolean prestado) {
		this.prestado = prestado;
	}
	
	public JuegoDeMesaFisico(String idJuego, String nombre, Date anoPublicacion, String empresaProduccion,
			TipoDeJuego tipoDeJuego, int minJugadores, int maxJugadores, int edadMinima, String caracteristicas,
			boolean dificil, String estadoDelJuego, boolean prestado) {
		
		super(idJuego, nombre, anoPublicacion, empresaProduccion, tipoDeJuego, minJugadores, maxJugadores, edadMinima,
				caracteristicas, dificil);
		this.estadoDelJuego = estadoDelJuego;
		this.prestado = prestado;
	}
	
}
