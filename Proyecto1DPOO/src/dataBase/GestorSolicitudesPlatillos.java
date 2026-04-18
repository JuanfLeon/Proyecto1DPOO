package dataBase;

import java.util.ArrayList;

import cafe.Cafe;
import cafe.Producto;

public class GestorSolicitudesPlatillos {

	public void aceptarSolicitud(SolicitudSugerenciaPlatillo solicitud) {
		
		String platillo = solicitud.getPlatilloSugerido();
		ArrayList<Producto> catalogo = Cafe.getCatalogoPlatillos();
	}
}
