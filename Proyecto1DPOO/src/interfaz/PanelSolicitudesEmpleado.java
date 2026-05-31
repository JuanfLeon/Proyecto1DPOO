package interfaz;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import dataBase.Empleado;
import dataBase.Solicitud;
import generals.DulcesNDados;

public class PanelSolicitudesEmpleado extends JPanel {

    private DulcesNDados app;
    private Empleado empleado;

    private JList<Solicitud> listaSolicitudes;
    private DefaultListModel<Solicitud> modelo;

    public PanelSolicitudesEmpleado(DulcesNDados app, Empleado empleado) {

        this.app = app;
        this.empleado = empleado;

        setLayout(new BorderLayout());

        modelo = new DefaultListModel<>();
        listaSolicitudes = new JList<>(modelo);

        add(new JScrollPane(listaSolicitudes), BorderLayout.CENTER);

        cargarSolicitudes();
    }

    public void cargarSolicitudes() {

        modelo.clear();

        for(Solicitud s : app.getDataBase().getSolicitudes()) {

            if(s.getSolicitante().equals(empleado)) {
                modelo.addElement(s);
            }
        }
    }
}