package interfaz;

import java.awt.*;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Supplier;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import generals.DulcesNDados;

public class MainWindow {

    private static final String RUTA_DB     = "datos/dataBase.bin";
    private static final String RUTA_CAFE   = "datos/cafe.bin";
    private static final String RUTA_TIENDA = "datos/tiendaDeJuegos.bin";

    private static final Color COLOR_PRIMARIO = new Color(52, 73, 94);
    private static final Color COLOR_ACENTO   = new Color(231, 76, 60);
    private static final Color COLOR_FONDO    = new Color(245, 246, 250);
    private static final Color COLOR_CARD     = Color.WHITE;
    private static final Color COLOR_EMPLEADO = new Color(39, 174, 96);

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            iniciar();
        });
    }

    private static void iniciar() {
        new File("datos").mkdirs();

        DulcesNDados app = new DulcesNDados();
        app.cargarDatosDataBase(RUTA_DB);
        app.cargarDatosCafe(RUTA_CAFE);
        app.cargarDatosTienda(RUTA_TIENDA);

        JFrame frame = construirFrame(app);
        frame.setVisible(true);
    }

    // =========================================================
    // FRAME PRINCIPAL
    // =========================================================

    private static JFrame construirFrame(DulcesNDados app) {
        JFrame frame = new JFrame("Dulces N Dados");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(700, 520);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                confirmarSalida(frame, app);
            }
        });

        frame.setContentPane(construirPantallaInicio(frame, app));
        return frame;
    }

    // =========================================================
    // PANTALLA DE INICIO
    // =========================================================

    private static JPanel construirPantallaInicio(JFrame frame, DulcesNDados app) {

        // Mapa rol -> fabrica de panel y color de su card
        // LinkedHashMap preserva el orden de insercion
        Map<String, Object[]> roles = new LinkedHashMap<>();
        roles.put("Cliente",       new Object[]{ COLOR_ACENTO,    "Reservar mesa, pedir juegos,\ncomprar y ver puntos.",           (Supplier<JPanel>) () -> new PanelCliente(app) });
       // roles.put("Administrador", new Object[]{ COLOR_PRIMARIO,  "Gestionar inventario, usuarios,\ntorneos e informes.",          (Supplier<JPanel>) () -> new PanelAdmin(app) });
        roles.put("Empleado",      new Object[]{ COLOR_EMPLEADO,  "Registrar ventas, gestionar\nmesas y turnos.",                  (Supplier<JPanel>) () -> new PanelEmpleado(app, null, app.getDataBase()) });

        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(COLOR_FONDO);
        root.add(construirHeader(), BorderLayout.NORTH);

        JLabel lblSeleccion = new JLabel("Seleccione su rol para continuar", SwingConstants.CENTER);
        lblSeleccion.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblSeleccion.setForeground(new Color(100, 100, 100));
        lblSeleccion.setBorder(new EmptyBorder(24, 0, 16, 0));

        JPanel panelCards = new JPanel(new GridLayout(1, roles.size(), 20, 0));
        panelCards.setBackground(COLOR_FONDO);
        panelCards.setBorder(new EmptyBorder(0, 40, 0, 40));

        for (Map.Entry<String, Object[]> entry : roles.entrySet()) {
            String           nombre    = entry.getKey();
            Color            color     = (Color)            entry.getValue()[0];
            String           desc      = (String)           entry.getValue()[1];
            Supplier<JPanel> fabrica   = (Supplier<JPanel>) entry.getValue()[2];

            panelCards.add(crearCard(nombre, desc, color, e -> {
                JFrame ventana = new JFrame("Dulces N Dados - " + nombre);
                ventana.setSize(1100, 700);
                ventana.setMinimumSize(new Dimension(900, 600));
                ventana.setLocationRelativeTo(frame);
                ventana.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ventana.setContentPane(fabrica.get());
                ventana.setVisible(true);
            }));
        }

        JPanel centro = new JPanel(new BorderLayout());
        centro.setBackground(COLOR_FONDO);
        centro.add(lblSeleccion, BorderLayout.NORTH);
        centro.add(panelCards,   BorderLayout.CENTER);

        root.add(centro,              BorderLayout.CENTER);
        root.add(construirStatusBar(), BorderLayout.SOUTH);

        return root;
    }

    // =========================================================
    // CARD DE ROL
    // =========================================================

    private static JPanel crearCard(String titulo, String descripcion,
                                     Color color,
                                     java.awt.event.ActionListener accion) {
        JPanel franja = new JPanel();
        franja.setBackground(color);
        franja.setPreferredSize(new Dimension(0, 6));

        JLabel lblTitulo = new JLabel(titulo, SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lblTitulo.setForeground(color);
        lblTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JTextArea lblDesc = new JTextArea(descripcion);
        lblDesc.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lblDesc.setForeground(new Color(100, 100, 100));
        lblDesc.setEditable(false);
        lblDesc.setOpaque(false);
        lblDesc.setLineWrap(true);
        lblDesc.setWrapStyleWord(true);
        lblDesc.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton btn = new JButton("Ingresar");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 13));
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(10, 24, 10, 24));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addActionListener(accion);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.setOpaque(false);
        btnPanel.add(btn);

        JPanel interior = new JPanel();
        interior.setLayout(new BoxLayout(interior, BoxLayout.Y_AXIS));
        interior.setOpaque(false);
        interior.setBorder(new EmptyBorder(16, 12, 16, 12));
        interior.add(lblTitulo);
        interior.add(Box.createVerticalStrut(10));
        interior.add(lblDesc);
        interior.add(Box.createVerticalGlue());
        interior.add(btnPanel);

        JPanel card = new JPanel(new BorderLayout());
        card.setBackground(COLOR_CARD);
        card.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220)));
        card.add(franja,   BorderLayout.NORTH);
        card.add(interior, BorderLayout.CENTER);

        return card;
    }

    // =========================================================
    // HEADER Y STATUS BAR
    // =========================================================

    private static JPanel construirHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARIO);
        header.setBorder(new EmptyBorder(24, 32, 24, 32));

        JLabel titulo = new JLabel("Dulces N Dados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Sistema de gestion - ISIS-1226 DPOO");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        subtitulo.setForeground(new Color(189, 195, 199));

        JPanel texto = new JPanel();
        texto.setLayout(new BoxLayout(texto, BoxLayout.Y_AXIS));
        texto.setOpaque(false);
        texto.add(titulo);
        texto.add(Box.createVerticalStrut(4));
        texto.add(subtitulo);

        header.add(texto, BorderLayout.WEST);
        return header;
    }

    private static JPanel construirStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 4));
        bar.setBackground(new Color(236, 240, 241));
        bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, new Color(189, 195, 199)));

        JLabel lbl = new JLabel("Datos cargados - sesion activa");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(Color.GRAY);
        bar.add(lbl);
        return bar;
    }

    // =========================================================
    // CERRAR
    // =========================================================

    private static void confirmarSalida(JFrame frame, DulcesNDados app) {
        int resp = JOptionPane.showConfirmDialog(
            frame,
            "Desea guardar los datos antes de salir?",
            "Cerrar aplicacion",
            JOptionPane.YES_NO_CANCEL_OPTION,
            JOptionPane.QUESTION_MESSAGE);

        if (resp == JOptionPane.CANCEL_OPTION) return;

        if (resp == JOptionPane.YES_OPTION) {
            app.guardarDatosDataBase(RUTA_DB, app.getDataBase());
            app.guardarDatosCafe(RUTA_CAFE, app.getCafe());
            app.guardarDatosTiendaDeJuegos(RUTA_TIENDA, app.getTiendaDeJuegos());
            JOptionPane.showMessageDialog(frame,
                "Datos guardados correctamente.",
                "Guardado", JOptionPane.INFORMATION_MESSAGE);
        }

        frame.dispose();
        System.exit(0);
    }
}