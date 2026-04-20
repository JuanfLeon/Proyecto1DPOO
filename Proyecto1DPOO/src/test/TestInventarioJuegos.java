package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Date;

import exceptions.JuegoNoDisponibleException;
import tiendaDeJuegos.*;

@DisplayName("Tests — InventarioJuegos")
public class TestInventarioJuegos {

    private InventarioJuegos inventario;
    private JuegoDeMesaFisico catan1;
    private JuegoDeMesaFisico catan2;
    private JuegoDeMesaFisico monopolio1;

    @BeforeEach
    void setUp() {
        inventario = new InventarioJuegos();
        catan1 = new JuegoDeMesaFisico("catan0", "Catan", 50000, new Date(),
                "Asmodee", TipoDeJuego.TABLERO, 3, 6, 10, "Estrategia", false, "nuevo", false);
        catan2 = new JuegoDeMesaFisico("catan1", "Catan", 50000, new Date(),
                "Asmodee", TipoDeJuego.TABLERO, 3, 6, 10, "Estrategia", false, "bueno", false);
        monopolio1 = new JuegoDeMesaFisico("monopolio0", "Monopolio", 45000, new Date(),
                "Hasbro", TipoDeJuego.TABLERO, 2, 8, 8, "Dinero", false, "nuevo", false);
    }

    @Test @DisplayName("Agregar juego VENTAS -> queda en inventarioVenta")
    void agregar_ventas() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        assertEquals(1, inventario.getInventarioVenta().get("Catan").size());
    }

    @Test @DisplayName("Agregar juego PRESTAMO -> queda en inventarioPrestamo")
    void agregar_prestamo() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.PRESTAMO);
        assertEquals(1, inventario.getInventarioPrestamo().get("Catan").size());
    }

    @Test @DisplayName("Agregar 2 copias del mismo juego -> lista tiene 2")
    void agregar_dosCopias() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(catan2, "Catan", TipoInventario.VENTAS);
        assertEquals(2, inventario.getInventarioVenta().get("Catan").size());
    }

    @Test @DisplayName("Agregar juegos distintos -> listas separadas")
    void agregar_juegosDistintos() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(monopolio1, "Monopolio", TipoInventario.VENTAS);
        assertEquals(1, inventario.getInventarioVenta().get("Catan").size());
        assertEquals(1, inventario.getInventarioVenta().get("Monopolio").size());
    }

    @Test @DisplayName("Validar stock suficiente VENTAS -> true")
    void validar_suficiente() throws JuegoNoDisponibleException {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(catan2, "Catan", TipoInventario.VENTAS);
        assertTrue(inventario.validarDisponibilidadJuegos("Catan", 2, TipoInventario.VENTAS));
    }

    @Test @DisplayName("Validar stock insuficiente -> JuegoNoDisponibleException")
    void validar_insuficiente_lanzaExcepcion() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        assertThrows(JuegoNoDisponibleException.class,
            () -> inventario.validarDisponibilidadJuegos("Catan", 2, TipoInventario.VENTAS));
    }

    @Test @DisplayName("eliminarPrimerJuego VENTAS -> retorna juego y queda 1 menos")
    void eliminarPrimero_ventas() throws JuegoNoDisponibleException {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(catan2, "Catan", TipoInventario.VENTAS);
        JuegoDeMesaFisico eliminado = inventario.eliminarPrimerJuego("Catan", TipoInventario.VENTAS);
        assertNotNull(eliminado);
        assertEquals(1, inventario.getInventarioVenta().get("Catan").size());
    }

    @Test @DisplayName("eliminarJuego por ID correcto -> elimina el exacto")
    void eliminarPorId_correcto() throws Exception {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(catan2, "Catan", TipoInventario.VENTAS);
        JuegoDeMesaFisico eliminado = inventario.eliminarJuegoInventario("catan1", "Catan", TipoInventario.VENTAS);
        assertEquals("catan1", eliminado.getIdJuego());
        assertEquals(1, inventario.getInventarioVenta().get("Catan").size());
        assertEquals("catan0", inventario.getInventarioVenta().get("Catan").get(0).getIdJuego());
    }

    @Test @DisplayName("eliminarJuego ID inexistente -> JuegoNoDisponibleException")
    void eliminarPorId_inexistente() {
        inventario.agregarJuegoInventario(catan1, "Catan", TipoInventario.VENTAS);
        assertThrows(JuegoNoDisponibleException.class,
            () -> inventario.eliminarJuegoInventario("idFalso", "Catan", TipoInventario.VENTAS));
    }

    @Test @DisplayName("agregarPrestamoAHistorial -> historial crece")
    void historial_crece() {
        inventario.agregarPrestamoAHistorial(new Prestamo("catan0", "MESA-01", LocalDate.now(), LocalDate.now().plusDays(1)));
        inventario.agregarPrestamoAHistorial(new Prestamo("monopolio0", "MESA-02", LocalDate.now(), LocalDate.now().plusDays(1)));
        assertEquals(2, inventario.getHistorialDePrestamos().size());
    }
}