package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import dataBase.*;

@DisplayName("Tests — Turno (validaciones de cobertura minima)")
public class TestTurno {

    private Mesero mesero1;
    private Mesero mesero2;
    private Mesero mesero3;
    private Cocinero cocinero1;
    private Cocinero cocinero2;

    @BeforeEach
    void setUp() {
        mesero1  = new Mesero("m1", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        mesero2  = new Mesero("m2", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        mesero3  = new Mesero("m3", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        cocinero1 = new Cocinero("c1", new ArrayList<>(), "p", new ArrayList<>(), "D");
        cocinero2 = new Cocinero("c2", new ArrayList<>(), "p", new ArrayList<>(), "D");
    }

    // ── Constructor 

    @Test @DisplayName("Turno con 2 meseros y 1 cocinero -> se crea correctamente")
    void constructor_valido() {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);

        assertDoesNotThrow(() -> new Turno(DiaSemana.LUNES, meseros, cocineros));
    }

    @Test @DisplayName("Turno con solo 1 mesero -> lanza Exception")
    void constructor_1mesero_lanzaExcepcion() {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); // solo 1, minimo es 2
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);

        assertThrows(Exception.class,
            () -> new Turno(DiaSemana.LUNES, meseros, cocineros));
    }

    @Test @DisplayName("Turno sin cocineros -> lanza Exception")
    void constructor_sinCocineros_lanzaExcepcion() {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>(); // vacia

        assertThrows(Exception.class,
            () -> new Turno(DiaSemana.LUNES, meseros, cocineros));
    }

    @Test @DisplayName("Turno con dia null -> lanza Exception")
    void constructor_diaNulo_lanzaExcepcion() {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);

        assertThrows(Exception.class,
            () -> new Turno(null, meseros, cocineros));
    }

    // ── agregarMesero 

    @Test @DisplayName("agregarMesero nuevo -> turno tiene 3 meseros")
    void agregarMesero_nuevo_suma() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        turno.agregarMesero(mesero3);

        assertEquals(3, turno.getMeseros().size());
    }

    @Test @DisplayName("agregarMesero duplicado -> lanza Exception")
    void agregarMesero_duplicado_lanzaExcepcion() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        assertThrows(Exception.class, () -> turno.agregarMesero(mesero1));
    }

    // ── eliminarMesero 

    @Test @DisplayName("eliminarMesero con 3 en turno -> quedan 2")
    void eliminarMesero_de3_quedan2() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2); meseros.add(mesero3);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        turno.eliminarMesero(mesero3);

        assertEquals(2, turno.getMeseros().size());
    }

    @Test @DisplayName("eliminarMesero con exactamente 2 -> lanza Exception (minimo)")
    void eliminarMesero_de2_lanzaExcepcion() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        assertThrows(Exception.class, () -> turno.eliminarMesero(mesero1));
    }

    // ── eliminarCocinero 

    @Test @DisplayName("eliminarCocinero con 2 cocineros -> quedan 1")
    void eliminarCocinero_de2_queda1() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1); cocineros.add(cocinero2);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        turno.eliminarCocinero(cocinero2);

        assertEquals(1, turno.getCocineros().size());
    }

    @Test @DisplayName("eliminarCocinero con 1 cocinero -> lanza Exception (minimo)")
    void eliminarCocinero_de1_lanzaExcepcion() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        assertThrows(Exception.class, () -> turno.eliminarCocinero(cocinero1));
    }

    // ── agregarCocinero 

    @Test @DisplayName("agregarCocinero duplicado -> lanza Exception")
    void agregarCocinero_duplicado_lanzaExcepcion() throws Exception {
        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);
        Turno turno = new Turno(DiaSemana.LUNES, meseros, cocineros);

        assertThrows(Exception.class, () -> turno.agregarCocinero(cocinero1));
    }
}