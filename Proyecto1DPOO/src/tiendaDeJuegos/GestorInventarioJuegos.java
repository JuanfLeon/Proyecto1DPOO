package tiendaDeJuegos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Date;

import exceptions.EstadoInvalidoException;
import exceptions.JuegoNoDisponibleException;
import generals.generadorID;

public class GestorInventarioJuegos {
	private InventarioJuegos inventarioJuegos;

	//Getters y Setters
	public InventarioJuegos getInventarioJuegos() {
		return inventarioJuegos;
	}

	public void setInventarioJuegos(InventarioJuegos inventarioJuegos) {
		this.inventarioJuegos = inventarioJuegos;
	}
	
	public void darJuegoPorRobado(String idJuego, String nombreJuego) throws JuegoNoDisponibleException {

	    HashMap<String, ArrayList<JuegoDeMesaFisico>> juegosPrestamo =
	            inventarioJuegos.getInventarioPrestamo();

	    ArrayList<JuegoDeMesaFisico> lista = juegosPrestamo.get(nombreJuego);

	    if (lista == null) {
	       	throw new JuegoNoDisponibleException(idJuego);
	    }

	    Iterator<JuegoDeMesaFisico> it = lista.iterator();

	    while (it.hasNext()) {
	        JuegoDeMesaFisico juego = it.next();

	        if (idJuego.equals(juego.getIdJuego())) {
	            it.remove(); 
	            break;
	        }
	    }
	}

	
	public void comprarJuegos(String nombreJuego, double precio, Date fechaPublicacion,   int cantidad, String empresaProduccion,
			TipoDeJuego tipoDeJuego, int minJugadores, int maxJugadores, int edadMinima, String caracteristicas,
			boolean dificil) {
		
		int i=0;
		while (i<cantidad){		
			String id=generadorID.generarIDJuego(nombreJuego);
			JuegoDeMesaFisico juego= new JuegoDeMesaFisico( id, 
															nombreJuego,
															precio,
															fechaPublicacion,
															empresaProduccion, 
															tipoDeJuego, 
															minJugadores, 
															maxJugadores, 
															edadMinima, 
															caracteristicas, 
															dificil, 
															"nuevo", //ESTADO
															false); //PRESTADO
			inventarioJuegos.agregarJuegoInventario(juego, nombreJuego, TipoInventario.VENTAS);
		}
	}
	public void repararJuego(String idJuegoNuevo, String idJuegoViejo, String nombreJuego) {
		
		try {
			inventarioJuegos.validarDisponibilidadJuegos(nombreJuego, 1, TipoInventario.VENTAS);
			inventarioJuegos.validarDisponibilidadJuegos(nombreJuego, 1, TipoInventario.PRESTAMO);
			
			JuegoDeMesaFisico juegoNuevo= inventarioJuegos.eliminarJuegoInventario(idJuegoNuevo, nombreJuego, TipoInventario.VENTAS);
			inventarioJuegos.eliminarJuegoInventario(idJuegoViejo, nombreJuego, TipoInventario.PRESTAMO);
			inventarioJuegos.agregarJuegoInventario(juegoNuevo, nombreJuego, TipoInventario.PRESTAMO);
			
			
			inventarioJuegos.agregarJuegoInventario(juegoNuevo,nombreJuego, TipoInventario.PRESTAMO);
		} catch (EstadoInvalidoException e) {
			e.getMessage();
		} catch (JuegoNoDisponibleException e) {
			e.getMessage();
		}
		
	}
}
