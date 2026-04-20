package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;

import cafe.*;
import dataBase.Cocinero;
import tiendaDeJuegos.*;

@DisplayName("Tests — Cocinero (despacharProducto y validaciones)")
public class TestCocinero {

    private Cocinero cocinero;
    private Mesa mesaNormal;
    private Mesa mesaConNinos;
    private Mesa mesaConJuegoAccion;

    @BeforeEach
    void setUp() {
        cocinero = new Cocinero("chef01", new ArrayList<>(), "pass", new ArrayList<>(), "DESC-C1");

        mesaNormal = new Mesa("MESA-01", 4, false, false, false, new ArrayList<>(), null);
        mesaConNinos = new Mesa("MESA-02", 3, false, true, false, new ArrayList<>(), null);

        // Mesa con juego de Accion en productosOrdenados
        ArrayList<Producto> productosConAccion = new ArrayList<>();
        JuegoDeMesa twister = new JuegoDeMesa("tw0", "Twister", 30000, new Date(),
                "Hasbro", TipoDeJuego.ACCION, 2, 6, 6, "Accion", false);
        productosConAccion.add(twister);
        mesaConJuegoAccion = new Mesa("MESA-03", 4, false, false, true, productosConAccion, null);
    }

    // ── Bebida alcoholica con ninos 

    @Test @DisplayName("Bebida alcoholica en mesa con ninos -> Exception")
    void bebidaAlcoholica_conNinos_lanzaExcepcion() {
        Bebida cerveza = new Bebida("Cerveza", 8000, true, false);
        assertThrows(Exception.class,
            () -> cocinero.despacharProducto(mesaConNinos, cerveza));
    }

    @Test @DisplayName("Bebida alcoholica en mesa normal -> retorna producto")
    void bebidaAlcoholica_mesaNormal_retornaProducto() throws Exception {
        Bebida cerveza = new Bebida("Cerveza", 8000, true, false);
        Producto resultado = cocinero.despacharProducto(mesaNormal, cerveza);
        assertNotNull(resultado);
        assertEquals("Cerveza", resultado.getNombre());
    }

    // ── Bebida caliente con juego Accion 

    @Test @DisplayName("Bebida caliente con juego Accion en mesa -> Exception")
    void bebidaCaliente_conJuegoAccion_lanzaExcepcion() {
        Bebida te = new Bebida("Te", 4000, false, true);
        assertThrows(Exception.class,
            () -> cocinero.despacharProducto(mesaConJuegoAccion, te));
    }

    @Test @DisplayName("Bebida caliente en mesa sin juego Accion -> retorna producto")
    void bebidaCaliente_sinJuegoAccion_retornaProducto() throws Exception {
        Bebida te = new Bebida("Te", 4000, false, true);
        Producto resultado = cocinero.despacharProducto(mesaNormal, te);
        assertNotNull(resultado);
    }

    // ── Bebida no alcoholica, no caliente 

    @Test @DisplayName("Bebida normal en cualquier mesa -> siempre retorna producto")
    void bebidaNormal_siempreRetorna() throws Exception {
        Bebida agua = new Bebida("Agua", 2000, false, false);
        assertNotNull(cocinero.despacharProducto(mesaConNinos, agua));
        assertNotNull(cocinero.despacharProducto(mesaConJuegoAccion, agua));
        assertNotNull(cocinero.despacharProducto(mesaNormal, agua));
    }

    // ── Pasteleria 

    @Test @DisplayName("Pasteleria siempre se puede despachar sin restriccion")
    void pasteleria_sinRestriccion() throws Exception {
        ArrayList<String> alergenos = new ArrayList<>();
        alergenos.add("mani");
        Pasteleria brownie = new Pasteleria("Brownie", 8000, alergenos);
        Producto resultado = cocinero.despacharProducto(mesaConNinos, brownie);
        assertNotNull(resultado);
    }

    // ── checkEdad y checkBebidaCaliente 

    @Test @DisplayName("checkEdad mesa con ninos -> true")
    void checkEdad_conNinos_true() {
        assertTrue(cocinero.checkEdad(mesaConNinos));
    }

    @Test @DisplayName("checkEdad mesa sin ninos -> false")
    void checkEdad_sinNinos_false() {
        assertFalse(cocinero.checkEdad(mesaNormal));
    }

    @Test @DisplayName("checkBebidaCaliente mesa con bebida caliente -> true")
    void checkBebidaCaliente_true() {
        assertTrue(cocinero.checkBebidaCaliente(mesaConJuegoAccion));
    }

    @Test @DisplayName("checkBebidaCaliente mesa sin bebida caliente -> false")
    void checkBebidaCaliente_false() {
        assertFalse(cocinero.checkBebidaCaliente(mesaNormal));
    }
}