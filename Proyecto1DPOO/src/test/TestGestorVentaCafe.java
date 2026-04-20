package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import cafe.*;
import dataBase.*;
import tiendaDeJuegos.TipoVenta;

@DisplayName("Tests — GestorVentaCafe (agregarFactura)")
public class TestGestorVentaCafe {

    private GestorVentaCafe gestor;
    private Mesa mesaConProductos;
    private Cliente cliente;
    private Empleado empleado;

    @BeforeEach
    void setUp() {
        gestor = new GestorVentaCafe();

        // Mesa con 2 productos: cafe $5000 y brownie $8000
        ArrayList<Producto> productos = new ArrayList<>();
        productos.add(new Bebida("Cafe", 5000, false, true));
        productos.add(new Pasteleria("Brownie", 8000, new ArrayList<>()));

        mesaConProductos = new Mesa("MESA-01", 2, false, false, true, productos, null);

        cliente = new Cliente("juan", new ArrayList<>(), "pass");
        empleado = new Mesero("pedro", new ArrayList<>(), "pass",
                new ArrayList<>(), "DESC-01", new ArrayList<>());
    }

    // ── Subtotal 

    @Test @DisplayName("Factura subtotal es suma de precios de productos de la mesa")
    void factura_subtotalCorrecto() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        // subtotal = 5000 + 8000 = 13000
        assertEquals(13000.0, f.getSubtotal(), 0.01);
    }

    // ── Impuesto al consumo 8%

    @Test @DisplayName("Factura impuestos es 8% del subtotal")
    void factura_impuesto8porciento() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        double esperado = 13000 * 0.08;
        assertEquals(esperado, f.getImpuestos(), 0.01);
    }

    // ── Propina 

    @Test @DisplayName("Factura propina es porcentaje sobre subtotal")
    void factura_propinaCorrecta() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        double esperado = 13000 * 0.10;
        assertEquals(esperado, f.getPropina(), 0.01);
    }

    @Test @DisplayName("Factura propina 0 cuando se pasa 0")
    void factura_propinaCero() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.0, false, cliente);
        assertEquals(0.0, f.getPropina(), 0.01);
    }

    // ── Descuento cliente con codigo 

    @Test @DisplayName("Cliente con codigo de descuento tiene descuento del 10%")
    void factura_descuento10porciento_cliente() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.0, true, cliente);
        double esperado = 13000 * 0.10;
        assertEquals(esperado, f.getDescuento(), 0.01);
    }

    @Test @DisplayName("Cliente sin codigo de descuento tiene descuento 0")
    void factura_sinDescuento() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.0, false, cliente);
        assertEquals(0.0, f.getDescuento(), 0.01);
    }

    // ── Descuento empleado 20% 

    @Test @DisplayName("Empleado tiene descuento del 20%")
    void factura_descuento20porciento_empleado() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.0, false, empleado);
        double esperado = 13000 * 0.20;
        assertEquals(esperado, f.getDescuento(), 0.01);
    }

    // ── DetalleVenta

    @Test @DisplayName("Factura tiene un DetalleVenta por cada producto de la mesa")
    void factura_detallesCorrectos() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        assertEquals(2, f.getDetallesDeLaVenta().size());
    }

    @Test @DisplayName("DetalleVenta tiene TipoVenta CAFE")
    void factura_detalles_tipoVentaCafe() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        DetalleVenta detalle = f.getDetallesDeLaVenta().get(0);
        assertEquals(tiendaDeJuegos.TipoVenta.CAFE, detalle.getTipoVenta());
    }

    @Test @DisplayName("DetalleVenta cantidad es 1 por producto")
    void factura_detalles_cantidadUno() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.0, false, cliente);
        for (DetalleVenta d : f.getDetallesDeLaVenta()) {
            assertEquals(1, d.getCantidad());
        }
    }

    // ── Mesa vacia 

    @Test @DisplayName("Mesa sin productos -> subtotal 0 y detalles vacios")
    void factura_mesaVacia() {
        Mesa mesaVacia = new Mesa("MESA-02", 2, false, false, false, new ArrayList<>(), null);
        Factura f = gestor.agregarFactura(mesaVacia, 0.0, false, cliente);
        assertEquals(0.0, f.getSubtotal(), 0.01);
        assertEquals(0, f.getDetallesDeLaVenta().size());
    }

    // ── Fecha 

    @Test @DisplayName("Factura tiene fecha de hoy")
    void factura_fechaEsHoy() {
        Factura f = gestor.agregarFactura(mesaConProductos, 0.10, false, cliente);
        assertEquals(java.time.LocalDate.now(), f.getFecha());
    }
}