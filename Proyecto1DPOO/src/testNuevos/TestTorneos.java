package testNuevos;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import dataBase.*;
import exceptions.*;
import generals.DiaSemana;
import tiendaDeJuegos.*;
import torneo.*;

import java.util.ArrayList; 

@DisplayName("Test - Torneos (Creación, inscripciín, cupos fanaticos")
public class TestTorneos {

	private JuegoDeMesa juegoCartas;
	private Cliente clienteFanatico;
	private Cliente clienteNormal;
	private DataBase db ;
	private GestorTorneos gestor ;
	
	@BeforeEach
	void setUp() {
		juegoCartas = new JuegoDeMesa("J-01", "Cartas", 3000, null,
				"CartasCo", TipoDeJuego.CARTAS, 2, 6, 10, "clasico", false);
		
		ArrayList<String> favsFan = new ArrayList<>();
		favsFan.add("Cartas");
		clienteFanatico = new Cliente("fanatico", favsFan, "pass");
		clienteNormal = new Cliente("normal", new ArrayList<>(), "pass");
		db = new DataBase (new ArrayList<>(), new ArrayList<>(),
				new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
		
		gestor = new GestorTorneos(db);
	}
	
	
	//-------------------------------------------------------
	// Constructor Torneo (cupos fanaticos = ceil(20%)) -----
	//-------------------------------------------------------
	@Test
	@DisplayName("Torneo Amistoso 10 participantes : 2 cupos fanaticos, 8 normales") 
	void amistoso_10participantes_cupos20pociento() {
		TorneoAmistoso t = new TorneoAmistoso(
				new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 10);
		assertEquals(2, t.getCantidadJuegoFavoritoDisponible());
		assertEquals(8, t.getCantidadParticipantesDisponible());
		}
	
	
	@Test
	@DisplayName("Torneo Amistoso 5 participantes : ceil(1) = 1 cupo fanatico")
	void amistoso_5participantes_1cupoFanatico() {
		TorneoAmistoso t = new TorneoAmistoso(
				new ArrayList<>(), DiaSemana.MARTES, juegoCartas, 5);
		assertEquals(1, t.getCantidadJuegoFavoritoDisponible());
		assertEquals(4, t.getCantidadParticipantesDisponible()) ;
	}
	
	@Test
	@DisplayName("Torneo Competitivo tiene precio de entrada y dinero recaudado")
	void competitivo_precioYDinero_correctos() {
		TorneoCompetitivo t = new TorneoCompetitivo(
			new ArrayList<>(), DiaSemana.VIERNES, juegoCartas, 8, 20000,0);
		assertEquals(20000, t.getPrecioEntrada());
		assertEquals(0, t.getDineroRecaudado());
	}
	
	
	
	//-------------------------------------
	// GestorTorneos.crearTorneo ----------
	//-------------------------------------
	@Test
	@DisplayName("Crear Torneo Amistoso : se agrega a db.getTorneos")
	void crearAmistoso_seAgregaDataBase() {
		gestor.crearTorneoAmistoso(new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 10);
		assertEquals(1,db.getTorneos().size());
		assertInstanceOf(TorneoAmistoso.class, db.getTorneos().get(0));
	}
	
	@Test
	@DisplayName("Crear Torneo Competitivo : se agrega a db.getTorneos()")
	void crearCompetitivo_seAgregaDataBase() {
		gestor.crearTorneoCompetitivo(new ArrayList<>(), DiaSemana.SABADO, juegoCartas, 6, 15000, 0);
		assertEquals(1, db.getTorneos().size());
		assertEquals(TorneoCompetitivo.class, db.getTorneos().get(0));
	}
	
	@Test
	@DisplayName("Se puede crear dos torneos al tiempo") 
	void dosTorneos_Simultaneos() {
		gestor.crearTorneoAmistoso(new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 10);
		gestor.crearTorneoCompetitivo(new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 6, 5000, 0);
		assertEquals(2, db.getTorneos().size());
	}
	
	
	//-------------------------------------------
	// Inscripcion (validacion de tamaño) -------
	//-------------------------------------------
	@Test
	@DisplayName("Inscripción con 1 usuario, validar")
	void inscripcion_1usuario_valida() throws Exception{
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(clienteNormal); 
		Inscripcion ins = new Inscripcion(usuarios);
		assertNotNull(ins) ;
	}
	
	@Test
	@DisplayName("Inscripcion con 3 usuarios: valida (maximo permitido)")
	void inscripcion_3usuarios_valida() throws Exception {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		usuarios.add(clienteNormal);
		usuarios.add(clienteFanatico);
		usuarios.add(new Cliente("extra", new ArrayList<>(), "pass"));
		Inscripcion ins = new Inscripcion(usuarios);
		assertNotNull(ins);
	}
	
	@Test
	@DisplayName("Incripcion con 4 usuarios: lanza RestriccionJugadorException")
	void inscripcion_4usuarios_lanzaException() {
		ArrayList<Usuario> usuarios = new ArrayList<>();
		for(int i = 0; i < 4; i++)
			usuarios.add(new Cliente("u" + i, new ArrayList<>(), "p"));
		assertThrows(Exception.class, () -> new Inscripcion(usuarios));
	}
	
	@Test
	@DisplayName("Inscripcion vacia: lanza RestriccionJugadoresException")
	void inscripcion_vacia_lanzaException() {
		assertThrows(Exception.class, () -> new Inscripcion(new ArrayList<>()));
	}
	
	
	
	//-------------------------------------------
	// GestorTorneos.inscribirUsuariosTorneo ----
	//-------------------------------------------
	@Test
	@DisplayName("Inscribir un fanatico en torneo con cupo disponible -> exito")
    void inscribir_fanatico_exito() throws CupoNoDisponibleException {
        TorneoAmistoso torneo = new TorneoAmistoso(
                new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 10);
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(clienteFanatico);
        gestor.inscribirUsuariosTorneo(grupo, torneo);
        assertEquals(1, torneo.getInscripciones().size());
    }
 
    @Test
    @DisplayName("Inscribir un cliente normal en torneo -> exito")
    void inscribir_normal_exito() throws CupoNoDisponibleException {
        TorneoAmistoso torneo = new TorneoAmistoso(
                new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 10);
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(clienteNormal);
        gestor.inscribirUsuariosTorneo(grupo, torneo);
        assertEquals(1, torneo.getInscripciones().size());
    }
 
    @Test
    @DisplayName("Exceder cupos fanaticos -> lanza CupoNoDisponibleException")
    void inscribir_excedeFanaticos_lanzaExcepcion() {
        // 3 participantes: ceil(0.6) = 1 cupo fanatico
        TorneoAmistoso torneo = new TorneoAmistoso(
                new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 3);
 
        ArrayList<String> favs = new ArrayList<>(); favs.add("Cartas");
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(new Cliente("fan1", favs, "p"));
        grupo.add(new Cliente("fan2", favs, "p")); // 2 fanaticos, solo 1 cupo
 
        assertThrows(CupoNoDisponibleException.class,
                () -> gestor.inscribirUsuariosTorneo(grupo, torneo));
    }
 
    @Test
    @DisplayName("Exceder cupos normales -> lanza CupoNoDisponibleException")
    void inscribir_excedeNormales_lanzaExcepcion() {
        // 2 participantes: 1 cupo fanatico, 1 cupo normal
        TorneoAmistoso torneo = new TorneoAmistoso(
                new ArrayList<>(), DiaSemana.LUNES, juegoCartas, 2);
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(new Cliente("n1", new ArrayList<>(), "p"));
        grupo.add(new Cliente("n2", new ArrayList<>(), "p")); // 2 normales, solo 1 cupo
 
        assertThrows(CupoNoDisponibleException.class,
                () -> gestor.inscribirUsuariosTorneo(grupo, torneo));
    }
}
