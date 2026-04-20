package persistencia;

public class CentralPersistencia {

	public static final String BIN = "Bin" ;
	
	public CentralPersistencia() {}
	
	/**
	 * Esto debería retornar la implementacion de persistencia para TDJ.
	 * @param tipo
	 * @throws IllegalArgmentException si no se reconoce el tipo
	 * @return IPersisteciaTiendaDeJuegos
	 */
	public static IPersistenciaTiendaDeJuegos getPersistenciaTiendaDeJuegos(String tipo) {
		if(BIN.equals(tipo)) {
			return new PersistenciaTiendaDeJuegosSerializacion();
			}
		
		throw new IllegalArgumentException ("El tipo de persistencia no es reconocido " + tipo);
		
	}

	/**
	 * Esto debería retornar la implementacion de persistencia de Cafe.
	 * @param tipo
	 * @return IPersistenciaCafe 
	 * @throws IllegalArgumentException si no se reconoce el tipo
	 */
	public static IPersistenciaCafe getPersistenciaCafe(String tipo) {
		
		if(BIN.equals(tipo)) {
			return new PersistenciaCafeSerializacion();
		}
		throw new IllegalArgumentException ("El tipo de persistencia no es reconocido " + tipo);
	}
	
	/**
	 * Esto debería retornar la implementacion de persistencia de DataBase.
	 * @param tipo
	 * @return IPersistenciaDataBase 
	 * @throws IllegalArgumentException si no se reconoce el tipo
	 */
	public static IPersistenciaDataBase getPersistenciaDataBaseSerializacion(String tipo) {
		
		if(BIN.equals(tipo)) {
			return new PersistenciaDataBaseSerializacion();
		}
		throw new IllegalArgumentException ("El tipo de persistencia no es reconocido " + tipo);
	}
	
}