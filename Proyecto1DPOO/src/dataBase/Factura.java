package dataBase;

import java.time.LocalDateTime;
import java.util.List;

public class Factura {

	private LocalDateTime fecha;
	private float subtotal;
	private float impuestos;
	private float propina;
	private List<DetalleVenta> detallesDeLaVenta;
	
}
