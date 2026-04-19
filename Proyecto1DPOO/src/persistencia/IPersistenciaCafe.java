package persistencia;

import java.io.IOException;

import cafe.Cafe;

public interface IPersistenciaCafe {
	 
    /**
     * Carga el estado del cafe desde el archivo indicado.
     * @param archivo ruta del archivo
     * @return CafeStructure cargado
     * @throws IOException si hay error leyendo el archivo
     * @throws ClassNotFoundException si la clase no coincide con lo serializado
     */
    Cafe cargarCafe(String archivo)
        throws IOException, ClassNotFoundException;
 
    /**
     * Guarda el estado del cafe en el archivo indicado.
     * @param archivo ruta del archivo
     * @param cafe objeto a persistir
     * @throws IOException si hay error escribiendo el archivo
     */
    void guardarCafe(String archivo, Cafe cafe)
        throws IOException;
}