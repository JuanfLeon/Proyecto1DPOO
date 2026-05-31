package interfaz;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import dataBase.DataBase;
import dataBase.Empleado;
import dataBase.Usuario;
import generals.DulcesNDados;

public class PanelEmpleado extends JPanel {

    private static final long serialVersionUID = 1L;

    private static final Color COLOR_PRIMARIO = new Color(39, 174, 96);
    private static final Color COLOR_ACENTO   = new Color(231, 76, 60);
    private static final Color COLOR_FONDO    = new Color(245, 246, 250);
    private static final Color COLOR_CARD     = Color.WHITE;

    private Empleado empleado;
    private final DulcesNDados app;
    private final DataBase db;

    private CardLayout cardLayout;
    private JPanel     cardPanel;

    private PanelBotonesEmpleado   panelBotones;
    private PanelContenidoEmpleado panelContenido;

    public PanelEmpleado(DulcesNDados app, Empleado empleado, DataBase db) {
        this.app      = app;
        this.empleado = empleado;   // null hasta que haga login
        this.db       = db;

        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);

        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
        cardPanel.setBackground(COLOR_FONDO);

        cardPanel.add(construirPanelLogin(), "login");
        // El panel de contenido se construye DESPUES del login
        // para evitar NullPointerException con empleado == null

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "login");
    }

    // =========================================================
    // PANEL LOGIN
    // =========================================================

    private JPanel construirPanelLogin() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(COLOR_FONDO);

        JPanel card = new JPanel(new BorderLayout(0, 16));
        card.setBackground(COLOR_CARD);
        card.setBorder(new EmptyBorder(32, 40, 32, 40));
        card.setPreferredSize(new Dimension(400, 320));

        JLabel titulo = new JLabel("Area de Empleados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(COLOR_PRIMARIO);

        JLabel subtitulo = new JLabel("Ingresa tus credenciales para continuar", SwingConstants.CENTER);
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitulo.setForeground(Color.GRAY);

        JPanel cabecera = new JPanel();
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
        cabecera.setBackground(COLOR_CARD);
        cabecera.add(titulo);
        cabecera.add(Box.createVerticalStrut(4));
        cabecera.add(subtitulo);

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(COLOR_CARD);

        form.add(etiqueta("Usuario:"));
        JTextField txtLogin = new JTextField();
        estilizarCampo(txtLogin);
        form.add(txtLogin);

        form.add(etiqueta("Contrasena:"));
        JPasswordField txtPass = new JPasswordField();
        estilizarCampo(txtPass);
        form.add(txtPass);

        JButton btnEntrar = boton("Ingresar", COLOR_PRIMARIO);
        form.add(new JLabel());
        form.add(btnEntrar);

        JLabel lblMsg = new JLabel(" ", SwingConstants.CENTER);
        lblMsg.setFont(new Font("Segoe UI", Font.PLAIN, 11));

        card.add(cabecera, BorderLayout.NORTH);
        card.add(form,     BorderLayout.CENTER);
        card.add(lblMsg,   BorderLayout.SOUTH);

        btnEntrar.addActionListener(e -> {
            String login = txtLogin.getText().trim();
            String pass  = new String(txtPass.getPassword()).trim();

            if (login.isEmpty() || pass.isEmpty()) {
                mostrarMsg(lblMsg, "Completa los campos.", COLOR_ACENTO);
                return;
            }

            Empleado encontrado = buscarEmpleado(login, pass);
            if (encontrado == null) {
                mostrarMsg(lblMsg, "Credenciales incorrectas o usuario no es empleado.", COLOR_ACENTO);
                return;
            }

            this.empleado = encontrado;
            txtLogin.setText("");
            txtPass.setText("");

            // Construir el contenido ahora que empleado != null
            construirYMostrarContenido();
        });

        outer.add(card);
        return outer;
    }

    // =========================================================
    // CONSTRUIR CONTENIDO (solo se llama tras autenticacion)
    // =========================================================

    private void construirYMostrarContenido() {
        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.setBackground(COLOR_FONDO);

        // Header con nombre del empleado
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(new Color(39, 174, 96));
        header.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblBienvenida = new JLabel("Bienvenido, " + empleado.getLogin());
        lblBienvenida.setFont(new Font("Segoe UI", Font.BOLD, 15));
        lblBienvenida.setForeground(Color.WHITE);

        JButton btnCerrar = boton("Cerrar sesion", COLOR_ACENTO);
        btnCerrar.addActionListener(e -> {
            this.empleado = null;
            cardLayout.show(cardPanel, "login");
        });

        JPanel derecha = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        derecha.setOpaque(false);
        derecha.add(btnCerrar);

        header.add(lblBienvenida, BorderLayout.WEST);
        header.add(derecha,       BorderLayout.EAST);

        // Contenido real del empleado
        panelBotones   = new PanelBotonesEmpleado();
        panelContenido = new PanelContenidoEmpleado(app, empleado);

        JPanel aux = new JPanel(new BorderLayout());
        aux.add(panelBotones,   BorderLayout.WEST);
        aux.add(panelContenido, BorderLayout.CENTER);

        panelPrincipal.add(header, BorderLayout.NORTH);
        panelPrincipal.add(aux,    BorderLayout.CENTER);

        // Conectar botones del sidebar
        panelBotones.getBtnVerTurnos().addActionListener(e ->
            panelContenido.mostrarPanel("TURNOS"));
        panelBotones.getBtnVerSolicitudes().addActionListener(e ->
            panelContenido.mostrarPanel("SOLICITUDES"));
        panelBotones.getBtnCambioHorario().addActionListener(e ->
            panelContenido.mostrarPanel("CAMBIO"));
        panelBotones.getBtnIntercambioHorario().addActionListener(e ->
            panelContenido.mostrarPanel("INTERCAMBIO"));
        panelBotones.getBtnSugerirPlatillo().addActionListener(e ->
            panelContenido.mostrarPanel("PLATILLO"));

        cardPanel.add(panelPrincipal, "contenido");
        cardLayout.show(cardPanel, "contenido");
    }

    // =========================================================
    // BUSCAR EMPLEADO EN LA BASE DE DATOS
    // =========================================================

    private Empleado buscarEmpleado(String login, String pass) {
        for (Usuario u : db.getUsuarios()) {
            if (u instanceof Empleado
                    && u.getLogin().equalsIgnoreCase(login)
                    && u.getContrasenia().equalsIgnoreCase(pass)) {
                return (Empleado) u;
            }
        }
        return null;
    }

    // =========================================================
    // HELPERS
    // =========================================================

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        lbl.setForeground(new Color(44, 62, 80));
        return lbl;
    }

    private JButton boton(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(7, 18, 7, 18));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            new EmptyBorder(5, 8, 5, 8)));
    }

    private void mostrarMsg(JLabel lbl, String texto, Color color) {
        lbl.setText(texto);
        lbl.setForeground(color);
    }
}