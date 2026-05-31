package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelBotonesEmpleado extends JPanel{
	
	private JButton btnCambioHorario;
	private JButton btnIntercambioHorario;
	private JButton btnSugerirPlatillo;
	private JButton btnVerTurnos;
	private JButton btnVerSolicitudes;
	
	
	public PanelBotonesEmpleado() {
		setLayout(new GridLayout(5,1));
		
		btnCambioHorario= new JButton("Cambio Horario");
		btnIntercambioHorario= new JButton("Intercambio Horario");
		btnSugerirPlatillo = new JButton("Sugerir Platillo");
		btnVerTurnos= new JButton("Ver Turnos");
		btnVerSolicitudes= new JButton("Ver Solicitudes");
		
		add(btnCambioHorario);
		add(btnIntercambioHorario);
		add(btnSugerirPlatillo);
		add(btnVerTurnos);
		add(btnVerSolicitudes);
		
		
	}


	public JButton getBtnCambioHorario() {
		return btnCambioHorario;
	}


	public JButton getBtnIntercambioHorario() {
		return btnIntercambioHorario;
	}


	public JButton getBtnSugerirPlatillo() {
		return btnSugerirPlatillo;
	}


	public JButton getBtnVerTurnos() {
		return btnVerTurnos;
	}


	public JButton getBtnVerSolicitudes() {
		return btnVerSolicitudes;
	}
	
	

}
