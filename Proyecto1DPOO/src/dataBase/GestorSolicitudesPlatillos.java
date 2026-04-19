package dataBase;

import java.io.Serializable;

import cafe.Cafe;
import cafe.Producto;

public class GestorSolicitudesPlatillos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void aceptarSolicitud(Cafe cafe, SolicitudSugerenciaPlatillo solicitud) {
		
		Producto platillo = (Producto) solicitud.getPlatilloSugerido();
		cafe.agregarPlatillo(platillo);
	}
}
