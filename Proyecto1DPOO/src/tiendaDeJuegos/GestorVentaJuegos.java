package tiendaDeJuegos;

import java.util.HashMap;
import java.time.LocalDate;
import calculadora.CalculadoraVentaJuegos;
import exceptions.JuegoNoDisponibleException;

public class GestorVentaJuegos {
	private CalculadoraVentaJuegos calc;
	
	public double calcularTotalVenta () {
		return Math.round(calc.calcularTotal());
	}
	
	
	public DetalleVenta crearDetalleVenta(InventarioJuegos inventario, String nombreJuego, int cantidad, TipoVenta tipoVenta)
			throws JuegoNoDisponibleException{
		
		HashMap<String,JuegoDeMesa> catalogo=inventario.getCatalogoJuegos();
		JuegoDeMesa juego= catalogo.getOrDefault(catalogo, null);
		if (juego==null) {
			throw new JuegoNoDisponibleException(nombreJuego);
		}
		else {
			double subtotal= calc.calcularSubtotal(juego, cantidad);
			double impuestos=calc.calcularImpuestos(subtotal);
			return new DetalleVenta(juego,cantidad,subtotal,impuestos,TipoVenta.TIENDADEJUEGOS);
		}
	}
	public Factura construirFactura(List<DetalleVenta> detallesVenta, double porcentajePropina,boolean tieneDescuento, Usuario cliente) {
		LocalDate fecha= LocalDate.now();
		double subtotal= 0.0;
		double impuestos= 0.0;
		for (DetalleVenta detalleVenta: detallesVenta) {
			subtotal+= detalleVenta.getSubtotal();
			impuestos+= detalleVenta.getImpuestos();
			
		}
		double descuento=0.0;
		double propina= calc.calcularPropina(subtotal, porcentajePropina); 
		if (cliente.isInstance(Empleado)) {
			descuento=calc.calcularDescuento(cliente, subtotal);
		}
		else if(tieneDescuento) {
			descuento=calc.calcularDescuento(subtotal, tieneDescuento);
		}
		double total= calc.calcularTotalJuegos(subtotal,impuestos,propina, descuento);
		return new Factura(fecha, subtotal, impuestos, propina,descuento, total);
	}
}
