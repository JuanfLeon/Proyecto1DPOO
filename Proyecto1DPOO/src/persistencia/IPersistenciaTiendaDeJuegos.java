package persistencia;

import java.io.IOException;

import tiendaDeJuegos.InventarioJuegos ;

public interface IPersistenciaTiendaDeJuegos {
	
	
	/**
	 * Cargar el inventario de juegos desde el archivo.
	 * @param archivo ruta 
	 * @return Iventario de juegos cargado
	 * @throws IOException por si hay un error leyendo el archivo
	 * @throws ClassNotFoundException si la clase no coincide 
	 */
	InventarioJuegos cargarTiendaDeJuegos(String archivo)
			throws IOException, ClassNotFoundException;
	
	
	/**
	 * Esto guarda el inventario de juegos en el archivo indicado
	 * @param archivo ruta del archivo
	 * @param inventario 
	 * @throws IOException si hay error escibiendo el archivo
	 */
	
	void guardarTiendaDeJuegos(String archivo, InventarioJuegos inventario)
		throws IOException ;
}
