package dataBase;

public class GestorSolicitudesTurno {

	public void aceptarSolicitud(SolicitudTurno solicitud) throws Exception {
		Turno t_ini = solicitud.getTurnoInicial();
		Turno t_des = solicitud.getTurnoDeseado();
		Empleado solicitante = solicitud.getSolicitante();
		
		if (solicitud instanceof SolicitudCambioTurno) {
			
			t_des.agregarEmpleado(solicitante);
            t_ini.eliminarEmpleado(solicitante);
			
		}
		
		if (solicitud instanceof SolicitudIntercambioTurno) {
			
			Empleado otro = ((SolicitudIntercambioTurno) solicitud).getEmpleadoIntercambiable();

		    t_des.agregarEmpleado(solicitante);
		    t_ini.eliminarEmpleado(solicitante);
		
		    t_ini.agregarEmpleado(otro);
		    t_des.eliminarEmpleado(otro);
			
		}
		
	}
}
