package interfaz;

import java.awt.CardLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import dataBase.Empleado;
import generals.DulcesNDados;

public class PanelContenidoEmpleado extends JPanel{

	private PanelTurnosEmpleado panelTurnos;
	private PanelSolicitudesEmpleado panelSolicitudes;
	private PanelCambioHorarioEmpleado panelCambioHorario;
	private PanelIntercambioHorarioEmpleado panelIntercambioHorario;
	private PanelSugerirPlatilloEmpleado panelSugerirPlatillo;

	private CardLayout layout;
	
	public PanelContenidoEmpleado(DulcesNDados app, Empleado empleado) {
		layout= new CardLayout();
		setLayout(layout);
		
		panelTurnos = new PanelTurnosEmpleado(empleado);
		panelSolicitudes = new PanelSolicitudesEmpleado(app, empleado);
		panelCambioHorario = new PanelCambioHorarioEmpleado(app, empleado);
		panelIntercambioHorario = new PanelIntercambioHorarioEmpleado(app, empleado);
		panelSugerirPlatillo = new PanelSugerirPlatilloEmpleado(app, empleado);
		
		add(panelTurnos, "TURNOS");
		add(panelSolicitudes, "SOLICITUDES");
		add(panelCambioHorario, "CAMBIO");
		add(panelIntercambioHorario, "INTERCAMBIO");
		add(panelSugerirPlatillo, "PLATILLO");
		
		layout.show(this, "TURNOS");
	}
	
	public void mostrarPanel(String nombre)
	{
	    layout.show(this, nombre);
	}
}
