package consola;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cafe.Cafe;
import cafe.Producto;
import dataBase.Cocinero;
import dataBase.DataBase;
import dataBase.Empleado;
import dataBase.GestorSolicitudesPlatillos;
import dataBase.GestorSolicitudesTurno;
import dataBase.Mesero;
import dataBase.Solicitud;
import dataBase.SolicitudSugerenciaPlatillo;
import dataBase.SolicitudTurno;
import dataBase.Usuario;
import exceptions.InventarioInsuficienteException;
import exceptions.JuegoNoDisponibleException;
import generals.DulcesNDados;
import tiendaDeJuegos.GestorInventarioJuegos;
import tiendaDeJuegos.InventarioJuegos;
import tiendaDeJuegos.JuegoDeMesaFisico;
import tiendaDeJuegos.TipoDeJuego;

public class MainAdmin {

    // =====================================================
    // MAIN ADMIN
    // =====================================================

    public static void ejecutar(DulcesNDados app) throws Exception {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda =
            app.getTiendaDeJuegos();

        GestorInventarioJuegos gest = new GestorInventarioJuegos();
    	gest.setInventarioJuegos(tienda);
        
        boolean salir = false;

        while (!salir) {

            System.out.println(
                "\n=== ADMINISTRADOR ==="
            );

            System.out.println(
                "1. Registrar empleado"
            );

            System.out.println(
                "2. Ver empleados"
            );

            System.out.println(
                "3. Agregar platillo"
            );

            System.out.println(
                "4. Ver solicitudes"
            );

            System.out.println(
                "5 Generar informe"
            );

            System.out.println(
                "6. Reparar juego"
            );

            System.out.println(
                "7. Reportar juego robado"
            );

            System.out.println(
                "8. Mover juego ventas → préstamo"
            );

            System.out.println(
                "9. Reabastecer inventario ventas"
            );

            System.out.println(
                "10. Reabastecer inventario préstamo"
            );

            System.out.println(
                "11. Aprobar solicitud horario"
            );

            System.out.println(
                "12. Aceptar sugerencia platillo"
            );

            System.out.println(
                "13. Asignar horario"
            );

            System.out.println(
                "14. Modificar horario"
            );

            System.out.println(
                "15. Consultar estado juego"
            );

            System.out.println(
                "16. Consultar número préstamos"
            );

            System.out.println(
                "17. Consultar fechas préstamo"
            );

            System.out.println(
                "0. Volver"
            );

            String opcion =
                ConsolaUtils.leerString(
                    "Seleccione opción: "
                );

            switch (opcion) {

                case "1":

                    registrarEmpleado(
                        db,
                        cafe
                    );

                    break;

                case "2":

                    verEmpleados(
                        cafe
                    );

                    break;

                case "3":

                    agregarPlatillo(
                        cafe
                    );

                    break;

                case "4":

                    verSolicitudes(
                        db
                    );

                    break;

                case "5":

                    generarInforme(
                        db
                    );

                    break;

                case "6":

                    repararJuego(
                        tienda, gest
                    );

                    break;

                case "7":

                    reportarJuegoRobado(
                        tienda, gest
                    );

                    break;

                case "8":

                    moverJuegoVentasAPrestamo(
                        tienda, gest
                    );

                    break;

                case "9":

                    reabastecerInventarioVentas(
                        tienda,gest
                    );

                    break;

                case "10":

                    reabastecerInventarioPrestamo(
                        tienda, gest
                    );

                    break;

                case "11":

                    aprobarSolicitudHorario(
                        db
                    );

                    break;

                case "12":

                    aceptarSugerenciaPlatillo(
                        cafe,
                        db
                    );

                    break;

                case "13":

                    asignarHorario(
                        db
                    );

                    break;

                case "14":

                    modificarHorario(
                        db
                    );

                    break;

                case "15":

                    consultarEstadoJuego(
                        tienda
                    );

                    break;

                case "16":

                    consultarNumPrestamosJuego(
                        tienda
                    );

                    break;

                case "17":

                    consultarFechaPrestamoJuego(
                        tienda
                    );

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
    }

    // =====================================================
    // EMPLEADOS
    // =====================================================

    static void registrarEmpleado(DataBase db, Cafe cafe) {
    	 System.out.println("\n=== REGISTRAR EMPLEADO ===");

    		    String login = ConsolaUtils.leerString( "Login: ");

    		    String contrasenia = ConsolaUtils.leerString("Contraseña: ");

    		    String codigoDescuento = ConsolaUtils.leerString("Código descuento: " );

    		    int tipo = ConsolaUtils.leerEntero("Tipo (1 = Mesero, 2 = Cocinero): ",1,2);

    		    Empleado nuevoEmpleado;

    		    if (tipo == 1) {

    		        nuevoEmpleado =
    		            new Mesero(
    		                login,
    		                new ArrayList<>(),
    		                contrasenia,
    		                new ArrayList<>(),
    		                codigoDescuento,
    		                new ArrayList<>()
    		            );
    		    }

    		    else {

    		        nuevoEmpleado =
    		            new Cocinero(
    		                login,
    		                new ArrayList<>(),
    		                contrasenia,
    		                new ArrayList<>(),
    		                codigoDescuento
    		            );
    		    }

    		    db.getUsuarios().add(
    		        nuevoEmpleado
    		    );

    		    cafe.getEmpleados().add(
    		        nuevoEmpleado
    		    );

    		    System.out.println(
    		        "Empleado registrado correctamente."
    		    );
    	
    }

    static void verEmpleados(Cafe cafe) {
    	System.out.print(cafe.getEmpleados());
    }

    // =====================================================
    // JUEGOS
    // =====================================================


    static void repararJuego(InventarioJuegos tienda, GestorInventarioJuegos gest) {
    	String idJuegoNuevo = ConsolaUtils.leerString( "Id del juego nuevo: ");
    	String idJuegoViejo = ConsolaUtils.leerString( "Id del juego viejo: ");
    	String nombreJuego = ConsolaUtils.leerString( "Nombre del juego: ");
    	gest.repararJuego(idJuegoNuevo, idJuegoViejo, nombreJuego);
    }

    static void reportarJuegoRobado(InventarioJuegos tienda, GestorInventarioJuegos gest) throws JuegoNoDisponibleException {
    	String idJuego = ConsolaUtils.leerString( "Id del juego: ");
    	String nombreJuego = ConsolaUtils.leerString( "Nombre del juego: ");
    	gest.darJuegoPorRobado(idJuego,nombreJuego);
    }

    static void moverJuegoVentasAPrestamo(InventarioJuegos tienda, GestorInventarioJuegos gest) throws InventarioInsuficienteException, JuegoNoDisponibleException {
    	String nombreJuego = ConsolaUtils.leerString( "Nombre del juego: ");
    	gest.moverJuegoDeVentasAPrestamo(nombreJuego);
    }

    static void reabastecerInventarioVentas(InventarioJuegos tienda, GestorInventarioJuegos gest) {
    	String nombreJuego = ConsolaUtils.leerString("Nombre del juego: ");

    	double precio = ConsolaUtils.leerDouble("Precio: ");

    	Date fechaPublicacion = ConsolaUtils.leerFecha("Fecha publicación: ");

    	int cantidad = ConsolaUtils.leerEntero("Cantidad: ", 1, 1000);

    	String empresaProduccion = ConsolaUtils.leerString("Empresa de producción: ");

    	TipoDeJuego tipoDeJuego = TipoDeJuego.valueOf(ConsolaUtils.leerString("Tipo de juego (CARTAS/TABLERO/ACCION): ").toUpperCase());

    	int minJugadores = ConsolaUtils.leerEntero("Mínimo jugadores: ", 1, 100);

    	int maxJugadores = ConsolaUtils.leerEntero("Máximo jugadores: ", minJugadores, 100);

    	int edadMinima = ConsolaUtils.leerEntero("Edad mínima: ", 0, 100);

    	String caracteristicas = ConsolaUtils.leerString("Características: ");

    	boolean dificil = ConsolaUtils.leerSiNo("¿Es difícil?");
    	gest.comprarJuegosVentas(nombreJuego, precio, fechaPublicacion, cantidad, empresaProduccion, tipoDeJuego, minJugadores, maxJugadores, edadMinima, caracteristicas, dificil);
    }

    static void reabastecerInventarioPrestamo(InventarioJuegos tienda, GestorInventarioJuegos gest) {
    	String nombreJuego = ConsolaUtils.leerString("Nombre del juego: ");

    	double precio = ConsolaUtils.leerDouble("Precio: ");

    	Date fechaPublicacion = ConsolaUtils.leerFecha("Fecha publicación: ");

    	int cantidad = ConsolaUtils.leerEntero("Cantidad: ", 1, 1000);

    	String empresaProduccion = ConsolaUtils.leerString("Empresa de producción: ");

    	TipoDeJuego tipoDeJuego = TipoDeJuego.valueOf(ConsolaUtils.leerString("Tipo de juego (CARTAS/TABLERO/ACCION): ").toUpperCase());

    	int minJugadores = ConsolaUtils.leerEntero("Mínimo jugadores: ", 1, 100);

    	int maxJugadores = ConsolaUtils.leerEntero("Máximo jugadores: ", minJugadores, 100);

    	int edadMinima = ConsolaUtils.leerEntero("Edad mínima: ", 0, 100);

    	String caracteristicas = ConsolaUtils.leerString("Características: ");

    	boolean dificil = ConsolaUtils.leerSiNo("¿Es difícil?");
    	gest.comprarJuegosPrestamo(nombreJuego, precio, fechaPublicacion, cantidad, empresaProduccion, tipoDeJuego, minJugadores, maxJugadores, edadMinima, caracteristicas, dificil);
    }

    // =====================================================
    // CAFÉ
    // =====================================================

    static void agregarPlatillo(Cafe cafe) {
    	String nombre = ConsolaUtils.leerString("Nombre: ");
    	double precio = ConsolaUtils.leerDouble("Precio: ");
    	Producto p = new Producto(nombre, precio);
    	cafe.agregarPlatillo(p);
    }

    static void aceptarSugerenciaPlatillo(Cafe cafe,DataBase db) {
    	GestorSolicitudesPlatillos gestsp = new GestorSolicitudesPlatillos();
    	verSolicitudes(db);
    	int index = ConsolaUtils.leerEntero("# de solicitud (siendo la primera = 0): ", 0, db.getSolicitudes().size());
    	SolicitudSugerenciaPlatillo sol = (SolicitudSugerenciaPlatillo) db.getSolicitudes().get(index);
    	gestsp.aceptarSolicitud(cafe,sol);
    }

    // =====================================================
    // SOLICITUDES
    // =====================================================

    static void verSolicitudes(DataBase db) {
    	System.out.print(db.getSolicitudes());
    }

    static void aprobarSolicitudHorario(DataBase db) throws Exception {
    	GestorSolicitudesTurno gestst = new GestorSolicitudesTurno();
    	verSolicitudes(db);
    	int index = ConsolaUtils.leerEntero("# de solicitud (siendo la primera = 0): ", 0, db.getSolicitudes().size());
    	Solicitud sol = db.getSolicitudes().get(index);
    	gestst.aceptarSolicitud((SolicitudTurno) sol);
    }

    // =====================================================
    // TURNOS
    // =====================================================

    static void asignarHorario(DataBase db) {
    	
    }

    static void modificarHorario(DataBase db) {

    }

    // =====================================================
    // CONSULTAS
    // =====================================================

    static void consultarEstadoJuego(InventarioJuegos tienda) {
    	String nombre = ConsolaUtils.leerString("Inventario a recorrer (VENTAS/PRESTAMOS): ").toLowerCase();
    	String juego = ConsolaUtils.leerString("Ingrese el id del juego: ");
    	String nombrejuego = ConsolaUtils.leerString("Ingrese el nombre del juego: ").toUpperCase();
    	HashMap<String, ArrayList<JuegoDeMesaFisico>> ventas = tienda.getInventarioVenta();
    	HashMap<String, ArrayList<JuegoDeMesaFisico>> prestamos = tienda.getInventarioVenta();
    	
    	
    	if (nombre.equals("ventas")) {
	    	ArrayList<JuegoDeMesaFisico> jjff = ventas.get(nombrejuego.toUpperCase());
	    	for(JuegoDeMesaFisico jf: jjff) {
	    		if (jf.getIdJuego().equals(juego)) {
	    			System.out.print("El estado del juego es" + jf.getEstadoDelJuego());
	    		}
	    	}
    	}
    	
    	else if (nombre.equals("prestamos")) {
	    	ArrayList<JuegoDeMesaFisico> jjff = prestamos.get(nombrejuego.toUpperCase());
	    	for(JuegoDeMesaFisico jf: jjff) {
	    		if (jf.getIdJuego().equals(juego)) {
	    			System.out.print("El estado del juego es" + jf.getEstadoDelJuego());
	    		}
	    	}
    	}
    }

    static void consultarNumPrestamosJuego(InventarioJuegos tienda) {

    }

    static void consultarFechaPrestamoJuego(InventarioJuegos tienda) {

    }

    // =====================================================
    // INFORME
    // =====================================================

    static void generarInforme(DataBase db) {

    }
}