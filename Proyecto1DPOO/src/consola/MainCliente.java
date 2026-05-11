package consola;

import cafe.Cafe;
import dataBase.DataBase;
import generals.DulcesNDados;
import tiendaDeJuegos.InventarioJuegos;

public class MainCliente {

    // =====================================================
    // MAIN CLIENTE
    // =====================================================

    public static void ejecutar(DulcesNDados app) {

        DataBase db = app.getDataBase();

        Cafe cafe = app.getCafe();

        InventarioJuegos tienda =
            app.getTiendaDeJuegos();

        boolean salir = false;

        while (!salir) {

            System.out.println(
                "\n=== CLIENTE ==="
            );

            System.out.println(
                "1. Registrarse"
            );

            System.out.println(
                "2. Iniciar sesión"
            );

            System.out.println(
                "3. Reservar mesa"
            );

            System.out.println(
                "4. Solicitar juego prestado"
            );

            System.out.println(
                "5. Comprar juego"
            );

            System.out.println(
                "6. Comprar platillo"
            );

            System.out.println(
                "7. Ver catálogo juegos"
            );

            System.out.println(
                "8. Ver menú"
            );

            System.out.println(
                "9. Ver puntos fidelidad"
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

                    registrarse(
                        db
                    );

                    break;

                case "2":

                    iniciarSesion(
                        db
                    );

                    break;

                case "3":

                    reservarMesa(
                        cafe,
                        db
                    );

                    break;

                case "4":

                    solicitarJuegoPrestado(
                        tienda,
                        cafe,
                        db
                    );

                    break;

                case "5":

                    comprarJuego(
                        tienda,
                        db
                    );

                    break;

                case "6":

                    comprarPlatillo(
                        cafe,
                        db
                    );

                    break;

                case "7":

                    verCatalogoJuegos(
                        tienda
                    );

                    break;

                case "8":

                    verMenu(
                        cafe
                    );

                    break;

                case "9":

                    verPuntosFidelidad(
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
    // AUTENTICACIÓN
    // =====================================================

    static void registrarse(
        DataBase db
    ) {

    }

    static void iniciarSesion(
        DataBase db
    ) {

    }

    // =====================================================
    // MESAS
    // =====================================================

    static void reservarMesa(
        Cafe cafe,
        DataBase db
    ) {

    }

    // =====================================================
    // JUEGOS
    // =====================================================

    static void solicitarJuegoPrestado(
        InventarioJuegos tienda,
        Cafe cafe,
        DataBase db
    ) {

    }

    static void comprarJuego(
        InventarioJuegos tienda,
        DataBase db
    ) {

    }

    static void verCatalogoJuegos(
        InventarioJuegos tienda
    ) {

    }

    // =====================================================
    // CAFÉ
    // =====================================================

    static void comprarPlatillo(
        Cafe cafe,
        DataBase db
    ) {

    }

    static void verMenu(
        Cafe cafe
    ) {

    }

    // =====================================================
    // FIDELIDAD
    // =====================================================

    static void verPuntosFidelidad(
        DataBase db
    ) {

    }
}