package cafe;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import calculadora.CalculadoraVentaCafe;
import dataBase.*;
import tiendaDeJuegos.TipoVenta;

public class GestorVentaCafe implements Serializable {
	private static final long serialVersionUID = 1L ;
	
	//Methods 
	public Factura agregarFactura(Mesa mesa, double propina, boolean tieneCodigoDeDescuento, Usuario cliente) {
		
		CalculadoraVentaCafe calc = new CalculadoraVentaCafe() ;
		
		//Quiero calcular el subtotal de los productos de la mesa
		double subtotal = calc.calcularSubtotal(mesa);
		
		//Quiero calcular el impuesto del consumo de la mesa(es de 8%)
		double impuesto = calc.calcularImpuestos(subtotal);
		
		//Quiero calcular la propina 
		double propinaVenta = calc.calcularPropina(subtotal, propina);
		
		//Quiero calcular el descuento (solo si aplica)
		double descuento = 0.0 ;
		
		if (cliente instanceof Empleado) {
			descuento = calc.calcularDescuento(cliente, subtotal);} //este tiene 20%
		
		else if (tieneCodigoDeDescuento){
			descuento = calc.calcularDescuento(subtotal, true) ; //este tiene 10%
		}
		
		//el total de todo
		double total = subtotal + impuesto + propinaVenta + descuento ;
		
		//detalles 
		ArrayList<DetalleVenta> detalles = new ArrayList<>() ;
		for (Producto p : mesa.getProductosOrdenados()) {
			double subtotalDetalle = p.getPrecio();
			double impuestosDetalle = calc.calcularImpuestos(subtotalDetalle) ;
			detalles.add(new DetalleVenta(p, 1, subtotalDetalle, impuestosDetalle, TipoVenta.CAFE));
		}
		return new Factura(LocalDate.now(), subtotal, impuesto, propinaVenta, descuento, total, detalles);
	}
}
