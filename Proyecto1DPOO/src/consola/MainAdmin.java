package consola;

import cafe.Cafe;
import dataBase.DataBase;
import generals.DulcesNDados;
import tiendaDeJuegos.InventarioJuegos;

public class MainAdmin {

    // =====================================================
    // MAIN ADMIN
    // =====================================================

    public static void ejecutar(DulcesNDados app) {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda =
            app.getTiendaDeJuegos();

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
                "3. Agregar juego préstamo"
            );

            System.out.println(
                "4. Agregar juego venta"
            );

            System.out.println(
                "5. Agregar platillo"
            );

            System.out.println(
                "6. Ver solicitudes"
            );

            System.out.println(
                "7. Generar informe"
            );

            System.out.println(
                "8. Reparar juego"
            );

            System.out.println(
                "9. Reportar juego robado"
            );

            System.out.println(
                "10. Mover juego ventas → préstamo"
            );

            System.out.println(
                "11. Reabastecer inventario ventas"
            );

            System.out.println(
                "12. Reabastecer inventario préstamo"
            );

            System.out.println(
                "13. Aprobar solicitud horario"
            );

            System.out.println(
                "14. Aceptar sugerencia platillo"
            );

            System.out.println(
                "15. Asignar horario"
            );

            System.out.println(
                "16. Modificar horario"
            );

            System.out.println(
                "17. Consultar estado juego"
            );

            System.out.println(
                "18. Consultar número préstamos"
            );

            System.out.println(
                "19. Consultar fechas préstamo"
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

                    agregarJuegoPrestamo(
                        tienda
                    );

                    break;

                case "4":

                    agregarJuegoVenta(
                        tienda
                    );

                    break;

                case "5":

                    agregarPlatillo(
                        cafe
                    );

                    break;

                case "6":

                    verSolicitudes(
                        db
                    );

                    break;

                case "7":

                    generarInforme(
                        db
                    );

                    break;

                case "8":

                    repararJuego(
                        tienda
                    );

                    break;

                case "9":

                    reportarJuegoRobado(
                        tienda
                    );

                    break;

                case "10":

                    moverJuegoVentasAPrestamo(
                        tienda
                    );

                    break;

                case "11":

                    reabastecerInventarioVentas(
                        tienda
                    );

                    break;

                case "12":

                    reabastecerInventarioPrestamo(
                        tienda
                    );

                    break;

                case "13":

                    aprobarSolicitudHorario(
                        db
                    );

                    break;

                case "14":

                    aceptarSugerenciaPlatillo(
                        cafe,
                        db
                    );

                    break;

                case "15":

                    asignarHorario(
                        db
                    );

                    break;

                case "16":

                    modificarHorario(
                        db
                    );

                    break;

                case "17":

                    consultarEstadoJuego(
                        tienda
                    );

                    break;

                case "18":

                    consultarNumPrestamosJuego(
                        tienda
                    );

                    break;

                case "19":

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

    static void registrarEmpleado(
        DataBase db,
        Cafe cafe
    ) {

    }

    static void verEmpleados(
        Cafe cafe
    ) {

    }

    // =====================================================
    // JUEGOS
    // =====================================================

    static void agregarJuegoPrestamo(
        InventarioJuegos tienda
    ) {

    }

    static void agregarJuegoVenta(
        InventarioJuegos tienda
    ) {

    }

    static void repararJuego(
        InventarioJuegos tienda
    ) {

    }

    static void reportarJuegoRobado(
        InventarioJuegos tienda
    ) {

    }

    static void moverJuegoVentasAPrestamo(
        InventarioJuegos tienda
    ) {

    }

    static void reabastecerInventarioVentas(
        InventarioJuegos tienda
    ) {

    }

    static void reabastecerInventarioPrestamo(
        InventarioJuegos tienda
    ) {

    }

    // =====================================================
    // CAFÉ
    // =====================================================

    static void agregarPlatillo(
        Cafe cafe
    ) {

    }

    static void aceptarSugerenciaPlatillo(
        Cafe cafe,
        DataBase db
    ) {

    }

    // =====================================================
    // SOLICITUDES
    // =====================================================

    static void verSolicitudes(
        DataBase db
    ) {

    }

    static void aprobarSolicitudHorario(
        DataBase db
    ) {

    }

    // =====================================================
    // TURNOS
    // =====================================================

    static void asignarHorario(
        DataBase db
    ) {

    }

    static void modificarHorario(
        DataBase db
    ) {

    }

    // =====================================================
    // CONSULTAS
    // =====================================================

    static void consultarEstadoJuego(
        InventarioJuegos tienda
    ) {

    }

    static void consultarNumPrestamosJuego(
        InventarioJuegos tienda
    ) {

    }

    static void consultarFechaPrestamoJuego(
        InventarioJuegos tienda
    ) {

    }

    // =====================================================
    // INFORME
    // =====================================================

    static void generarInforme(
        DataBase db
    ) {

    }
}