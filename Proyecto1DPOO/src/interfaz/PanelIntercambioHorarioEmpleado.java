package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import dataBase.Empleado;
import dataBase.SolicitudIntercambioTurno;
import dataBase.Turno;
import dataBase.Usuario;
import generals.DulcesNDados;

public class PanelIntercambioHorarioEmpleado extends JPanel {

    private JComboBox<Turno> comboMiTurno;
    private JComboBox<Empleado> comboEmpleado;
    private JComboBox<Turno> comboTurnoOtro;

    private JButton btnSolicitar;

    private DulcesNDados app;
    private Empleado empleado;

    public PanelIntercambioHorarioEmpleado(
            DulcesNDados app,
            Empleado empleado) {

        this.app = app;
        this.empleado = empleado;

        setLayout(new GridLayout(4,2));

        add(new JLabel("Mi turno"));
        comboMiTurno = new JComboBox<>();
        add(comboMiTurno);

        add(new JLabel("Empleado"));
        comboEmpleado = new JComboBox<>();
        add(comboEmpleado);

        add(new JLabel("Turno del empleado"));
        comboTurnoOtro = new JComboBox<>();
        add(comboTurnoOtro);

        btnSolicitar =
                new JButton("Solicitar intercambio");

        add(btnSolicitar);

        cargarDatos();

        comboEmpleado.addActionListener(
                e -> actualizarTurnosEmpleado());

        btnSolicitar.addActionListener(
                e -> solicitarIntercambio());
    }

    private void cargarDatos() {

        for(Turno t : empleado.getTurnoLaboral()) {
            comboMiTurno.addItem(t);
        }

        for(Usuario u :
                app.getDataBase().getUsuarios()) {

            if(u instanceof Empleado
                    && u != empleado
                    && u.getClass().equals(
                            empleado.getClass())) {

                comboEmpleado.addItem((Empleado)u);
            }
        }

        actualizarTurnosEmpleado();
    }

    private void actualizarTurnosEmpleado() {

        comboTurnoOtro.removeAllItems();

        Empleado otro =
                (Empleado) comboEmpleado.getSelectedItem();

        if(otro == null) {
            return;
        }

        for(Turno t : otro.getTurnoLaboral()) {
            comboTurnoOtro.addItem(t);
        }
    }

    private void solicitarIntercambio() {

        Turno miTurno =
                (Turno) comboMiTurno.getSelectedItem();

        Turno turnoOtro =
                (Turno) comboTurnoOtro.getSelectedItem();

        Empleado otro =
                (Empleado) comboEmpleado.getSelectedItem();

        if(miTurno == null
                || turnoOtro == null
                || otro == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe seleccionar todos los campos");

            return;
        }

        SolicitudIntercambioTurno solicitud =
                empleado.solicitarIntercambioHorario(
                        miTurno,
                        turnoOtro,
                        otro);

        app.getDataBase()
           .getSolicitudes()
           .add(solicitud);

        JOptionPane.showMessageDialog(
                this,
                "Solicitud enviada");
    }
}