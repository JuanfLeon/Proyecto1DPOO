package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import dataBase.*;

@DisplayName("Tests — Solicitudes de turno y sugerencias de platillo")
public class TestSolicitudes {

    private Mesero mesero1;
    private Mesero mesero2;
    private Mesero mesero3;
    private Cocinero cocinero1;
    private Turno turnoLunes;
    private Turno turnoMartes;
    private GestorSolicitudesTurno gestorTurnos;
    private GestorSolicitudesPlatillos gestorPlatillos;

    @BeforeEach
    void setUp() throws Exception {
        mesero1  = new Mesero("m1", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        mesero2  = new Mesero("m2", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        mesero3  = new Mesero("m3", new ArrayList<>(), "p", new ArrayList<>(), "D", new ArrayList<>());
        cocinero1 = new Cocinero("c1", new ArrayList<>(), "p", new ArrayList<>(), "D");

        ArrayList<Mesero> meseros = new ArrayList<>();
        meseros.add(mesero1); meseros.add(mesero2); meseros.add(mesero3);
        ArrayList<Cocinero> cocineros = new ArrayList<>();
        cocineros.add(cocinero1);

        turnoLunes  = new Turno(DiaSemana.LUNES,  meseros, cocineros);
        turnoMartes = new Turno(DiaSemana.MARTES, meseros, cocineros);

        gestorTurnos   = new GestorSolicitudesTurno();
        gestorPlatillos = new GestorSolicitudesPlatillos();
    }

    // ── SolicitudCambioTurno 
    
    @Test @DisplayName("Empleado crea SolicitudCambioTurno con datos correctos")
    void solicitudCambio_creada_correctamente() {
        SolicitudCambioTurno sol = mesero1.solicitarCambioHorario(turnoLunes, turnoMartes);

        assertNotNull(sol);
        assertEquals(mesero1, sol.getSolicitante());
        assertEquals(turnoLunes,  sol.getTurnoInicial());
        assertEquals(turnoMartes, sol.getTurnoDeseado());
    }

    @Test @DisplayName("aceptarSolicitudCambio -> mesero1 pasa de lunes a martes")
    void aceptarCambio_meseroMueveATurnoDeseado() throws Exception {
        SolicitudCambioTurno sol = mesero1.solicitarCambioHorario(turnoLunes, turnoMartes);

        gestorTurnos.aceptarSolicitud(sol);

        assertTrue(turnoMartes.getMeseros().contains(mesero1));
        assertFalse(turnoLunes.getMeseros().contains(mesero1));
    }

    // ── SolicitudIntercambioTurno 

    @Test @DisplayName("Empleado crea SolicitudIntercambioTurno con ambos empleados")
    void solicitudIntercambio_creada_correctamente() {
        SolicitudIntercambioTurno sol = mesero1.solicitarIntercambioHorario(
                turnoLunes, turnoMartes, mesero2);

        assertNotNull(sol);
        assertEquals(mesero1, sol.getSolicitante());
        assertEquals(mesero2, sol.getEmpleadoIntercambiable());
        assertEquals(turnoLunes,  sol.getTurnoInicial());
        assertEquals(turnoMartes, sol.getTurnoDeseado());
    }

    @Test @DisplayName("aceptarSolicitudIntercambio -> empleados intercambian turnos")
    void aceptarIntercambio_intercambiaCorrectamente() throws Exception {
        SolicitudIntercambioTurno sol = mesero1.solicitarIntercambioHorario(
                turnoLunes, turnoMartes, mesero2);

        gestorTurnos.aceptarSolicitud(sol);

        // mesero1 debe estar en martes, mesero2 en lunes
        assertTrue(turnoMartes.getMeseros().contains(mesero1));
        assertTrue(turnoLunes.getMeseros().contains(mesero2));
    }

    // ── SolicitudSugerenciaPlatillo 

    @Test @DisplayName("Empleado crea SolicitudSugerenciaPlatillo")
    void solicitudSugerencia_creada() {
        cafe.Bebida kombucha = new cafe.Bebida("Kombucha", 7000, false, false);
        SolicitudSugerenciaPlatillo sol = new SolicitudSugerenciaPlatillo(kombucha, mesero1);

        assertNotNull(sol);
        assertEquals(mesero1,  sol.getSolicitante());
        assertEquals(kombucha, sol.getPlatilloSugerido());
    }

    @Test @DisplayName("aceptarSugerencia -> platillo se agrega al cafe")
    void aceptarSugerencia_agregaAlCafe() {
        cafe.Cafe cafeLocal = new cafe.Cafe(20, new ArrayList<>(), new ArrayList<>());
        cafeLocal.setCatalogoPlatillos(new ArrayList<>());

        cafe.Bebida kombucha = new cafe.Bebida("Kombucha", 7000, false, false);
        SolicitudSugerenciaPlatillo sol = new SolicitudSugerenciaPlatillo(kombucha, mesero1);

        gestorPlatillos.aceptarSolicitud(cafeLocal, sol);

        assertTrue(cafeLocal.getCatalogoPlatillos().contains(kombucha));
    }

    // ── Empleado.agregarTurno 

    @Test @DisplayName("Empleado.agregarTurno agrega correctamente")
    void empleado_agregarTurno_correcto() throws Exception {
        mesero1.agregarTurno(turnoLunes);
        assertTrue(mesero1.getTurnoLaboral().contains(turnoLunes));
    }

    @Test @DisplayName("Empleado.agregarTurno duplicado -> lanza Exception")
    void empleado_agregarTurno_duplicado_lanzaExcepcion() throws Exception {
        mesero1.agregarTurno(turnoLunes);
        assertThrows(Exception.class, () -> mesero1.agregarTurno(turnoLunes));
    }

    @Test @DisplayName("Empleado.eliminarTurno -> turno se quita")
    void empleado_eliminarTurno() throws Exception {
        mesero1.agregarTurno(turnoLunes);
        mesero1.eliminarTurno(turnoLunes);
        assertFalse(mesero1.getTurnoLaboral().contains(turnoLunes));
    }
}