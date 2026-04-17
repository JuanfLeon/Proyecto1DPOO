package dataBase;

import java.time.LocalDateTime;
import java.util.List;

public class Factura {

	private LocalDateTime fecha;
	private double subtotal;
	private double impuestos;
	private double propina;
	private List<DetalleVenta> detallesDeLaVenta;
	
}
