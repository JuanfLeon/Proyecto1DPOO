package dataBase;

public class SolicitudSugerenciaPlatillo extends Solicitud{

	private String platilloSugerido;
	
	public SolicitudSugerenciaPlatillo(String platilloSugerido, Empleado empleadoSolicitante) {
		super(empleadoSolicitante);
		this.platilloSugerido = platilloSugerido;
		
	}
	
	public String getPlatilloSugerido() {
		return platilloSugerido;
	}
}
