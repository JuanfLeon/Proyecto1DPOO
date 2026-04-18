package calculadora;

import cafe.Mesa;

public class CalculadoraVentaCafe implements Calculadora{

	@Override
	public double calcularImpuestos(double subtotal) {
		return subtotal*impuestoConsumoAsociado;
	}

	public double calcularTotal(Mesa mesa, double propina) {
		
		double subtotal= calcularSubtotal(mesa);
		double impuestos= calcularImpuestos(subtotal);
		double propinaVenta = calcularPropina(subtotal,propina);
		
		
		
		
		return subtotal+ impuestos+ propinaVenta;
	}

}
