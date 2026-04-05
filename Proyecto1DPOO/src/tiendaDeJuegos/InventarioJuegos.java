package tiendaDeJuegos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exceptions.JuegoNoDisponibleException;

public class InventarioJuegos {
	private Map<String,Integer> inventarioPrestamo;
	private Map<String,Integer> inventarioVentas;
	private ArrayList<Prestamo> historialPrestamos;
	
	public Map<String, Integer> buscarJuegosEnVentas(List<String> juegosBuscados) throws JuegoNoDisponibleException {
	    Map<String, Integer> stockBuscado = new HashMap<>();

	    for (String juegoBuscado : juegosBuscados) {
	        stockBuscado.put(juegoBuscado, stockBuscado.getOrDefault(juegoBuscado, 0) + 1);
	    }

	    validarDisponibilidadJuego(stockBuscado, TipoInventario.VENTAS);

	    return stockBuscado;
	}


	public void validarDisponibilidadJuego(Map<String, Integer> solicitud, TipoInventario tipoInventario) 
	        throws JuegoNoDisponibleException {

	    for (String nombreJuego : solicitud.keySet()) {
	        int cantidadSolicitada = solicitud.get(nombreJuego);
	        int disponibles;

	        if (tipoInventario == TipoInventario.VENTAS) {
	            disponibles = inventarioVentas.getOrDefault(nombreJuego, 0);
	        } else {
	            disponibles = inventarioPrestamo.getOrDefault(nombreJuego, 0);
	        }

	        if (disponibles < cantidadSolicitada) {
	            throw new JuegoNoDisponibleException(nombreJuego);
	        }
	    }
	
		}
	public void reducirStockVentas(Map<String, Integer> compra) throws JuegoNoDisponibleException {
		//Evita reducciones invalidad del stock
	    validarDisponibilidadJuego(compra, TipoInventario.VENTAS);

	    for (String nombreJuego : compra.keySet()) {
	        int cantidad = compra.get(nombreJuego);
	        int disponibles = inventarioVentas.get(nombreJuego);

	        inventarioVentas.put(nombreJuego, disponibles - cantidad);
	    }
	}
	public void aumentarStock(Map<String, Integer> nuevosJuegos, TipoInventario tipoInventario) {

	    for (String nombreJuego : nuevosJuegos.keySet()) {
	        int cantidadNueva = nuevosJuegos.get(nombreJuego);
	        int cantidadActual;

	        if (tipoInventario == TipoInventario.VENTAS) {
	            cantidadActual = inventarioVentas.getOrDefault(nombreJuego, 0);
	            inventarioVentas.put(nombreJuego, cantidadActual + cantidadNueva);
	        } else {
	            cantidadActual = inventarioPrestamo.getOrDefault(nombreJuego, 0);
	            inventarioPrestamo.put(nombreJuego, cantidadActual + cantidadNueva);
	        }
	    }
	}
}
	

