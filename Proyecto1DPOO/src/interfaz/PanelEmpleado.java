package interfaz;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import dataBase.DataBase;
import dataBase.Empleado;
import generals.DulcesNDados;

public class PanelEmpleado extends JPanel{
	private Empleado empleado;
	private DulcesNDados app;
	private DataBase db;
	
	private PanelBotonesEmpleado panelBotones;
	private PanelContenidoEmpleado panelContenido;
	
	private JLabel txtEncabezado;
	
	

	
	
	public PanelEmpleado(DulcesNDados app, Empleado empleado, DataBase db) {
		this.empleado=empleado;
		this.app=app;
		this.db= db;
		
		panelBotones= new PanelBotonesEmpleado();
		panelContenido= new PanelContenidoEmpleado(app, empleado);
		
		setLayout(new BorderLayout());
		
		txtEncabezado= new JLabel("Bienvenido " + empleado.getLogin() );
		
		JPanel aux= new JPanel();
		aux.setLayout(new BorderLayout());
		
		aux.add(panelBotones, BorderLayout.WEST);
		aux.add(panelContenido, BorderLayout.CENTER);
		
		add(txtEncabezado, BorderLayout.NORTH);
		add(aux, BorderLayout.CENTER);
		
		panelBotones.getBtnVerTurnos().addActionListener(e -> {
		    panelContenido.mostrarPanel("TURNOS");
		});

		panelBotones.getBtnVerSolicitudes().addActionListener(e -> {
		    panelContenido.mostrarPanel("SOLICITUDES");
		});

		panelBotones.getBtnCambioHorario().addActionListener(e -> {
		    panelContenido.mostrarPanel("CAMBIO");
		});

		panelBotones.getBtnIntercambioHorario().addActionListener(e -> {
		    panelContenido.mostrarPanel("INTERCAMBIO");
		});

		panelBotones.getBtnSugerirPlatillo().addActionListener(e -> {
		    panelContenido.mostrarPanel("PLATILLO");
		});
		
		setVisible(true);
	}

	
}
