package interfaz;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cafe.Producto;
import dataBase.Empleado;
import dataBase.SolicitudSugerenciaPlatillo;
import generals.DulcesNDados;

public class PanelSugerirPlatilloEmpleado extends JPanel {

    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JButton btnEnviar;

    private DulcesNDados app;
    private Empleado empleado;

    public PanelSugerirPlatilloEmpleado(DulcesNDados app, Empleado empleado) {

        this.app = app;
        this.empleado = empleado;

        setLayout(new GridLayout(3,2));

        add(new JLabel("Nombre del platillo"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Precio"));
        txtPrecio = new JTextField();
        add(txtPrecio);

        btnEnviar = new JButton("Enviar sugerencia");
        add(btnEnviar);

        btnEnviar.addActionListener(e -> enviarSugerencia());
    }

    private void enviarSugerencia() {

        try {

            String nombre = txtNombre.getText();

            double precio =
                    Double.parseDouble(txtPrecio.getText());

            Producto producto =
                    new Producto(nombre, precio);

            SolicitudSugerenciaPlatillo solicitud =
                    new SolicitudSugerenciaPlatillo(
                            producto,
                            empleado);

            app.getDataBase()
               .getSolicitudes()
               .add(solicitud);

            JOptionPane.showMessageDialog(
                    this,
                    "Sugerencia enviada");

            txtNombre.setText("");
            txtPrecio.setText("");

        }
        catch(Exception e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Datos inválidos");
        }
    }
}