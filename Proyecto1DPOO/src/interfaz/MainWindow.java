package interfaz;

import java.awt.*;
import java.io.File;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import generals.DulcesNDados;

public class MainWindow {

    private static final String RUTA_DB     = "datos/dataBase.bin";
    private static final String RUTA_CAFE   = "datos/cafe.bin";
    private static final String RUTA_TIENDA = "datos/tiendaDeJuegos.bin";

    static final Color COLOR_PRIMARIO = new Color(52, 73, 94);
    static final Color COLOR_ACENTO   = new Color(231, 76, 60);
    static final Color COLOR_FONDO    = new Color(245, 246, 250);

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

    private static JFrame construirFrame(DulcesNDados app) {
        JFrame frame = new JFrame("Dulces N Dados");
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1100, 700);
        frame.setMinimumSize(new Dimension(900, 600));
        frame.setLocationRelativeTo(null);
        frame.setBackground(COLOR_FONDO);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
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
        });

        frame.setContentPane(construirContenido(app));
        return frame;
    }

    private static JPanel construirContenido(DulcesNDados app) {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(COLOR_FONDO);

        root.add(construirHeader(), BorderLayout.NORTH);

        JTabbedPane tabs = new JTabbedPane(JTabbedPane.TOP);
        tabs.setFont(new Font("Segoe UI", Font.BOLD, 13));
        tabs.setBackground(COLOR_FONDO);

        tabs.addTab("Cliente",       new PanelCliente(app));
        //tabs.addTab("Administrador", new PanelAdmin(app));
        tabs.addTab("Empleado",      new PanelEmpleado(app, null, app.getDataBase()));

        root.add(tabs, BorderLayout.CENTER);
        root.add(construirStatusBar(), BorderLayout.SOUTH);

        return root;
    }

    private static JPanel construirHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARIO);
        header.setBorder(new EmptyBorder(12, 24, 12, 24));

        JLabel titulo = new JLabel("Dulces N Dados");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);

        JLabel subtitulo = new JLabel("Sistema de gestion - ISIS-1226 DPOO");
        subtitulo.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        subtitulo.setForeground(new Color(189, 195, 199));

        JPanel izq = new JPanel();
        izq.setLayout(new BoxLayout(izq, BoxLayout.Y_AXIS));
        izq.setOpaque(false);
        izq.add(titulo);
        izq.add(subtitulo);

        header.add(izq, BorderLayout.WEST);
        return header;
    }

    private static JPanel construirStatusBar() {
        JPanel bar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 16, 4));
        bar.setBackground(new Color(236, 240, 241));
        bar.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0,
            new Color(189, 195, 199)));

        JLabel lbl = new JLabel("Datos cargados - sesion activa");
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        lbl.setForeground(Color.GRAY);
        bar.add(lbl);
        return bar;
    }
}