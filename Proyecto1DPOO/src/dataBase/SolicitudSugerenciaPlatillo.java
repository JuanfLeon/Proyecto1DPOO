package dataBase;

import cafe.Producto;

public class SolicitudSugerenciaPlatillo extends Solicitud{

	private Producto platilloSugerido;
	
	public SolicitudSugerenciaPlatillo(Producto platilloSugerido, Empleado empleadoSolicitante) {
		super(empleadoSolicitante);
		this.platilloSugerido = platilloSugerido;
		
	}
	
	public Producto getPlatilloSugerido() {
		return platilloSugerido;
	}

	public void setPlatilloSugerido(Producto platilloSugerido) {
		this.platilloSugerido = platilloSugerido;
	}
}
