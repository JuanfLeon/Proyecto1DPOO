package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dataBase.Empleado;
import dataBase.SolicitudCambioTurno;
import dataBase.Turno;
import generals.DulcesNDados;

public class PanelCambioHorarioEmpleado extends JPanel {

    private JComboBox<Turno> comboActual;
    private JComboBox<Turno> comboDeseado;

    private JButton btnSolicitar;

    private Empleado empleado;
    private DulcesNDados app;

    public PanelCambioHorarioEmpleado(
            DulcesNDados app,
            Empleado empleado) {

        this.app = app;
        this.empleado = empleado;

        setLayout(new GridLayout(3,2));

        add(new JLabel("Turno actual"));
        comboActual = new JComboBox<>();
        add(comboActual);

        add(new JLabel("Turno deseado"));
        comboDeseado = new JComboBox<>();
        add(comboDeseado);

        btnSolicitar =
                new JButton("Solicitar cambio");

        add(btnSolicitar);

        cargarTurnos();

        btnSolicitar.addActionListener(
                e -> solicitarCambio());
    }

    private void cargarTurnos() {

        for(Turno t :
                empleado.getTurnoLaboral()) {

            comboActual.addItem(t);
        }

        for(Turno t :
                app.getDataBase().getTurnos()) {

            if(t != null) {
                comboDeseado.addItem(t);
            }
        }
    }

    private void solicitarCambio() {

        Turno actual =
                (Turno) comboActual.getSelectedItem();

        Turno deseado =
                (Turno) comboDeseado.getSelectedItem();

        SolicitudCambioTurno solicitud =
                empleado.solicitarCambioHorario(
                        actual,
                        deseado);

        app.getDataBase()
           .getSolicitudes()
           .add(solicitud);

        JOptionPane.showMessageDialog(
                this,
                "Solicitud enviada");
    }
}