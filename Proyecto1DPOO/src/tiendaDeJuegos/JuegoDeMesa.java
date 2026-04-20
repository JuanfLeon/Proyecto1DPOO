package tiendaDeJuegos;

import java.io.Serializable;
import java.util.Date;

import cafe.Producto;

public class JuegoDeMesa extends Producto implements Serializable{
	private static final long serialVersionUID = 1L ;
	protected String idJuego;
	protected Date anoPublicacion;
	protected String empresaProduccion;
	protected TipoDeJuego tipoDeJuego;
	protected int minJugadores;
	protected int maxJugadores;
	protected int edadMinima;
	protected String caracteristicas;
	protected boolean dificil;
	
	public String getIdJuego() {
		return idJuego;
	}

	public void setIdJuego(String idJuego) {
		this.idJuego = idJuego;
	}

	public Date getAnoPublicacion() {
		return anoPublicacion;
	}

	public void setAnoPublicacion(Date anoPublicacion) {
		this.anoPublicacion = anoPublicacion;
	}

	public String getEmpresaProduccion() {
		return empresaProduccion;
	}

	public void setEmpresaProduccion(String empresaProduccion) {
		this.empresaProduccion = empresaProduccion;
	}

	public TipoDeJuego getTipoDeJuego() {
		return tipoDeJuego;
	}

	public void setTipoDeJuego(TipoDeJuego tipoDeJuego) {
		this.tipoDeJuego = tipoDeJuego;
	}

	public int getMinJugadores() {
		return minJugadores;
	}

	public void setMinJugadores(int minJugadores) {
		this.minJugadores = minJugadores;
	}

	public int getMaxJugadores() {
		return maxJugadores;
	}

	public void setMaxJugadores(int maxJugadores) {
		this.maxJugadores = maxJugadores;
	}

	public int getEdadMinima() {
		return edadMinima;
	}

	public void setEdadMinima(int edadMinima) {
		this.edadMinima = edadMinima;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public boolean isDificil() {
		return dificil;
	}

	public void setDificil(boolean dificil) {
		this.dificil = dificil;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return this.nombre;
	}

	public JuegoDeMesa(String idJuego, String nombre,double precio ,Date anoPublicacion, String empresaProduccion,
			TipoDeJuego tipoDeJuego, int minJugadores, int maxJugadores, int edadMinima, String caracteristicas,
			boolean dificil) {
		super(nombre,precio);
		this.idJuego = idJuego;
		this.anoPublicacion = anoPublicacion;
		this.empresaProduccion = empresaProduccion;
		this.tipoDeJuego = tipoDeJuego;
		this.minJugadores = minJugadores;
		this.maxJugadores = maxJugadores;
		this.edadMinima = edadMinima;
		this.caracteristicas = caracteristicas;
		this.dificil = dificil;
	}
	
}
