package interfaz;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import dataBase.Empleado;
import dataBase.Turno;

public class PanelTurnosEmpleado extends JPanel
{
	private Empleado empleado;
	private JTextArea txtTurnos;

	public PanelTurnosEmpleado(Empleado empleado)
	{
		this.empleado = empleado;

		setLayout(new BorderLayout());

		txtTurnos = new JTextArea();
		txtTurnos.setEditable(false);

		add(new JScrollPane(txtTurnos), BorderLayout.CENTER);

		cargarTurnos();
	}

	public void cargarTurnos()
	{
		txtTurnos.setText("");

		if (empleado.getTurnoLaboral() == null
				|| empleado.getTurnoLaboral().isEmpty())
		{
			txtTurnos.setText("No tiene turnos asignados.");
			return;
		}

		for (Turno t : empleado.getTurnoLaboral())
		{
			txtTurnos.append(
					"Día: " + t.getDia_turno()
					+ "\nMeseros: " + t.getMeseros().size()
					+ "\nCocineros: " + t.getCocineros().size()
					+ "\n\n");
		}
	}
}