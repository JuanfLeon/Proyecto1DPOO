package generals;

import java.io.IOException;

import cafe.Cafe;
import dataBase.DataBase;
import persistencia.CentralPersistencia;
import persistencia.IPersistenciaCafe;
import persistencia.IPersistenciaDataBase;
import persistencia.IPersistenciaTiendaDeJuegos;
import tiendaDeJuegos.InventarioJuegos;

public class DulcesNDados {

	private Cafe cafe;
	private InventarioJuegos tiendaDeJuegos;
	private DataBase dataBase;
	private CentralPersistencia centralPersistencia;
	
	
	
		
		
	public DulcesNDados(Cafe cafe, InventarioJuegos tiendaDeJuegos, DataBase dataBase) {
		this.cafe = cafe;
		this.tiendaDeJuegos = tiendaDeJuegos;
		this.dataBase = dataBase;
		this.centralPersistencia= new CentralPersistencia();
	}


	
	//METODO MAIN POR INSTRUCCIONES DEL PROFESOR NO IMPLEMENTADO
	public static void main(String[] args) {
	}
	
	
	//Getters y Setters
	public Cafe getCafe() {
		return cafe;
	}

	public void setCafe(Cafe cafe) {
		this.cafe = cafe;
	}

	public InventarioJuegos getTiendaDeJuegos() {
		return tiendaDeJuegos;
	}

	public void setTiendaDeJuegos(InventarioJuegos tienda) {
		this.tiendaDeJuegos = tienda;
	}

	public DataBase getDataBase() {
		return dataBase;
	}

	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}

	public CentralPersistencia getCentralPersistencia() {
		return centralPersistencia;
	}

	public void setCentralPersistencia(CentralPersistencia centralPersistencia) {
		this.centralPersistencia = centralPersistencia;
	}

	
	//LECTURA DE DATOS
	private void cargarDatosDataBase(String ruta){
		IPersistenciaDataBase interfazPersistencia= CentralPersistencia.getPersistenciaDataBaseSerializacion("BIN");
		try {
			DataBase dataBase= interfazPersistencia.cargarDataBase(ruta);
			this.setDataBase(dataBase);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	private void cargarDatosTienda(String ruta) {
		IPersistenciaTiendaDeJuegos interfazPersistencia= CentralPersistencia.getPersistenciaTiendaDeJuegos("BIN");
		try {
			InventarioJuegos tienda= interfazPersistencia.cargarTiendaDeJuegos(ruta);
			this.setTiendaDeJuegos(tienda);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public void cargarDatosCafe(String ruta) {
		IPersistenciaCafe interfazPersistencia= CentralPersistencia.getPersistenciaCafe("BIN");
		try {
			Cafe cafe= interfazPersistencia.cargarCafe(ruta);
			this.setCafe(cafe);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
		
	//GUARDAR INFORMACIÓN
	public void guardarDatosDataBase(String archivo, DataBase dataBase) {
		IPersistenciaDataBase interfazPersistencia= CentralPersistencia.getPersistenciaDataBaseSerializacion("BIN");
		try {
			interfazPersistencia.guardarDataBase(archivo, dataBase);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void guardarDatosTiendaDeJuegos(String archivo, InventarioJuegos inventario) {
		IPersistenciaTiendaDeJuegos interfazPersistencia= CentralPersistencia.getPersistenciaTiendaDeJuegos("BIN");
		try {
			interfazPersistencia.guardarTiendaDeJuegos(archivo, inventario);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	public void guardarDatosCafe(String archivo, Cafe cafe) {
		IPersistenciaCafe interfazPersistencia= CentralPersistencia.getPersistenciaCafe("BIN");
		try {
			interfazPersistencia.guardarCafe(archivo, cafe);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
}

