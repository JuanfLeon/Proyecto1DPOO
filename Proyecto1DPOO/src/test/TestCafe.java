package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import cafe.*;
import dataBase.*;

@DisplayName("Tests — Cafe (agregarMesa, agregarPlatillo)")
public class TestCafe {

    private Cafe cafe;
    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cafe = new Cafe(10, new ArrayList<>(), new ArrayList<>());
        cafe.setCatalogoPlatillos(new ArrayList<>());
        cliente = new Cliente("juan", new ArrayList<>(), "pass");
    }

    // ── agregarMesa 

    @Test @DisplayName("agregarMesa -> lista de mesas crece en 1")
    void agregarMesa_creceEnUno() {
        cafe.agregarMesa("MESA-01", 4, false, false, false, new ArrayList<>(), cliente);
        assertEquals(1, cafe.getMesas().size());
    }

    @Test @DisplayName("agregarMesa guarda datos correctamente")
    void agregarMesa_datosCorrectos() {
        cafe.agregarMesa("MESA-01", 3, true, false, false, new ArrayList<>(), cliente);
        Mesa mesa = cafe.getMesas().get(0);
        assertEquals("MESA-01", mesa.getIdMesa());
        assertEquals(3, mesa.getCantidadClientes());
        assertTrue(mesa.isTieneJovenes());
        assertFalse(mesa.isTieneNinos());
    }

    @Test @DisplayName("agregarMesa multiples -> todas quedan en lista")
    void agregarMesa_multiples() {
        cafe.agregarMesa("MESA-01", 2, false, false, false, new ArrayList<>(), cliente);
        cafe.agregarMesa("MESA-02", 4, false, true,  false, new ArrayList<>(), cliente);
        cafe.agregarMesa("MESA-03", 3, true,  false, false, new ArrayList<>(), cliente);
        assertEquals(3, cafe.getMesas().size());
    }

    // ── agregarPlatillo 

    @Test @DisplayName("agregarPlatillo bebida -> queda en catalogo")
    void agregarPlatillo_bebida() {
        Bebida cafe_bebida = new Bebida("Espresso", 4000, false, true);
        cafe.agregarPlatillo(cafe_bebida);
        assertTrue(cafe.getCatalogoPlatillos().contains(cafe_bebida));
    }

    @Test @DisplayName("agregarPlatillo pasteleria -> queda en catalogo")
    void agregarPlatillo_pasteleria() {
        Pasteleria muffin = new Pasteleria("Muffin", 5000, new ArrayList<>());
        cafe.agregarPlatillo(muffin);
        assertEquals(1, cafe.getCatalogoPlatillos().size());
    }

    @Test @DisplayName("agregarPlatillo multiples -> todos en catalogo")
    void agregarPlatillo_multiples() {
        cafe.agregarPlatillo(new Bebida("Cafe", 4000, false, true));
        cafe.agregarPlatillo(new Bebida("Jugo", 5000, false, false));
        cafe.agregarPlatillo(new Pasteleria("Brownie", 8000, new ArrayList<>()));
        assertEquals(3, cafe.getCatalogoPlatillos().size());
    }

    // ── capacidadMaxClientes 

    @Test @DisplayName("capacidadMaxClientes se puede modificar")
    void capacidad_modificable() {
        cafe.setCapacidadMaxClientes(20);
        assertEquals(20, cafe.getCapacidadMaxClientes());
    }

    // ── Mesa: cliente titular 

    @Test @DisplayName("Mesa guarda referencia al clienteTitular")
    void mesa_clienteTitular_correcto() {
        cafe.agregarMesa("MESA-01", 2, false, false, false, new ArrayList<>(), cliente);
        Mesa mesa = cafe.getMesas().get(0);
        assertEquals(cliente, mesa.getClienteTitular());
    }

    // ── Mesa: tieneBebidaCaliente 

    @Test @DisplayName("Mesa con tieneBebidaCaliente=true -> flag activo")
    void mesa_bebidaCaliente_activa() {
        cafe.agregarMesa("MESA-01", 2, false, false, true, new ArrayList<>(), cliente);
        assertTrue(cafe.getMesas().get(0).isTieneBebidaCaliente());
    }
}