package calculadora;


public class CalculadoraVentaJuegos implements Calculadora{

	@Override
	public double calcularImpuestos(double subtotal) {
		return subtotal* IVA;
	}

	public double calcularTotalJuegos(double subtotal, double impuestos, double propina, double descuento) {
		return subtotal-descuento+impuestos+propina;
	}
	
}