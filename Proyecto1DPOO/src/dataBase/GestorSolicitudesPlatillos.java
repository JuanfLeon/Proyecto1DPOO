package dataBase;

import cafe.Cafe;
import cafe.Producto;

public class GestorSolicitudesPlatillos {

	public void aceptarSolicitud(Cafe cafe, SolicitudSugerenciaPlatillo solicitud) {
		
		Producto platillo = (Producto) solicitud.getPlatilloSugerido();
		cafe.agregarPlatillo(platillo);
	}
}
