package consola;

import java.util.ArrayList;

import cafe.Cafe;
import cafe.Producto;
import dataBase.DataBase;
import dataBase.Empleado;
import dataBase.SolicitudSugerenciaPlatillo;
import dataBase.Usuario;
import generals.DulcesNDados;
import tiendaDeJuegos.InventarioJuegos;

public class MainEmpleado {

    // =====================================================
    // MAIN EMPLEADO
    // =====================================================

    public static void ejecutar(DulcesNDados app) {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda =
            app.getTiendaDeJuegos();

        boolean salir = false;

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

    static void solicitarCambioHorario(
        DataBase db
    ) {

    }

    static void solicitarIntercambioHorario(
        DataBase db
    ) {

    }

    static void verTurnos(DataBase db) {
    	System.out.print(db.getTurnos());
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