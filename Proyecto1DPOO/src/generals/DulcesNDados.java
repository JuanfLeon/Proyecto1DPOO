package generals;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import cafe.Cafe;
import consola.ConsolaUtils;
import consola.MainAdmin;
import consola.MainCliente;
import consola.MainEmpleado;
import dataBase.DataBase;
import dataBase.Turno;
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

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public DulcesNDados() {

        this.centralPersistencia =
            new CentralPersistencia();

        // Objetos por defecto
        // (se reemplazan al cargar serialización)

        this.cafe = new Cafe(
            50,
            new ArrayList<>(),
            new ArrayList<>()
        );

        this.dataBase = new DataBase(
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>(),
            new ArrayList<>()
        );

        this.tiendaDeJuegos =
            new InventarioJuegos();
    }

    // =====================================================
    // MAIN
    // =====================================================

    public static void main(String[] args) throws Exception {

        // Crear carpeta datos
        new File("datos").mkdirs();

        DulcesNDados app =
            new DulcesNDados();

        // =========================================
        // CARGAR DATOS SERIALIZADOS
        // =========================================

        app.cargarDatosDataBase(
            "datos/dataBase.bin"
        );

        app.cargarDatosCafe(
            "datos/cafe.bin"
        );

        app.cargarDatosTienda(
            "datos/tiendaDeJuegos.bin"
        );

        // =========================================
        // SISTEMA
        // =========================================

        boolean salir = false;

        while (!salir) {

            System.out.println(
                "\n=== DULCES N' DADOS ==="
            );

            System.out.println(
                "1. Administrador"
            );

            System.out.println(
                "2. Empleado"
            );

            System.out.println(
                "3. Cliente"
            );

            System.out.println(
                "0. Salir"
            );

            String opcion =
                ConsolaUtils.leerString(
                    "Seleccione una opción: "
                );

            switch (opcion) {

                case "1":

                    MainAdmin.ejecutar(app);
                    break;

                case "2":

                    MainEmpleado.ejecutar(app);
                    break;

                case "3":

                    MainCliente.ejecutar(app);
                    break;

                case "0":

                    salir = true;
                    break;

                default:

                    System.out.println(
                        "Opción inválida."
                    );
            }
        }

        // =========================================
        // GUARDAR DATOS
        // =========================================

        app.guardarDatosDataBase(
            "datos/dataBase.bin",
            app.getDataBase()
        );

        app.guardarDatosCafe(
            "datos/cafe.bin",
            app.getCafe()
        );

        app.guardarDatosTiendaDeJuegos(
            "datos/tiendaDeJuegos.bin",
            app.getTiendaDeJuegos()
        );

        System.out.println(
            "Datos guardados correctamente."
        );
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
	public void cargarDatosDataBase(String ruta){
		IPersistenciaDataBase interfazPersistencia= CentralPersistencia.getPersistenciaDataBaseSerializacion("BIN");
		try {
			DataBase dataBase= interfazPersistencia.cargarDataBase(ruta);
			this.setDataBase(dataBase);
		} catch (ClassNotFoundException | IOException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public void cargarDatosTienda(String ruta) {
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

