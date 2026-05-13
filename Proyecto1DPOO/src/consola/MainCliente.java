package consola;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import cafe.Cafe;
import cafe.Mesa;
import cafe.Producto;
import dataBase.Cliente;
import dataBase.DataBase;
import dataBase.Usuario;
import generals.DulcesNDados;
import generals.generadorID;
import tiendaDeJuegos.InventarioJuegos;
import tiendaDeJuegos.JuegoDeMesa;
import tiendaDeJuegos.JuegoDeMesaFisico;
import tiendaDeJuegos.Prestamo;
import tiendaDeJuegos.TipoInventario;


public class MainCliente {
    static Cliente clienteActivo = null;


    // =====================================================
    // MAIN CLIENTE
    // =====================================================

    public static void ejecutar(DulcesNDados app) {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda = app.getTiendaDeJuegos();

        boolean salir = false;

        while (!salir) {

            System.out.println("\n=== CLIENTE ===");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Reservar mesa");
            System.out.println("4. Solicitar juego prestado");
            System.out.println("5. Comprar juego");
            System.out.println("6. Comprar platillo");
            System.out.println("7. Ver catálogo juegos");
            System.out.println("8. Ver menú");
            System.out.println("9. Ver puntos fidelidad");
            System.out.println("0. Volver");
            String opcion =ConsolaUtils.leerString("Seleccione opción: ");
            
            switch (opcion) {
                case "1":registrarse(db);			break;
                case "2":iniciarSesion(db);			break;
                case "3":reservarMesa(cafe,db);		break;
                case "4":solicitarJuegoPrestado(tienda,cafe,db);	break;
                case "5":comprarJuego(tienda,db);	break;
                case "6":comprarPlatillo(cafe,db);	break;
                case "7":verCatalogoJuegos(tienda);	break;
                case "8":verMenu(cafe);				break;
                case "9":verPuntosFidelidad(db);	break;
                case "0":salir = true;				break;

                default:System.out.println("Opción inválida.");
            }
        }
    }

    // ===================================================
    // 					AUTENTICACIÓN
    // ===================================================
    
    static void registrarse(DataBase db) {
    	System.out.println("\n--- REGISTRO DE USUARIO ---") ;
    	String login = ConsolaUtils.leerString("Ingrese su nombre Usuario: ");
    	
    	for(Usuario u : db.getUsuarios()) {
    		if(u.getLogin().equalsIgnoreCase(login)) {
    			System.out.println("El usuario" + login + "ya exisye. Elija otro.");
    			return ;
    		}
    	}
    	
    	String contrasenia = ConsolaUtils.leerString("Ingrese la contraeña: ");
    	
    	ArrayList<String> juegosFav = new ArrayList<>() ;
    	System.out.println("Juegos favoritos (Enter para omitir): ");
    	while (true) {
    		System.out.print("Juego favorito: ");
    		String fav = ConsolaUtils.sc.nextLine().trim();
    		if (fav.isEmpty()) break;
    		juegosFav.add(fav);
    	}
    	
    	String idCliente = generadorID.generarIDUsuario(login);
    	Cliente nuevo = new Cliente(login, juegosFav, contrasenia);
    	nuevo.setIdCliente(login);
    	nuevo.setPuntosFidelidad(0);
    	nuevo.setJuegosPrestados(new ArrayList<>());
    	nuevo.setJuegosComprados(new ArrayList<>());
    	
    	db.getUsuarios().add(nuevo); 
    	clienteActivo = nuevo ;
    	
    	System.out.println("Registro exitoso. Bienvenido, " + login + "ID: " + idCliente);
    }

    static void iniciarSesion(DataBase db) {

    	System.out.println("\n--- INICIO DE SESION ---");
    	
    	String login = ConsolaUtils.leerString("Usuario: ");
    	String contrasenia = ConsolaUtils.leerString("Contraseña: ");
    	
    	for (Usuario u : db.getUsuarios()) {
    		if (u instanceof Cliente
    				&& u.getLogin().equalsIgnoreCase(login)
    				&& u.getContrasenia().equalsIgnoreCase(contrasenia)) {
    			clienteActivo = (Cliente) u;
    			System.out.println("Sesion iniciada. Bienvenido, " + login );
    			return ;
    		}
    	}
    	System.out.println("Usuario o contraseña incorrectos");
    }

    // ===================================================
    // 						MESAS
    // ===================================================
    
    static void reservarMesa(Cafe cafe,DataBase db) {

    	System.out.println("\n--- RESERVA MESA ---") ;
    	
    	if (clienteActivo == null) {
    		System.out.println("Debe iniciar sesion primero");
    		return;}
    	
    	if (clienteActivo.getMesa() != null) {
    		System.out.println("ya tiene una mesa asignada: " + clienteActivo.getMesa().getIdMesa());
    	return ;
    	}
    	System.out.println("Capacidad maxima del cafe: " + cafe.getCapacidadMaxClientes()) ;
    	System.out.println("Mesas actuales: " + cafe.getMesas()) ;
    	
    	int cantidadClientes = ConsolaUtils.leerEntero("Numero de personas", 1, 20);
    	boolean tieneNinos = ConsolaUtils.leerSiNo("¿Hay niños en la mesa?");
    	boolean tieneJovenes = ConsolaUtils.leerSiNo("¿Hay jovenes (mayores de edad)?");
    	boolean tieneBebidaC = ConsolaUtils.leerSiNo("¿Desea bebida caliente en la mesa?");
    	
    	String idMesa = "Mesa-" +(cafe.getMesas().size() + 1);
    	cafe.agregarMesa(idMesa, cantidadClientes, tieneJovenes, tieneNinos, tieneBebidaC, new ArrayList<>(), clienteActivo);
    	
    	for (Mesa m : cafe.getMesas()) {
    		if (m.getIdMesa().equals(idMesa)) {
    			clienteActivo.setMesa(m);
    			break;
    		}
    	}
    	System.out.println("Mesa '" + idMesa + "' reservada para " + cantidadClientes + "persona(s)!" ) ;
    	
    }
    
    // ===================================================
    // 						JUEGOS
    // ===================================================
    		
    static void solicitarJuegoPrestado(InventarioJuegos tienda,Cafe cafe,DataBase db) {

    	System.out.println("\n--- SOLICITAR JUEGO PRESTADO ---");
    	
    	if (clienteActivo == null) {
    		System.out.println("Debe iniciar sesión primero");
    		return ;}
    	
    	if (clienteActivo.getMesa() == null) {
    		System.out.println("Debe tener una mesa asignada primero");
    		return;}
    	
    	if (tienda.getInventarioPrestamo() == null || tienda.getInventarioPrestamo().isEmpty()) {
    		System.out.println("No hay juegos disponibles para prestamo");
    		return;}
    	
    	System.out.println("Juegos disponibles para venta");
    	int i = 1;
    	ArrayList<String> disponibles = new ArrayList<>();
    	for (Map.Entry<String, ArrayList<JuegoDeMesaFisico>> entry : tienda.getInventarioVenta().entrySet()) {
    		if (!entry.getValue().isEmpty()) {
    			System.out.println(i + " ." + entry.getKey() + " (" + entry.getValue() + " disponibles");
    			disponibles.add(entry.getKey());
    			i++;
    		}
    	}
    	
    	if (disponibles.isEmpty()) {
        	System.out.println("No hay unidades disponibles");
        	return ;
    	}
    	
    	int opcion = ConsolaUtils.leerEntero("Seleccion el juego: ", 1, disponibles.size());
    	String nombreJuego = disponibles.get(opcion - 1) ;
    	
    	try {
    		tienda.validarDisponibilidadJuegos(nombreJuego, 1, TipoInventario.PRESTAMO);
    		JuegoDeMesaFisico juego = tienda.eliminarPrimerJuego(nombreJuego, TipoInventario.PRESTAMO);
    		
    		juego.setPrestado(true);
    		
    		LocalDate hoy = LocalDate.now();
    		LocalDate devolucion = hoy.plusDays(1);
    		Prestamo prestamo = new Prestamo(juego.getIdJuego(),
    				clienteActivo.getMesa().getIdMesa(), hoy, devolucion);	
    		tienda.agregarPrestamoAHistorial(prestamo);
    		
    		if (clienteActivo.getJuegosPrestados() == null)
    			clienteActivo.setJuegosPrestados(new ArrayList<>());
    		clienteActivo.getJuegosPrestados().add(juego);
    		
    		System.out.println("Juego '" + nombreJuego + "' prestado exitosamente");
    		System.out.println("Devolucion el : " + devolucion);
    	}
    	
    	catch (Exception e ) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }

    static void comprarJuego(InventarioJuegos tienda,DataBase db) {

    	System.out.println("\n--- COMPRAR JUEGO ---");
    	
    	if (clienteActivo == null) {
    		System.out.println("Debe iniciar sesion primero");
    		return ;
    	}
    	
    	if (tienda.getInventarioVenta() == null || tienda.getInventarioVenta().isEmpty()) {
    		System.out.println("No hay juegos disponibles para vender");
    		return;
    	}
    	
    	System.out.println("Juegos disponibles para venta");
    	int i = 1 ;
    	ArrayList<String> disponibles = new ArrayList<>(); 
    	for (Map.Entry<String, ArrayList<JuegoDeMesaFisico>> entry : tienda.getInventarioVenta().entrySet()) {
    		if(!entry.getValue().isEmpty()) {
    			double precio = entry.getValue().get(0).getPrecio();
    			System.out.println(i + ". " + entry.getKey()
    			+ " | $" + precio
    			+ " | Stock" + entry.getValue().size());
    			disponibles.add(entry.getKey());
    			i++;
    		}
    	}
    	
    	if (disponibles.isEmpty()) {
    		System.out.println("No hay unidades disponibles");
    		return;
    	}
    	
    	int opcion = ConsolaUtils.leerEntero("Seleccione el juego", 1, disponibles.size());
    	String nombreJuego = disponibles.get(opcion -1);
    	
    	try {
    		tienda.validarDisponibilidadJuegos(nombreJuego, 1, TipoInventario.VENTAS);
    		JuegoDeMesaFisico juego = tienda.eliminarPrimerJuego(nombreJuego, TipoInventario.VENTAS);
    		
    		if (clienteActivo.getJuegosComprados() == null)
    			clienteActivo.setJuegosComprados(new ArrayList<>());
    		clienteActivo.getJuegosComprados().add(juego);
    		
    		double puntos = juego.getPrecio() / 1000.0; 
    		clienteActivo.setPuntosFidelidad(clienteActivo.getPuntosFidelidad() + puntos);
    		
    		System.out.println("Juego '" + nombreJuego + "' comprado por $" + juego.getPrecio());
    		System.out.printf("Puntos de fidelidad: %.2f%n", clienteActivo.getPuntosFidelidad());
    		
    	}
    	catch(Exception e) {
    		System.out.println("Error: " + e.getMessage());
    	}
    }
    

    static void verCatalogoJuegos(InventarioJuegos tienda) {

    	System.out.println("\n--- CATALOGO DE JUEGOS ---");
    	
    	if (tienda.getCatalogoJuegos() == null || tienda.getCatalogoJuegos().isEmpty()) {
    		System.out.println("El catalogo esta vacío");
    		return;
    	}
    	
    	int i = 1;
    	for (Map.Entry<String, JuegoDeMesa> entry : tienda.getCatalogoJuegos().entrySet()) {
    		JuegoDeMesa j = entry.getValue();
    		System.out.println("\n" + i + "." + j.getNombre());
    		System.out.println("	Tipo: " + j.getTipoDeJuego());
    		System.out.println("	Jugadores: " + j.getMinJugadores() + " - " + j.getMaxJugadores());
    		System.out.println("	Edad minima: " + j.getEdadMinima());
    		System.out.println("	Precio: " + j.getPrecio());
    		System.out.println("	Dificultad: " + (j.isDificil() ? "Alta" : "Normal"));
    		if (j.getCaracteristicas() != null && !j.getCaracteristicas().isEmpty())
    			System.out.println("	Características: " + j.getCaracteristicas());
    		i++ ;
    	}
    	
    	System.out.println("\n DISPONIBILIDAD");
    	for (String nombre : tienda.getCatalogoJuegos().keySet()) {
    		int stockP = (tienda.getInventarioPrestamo() != null
    				&& tienda.getInventarioPrestamo().containsKey(nombre))
    			? tienda.getInventarioPrestamo().get(nombre).size() : 0;     
    		int stockV = (tienda.getInventarioVenta() != null
    				&& tienda.getInventarioVenta().containsKey(nombre))
    				? tienda.getInventarioVenta().get(nombre).size() : 0;
    		System.out.println(nombre + " | Prestamo: " + stockP + " | Venta: " + stockV);
    		}
    }

    // ===================================================
    // 						CAFÉ
    // ===================================================

    static void comprarPlatillo(Cafe cafe,DataBase db) {
    	
    	System.out.println("\n--- COMPRAR PLATILLO ---");
    	
    	if (clienteActivo == null) {
    		System.out.println("Debe iniciar sesion primero");
    		return;}
    	
    	if (clienteActivo.getMesa() == null) {
    		System.out.println("Debete tener una mesa asignada para ordenar");
    		return;}
    	
    	if (cafe.getCatalogoPlatillos() == null || cafe.getCatalogoPlatillos().isEmpty()) {
    		System.out.println("El menu está vacío");
    		return;}
    	
    	System.out.println("\n MENU:");
    	ArrayList<Producto> catalogo = cafe.getCatalogoPlatillos();
    	for(int i = 0; i < catalogo.size(); i++) {
    		System.out.println((i + 1) + ". " + catalogo.get(i));
    	}
    	
    	int opcion = ConsolaUtils.leerEntero("Seleccione el platillo: ", 1, catalogo.size());
    	Producto seleccionado = catalogo.get(opcion - 1) ;
    	
    	clienteActivo.getMesa().getProductosOrdenados().add(seleccionado);
    	
    	double puntos = seleccionado.getPrecio() / 500.0;
    	clienteActivo.setPuntosFidelidad(clienteActivo.getPuntosFidelidad() + puntos);
    	
    	System.out.println("'" + seleccionado.getNombre() + "' agregado a tu mesa por: " + seleccionado.getPrecio());
		System.out.printf("Puntos de fidelidad %.2f%n", clienteActivo.getPuntosFidelidad());

    }

    static void verMenu(Cafe cafe) {

		System.out.println("\n--- MENU DEL CAFE ---");
		
		if (cafe.getCatalogoPlatillos() == null || cafe.getCatalogoPlatillos().isEmpty()) {
    		System.out.println("El menu está vacío");
    		return;}
		
		System.out.println("Capacidad del cafe: " + cafe.getCapacidadMaxClientes() + "personas");
		System.out.println("\n PRODUCTOS: ");
		for (int i = 0; i<cafe.getCatalogoPlatillos().size(); i++) {
    		System.out.println((i + 1) + ". " + cafe.getCatalogoPlatillos().get(i));
		}
    }

    // =====================================================
    // 						FIDELIDAD
    // =====================================================

    static void verPuntosFidelidad(DataBase db) {

		System.out.println("\n--- PUNTOS DE FIDELIDAD ---");
		
		if (clienteActivo == null) {
    		System.out.println("Debe iniciar sesion primero");
    		return;}
		
		System.out.println("Cliente: " + clienteActivo.getLogin());
		System.out.printf("Puntos acumulados: %2.f%n", clienteActivo.getPuntosFidelidad());
		
		if(clienteActivo.getJuegosComprados() != null
				&& !clienteActivo.getJuegosComprados().isEmpty()) {
    		System.out.println("\n Juegos comprados:");
    		for (JuegoDeMesa j : clienteActivo.getJuegosComprados())
        		System.out.println(" -" + j.getNombre() + " | $" + j.getPrecio());
    		}

		if(clienteActivo != null
				&& !clienteActivo.getJuegosPrestados().isEmpty()) {
    		System.out.println("\n Juegos prestados actualmente:");
    		for (JuegoDeMesaFisico j : clienteActivo.getJuegosPrestados())
        		System.out.println(" -" + j.getNombre());
    		}
		}
}