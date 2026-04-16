package tiendaDeJuegos;

import java.util.ArrayList;
import java.util.HashMap;

import exceptions.JuegoNoDisponibleException;

public class TiendaDeJuegos {
	
	private InventarioJuegos inventarioJuegos;
	private GestorVentaJuegos gestorVentaJuegos;
	private GestorPrestamoJuegos gestorPrestamoJuegos;
	private GestorInventarioJuegos gestorInventarioJuegos;
	
	public TiendaDeJuegos() {
		this.inventarioJuegos= new InventarioJuegos();
		this.gestorVentaJuegos= new GestorVentaJuegos();
		gestorPrestamoJuegos= new GestorPrestamoJuegos();
		this.gestorInventarioJuegos= new GestorInventarioJuegos();
	}

	public Factura venderJuego(HashMap<String, Integer> juegosDeseados,double propina ,double descuento, Usuario cliente) throws JuegoNoDisponibleException {
		ArrayList<DetalleVenta> detallesVenta= new ArrayList<>();
		
		for(String nombreJuego: juegosDeseados.keySet()) {
			gestorInventarioJuegos.getInventarioJuegos().validarDisponibilidadJuegos(nombreJuego,
																					juegosDeseados.get(nombreJuego),
																					TipoInventario.VENTAS);
			DetalleVenta detalleVenta =gestorVentaJuegos.crearDetalleVenta(inventarioJuegos, nombreJuego, juegosDeseados.get(nombreJuego), TipoVenta.TIENDADEJUEGOS);
			detallesVenta.append(detalleVenta);
			
			inventarioJuegos.eliminarPrimerJuego(nombreJuego, TipoInventario.VENTAS);
		}	
		Factura faturaVenta=gestorVentaJuegos.construirFactura(detallesVenta, propina, descuento, cliente);
		
		return facturaVenta;
		
	}
	public Prestamo prestarJuego()
	
	
}
