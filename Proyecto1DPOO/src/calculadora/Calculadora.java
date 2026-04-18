package calculadora;

import java.util.ArrayList;

import cafe.Mesa;
import cafe.Producto;
import dataBase.Empleado;
import dataBase.Usuario;
import tiendaDeJuegos.JuegoDeMesa;

public interface Calculadora {
	static final double IVA=0.19;
	static final double descuentoEmpleados= 0.2;
	static final double descuentoClientes= 0.1;
	static final double impuestoConsumoAsociado= 0.08;
	static final double propinaBase=0.1;
	static final double porcentajePuntos=0.1;
	
	public double calcularImpuestos(double subtotal);

	
	public default double calcularDescuento(Usuario empleado, double subtotal) {
		if (empleado instanceof Empleado) {
		return subtotal*(descuentoEmpleados);
		}
		else {
			return subtotal;
		}
	}
	
	public default double calcularDescuento(double subtotal, boolean tieneCodigo) {
		if(tieneCodigo) {
			return subtotal*(descuentoClientes);
		}
		return subtotal;
	}
	public default double calcularPropina(double subtotal) {
		return subtotal*(propinaBase);
	}
	public default double calcularPropina(double subtotal, double porcentaje) {
		return subtotal*(porcentaje);
	}
	public default int calcularPuntosFidelidad(double subtotal) {
		return (int) Math.round(subtotal*porcentajePuntos);
	}
	public default double calcularSubtotal(Mesa mesa) {
		ArrayList<Producto> productosOrdenados= mesa.getProductosOrdenados();
		double subtotal=0.0;
		for (Producto producto: productosOrdenados) {
			subtotal+=producto.getPrecio();
		}
		return subtotal;
	}
	public default double calcularSubtotal(JuegoDeMesa juego,double cantidad) {
		return juego.getPrecio()*cantidad;
	}
}
