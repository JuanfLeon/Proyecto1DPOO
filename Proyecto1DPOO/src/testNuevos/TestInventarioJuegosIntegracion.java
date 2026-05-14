package testNuevos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;

import exceptions.InventarioInsuficienteException;
import exceptions.JuegoNoDisponibleException;
import tiendaDeJuegos.*;

@DisplayName("Tests — InventarioJuegos (disponibilidad, mover, prestamo)")
public class TestInventarioJuegosIntegracion {

    private InventarioJuegos inventario;
    private static final String NOMBRE = "Catan";

    @BeforeEach
    void setUp() {
        inventario = new InventarioJuegos();
        inventario.agregarJuegoInventario(crearFisico("V-001"), NOMBRE, TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(crearFisico("V-002"), NOMBRE, TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(crearFisico("P-001"), NOMBRE, TipoInventario.PRESTAMO);
    }

    private JuegoDeMesaFisico crearFisico(String id) {
        return new JuegoDeMesaFisico(id, NOMBRE, 50000, null,
                "Empresa", TipoDeJuego.TABLERO, 2, 4, 10, "", false, "nuevo", false);
    }

    // Validar Disponibilidad ------------------------------------

    @Test
    @DisplayName("validar 1 unidad de venta disponible: retorna true")
    void validar_ventaDisponible_retornaTrue() throws JuegoNoDisponibleException {
        assertTrue(inventario.validarDisponibilidadJuegos(NOMBRE, 1, TipoInventario.VENTAS));
    }

    @Test
    @DisplayName("validar mas unidades que stock: lanza JuegoNoDisponibleException")
    void validar_stockInsuficiente_lanzaExcepcion() {
        assertThrows(JuegoNoDisponibleException.class, () -> inventario.validarDisponibilidadJuegos(NOMBRE, 10, TipoInventario.VENTAS));
    }

    @Test
    @DisplayName("validar 1 unidad de prestamo disponible : retorna true")
    void validar_prestamoDisponible_retornaTrue() throws JuegoNoDisponibleException {
        assertTrue(inventario.validarDisponibilidadJuegos(NOMBRE, 1, TipoInventario.PRESTAMO));
    }
    

    //  Eliminar Primer Juego -----------------
    @Test
    @DisplayName("Eliminar Primer Juego de venta: stock baja a 1")
    void eliminarPrimero_venta_stockBaja() throws JuegoNoDisponibleException {
        inventario.eliminarPrimerJuego(NOMBRE, TipoInventario.VENTAS);
        assertEquals(1, inventario.getInventarioVenta().get(NOMBRE).size());
    }

    @Test
    @DisplayName("Eliminar Primer Juego de prestamo: stock baja a 0")
    void eliminarPrimero_prestamo_stockCero() throws JuegoNoDisponibleException {
        inventario.eliminarPrimerJuego(NOMBRE, TipoInventario.PRESTAMO);
        assertEquals(0, inventario.getInventarioPrestamo().get(NOMBRE).size());
    }
    

    // Agregar Prestamo a Historial -----------
    @Test
    @DisplayName("Agregar Prestamo A Historial: historial tiene 1 entrada")
    void agregarPrestamo_historialCrece() {
        Prestamo p = new Prestamo("P-001", "MESA-1",LocalDate.now(), LocalDate.now().plusDays(1));
        inventario.agregarPrestamoAHistorial(p);
        assertEquals(1, inventario.getHistorialDePrestamos().size());
    }

    
    // moverJuegoDeVentasAPrestamo ------
    @Test
    @DisplayName("Mover De Ventas a Prestamo: venta pierde 1, prestamo gana 1")
    void mover_ventaAPrestamo_ajustaAmbosStocks()
            throws InventarioInsuficienteException, JuegoNoDisponibleException {

        GestorInventarioJuegos gestor = new GestorInventarioJuegos();
        gestor.setInventarioJuegos(inventario);

        int ventasAntes    = inventario.getInventarioVenta().get(NOMBRE).size();    // 2
        int prestamoAntes  = inventario.getInventarioPrestamo().get(NOMBRE).size(); // 1

        gestor.moverJuegoDeVentasAPrestamo(NOMBRE);

        assertEquals(ventasAntes - 1, inventario.getInventarioVenta().get(NOMBRE).size());
        assertEquals(prestamoAntes + 1, inventario.getInventarioPrestamo().get(NOMBRE).size());
    }

    @Test
    @DisplayName("Mover de Ventas A Prestamo sin stock:  lanza InventarioInsuficienteException")
    void mover_sinStock_lanzaExcepcion() {
        inventario.getInventarioVenta().get(NOMBRE).clear();

        GestorInventarioJuegos gestor = new GestorInventarioJuegos();
        gestor.setInventarioJuegos(inventario);

        assertThrows(InventarioInsuficienteException.class, () -> gestor.moverJuegoDeVentasAPrestamo(NOMBRE));
    }
}