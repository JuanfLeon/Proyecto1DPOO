package consola;

import java.util.ArrayList;

import cafe.Cafe;
import cafe.Producto;
import dataBase.DataBase;
import dataBase.Empleado;
import dataBase.SolicitudCambioTurno;
import dataBase.SolicitudIntercambioTurno;
import dataBase.SolicitudSugerenciaPlatillo;
import dataBase.Turno;
import dataBase.Usuario;
import generals.DulcesNDados;
import tiendaDeJuegos.InventarioJuegos;

public class MainEmpleado {
	static Empleado empleadoActivo = null ;

    // =====================================================
    // MAIN EMPLEADO
    // =====================================================

    public static void ejecutar(DulcesNDados app) {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda =
            app.getTiendaDeJuegos();

        boolean salir = false;
        empleadoActivo = null;

        while (!salir) {

            System.out.println(
                "\n=== EMPLEADO ==="
            );

            System.out.println(
                "1. Solicitar cambio horario"
            );

            System.out.println(
                "2. Solicitar intercambio horario"
            );

            System.out.println(
                "3. Sugerir platillo"
            );

            System.out.println(
                "4. Ver turnos"
            );

            System.out.println(
                "5. Ver solicitudes"
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

                    solicitarCambioHorario(
                        db
                    );

                    break;

                case "2":

                    solicitarIntercambioHorario(
                        db
                    );

                    break;

                case "3":

                    sugerirPlatillo(
                        cafe,
                        db
                    );

                    break;

                case "4":

                    verTurnos(
                        db
                    );

                    break;

                case "5":

                    verSolicitudes(
                        db
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
    // HORARIOS
    // =====================================================

    static void solicitarCambioHorario(DataBase db) {
    	System.out.println("\n--- SOLICITAR CAMBIO DE HORARIO ---");
    	 
        if (empleadoActivo == null) {
            System.out.println("Debe iniciar sesion primero.");
            return;
        }
 
        ArrayList<Turno> turnosEmpleado = empleadoActivo.getTurnoLaboral();
        if (turnosEmpleado == null || turnosEmpleado.isEmpty()) {
            System.out.println("No tiene turnos asignados actualmente.");
            return;
        }
 
        System.out.println("\nSus turnos actuales:");
        for (int i = 0; i < turnosEmpleado.size(); i++) {
            System.out.println((i + 1) + ". " + turnosEmpleado.get(i));
        }
        int opActual = ConsolaUtils.leerEntero("Seleccione el turno que desea cambiar: ",
                1, turnosEmpleado.size());
        Turno turnoInicial = turnosEmpleado.get(opActual - 1);
 
        ArrayList<Turno> turnosSistema = new ArrayList<>();
        for (Turno t : db.getTurnos()) {
            if (t != null && t != turnoInicial) turnosSistema.add(t);
        }
        if (turnosSistema.isEmpty()) {
            System.out.println("No hay otros turnos configurados en el sistema.");
            return;
        }
 
        System.out.println("\nTurnos disponibles:");
        for (int i = 0; i < turnosSistema.size(); i++) {
            System.out.println((i + 1) + ". " + turnosSistema.get(i));
        }
        int opDeseado = ConsolaUtils.leerEntero("Seleccione el turno deseado: ",
                1, turnosSistema.size());
        Turno turnoDeseado = turnosSistema.get(opDeseado - 1);
 
        SolicitudCambioTurno solicitud =
                empleadoActivo.solicitarCambioHorario(turnoInicial, turnoDeseado);
        db.getSolicitudes().add(solicitud);
 
        System.out.println("Solicitud de cambio enviada.");
        System.out.println("  Turno actual:  " + turnoInicial.getDia_turno());
        System.out.println("  Turno deseado: " + turnoDeseado.getDia_turno());
        System.out.println("Pendiente de aprobacion por el administrador.");
    }

    
    static void solicitarIntercambioHorario(DataBase db) {
    	System.out.println("\n--- SOLICITAR INTERCAMBIO DE HORARIO ---");
    	 
        if (empleadoActivo == null) {
            System.out.println("Debe iniciar sesion primero.");
            return;
        }
 
        ArrayList<Turno> turnosEmpleado = empleadoActivo.getTurnoLaboral();
        if (turnosEmpleado == null || turnosEmpleado.isEmpty()) {
            System.out.println("No tiene turnos asignados actualmente.");
            return;
        }
 
        System.out.println("\nSus turnos actuales:");
        for (int i = 0; i < turnosEmpleado.size(); i++) {
            System.out.println((i + 1) + ". " + turnosEmpleado.get(i));
        }
        
        int opActual = ConsolaUtils.leerEntero("Seleccione el turno que desea ceder: ",
                1, turnosEmpleado.size());
        Turno turnoInicial = turnosEmpleado.get(opActual - 1);
 
        
        ArrayList<Empleado> otrosEmpleados = new ArrayList<>();
        for (Usuario u : db.getUsuarios()) {
            if (u instanceof Empleado && u != empleadoActivo
                    && u.getClass().equals(empleadoActivo.getClass())) {
                otrosEmpleados.add((Empleado) u);
            }
        }
        if (otrosEmpleados.isEmpty()) {
            System.out.println("No hay otros empleados del mismo tipo en el sistema.");
            return;
        }
 
        System.out.println("\nEmpleados disponibles para intercambio:");
        for (int i = 0; i < otrosEmpleados.size(); i++) {
            System.out.println((i + 1) + ". " + otrosEmpleados.get(i).getLogin());
        }
        int opEmp = ConsolaUtils.leerEntero("Seleccione el empleado: ", 1, otrosEmpleados.size());
        Empleado otroEmpleado = otrosEmpleados.get(opEmp - 1);
 
        
        ArrayList<Turno> turnosOtro = otroEmpleado.getTurnoLaboral();
        if (turnosOtro == null || turnosOtro.isEmpty()) {
            System.out.println("El empleado seleccionado no tiene turnos asignados.");
            return;
        }
 
        System.out.println("\nTurnos de " + otroEmpleado.getLogin() + ":");
        for (int i = 0; i < turnosOtro.size(); i++) {
            System.out.println((i + 1) + ". " + turnosOtro.get(i));
        }
        int opDeseado = ConsolaUtils.leerEntero("Seleccione el turno a recibir: ", 1, turnosOtro.size());
        Turno turnoDeseado = turnosOtro.get(opDeseado - 1);
 
        SolicitudIntercambioTurno solicitud = empleadoActivo.solicitarIntercambioHorario(turnoInicial, turnoDeseado, otroEmpleado);
        db.getSolicitudes().add(solicitud);
 
        System.out.println("Solicitud de intercambio enviada.");
        System.out.println("  Su turno:    " + turnoInicial.getDia_turno()
                + "  <->  Turno de " + otroEmpleado.getLogin()
                + ": " + turnoDeseado.getDia_turno());
        System.out.println("Pendiente de aprobacion por el administrador.");
    }

    
    static void verTurnos(DataBase db) {
    	System.out.println("\n--- MIS TURNOS ---");
    	 
        if (empleadoActivo == null) {
            System.out.println("Debe iniciar sesion primero.");
            return;
        }
 
        ArrayList<Turno> turnosEmpleado = empleadoActivo.getTurnoLaboral();
        if (turnosEmpleado == null || turnosEmpleado.isEmpty()) {
            System.out.println("No tiene turnos asignados.");
        } else {
            System.out.println("Turnos de " + empleadoActivo.getLogin() + ":");
            for (Turno t : turnosEmpleado) {
                System.out.println("  - " + t.getDia_turno()
                        + " | Meseros: " + t.getMeseros().size()
                        + " | Cocineros: " + t.getCocineros().size());
            }
        }
 
        System.out.println("\nTodos los turnos del sistema:");
        boolean hayAlguno = false;
        for (Turno t : db.getTurnos()) {
            if (t != null) {
                System.out.println("  " + t);
                hayAlguno = true;
            }
        }
        if (!hayAlguno) {
            System.out.println("  No hay turnos configurados en el sistema.");
        }
    }

    // =====================================================
    // PLATILLOS
    // =====================================================

    static void sugerirPlatillo(Cafe cafe, DataBase db) {
    	String nom_e = ConsolaUtils.leerString("Ingrese su id de empleado: ");
    	ArrayList<Usuario> cosos = db.getUsuarios();
    	Empleado login = null;
    	for (Usuario u: cosos) {
    		if (u.getLogin().toLowerCase().equals(nom_e.toLowerCase())) {
    			login = (Empleado) u;
    		}
    	}
    	
    	String nom = ConsolaUtils.leerString("Ingrese el nombre del platillo: ");
    	double precio = ConsolaUtils.leerDouble("Ingrese el precio: ");
    	Producto prod = new Producto(nom, precio);
    	SolicitudSugerenciaPlatillo sol = new SolicitudSugerenciaPlatillo(prod, login);
    	db.getSolicitudes().add(sol);
    }

    // =====================================================
    // SOLICITUDES
    // =====================================================

    static void verSolicitudes(DataBase db) {
    	System.out.print(db.getSolicitudes());
    }
}