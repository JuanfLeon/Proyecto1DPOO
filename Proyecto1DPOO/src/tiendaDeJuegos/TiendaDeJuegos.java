package tiendaDeJuegos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import cafe.Mesa;
import dataBase.DetalleVenta;
import dataBase.Factura;
import dataBase.Usuario;
import exceptions.JuegoNoDisponibleException;

public class TiendaDeJuegos {
	
	private InventarioJuegos inventarioJuegos;
	private GestorVentaJuegos gestorVentaJuegos;
	private GestorInventarioJuegos gestorInventarioJuegos;
	
	public TiendaDeJuegos() {
		this.inventarioJuegos= new InventarioJuegos();
		this.gestorVentaJuegos= new GestorVentaJuegos();
		this.gestorInventarioJuegos= new GestorInventarioJuegos();
	}

	public Factura venderJuego(HashMap<String, Integer> juegosDeseados,double propina ,boolean tieneDescuento, Usuario cliente) throws JuegoNoDisponibleException {
		ArrayList<DetalleVenta> detallesVenta= new ArrayList<>();
		
		for(String nombreJuego: juegosDeseados.keySet()) {
			gestorInventarioJuegos.getInventarioJuegos().validarDisponibilidadJuegos(nombreJuego,
																					juegosDeseados.get(nombreJuego),
																					TipoInventario.VENTAS);
			DetalleVenta detalleVenta =gestorVentaJuegos.crearDetalleVenta(inventarioJuegos, nombreJuego, juegosDeseados.get(nombreJuego), TipoVenta.TIENDADEJUEGOS);
			detallesVenta.add(detalleVenta);
			
			inventarioJuegos.eliminarPrimerJuego(nombreJuego, TipoInventario.VENTAS);
		}	
		Factura facturaVenta=gestorVentaJuegos.construirFactura(detallesVenta, propina, tieneDescuento, cliente);
		
		return facturaVenta;
		
	}
	
	
	public Prestamo prestarJuego(String nombreJuego,Mesa mesa, LocalDate tiempoLimite) throws JuegoNoDisponibleException {
		try {
			gestorInventarioJuegos.getInventarioJuegos().validarDisponibilidadJuegos(nombreJuego, 1, TipoInventario.PRESTAMO);
			
			JuegoDeMesaFisico juego=inventarioJuegos.eliminarPrimerJuego(nombreJuego, TipoInventario.PRESTAMO);
			LocalDate fechaActual= LocalDate.now();
			Prestamo prestamo= new Prestamo(juego.getIdJuego(),mesa.getIdMesa(), fechaActual, tiempoLimite);
			inventarioJuegos.agregarPrestamoAHistorial(prestamo);
			return prestamo;
		} catch (JuegoNoDisponibleException e) {
			e.getMessage();
		}
		return null;
		
		
		
		
	}
	
	
}
