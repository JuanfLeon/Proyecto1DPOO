package testNuevos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
 
import java.util.ArrayList;

import dataBase.*; 
import generals.DiaSemana; 

@DisplayName("Test - Solicitudes de turno")
public class TestSolicitudesTurno {
	
	private Mesero mesero1;
	private Mesero mesero2;
	private Mesero mesero3;
	private Cocinero cocinero1;
	private Turno turnoLunes;
	private Turno turnoMartes;
	private GestorSolicitudesTurno gestor;
	
	@BeforeEach
	void setUp() throws Exception{
		mesero1 = new Mesero("m1", new ArrayList<>(), "p", new ArrayList<>(), "D1", new ArrayList<>() );
		mesero2 = new Mesero("m2", new ArrayList<>(), "p", new ArrayList<>(), "D2", new ArrayList<>() );
		mesero3 = new Mesero("m3", new ArrayList<>(), "p", new ArrayList<>(), "D3", new ArrayList<>() );
		cocinero1 = new Cocinero("c1", new ArrayList<>(), "p", new ArrayList<>(), "D4");
		
		ArrayList<Mesero> mLunes = new ArrayList<>();
		mLunes.add(mesero1); mLunes.add(mesero2); mLunes.add(mesero3);
		ArrayList<Cocinero> cLunes = new ArrayList<>();
		cLunes.add(cocinero1);
		turnoLunes = new Turno(DiaSemana.LUNES, mLunes,cLunes);
		
		ArrayList<Mesero> mMartes = new ArrayList<>();
		mMartes.add(mesero1); mMartes.add(mesero2);
		ArrayList<Cocinero> cMartes = new ArrayList<>();
		cMartes.add(cocinero1);
		turnoMartes = new Turno(DiaSemana.MARTES, mMartes, cMartes);
	}

	
	// Solicitudes Cambio Turno
	@Test
	@DisplayName("SolicitarCambioHorario devuelve SCH con datos correctos")
	void solicitudCambio_daatos_correctos() {
		SolicitudCambioTurno s = mesero3.solicitarCambioHorario(turnoLunes, turnoMartes);
		
		assertSame(turnoLunes, s.getTurnoInicial());
		assertSame(turnoMartes, s.getTurnoDeseado());
		assertSame(mesero3, s.getSolicitante());
		
	}
	
	@Test
	@DisplayName("Aceptar Solicitud Cambio: mesero pasa de turnoLunes a turnoMartes")
	void aceptarCambio_mueveMesero() throws Exception{
		
		SolicitudCambioTurno s = mesero3.solicitarCambioHorario(turnoLunes, turnoMartes);
		gestor.aceptarSolicitud(s);
		
		assertFalse(turnoLunes.getMeseros().contains(mesero3));
		assertTrue(turnoMartes.getMeseros().contains(mesero3));
	}
	
	
	// Solicitud Intercambio de Turno
	@Test
	@DisplayName("Solicitar Intercambio de Horario: devuelve solicitud con empleado Intermbiale")
	void solicitudIntercambio_datos_correctos() {
		SolicitudIntercambioTurno s = mesero3.solicitarIntercambioHorario(turnoLunes, turnoMartes, mesero2);
		
		assertSame(mesero3, s.getSolicitante());
		assertSame(mesero2, s.getEmpleadoIntercambiable());
	}
	
	@Test
	@DisplayName("Aceptar Solicitud Intercambio: ambos empleados intercambian turno")
	void aceptarIntercambio_intercambioTurnos() throws Exception{
		//mesero3 en turnoLunes y mesero2 en turnoMartes
		
		SolicitudIntercambioTurno s = mesero3.solicitarIntercambioHorario(turnoMartes, turnoMartes, mesero2);
		gestor.aceptarSolicitud(s);
		
		//mesero3 ahora en turnoMartes
		assertTrue(turnoMartes.getMeseros().contains(mesero3));
		assertFalse(turnoLunes.getMeseros().contains(mesero3));
		
		//mesero2 ahora en turnoLunes
		assertTrue(turnoLunes.getMeseros().contains(mesero2));
	}
	

	// Restriccion minimo meseros al eliminar
	@Test
	@DisplayName("Aceptar Cambio con 2 meseros en turno origen, lanza opcion Exception(minimo")
	void aceptarCambio_turnoconMinimo_lanzaException() {
		//turnoMartes ya tiene exacamente 2 meseros (mesero1 y mesero2)
		// intentar sacar mesero2 de ahi, entonces deberia fallar el programa
		
		SolicitudCambioTurno s = mesero2.solicitarCambioHorario(turnoMartes, turnoMartes);
		
		assertThrows(Exception.class, () -> gestor.aceptarSolicitud(s));
	}
}
