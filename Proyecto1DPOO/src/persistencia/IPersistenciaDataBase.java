package persistencia;

import java.io.IOException;
import dataBase.DataBase; 

public interface IPersistenciaDataBase {
	
    /**
     * Carga la base de datos general (usuarios, facturas, informes)
     * desde el archivo indicado.
     * @param archivo ruta del archivo
     * @return DataBase cargado
     * @throws IOException si hay error leyendo el archivo
     * @throws ClassNotFoundException si la clase no coincide con lo serializado
     */
	DataBase cargarDataBase(String archivo) 
		throws IOException, ClassNotFoundException ;
	
	
	 /**
     * Guarda el estado del cafe en el archivo indicado.
     * @param archivo ruta del archivo
     * @param cafe objeto a persistir
     * @throws IOException si hay error escribiendo el archivo
     */
	
	void guardarDataBase(String archivo, DataBase dataBase)
	throws IOException;
}
