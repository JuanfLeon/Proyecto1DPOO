package calculadora;

import java.util.List;
import tiendaDeJuegos.DetalleVenta;

public class CalculadoraVentas {

    public int calcularPuntosFidelidad(float montoVenta) {
        return Math.round(montoVenta * 0.01f);
    }

    public float calcularSubTotal(List<DetalleVenta> detalles) {
        float subtotal = 0;
        for (DetalleVenta d : detalles) {
            subtotal += d.getSubtotal();
        }
        return subtotal;
    }

    public float calcularImpuesto(float subtotal) {
        return subtotal * 0.08f;
    }

    public float calcularPropina(float subtotal, float porcentajePropina) {
        return subtotal * porcentajePropina;
    }

    public float calcularTotal(float subtotal, float impuesto, float propina) {
        return subtotal + impuesto + propina;
    }
}