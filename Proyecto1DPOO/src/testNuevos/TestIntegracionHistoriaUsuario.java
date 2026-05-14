package testNuevos;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
 
import java.util.ArrayList;
 
import cafe.*;
import dataBase.*;
import exceptions.*;
import generals.DiaSemana;
import tiendaDeJuegos.*;
import torneo.*;
 
@DisplayName("Pruebas de Integracion — Historias de Usuario")
public class TestIntegracionHistoriaUsuario {
 
    private DataBase db;
    private InventarioJuegos inventario;
    private Cafe cafe;
    private Mesero mesero1;
    private Mesero mesero2;
    private Cocinero cocinero1;
    private Turno turnoLunes;
    private Cliente cliente;
    private JuegoDeMesa juegoBase;
 
    @BeforeEach
    void setUp() throws Exception {
        db = new DataBase(new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        inventario = new InventarioJuegos();
        cafe = new Cafe(30, new ArrayList<>(), new ArrayList<>());
        cafe.setCatalogoPlatillos(new ArrayList<>());
 
        mesero1 = new Mesero("m1", new ArrayList<>(), "p", new ArrayList<>(), "D1", new ArrayList<>());
        mesero2 = new Mesero("m2", new ArrayList<>(), "p", new ArrayList<>(), "D2", new ArrayList<>());
        cocinero1 = new Cocinero("c1", new ArrayList<>(), "p", new ArrayList<>(), "D3");
 
        ArrayList<Mesero> ms = new ArrayList<>(); ms.add(mesero1); ms.add(mesero2);
        ArrayList<Cocinero> cs = new ArrayList<>(); cs.add(cocinero1);
        turnoLunes = new Turno(DiaSemana.LUNES, ms, cs);
 
        ArrayList<String> favs = new ArrayList<>(); favs.add("Catan");
        cliente = new Cliente("ana", favs, "1234");
        cliente.setJuegosComprados(new ArrayList<>());
        cliente.setJuegosPrestados(new ArrayList<>());
        cliente.setPuntosFidelidad(0);
 
        juegoBase = new JuegoDeMesa("J-BASE", "Catan", 60000, null, "MakersEd", TipoDeJuego.TABLERO, 3, 6, 12, "clasico", false);
 
        JuegoDeMesaFisico f1 = new JuegoDeMesaFisico("V1","Catan",60000,null,"M",TipoDeJuego.TABLERO,3,6,12,"",false,"nuevo",false);
        JuegoDeMesaFisico f2 = new JuegoDeMesaFisico("V2","Catan",60000,null,"M",TipoDeJuego.TABLERO,3,6,12,"",false,"nuevo",false);
        JuegoDeMesaFisico f3 = new JuegoDeMesaFisico("P1","Catan",60000,null,"M",TipoDeJuego.TABLERO,3,6,12,"",false,"nuevo",false);
        inventario.agregarJuegoInventario(f1, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(f2, "Catan", TipoInventario.VENTAS);
        inventario.agregarJuegoInventario(f3, "Catan", TipoInventario.PRESTAMO);
    }
 

    // HU-01: Cliente reserva mesa ----
    @Test
    @DisplayName("HU-01: Cliente reserva mesa con bebida caliente")
    void hu01_clienteReservaMesa() {
        cafe.agregarMesa("M-01", 4, false, false, true, new ArrayList<>(), cliente);
        assertEquals(1, cafe.getMesas().size());
        assertTrue(cafe.getMesas().get(0).isTieneBebidaCaliente());
    }
 

    
    // HU-02: Cliente ordena platillo -- 
    @Test
    @DisplayName("HU-02: Cliente agrega platillo a mesa y se registra")
    void hu02_clienteOrdenaPlatillo() {
        cafe.agregarMesa("M-02", 2, false, false, false, new ArrayList<>(), cliente);
        Mesa mesa = cafe.getMesas().get(0);
        mesa.getProductosOrdenados().add(new Bebida("Americano", 5000, false, true));
        assertEquals(1, mesa.getProductosOrdenados().size());
    }
 
    
    
    // HU-03: Empleado solicita cambio de turno --
    @Test
    @DisplayName("HU-03: Mesero solicita cambio de turno: queda en DataBase")
    void hu03_empleadoSolicitaCambio() throws Exception {
        Mesero m3 = new Mesero("m3", new ArrayList<>(), "p", new ArrayList<>(), "D3", new ArrayList<>());
        Cocinero c2 = new Cocinero("c2", new ArrayList<>(), "p", new ArrayList<>(), "D4");
        ArrayList<Mesero>   ms2 = new ArrayList<>(); ms2.add(mesero1); ms2.add(m3);
        ArrayList<Cocinero> cs2 = new ArrayList<>(); cs2.add(c2);
        Turno turnoMartes = new Turno(DiaSemana.MARTES, ms2, cs2);
 
        SolicitudCambioTurno s = m3.solicitarCambioHorario(turnoMartes, turnoLunes);
        db.getSolicitudes().add(s);
 
        assertEquals(1, db.getSolicitudes().size());
        assertInstanceOf(SolicitudCambioTurno.class, db.getSolicitudes().get(0));
    }
 
    
    
    // HU-04: Admin aprueba cambio de turno ----------
    @Test
    @DisplayName("HU-04: Admin acepta cambio y el mesero cambia efectivamente de turno")
    void hu04_adminAceptaCambioTurno() throws Exception {
        Mesero m3 = new Mesero("m3", new ArrayList<>(), "p", new ArrayList<>(), "D3", new ArrayList<>());
        Cocinero c2 = new Cocinero("c2", new ArrayList<>(), "p", new ArrayList<>(), "D4");
        ArrayList<Mesero>   ms2 = new ArrayList<>(); ms2.add(mesero1); ms2.add(mesero2); ms2.add(m3);
        ArrayList<Cocinero> cs2 = new ArrayList<>(); cs2.add(c2);
        Turno turnoMartes = new Turno(DiaSemana.MARTES, ms2, cs2);
 
        SolicitudCambioTurno s = m3.solicitarCambioHorario(turnoMartes, turnoLunes);
        new GestorSolicitudesTurno().aceptarSolicitud(s);
 
        assertTrue(turnoLunes.getMeseros().contains(m3));
        assertFalse(turnoMartes.getMeseros().contains(m3));
    }

    
    
    // HU-05: Admin crea torneo amistoso ------
    @Test
    @DisplayName("HU-05: Admin crea torneo amistoso -> queda en DataBase")
    void hu05_adminCreaTorneoAmistoso() {
        new GestorTorneos(db).crearTorneoAmistoso(
                new ArrayList<>(), DiaSemana.VIERNES, juegoBase, 12);
        assertEquals(1, db.getTorneos().size());
        assertInstanceOf(TorneoAmistoso.class, db.getTorneos().get(0));
    }

    
    
    // HU-06: Cliente se inscribe en torneo ------
    @Test
    @DisplayName("HU-06: Cliente fanatico se inscribe -> inscripcion registrada")
    void hu06_clienteSeInscribeTorneo() throws CupoNoDisponibleException {
        GestorTorneos gestor = new GestorTorneos(db);
        gestor.crearTorneoAmistoso(new ArrayList<>(), DiaSemana.VIERNES, juegoBase, 10);
        Torneo torneo = db.getTorneos().get(0);
 
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(cliente); // tiene "Catan" como favorito
        gestor.inscribirUsuariosTorneo(grupo, torneo);
 
        assertEquals(1, torneo.getInscripciones().size());
    }
 
    
    
    // HU-07: Cupos fanaticos se respetan ----------
    @Test
    @DisplayName("HU-07: Exceder cupo fanatico -> inscripcion rechazada")
    void hu07_cupoFanaticoRespetado() {
        GestorTorneos gestor = new GestorTorneos(db);
        gestor.crearTorneoAmistoso(new ArrayList<>(), DiaSemana.LUNES, juegoBase, 3);
        Torneo torneo = db.getTorneos().get(0);
 
        ArrayList<String> favCatan = new ArrayList<>(); favCatan.add("Catan");
        ArrayList<Usuario> grupo = new ArrayList<>();
        grupo.add(new Cliente("fan1", favCatan, "p"));
        grupo.add(new Cliente("fan2", favCatan, "p"));
 
        assertThrows(CupoNoDisponibleException.class,
                () -> gestor.inscribirUsuariosTorneo(grupo, torneo));
    }
 
    
    
    // HU-08: Empleado sugiere platillo, admin lo aceptan ------------
    @Test
    @DisplayName("HU-08: Sugerencia de platillo aceptada -> aparece en menu del cafe")
    void hu08_sugerenciaPlatilloAceptada() {
        Bebida sugerida = new Bebida("Matcha Latte", 7000, false, true);
        SolicitudSugerenciaPlatillo sol = new SolicitudSugerenciaPlatillo(sugerida, mesero1);
        db.getSolicitudes().add(sol);
 
        new GestorSolicitudesPlatillos().aceptarSolicitud(cafe, sol);
 
        assertEquals(1, cafe.getCatalogoPlatillos().size());
        assertEquals("Matcha Latte", cafe.getCatalogoPlatillos().get(0).getNombre());
    }
 
    
    
    //HU-09: Factura cafe con descuento empleado ----------------
    @Test
    @DisplayName("HU-09: Factura para empleado -> total menor que sin descuento")
    void hu09_facturaEmpleado_descuento20() {
        ArrayList<Producto> prods = new ArrayList<>();
        prods.add(new Bebida("Espresso", 4000, false, true));
        prods.add(new Pasteleria("Croissant", 6000, new ArrayList<>()));
        Mesa mesa = new Mesa("M-E", 1, false, false, true, prods, null);
 
        Factura f               = new GestorVentaCafe().agregarFactura(mesa, 0.0, false, mesero1);
        double totalSinDescuento = 10000 + 10000 * 0.08;
 
        assertTrue(f.getTotal() < totalSinDescuento);
    }
 
    
    //HU-10: Inventario insuficiente al mover ---------------------
    @Test
    @DisplayName("HU-10: Mover juego sin stock en ventas -> lanza InventarioInsuficienteException")
    void hu10_moverJuegoSinStock_lanzaExcepcion() {
        inventario.getInventarioVenta().get("Catan").clear();
        GestorInventarioJuegos gest = new GestorInventarioJuegos();
        gest.setInventarioJuegos(inventario);
 
        assertThrows(InventarioInsuficienteException.class,
                () -> gest.moverJuegoDeVentasAPrestamo("Catan"));
    }
}