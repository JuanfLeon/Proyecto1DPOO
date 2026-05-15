package consola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import cafe.Cafe;
import cafe.Producto;
import dataBase.Cocinero;
import dataBase.DataBase;
import dataBase.Empleado;
import dataBase.Factura;
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
import tiendaDeJuegos.Prestamo;
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
    	System.out.println("\n--- ASIGNAR HORARIO ---");

    	generals.DiaSemana[] dias = generals.DiaSemana.values();
    	System.out.println("Dias disponibles:");
    	for (int i = 0; i < dias.length; i++) {
    		System.out.println((i + 1) + ". " + dias[i]);
    	}
    	
    	int opDia = ConsolaUtils.leerEntero("Seleccione el dia: ", 1, dias.length);
    	generals.DiaSemana dia = dias[opDia - 1];
    	int indiceDia = opDia - 1;

    	if (db.getTurnos()[indiceDia] != null) {
    		System.out.println("Ya existe un turno para " + dia + ". Use 'Modificar horario' para editarlo.");
    		return;
    	}

    	ArrayList<dataBase.Mesero> meserosDisponibles = new ArrayList<>();
    	for (dataBase.Usuario u : db.getUsuarios()) {
    		if (u instanceof dataBase.Mesero) meserosDisponibles.add((dataBase.Mesero) u);
    	}
    	if (meserosDisponibles.size() < 2) {
    		System.out.println("Se necesitan al menos 2 meseros registrados para crear un turno.");
    		return;
    	}

    	System.out.println("\nMeseros disponibles:");
    	for (int i = 0; i < meserosDisponibles.size(); i++) {
    		System.out.println((i + 1) + ". " + meserosDisponibles.get(i).getLogin());
    	}
    	int cantMeseros = ConsolaUtils.leerEntero("Cuantos meseros asignar (min 2): ",
    			2, meserosDisponibles.size());
    	ArrayList<dataBase.Mesero> meserosAsignados = new ArrayList<>();
    	for (int i = 0; i < cantMeseros; i++) {
    		int op = ConsolaUtils.leerEntero("Mesero " + (i + 1) + ": ", 1, meserosDisponibles.size());
    		dataBase.Mesero m = meserosDisponibles.get(op - 1);
    		if (meserosAsignados.contains(m)) {
    			System.out.println("Ya fue seleccionado. Elija otro.");
    			i--;
    		} else {
    			meserosAsignados.add(m);
    		}
    	}

    	ArrayList<dataBase.Cocinero> cocinerosDisponibles = new ArrayList<>();
    	for (dataBase.Usuario u : db.getUsuarios()) {
    		if (u instanceof dataBase.Cocinero) cocinerosDisponibles.add((dataBase.Cocinero) u);
    	}
    	if (cocinerosDisponibles.isEmpty()) {
    		System.out.println("Se necesita al menos 1 cocinero registrado para crear un turno.");
    		return;
    	}

    	System.out.println("\nCocineros disponibles:");
    	for (int i = 0; i < cocinerosDisponibles.size(); i++) {
    		System.out.println((i + 1) + ". " + cocinerosDisponibles.get(i).getLogin());
    	}
    	int cantCocineros = ConsolaUtils.leerEntero("Cuantos cocineros asignar (min 1): ",
    			1, cocinerosDisponibles.size());
    	ArrayList<dataBase.Cocinero> cocinerosAsignados = new ArrayList<>();
    	for (int i = 0; i < cantCocineros; i++) {
    		int op = ConsolaUtils.leerEntero("Cocinero " + (i + 1) + ": ", 1, cocinerosDisponibles.size());
    		dataBase.Cocinero c = cocinerosDisponibles.get(op - 1);
    		if (cocinerosAsignados.contains(c)) {
    			System.out.println("Ya fue seleccionado. Elija otro.");
    			i--;
    		} else {
    			cocinerosAsignados.add(c);
    		}
    	}

    	try {
    		dataBase.Turno nuevoTurno = new dataBase.Turno(dia, meserosAsignados, cocinerosAsignados);
    		for (dataBase.Mesero m : meserosAsignados) m.agregarTurno(nuevoTurno);
    		for (dataBase.Cocinero c : cocinerosAsignados) c.agregarTurno(nuevoTurno);
    		db.getTurnos()[indiceDia] = nuevoTurno;
    		System.out.println("Turno del " + dia + " creado con " + cantMeseros + " mesero(s) y " + cantCocineros + " cocinero(s).");
    	} 
    	catch (Exception e) {
    		System.out.println("Error al crear turno: " + e.getMessage());
    	}
    }

    static void modificarHorario(DataBase db) {
    	System.out.println("\n---MODIFICAR HORARIO---");
    	
    	generals.DiaSemana[] dias = generals.DiaSemana.values();
    	ArrayList<Integer> indicesConTurno = new ArrayList<>();
    	System.out.println("Turnos actuales: ");
    	for (int i = 0; i<db.getTurnos().length; i++) {
    		if (db.getTurnos()[i] != null) {
    			System.out.println((indicesConTurno.size() +1) + ". " + db.getTurnos()[i]);
    			indicesConTurno.add(i);
    		}
    	}
    	
    	if (indicesConTurno.isEmpty()) {
    		System.out.println("No hay turnos configurados. Use 'Asignar Horario' primero");
    		return;
    	}
    	
    	int opTurno = ConsolaUtils.leerEntero("Seleccione el turno a modificar: ", 1, indicesConTurno.size());
    	int indiceTurno = indicesConTurno.get(opTurno -1);
    	dataBase.Turno turno = db.getTurnos()[indiceTurno]; 
    	
    	System.out.println("\n ¿Qué desea modificar?");
    	System.out.println("1. Agregar mesero");
    	System.out.println("2. Eliminar mesero");
    	System.out.println("3. Agregar cocinero");
    	System.out.println("4. Eliminar cocinero");
    	String accion = ConsolaUtils.leerString("Seleecione: ");

    	try {
    		if (accion.equals("1")) {
    			ArrayList<dataBase.Mesero> disponibles = new ArrayList<>();
    			for (dataBase.Usuario u : db.getUsuarios()) {
    				if (u instanceof dataBase.Mesero && !turno.getMeseros().contains(u))
    					disponibles.add((dataBase.Mesero) u);
    			}
    			if (disponibles.isEmpty()) {
    				System.out.println("No hay meseros disponibles para agregar.");
    				return;
    			}
    			for (int i = 0; i < disponibles.size(); i++)
    				System.out.println((i + 1) + ". " + disponibles.get(i).getLogin());
    			int op = ConsolaUtils.leerEntero("Seleccione mesero: ", 1, disponibles.size());
    			turno.agregarMesero(disponibles.get(op - 1));
    			System.out.println("Mesero agregado al turno.");

    		} 
    		
    		else if (accion.equals("2")) {
    			ArrayList<dataBase.Mesero> enTurno = turno.getMeseros();
    			if (enTurno.isEmpty()) { System.out.println("No hay meseros en este turno."); 
    			return; }
    			for (int i = 0; i < enTurno.size(); i++)
    				System.out.println((i + 1) + ". " + enTurno.get(i).getLogin());
    			int op = ConsolaUtils.leerEntero("Seleccione mesero a eliminar: ", 1, enTurno.size());
    			turno.eliminarMesero(enTurno.get(op - 1));
    			System.out.println("Mesero eliminado del turno.");

    		} 
    		
    		else if (accion.equals("3")) {
    			ArrayList<dataBase.Cocinero> disponibles = new ArrayList<>();
    			for (dataBase.Usuario u : db.getUsuarios()) {
    				if (u instanceof dataBase.Cocinero && !turno.getCocineros().contains(u))
    					disponibles.add((dataBase.Cocinero) u);
    			}
    			if (disponibles.isEmpty()) {
    				System.out.println("No hay cocineros disponibles para agregar.");
    				return;
    			}
    			for (int i = 0; i < disponibles.size(); i++)
    				System.out.println((i + 1) + ". " + disponibles.get(i).getLogin());
    			int op = ConsolaUtils.leerEntero("Seleccione cocinero: ", 1, disponibles.size());
    			turno.agregarCocinero(disponibles.get(op - 1));
    			System.out.println("Cocinero agregado al turno.");

    		} 
    		
    		else if (accion.equals("4")) {
    			ArrayList<dataBase.Cocinero> enTurno = turno.getCocineros();
    			if (enTurno.isEmpty()) { System.out.println("No hay cocineros en este turno."); 
    			return; }
    			for (int i = 0; i < enTurno.size(); i++)
    				System.out.println((i + 1) + ". " + enTurno.get(i).getLogin());
    			int op = ConsolaUtils.leerEntero("Seleccione cocinero a eliminar: ", 1, enTurno.size());
    			turno.eliminarCocinero(enTurno.get(op - 1));
    			System.out.println("Cocinero eliminado del turno.");

    		} else {
    			System.out.println("Opcion invalida.");
    		}
    	} 
    	catch (Exception e) {
    		System.out.println("Error: " + e.getMessage());
    	}
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
    	int numero = 0;
    	String juego = ConsolaUtils.leerString("Ingrese el id del juego: ");
    	ArrayList<Prestamo> historialp = tienda.getHistorialDePrestamos();
    	for(Prestamo p: historialp) {
    		if (p.getIdJuego().equals(juego)){
    			numero += 1;
    		}
    	}
    	System.out.print("El numero de prestamos del juego es de "+numero+" prestamos.");
    }

    static void consultarFechaPrestamoJuego(InventarioJuegos tienda) {
    	ArrayList<LocalDate> fechas = new ArrayList<LocalDate>();
    	String juego = ConsolaUtils.leerString("Ingrese el id del juego: ");
    	ArrayList<Prestamo> historialp = tienda.getHistorialDePrestamos();
    	for(Prestamo p: historialp) {
    		if (p.getIdJuego().equals(juego)){
    			fechas.add(p.getFechaInicio());
    		}
    	}
    	System.out.print(fechas);
    }

    // =====================================================
    // INFORME
    // =====================================================

    static void generarInforme(DataBase db) {
    	LocalDate fechaInicio = ConsolaUtils.leerFechaLD("Fecha publicación: ");
    	LocalDate fechaFin = ConsolaUtils.leerFechaLD("Fecha publicación: ");
    	db.generarInforme(fechaInicio, fechaFin);
    }
}