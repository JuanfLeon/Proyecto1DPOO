package tiendaDeJuegos;

import java.util.ArrayList;
import java.util.HashMap;


import java.time.LocalDate;
import calculadora.CalculadoraVentaJuegos;
import dataBase.DetalleVenta;
import dataBase.Empleado;
import dataBase.Factura;
import dataBase.Usuario;
import exceptions.JuegoNoDisponibleException;

public class GestorVentaJuegos {
	private CalculadoraVentaJuegos calc;
	
	public double calcularTotalVenta (double subtotal, double impuestos, double propina, double descuento) {
		return Math.round(calc.calcularTotalJuegos(subtotal,impuestos,propina,descuento));
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
	public Factura construirFactura(ArrayList<DetalleVenta> detallesVenta, double porcentajePropina, boolean tieneDescuento, Usuario cliente) {
		LocalDate fecha= LocalDate.now();
		double subtotal= 0.0;
		double impuestos= 0.0;
		for (DetalleVenta detalleVenta: detallesVenta) {
			subtotal+= detalleVenta.getSubtotal();
			impuestos+= detalleVenta.getImpuestos();
			
		}
		double descuento=0.0;
		double propina= calc.calcularPropina(subtotal, porcentajePropina); 
		if (cliente instanceof Empleado) {
			descuento=calc.calcularDescuento(cliente, subtotal);
		}
		else if(tieneDescuento) {
			descuento=calc.calcularDescuento(subtotal, tieneDescuento);
		}
		double total= calcularTotalVenta(subtotal,impuestos,propina, descuento);
		return new Factura(fecha, subtotal, impuestos, propina,descuento, total);
	}
}
