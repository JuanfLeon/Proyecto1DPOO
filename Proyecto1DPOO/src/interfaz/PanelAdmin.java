package interfaz;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.WeekFields;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import cafe.Cafe;
import cafe.Producto;
import dataBase.Cocinero;
import dataBase.DataBase;
import dataBase.DetalleVenta;
import dataBase.Empleado;
import dataBase.Factura;
import dataBase.GestorSolicitudesPlatillos;
import dataBase.GestorSolicitudesTurno;
import dataBase.Mesero;
import dataBase.Solicitud;
import dataBase.SolicitudSugerenciaPlatillo;
import dataBase.SolicitudTurno;
import generals.DulcesNDados;
import tiendaDeJuegos.GestorInventarioJuegos;
import tiendaDeJuegos.InventarioJuegos;
import tiendaDeJuegos.JuegoDeMesaFisico;
import tiendaDeJuegos.Prestamo;
import tiendaDeJuegos.TipoDeJuego;
import tiendaDeJuegos.TipoVenta;

public class PanelAdmin extends JPanel {

    private static final long serialVersionUID = 1L;

    // =========================================================
    // COLORES Y FUENTES
    // =========================================================
    private static final Color COLOR_PRIMARIO = new Color(52, 73, 94);
    private static final Color COLOR_ACENTO   = new Color(231, 76, 60);
    private static final Color COLOR_FONDO    = new Color(245, 246, 250);
    private static final Color COLOR_CARD     = Color.WHITE;
    private static final Color COLOR_TEXTO    = new Color(44, 62, 80);
    private static final Color COLOR_EXITO    = new Color(39, 174, 96);
    private static final Font  FUENTE_TITULO  = new Font("Segoe UI", Font.BOLD, 15);
    private static final Font  FUENTE_NORMAL  = new Font("Segoe UI", Font.PLAIN, 13);
    private static final Font  FUENTE_SMALL   = new Font("Segoe UI", Font.PLAIN, 11);

    private final DulcesNDados app;

    public PanelAdmin(DulcesNDados app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);
        add(construirPanelPrincipal(), BorderLayout.CENTER);
    }

    // =========================================================
    // PANEL PRINCIPAL
    // =========================================================

    private JPanel construirPanelPrincipal() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(COLOR_FONDO);

        // Header
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(COLOR_PRIMARIO);
        header.setBorder(new EmptyBorder(10, 20, 10, 20));
        JLabel titulo = new JLabel("Dulces N Dados  —  Administrador");
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titulo.setForeground(Color.WHITE);
        header.add(titulo, BorderLayout.WEST);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setBorder(new EmptyBorder(16, 0, 16, 0));
        
        CardLayout cl = new CardLayout();
        JPanel contenido = new JPanel(cl);
        contenido.setBackground(COLOR_FONDO);

        contenido.add(crearPanelRegistrarEmpleado(),     "registrarEmpleado");
        contenido.add(crearPanelVerEmpleados(),          "verEmpleados");
        contenido.add(crearPanelAgregarPlatillo(),       "agregarPlatillo");
        contenido.add(crearPanelVerSolicitudes(),        "verSolicitudes");
        contenido.add(crearPanelGenerarInforme(),        "generarInforme");
        contenido.add(crearPanelRepararJuego(),          "repararJuego");
        contenido.add(crearPanelReportarRobado(),        "reportarRobado");
        contenido.add(crearPanelMoverJuego(),            "moverJuego");
        contenido.add(crearPanelReabastecerVentas(),     "reabastecerVentas");
        contenido.add(crearPanelReabastecerPrestamo(),   "reabastecerPrestamo");
        contenido.add(crearPanelAprobarHorario(),        "aprobarHorario");
        contenido.add(crearPanelAceptarSugerencia(),     "aceptarSugerencia");
        contenido.add(crearPanelAsignarHorario(),        "asignarHorario");
        contenido.add(crearPanelModificarHorario(),      "modificarHorario");
        contenido.add(crearPanelConsultarEstado(),       "consultarEstado");
        contenido.add(crearPanelConsultarNumPrestamos(), "consultarNumPrestamos");
        contenido.add(crearPanelConsultarFechas(),       "consultarFechas");
        contenido.add(crearPanelGraficaPastel(),         "graficaPastel");
        contenido.add(crearPanelGraficaBarras(),         "graficaBarras");
        contenido.add(crearPanelGraficaLineas(),         "graficaLineas");

        // Items del sidebar
        String[][] items = {
            {"Registrar empleado",       "registrarEmpleado"},
            {"Ver empleados",            "verEmpleados"},
            {"Agregar platillo",         "agregarPlatillo"},
            {"Ver solicitudes",          "verSolicitudes"},
            {"Generar informe",          "generarInforme"},
            {"Reparar juego",            "repararJuego"},
            {"Reportar juego robado",    "reportarRobado"},
            {"Mover juego → préstamo",   "moverJuego"},
            {"Reabastecer ventas",       "reabastecerVentas"},
            {"Reabastecer préstamo",     "reabastecerPrestamo"},
            {"Aprobar solicitud horario","aprobarHorario"},
            {"Aceptar sugerencia",       "aceptarSugerencia"},
            {"Asignar horario",          "asignarHorario"},
            {"Modificar horario",        "modificarHorario"},
            {"Estado de juego",          "consultarEstado"},
            {"N° préstamos de juego",    "consultarNumPrestamos"},
            {"Fechas de préstamo",       "consultarFechas"},
            {"Gráfica: disponibilidad",  "graficaPastel"},
            {"Gráfica: ventas 5 días",   "graficaBarras"},
            {"Gráfica: reservas",        "graficaLineas"},
        };

        for (String[] item : items) {
            JButton btn = botonSidebar(item[0]);
            String card = item[1];
            btn.addActionListener(e -> cl.show(contenido, card));
            btn.setAlignmentX(Component.LEFT_ALIGNMENT);
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(2));
        }
        // Empuja todos los botones hacia arriba si sobra espacio
        sidebar.add(Box.createVerticalGlue());

        // El sidebar va dentro de un JScrollPane para poder desplazarse
        // cuando hay más botones de los que caben en la ventana.
        JScrollPane sidebarScroll = new JScrollPane(sidebar);
        sidebarScroll.setBorder(null);
        sidebarScroll.setPreferredSize(new Dimension(200, 0));
        sidebarScroll.getVerticalScrollBar().setUnitIncrement(16);
        sidebarScroll.setHorizontalScrollBarPolicy(
                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        panel.add(header,        BorderLayout.NORTH);
        panel.add(sidebarScroll, BorderLayout.WEST);
        panel.add(contenido,     BorderLayout.CENTER);
        return panel;
    }

    // =========================================================
    // SECCIONES
    // =========================================================

    private JPanel crearPanelRegistrarEmpleado() {
        JPanel p = panelContenido("Registrar empleado");

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField    txtLogin  = new JTextField();
        JPasswordField txtPass  = new JPasswordField();
        JTextField    txtCod    = new JTextField();
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Mesero", "Cocinero"});
        estilizarCampo(txtLogin); estilizarCampo(txtPass); estilizarCampo(txtCod);

        form.add(etiqueta("Login:"));           form.add(txtLogin);
        form.add(etiqueta("Contraseña:"));      form.add(txtPass);
        form.add(etiqueta("Cód. descuento:"));  form.add(txtCod);
        form.add(etiqueta("Tipo:"));            form.add(cmbTipo);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Registrar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            String login = txtLogin.getText().trim();
            String pass  = new String(txtPass.getPassword()).trim();
            String cod   = txtCod.getText().trim();

            if (login.isEmpty() || pass.isEmpty()) {
                mostrarMsg(lblMsg, "Login y contraseña son obligatorios.", COLOR_ACENTO);
                return;
            }

            Empleado nuevo;
            if (cmbTipo.getSelectedIndex() == 0) {
                nuevo = new Mesero(login, new ArrayList<>(), pass,
                                   new ArrayList<>(), cod, new ArrayList<>());
            } else {
                nuevo = new Cocinero(login, new ArrayList<>(), pass,
                                     new ArrayList<>(), cod);
            }
            app.getDataBase().getUsuarios().add(nuevo);
            app.getCafe().getEmpleados().add(nuevo);

            txtLogin.setText(""); txtPass.setText(""); txtCod.setText("");
            mostrarMsg(lblMsg, "Empleado '" + login + "' registrado correctamente.", COLOR_EXITO);
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelVerEmpleados() {
        JPanel p = panelContenido("Ver empleados");

        String[] cols = {"#", "Login", "Tipo", "Cód. descuento"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar", COLOR_PRIMARIO);
        btnRef.addActionListener(e -> cargarEmpleados(model));
        cargarEmpleados(model);

        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelAgregarPlatillo() {
        JPanel p = panelContenido("Agregar platillo");

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtNombre = new JTextField();
        JTextField txtPrecio = new JTextField();
        estilizarCampo(txtNombre); estilizarCampo(txtPrecio);

        form.add(etiqueta("Nombre:")); form.add(txtNombre);
        form.add(etiqueta("Precio:")); form.add(txtPrecio);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Agregar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            String nombre = txtNombre.getText().trim();
            String precioStr = txtPrecio.getText().trim();
            if (nombre.isEmpty() || precioStr.isEmpty()) {
                mostrarMsg(lblMsg, "Todos los campos son obligatorios.", COLOR_ACENTO);
                return;
            }
            try {
                double precio = Double.parseDouble(precioStr);
                app.getCafe().agregarPlatillo(new Producto(nombre, precio));
                txtNombre.setText(""); txtPrecio.setText("");
                mostrarMsg(lblMsg, "Platillo '" + nombre + "' agregado.", COLOR_EXITO);
            } catch (NumberFormatException ex) {
                mostrarMsg(lblMsg, "Precio inválido.", COLOR_ACENTO);
            }
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelVerSolicitudes() {
        JPanel p = panelContenido("Ver solicitudes");

        JTextArea area = new JTextArea();
        area.setEditable(false);
        area.setFont(FUENTE_NORMAL);
        area.setBorder(new EmptyBorder(8, 8, 8, 8));

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar", COLOR_PRIMARIO);
        btnRef.addActionListener(e -> {
            java.util.List<Solicitud> solis = app.getDataBase().getSolicitudes();
            if (solis == null || solis.isEmpty()) {
                area.setText("No hay solicitudes.");
            } else {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < solis.size(); i++)
                    sb.append(i).append(". ").append(solis.get(i)).append("\n");
                area.setText(sb.toString());
            }
        });
        btnRef.doClick();

        p.add(new JScrollPane(area), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelGenerarInforme() {
        JPanel p = panelContenido("Generar informe");

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtInicio = new JTextField("YYYY-MM-DD");
        JTextField txtFin    = new JTextField("YYYY-MM-DD");
        estilizarCampo(txtInicio); estilizarCampo(txtFin);

        form.add(etiqueta("Fecha inicio:")); form.add(txtInicio);
        form.add(etiqueta("Fecha fin:"));    form.add(txtFin);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Generar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                LocalDate inicio = LocalDate.parse(txtInicio.getText().trim());
                LocalDate fin    = LocalDate.parse(txtFin.getText().trim());
                app.getDataBase().generarInforme(inicio, fin);
                mostrarMsg(lblMsg, "Informe generado correctamente.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Formato de fecha inválido. Use YYYY-MM-DD.", COLOR_ACENTO);
            }
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelRepararJuego() {
        JPanel p = panelContenido("Reparar juego");

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtIdNuevo = new JTextField();
        JTextField txtIdViejo = new JTextField();
        JTextField txtNombre  = new JTextField();
        estilizarCampo(txtIdNuevo); estilizarCampo(txtIdViejo); estilizarCampo(txtNombre);

        form.add(etiqueta("ID juego nuevo:")); form.add(txtIdNuevo);
        form.add(etiqueta("ID juego viejo:")); form.add(txtIdViejo);
        form.add(etiqueta("Nombre juego:"));   form.add(txtNombre);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Reparar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                GestorInventarioJuegos gest = new GestorInventarioJuegos();
                gest.setInventarioJuegos(app.getTiendaDeJuegos());
                gest.repararJuego(txtIdNuevo.getText().trim(),
                                  txtIdViejo.getText().trim(),
                                  txtNombre.getText().trim());
                mostrarMsg(lblMsg, "Juego reparado correctamente.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelReportarRobado() {
        JPanel p = panelContenido("Reportar juego robado");

        JPanel form = new JPanel(new GridLayout(2, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtId     = new JTextField();
        JTextField txtNombre = new JTextField();
        estilizarCampo(txtId); estilizarCampo(txtNombre);

        form.add(etiqueta("ID juego:"));     form.add(txtId);
        form.add(etiqueta("Nombre juego:")); form.add(txtNombre);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Reportar", COLOR_ACENTO);

        btnOk.addActionListener(e -> {
            try {
                GestorInventarioJuegos gest = new GestorInventarioJuegos();
                gest.setInventarioJuegos(app.getTiendaDeJuegos());
                gest.darJuegoPorRobado(txtId.getText().trim(), txtNombre.getText().trim());
                mostrarMsg(lblMsg, "Juego reportado como robado.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelMoverJuego() {
        JPanel p = panelContenido("Mover juego: ventas → préstamo");

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtNombre = new JTextField();
        estilizarCampo(txtNombre);
        form.add(etiqueta("Nombre juego:")); form.add(txtNombre);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Mover", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                GestorInventarioJuegos gest = new GestorInventarioJuegos();
                gest.setInventarioJuegos(app.getTiendaDeJuegos());
                gest.moverJuegoDeVentasAPrestamo(txtNombre.getText().trim());
                mostrarMsg(lblMsg, "Juego movido a préstamo correctamente.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelReabastecerVentas() {
        return crearPanelReabastecer("Reabastecer inventario de ventas", false);
    }

    private JPanel crearPanelReabastecerPrestamo() {
        return crearPanelReabastecer("Reabastecer inventario de préstamo", true);
    }

    // Panel reutilizable para ambos tipos de reabastecimiento
    private JPanel crearPanelReabastecer(String titulo, boolean esPrestamo) {
        JPanel p = panelContenido(titulo);

        JPanel form = new JPanel(new GridLayout(0, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtNombre   = new JTextField();
        JTextField txtPrecio   = new JTextField();
        JTextField txtFecha    = new JTextField("YYYY-MM-DD");
        JSpinner   spnCantidad = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        JTextField txtEmpresa  = new JTextField();
        JComboBox<TipoDeJuego> cmbTipo = new JComboBox<>(TipoDeJuego.values());
        JSpinner   spnMin      = new JSpinner(new SpinnerNumberModel(1, 1, 100, 1));
        JSpinner   spnMax      = new JSpinner(new SpinnerNumberModel(2, 1, 100, 1));
        JSpinner   spnEdad     = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1));
        JTextField txtCaract   = new JTextField();
        JCheckBox  chkDificil  = new JCheckBox("¿Es difícil?");
        chkDificil.setBackground(COLOR_CARD);

        estilizarCampo(txtNombre); estilizarCampo(txtPrecio);
        estilizarCampo(txtFecha);  estilizarCampo(txtEmpresa);
        estilizarCampo(txtCaract);

        form.add(etiqueta("Nombre:"));          form.add(txtNombre);
        form.add(etiqueta("Precio:"));          form.add(txtPrecio);
        form.add(etiqueta("Fecha publicación:"));form.add(txtFecha);
        form.add(etiqueta("Cantidad:"));        form.add(spnCantidad);
        form.add(etiqueta("Empresa:"));         form.add(txtEmpresa);
        form.add(etiqueta("Tipo de juego:"));   form.add(cmbTipo);
        form.add(etiqueta("Min. jugadores:"));  form.add(spnMin);
        form.add(etiqueta("Max. jugadores:"));  form.add(spnMax);
        form.add(etiqueta("Edad mínima:"));     form.add(spnEdad);
        form.add(etiqueta("Características:")); form.add(txtCaract);
        form.add(new JLabel());                 form.add(chkDificil);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Agregar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                String nombre   = txtNombre.getText().trim();
                double precio   = Double.parseDouble(txtPrecio.getText().trim());
                Date fecha = new Date(LocalDate.parse(txtFecha.getText().trim())
                        .atStartOfDay(java.time.ZoneId.systemDefault())
                        .toInstant().toEpochMilli());
                int    cantidad = (int) spnCantidad.getValue();
                String empresa  = txtEmpresa.getText().trim();
                TipoDeJuego tipo = (TipoDeJuego) cmbTipo.getSelectedItem();
                int    min      = (int) spnMin.getValue();
                int    max      = (int) spnMax.getValue();
                int    edad     = (int) spnEdad.getValue();
                String caract   = txtCaract.getText().trim();
                boolean dificil = chkDificil.isSelected();

                GestorInventarioJuegos gest = new GestorInventarioJuegos();
                gest.setInventarioJuegos(app.getTiendaDeJuegos());

                if (esPrestamo)
                    gest.comprarJuegosPrestamo(nombre, precio, fecha, cantidad,
                                               empresa, tipo, min, max, edad, caract, dificil);
                else
                    gest.comprarJuegosVentas(nombre, precio, fecha, cantidad,
                                             empresa, tipo, min, max, edad, caract, dificil);

                mostrarMsg(lblMsg, cantidad + " unidad(es) de '" + nombre + "' agregadas.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        p.add(new JScrollPane(form), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelAprobarHorario() {
        JPanel p = panelContenido("Aprobar solicitud de horario");

        JTextArea areaSolis = new JTextArea(6, 0);
        areaSolis.setEditable(false);
        areaSolis.setFont(FUENTE_NORMAL);

        JSpinner spnIndex = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(8, 16, 8, 16));
        form.add(etiqueta("# de solicitud:")); form.add(spnIndex);

        JLabel  lblMsg  = mensajeEstado();
        JButton btnRef  = boton("Ver solicitudes", COLOR_PRIMARIO);
        JButton btnOk   = boton("Aprobar", COLOR_EXITO);

        btnRef.addActionListener(e -> {
            java.util.List<Solicitud> solis = app.getDataBase().getSolicitudes();
            if (solis == null || solis.isEmpty()) { areaSolis.setText("No hay solicitudes."); return; }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < solis.size(); i++)
                sb.append(i).append(". ").append(solis.get(i)).append("\n");
            areaSolis.setText(sb.toString());
            ((SpinnerNumberModel) spnIndex.getModel()).setMaximum(solis.size() - 1);
        });

        btnOk.addActionListener(e -> {
            try {
                int idx = (int) spnIndex.getValue();
                Solicitud sol = app.getDataBase().getSolicitudes().get(idx);
                GestorSolicitudesTurno gest = new GestorSolicitudesTurno();
                gest.aceptarSolicitud((SolicitudTurno) sol);
                mostrarMsg(lblMsg, "Solicitud #" + idx + " aprobada.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(COLOR_FONDO);
        centro.add(new JScrollPane(areaSolis), BorderLayout.CENTER);
        centro.add(form, BorderLayout.SOUTH);

        p.add(centro, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelAceptarSugerencia() {
        JPanel p = panelContenido("Aceptar sugerencia de platillo");

        JTextArea areaSolis = new JTextArea(6, 0);
        areaSolis.setEditable(false);
        areaSolis.setFont(FUENTE_NORMAL);

        JSpinner spnIndex = new JSpinner(new SpinnerNumberModel(0, 0, 999, 1));

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(8, 16, 8, 16));
        form.add(etiqueta("# de solicitud:")); form.add(spnIndex);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Ver solicitudes", COLOR_PRIMARIO);
        JButton btnOk  = boton("Aceptar", COLOR_EXITO);

        btnRef.addActionListener(e -> {
            java.util.List<Solicitud> solis = app.getDataBase().getSolicitudes();
            if (solis == null || solis.isEmpty()) { areaSolis.setText("No hay solicitudes."); return; }
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < solis.size(); i++)
                sb.append(i).append(". ").append(solis.get(i)).append("\n");
            areaSolis.setText(sb.toString());
            ((SpinnerNumberModel) spnIndex.getModel()).setMaximum(solis.size() - 1);
        });

        btnOk.addActionListener(e -> {
            try {
                int idx = (int) spnIndex.getValue();
                SolicitudSugerenciaPlatillo sol =
                    (SolicitudSugerenciaPlatillo) app.getDataBase().getSolicitudes().get(idx);
                GestorSolicitudesPlatillos gest = new GestorSolicitudesPlatillos();
                gest.aceptarSolicitud(app.getCafe(), sol);
                mostrarMsg(lblMsg, "Sugerencia #" + idx + " aceptada.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        JPanel centro = new JPanel(new BorderLayout(0, 8));
        centro.setBackground(COLOR_FONDO);
        centro.add(new JScrollPane(areaSolis), BorderLayout.CENTER);
        centro.add(form, BorderLayout.SOUTH);

        p.add(centro, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelAsignarHorario() {
        JPanel p = panelContenido("Asignar horario");

        // Selector de día
        generals.DiaSemana[] dias = generals.DiaSemana.values();
        JComboBox<generals.DiaSemana> cmbDia = new JComboBox<>(dias);

        // Listas de meseros y cocineros disponibles
        DefaultListModel<String> modelMeseros  = new DefaultListModel<>();
        DefaultListModel<String> modelCocineros = new DefaultListModel<>();
        JList<String> listMeseros  = new JList<>(modelMeseros);
        JList<String> listCocineros = new JList<>(modelCocineros);
        listMeseros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        listCocineros.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        JButton btnCargar = boton("Cargar empleados", COLOR_PRIMARIO);
        btnCargar.addActionListener(e -> {
            modelMeseros.clear(); modelCocineros.clear();
            for (dataBase.Usuario u : app.getDataBase().getUsuarios()) {
                if (u instanceof Mesero)   modelMeseros.addElement(u.getLogin());
                if (u instanceof Cocinero) modelCocineros.addElement(u.getLogin());
            }
        });

        JPanel listas = new JPanel(new GridLayout(1, 2, 10, 0));
        listas.setBackground(COLOR_FONDO);
        JPanel pm = new JPanel(new BorderLayout()); pm.setBackground(COLOR_FONDO);
        pm.add(etiqueta("Meseros (Ctrl+clic para varios):"), BorderLayout.NORTH);
        pm.add(new JScrollPane(listMeseros), BorderLayout.CENTER);
        JPanel pc = new JPanel(new BorderLayout()); pc.setBackground(COLOR_FONDO);
        pc.add(etiqueta("Cocineros:"), BorderLayout.NORTH);
        pc.add(new JScrollPane(listCocineros), BorderLayout.CENTER);
        listas.add(pm); listas.add(pc);

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(COLOR_FONDO);
        top.add(etiqueta("Día:")); top.add(cmbDia); top.add(btnCargar);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Asignar turno", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                generals.DiaSemana dia = (generals.DiaSemana) cmbDia.getSelectedItem();
                int indiceDia = cmbDia.getSelectedIndex();

                if (app.getDataBase().getTurnos()[indiceDia] != null) {
                    mostrarMsg(lblMsg, "Ya existe un turno para " + dia + ". Use 'Modificar horario'.", COLOR_ACENTO);
                    return;
                }

                java.util.List<String> selMeseros  = listMeseros.getSelectedValuesList();
                java.util.List<String> selCocineros = listCocineros.getSelectedValuesList();

                if (selMeseros.size() < 2) {
                    mostrarMsg(lblMsg, "Seleccione al menos 2 meseros.", COLOR_ACENTO); return;
                }
                if (selCocineros.isEmpty()) {
                    mostrarMsg(lblMsg, "Seleccione al menos 1 cocinero.", COLOR_ACENTO); return;
                }

                ArrayList<Mesero>   mesAsig = new ArrayList<>();
                ArrayList<Cocinero> cocAsig = new ArrayList<>();

                for (dataBase.Usuario u : app.getDataBase().getUsuarios()) {
                    if (u instanceof Mesero   && selMeseros.contains(u.getLogin()))
                        mesAsig.add((Mesero) u);
                    if (u instanceof Cocinero && selCocineros.contains(u.getLogin()))
                        cocAsig.add((Cocinero) u);
                }

                dataBase.Turno turno = new dataBase.Turno(dia, mesAsig, cocAsig);
                for (Mesero m   : mesAsig) m.agregarTurno(turno);
                for (Cocinero c : cocAsig) c.agregarTurno(turno);
                app.getDataBase().getTurnos()[indiceDia] = turno;

                mostrarMsg(lblMsg, "Turno del " + dia + " asignado correctamente.", COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        p.add(top,   BorderLayout.NORTH);
        p.add(listas, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelModificarHorario() {
        JPanel p = panelContenido("Modificar horario");

        generals.DiaSemana[] dias = generals.DiaSemana.values();
        JComboBox<String> cmbTurno = new JComboBox<>();
        JComboBox<String> cmbAccion = new JComboBox<>(new String[]{
            "Agregar mesero", "Eliminar mesero", "Agregar cocinero", "Eliminar cocinero"
        });
        JTextField txtEmpleado = new JTextField();
        estilizarCampo(txtEmpleado);

        JButton btnCargar = boton("Cargar turnos", COLOR_PRIMARIO);
        btnCargar.addActionListener(e -> {
            cmbTurno.removeAllItems();
            dataBase.Turno[] turnos = app.getDataBase().getTurnos();
            for (int i = 0; i < turnos.length; i++)
                if (turnos[i] != null)
                    cmbTurno.addItem(dias[i].toString());
        });

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));
        form.add(etiqueta("Turno:"));    form.add(cmbTurno);
        form.add(etiqueta("Acción:"));   form.add(cmbAccion);
        form.add(etiqueta("Login empleado:")); form.add(txtEmpleado);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Aplicar", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            try {
                String diaStr = (String) cmbTurno.getSelectedItem();
                if (diaStr == null) { mostrarMsg(lblMsg, "Cargue los turnos primero.", COLOR_ACENTO); return; }

                dataBase.Turno turno = null;
                for (int i = 0; i < dias.length; i++)
                    if (dias[i].toString().equals(diaStr))
                        turno = app.getDataBase().getTurnos()[i];

                if (turno == null) { mostrarMsg(lblMsg, "Turno no encontrado.", COLOR_ACENTO); return; }

                String loginEmp = txtEmpleado.getText().trim();
                int accion = cmbAccion.getSelectedIndex();

                for (dataBase.Usuario u : app.getDataBase().getUsuarios()) {
                    if (!u.getLogin().equalsIgnoreCase(loginEmp)) continue;
                    if      (accion == 0 && u instanceof Mesero)   turno.agregarMesero((Mesero) u);
                    else if (accion == 1 && u instanceof Mesero)   turno.eliminarMesero((Mesero) u);
                    else if (accion == 2 && u instanceof Cocinero) turno.agregarCocinero((Cocinero) u);
                    else if (accion == 3 && u instanceof Cocinero) turno.eliminarCocinero((Cocinero) u);
                    mostrarMsg(lblMsg, "Turno modificado correctamente.", COLOR_EXITO);
                    return;
                }
                mostrarMsg(lblMsg, "Empleado '" + loginEmp + "' no encontrado.", COLOR_ACENTO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT));
        top.setBackground(COLOR_FONDO);
        top.add(btnCargar);

        p.add(top,  BorderLayout.NORTH);
        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelConsultarEstado() {
        JPanel p = panelContenido("Consultar estado de juego");

        JPanel form = new JPanel(new GridLayout(3, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JComboBox<String> cmbInv = new JComboBox<>(new String[]{"ventas", "prestamos"});
        JTextField txtId     = new JTextField();
        JTextField txtNombre = new JTextField();
        estilizarCampo(txtId); estilizarCampo(txtNombre);

        form.add(etiqueta("Inventario:"));     form.add(cmbInv);
        form.add(etiqueta("ID del juego:"));   form.add(txtId);
        form.add(etiqueta("Nombre juego:"));   form.add(txtNombre);

        JTextArea areaResultado = new JTextArea(3, 0);
        areaResultado.setEditable(false);
        areaResultado.setFont(FUENTE_NORMAL);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Consultar", COLOR_PRIMARIO);

        btnOk.addActionListener(e -> {
            String inv     = (String) cmbInv.getSelectedItem();
            String id      = txtId.getText().trim();
            String nombre  = txtNombre.getText().trim().toUpperCase();
            InventarioJuegos tienda = app.getTiendaDeJuegos();

            java.util.Map<String, ArrayList<JuegoDeMesaFisico>> mapa =
                inv.equals("ventas") ? tienda.getInventarioVenta() : tienda.getInventarioPrestamo();

            if (mapa == null || !mapa.containsKey(nombre)) {
                areaResultado.setText("Juego no encontrado.");
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (JuegoDeMesaFisico jf : mapa.get(nombre))
                if (jf.getIdJuego().equals(id))
                    sb.append("Estado: ").append(jf.getEstadoDelJuego()).append("\n");
            areaResultado.setText(sb.length() == 0 ? "ID no encontrado." : sb.toString());
        });

        p.add(form,             BorderLayout.NORTH);
        p.add(areaResultado,    BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelConsultarNumPrestamos() {
        JPanel p = panelContenido("Consultar número de préstamos de un juego");

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtId = new JTextField();
        estilizarCampo(txtId);
        form.add(etiqueta("ID del juego:")); form.add(txtId);

        JLabel lblResultado = new JLabel(" ");
        lblResultado.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblResultado.setForeground(COLOR_PRIMARIO);
        lblResultado.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Consultar", COLOR_PRIMARIO);

        btnOk.addActionListener(e -> {
            String id = txtId.getText().trim();
            int numero = 0;
            for (Prestamo pr : app.getTiendaDeJuegos().getHistorialDePrestamos())
                if (pr.getIdJuego().equals(id)) numero++;
            lblResultado.setText("Total de préstamos: " + numero);
        });

        p.add(form,         BorderLayout.NORTH);
        p.add(lblResultado, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelConsultarFechas() {
        JPanel p = panelContenido("Consultar fechas de préstamo de un juego");

        JPanel form = new JPanel(new GridLayout(1, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        JTextField txtId = new JTextField();
        estilizarCampo(txtId);
        form.add(etiqueta("ID del juego:")); form.add(txtId);

        JTextArea areaFechas = new JTextArea(6, 0);
        areaFechas.setEditable(false);
        areaFechas.setFont(FUENTE_NORMAL);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Consultar", COLOR_PRIMARIO);

        btnOk.addActionListener(e -> {
            String id = txtId.getText().trim();
            StringBuilder sb = new StringBuilder();
            for (Prestamo pr : app.getTiendaDeJuegos().getHistorialDePrestamos())
                if (pr.getIdJuego().equals(id))
                    sb.append("Inicio: ").append(pr.getFechaInicio()).append("\n");
            areaFechas.setText(sb.length() == 0 ? "No hay préstamos para ese ID." : sb.toString());
        });

        p.add(form,                    BorderLayout.NORTH);
        p.add(new JScrollPane(areaFechas), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk),      BorderLayout.SOUTH);
        return p;
    }

    // =========================================================
    // GRÁFICAS (Proyecto 3)
    // =========================================================

    /**
     * PASTEL: distribución de copias de un juego entre venta y préstamo.
     * Las copias se cuentan como el tamaño de la lista en cada inventario.
     * La clave en ambos mapas está en MAYÚSCULAS (igual que en consultarEstado).
     */
    private JPanel crearPanelGraficaPastel() {
        JPanel p = panelContenido("Gráfica de pastel: disponibilidad de un juego");

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        top.setBackground(COLOR_FONDO);
        JTextField txtNombre = new JTextField(18);
        estilizarCampo(txtNombre);
        JButton btnGen = boton("Generar gráfica", COLOR_PRIMARIO);
        top.add(etiqueta("Nombre del juego:"));
        top.add(txtNombre);
        top.add(btnGen);

        // Contenedor que se reemplaza en cada consulta
        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(COLOR_CARD);

        JLabel lblMsg = mensajeEstado();

        btnGen.addActionListener(e -> {
            String key = txtNombre.getText().trim().toUpperCase();
            if (key.isEmpty()) {
                mostrarMsg(lblMsg, "Escriba el nombre del juego.", COLOR_ACENTO);
                return;
            }
            InventarioJuegos tienda = app.getTiendaDeJuegos();
            int venta    = tienda.getInventarioVenta().getOrDefault(key, new ArrayList<>()).size();
            int prestamo = tienda.getInventarioPrestamo().getOrDefault(key, new ArrayList<>()).size();

            if (venta == 0 && prestamo == 0) {
                mostrarMsg(lblMsg, "No hay copias de '" + key + "' en ningún inventario.", COLOR_ACENTO);
                contenedor.removeAll(); contenedor.revalidate(); contenedor.repaint();
                return;
            }

            DefaultPieDataset<String> dataset = new DefaultPieDataset<>();
            dataset.setValue("Copias venta (" + venta + ")", venta);
            dataset.setValue("Copias préstamo (" + prestamo + ")", prestamo);

            JFreeChart chart = ChartFactory.createPieChart(
                    "Disponibilidad " + key, dataset, true, true, false);

            contenedor.removeAll();
            contenedor.add(new ChartPanel(chart), BorderLayout.CENTER);
            contenedor.revalidate();
            contenedor.repaint();
            mostrarMsg(lblMsg, "Gráfica generada.", COLOR_EXITO);
        });

        p.add(top, BorderLayout.NORTH);
        p.add(contenedor, BorderLayout.CENTER);
        p.add(sur(lblMsg), BorderLayout.SOUTH);
        return p;
    }

    /**
     * BARRAS: ventas de cafetería vs juegos en un rango de 5 días.
     * Se clasifica POR LÍNEA de detalle (una factura puede mezclar tipos).
     * El monto es el subtotal de cada detalle (base, SIN impuestos).
     */
    private JPanel crearPanelGraficaBarras() {
        JPanel p = panelContenido("Gráfica de barras: ventas café vs juegos (5 días)");

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        top.setBackground(COLOR_FONDO);
        JTextField txtInicio = new JTextField("YYYY-MM-DD", 12);
        estilizarCampo(txtInicio);
        JButton btnGen = boton("Generar gráfica", COLOR_PRIMARIO);
        top.add(etiqueta("Fecha inicio (5 días desde aquí):"));
        top.add(txtInicio);
        top.add(btnGen);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(COLOR_CARD);
        JLabel lblMsg = mensajeEstado();

        btnGen.addActionListener(e -> {
            LocalDate inicio;
            try {
                inicio = LocalDate.parse(txtInicio.getText().trim());
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Fecha inválida. Use YYYY-MM-DD.", COLOR_ACENTO);
                return;
            }
            LocalDate fin = inicio.plusDays(4); // 5 días: inicio + 4

            // Acumuladores por día: índice 0..4
            double[] cafePorDia   = new double[5];
            double[] juegosPorDia = new double[5];

            for (Factura f : app.getDataBase().getFacturas()) {
                LocalDate fecha = f.getFecha();
                if (fecha == null) continue;
                if (fecha.isBefore(inicio) || fecha.isAfter(fin)) continue;

                int idx = (int) ChronoUnit.DAYS.between(inicio, fecha); // 0..4

                for (DetalleVenta d : f.getDetallesDeLaVenta()) {
                    if (d.getTipoVenta() == TipoVenta.CAFE)
                        cafePorDia[idx]   += d.getSubtotal();
                    else if (d.getTipoVenta() == TipoVenta.TIENDADEJUEGOS)
                        juegosPorDia[idx] += d.getSubtotal();
                }
            }

            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < 5; i++) {
                String dia = inicio.plusDays(i).toString(); // etiqueta del eje X
                dataset.addValue(cafePorDia[i],   "Cafetería", dia);
                dataset.addValue(juegosPorDia[i], "Juegos",    dia);
            }

            JFreeChart chart = ChartFactory.createBarChart(
                    "Ventas por periodo (" + inicio + ")",
                    "Categorias", "Valor en Pesos", dataset);

            contenedor.removeAll();
            contenedor.add(new ChartPanel(chart), BorderLayout.CENTER);
            contenedor.revalidate(); contenedor.repaint();
            mostrarMsg(lblMsg, "Ventas de " + inicio + " a " + fin + " graficadas.", COLOR_EXITO);
        });

        p.add(top, BorderLayout.NORTH);
        p.add(contenedor, BorderLayout.CENTER);
        p.add(sur(lblMsg), BorderLayout.SOUTH);
        return p;
    }

    /**
     * LÍNEAS: número de reservas (préstamos) por día durante UNA semana concreta.
     * El admin escribe cualquier fecha; el sistema deduce la semana ISO (lunes-domingo)
     * a la que pertenece y grafica esa semana completa.
     */
    private JPanel crearPanelGraficaLineas() {
        JPanel p = panelContenido("Gráfica de líneas: reservas de una semana");

        JPanel top = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        top.setBackground(COLOR_FONDO);
        JTextField txtFecha = new JTextField("YYYY-MM-DD", 12);
        estilizarCampo(txtFecha);
        JButton btnGen = boton("Generar gráfica", COLOR_PRIMARIO);
        top.add(etiqueta("Una fecha de la semana:"));
        top.add(txtFecha);
        top.add(btnGen);

        JPanel contenedor = new JPanel(new BorderLayout());
        contenedor.setBackground(COLOR_CARD);
        JLabel lblMsg = mensajeEstado();

        // Regla de semanas: lunes como primer día (semana ISO)
        WeekFields wf = WeekFields.of(Locale.FRANCE);

        btnGen.addActionListener(e -> {
            LocalDate ref;
            try {
                ref = LocalDate.parse(txtFecha.getText().trim());
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Fecha inválida. Use YYYY-MM-DD.", COLOR_ACENTO);
                return;
            }

            int semanaObjetivo = ref.get(wf.weekOfWeekBasedYear());
            int anioObjetivo   = ref.get(wf.weekBasedYear());

            int[] conteo = new int[7]; // 0=Lunes ... 6=Domingo
            for (Prestamo pr : app.getTiendaDeJuegos().getHistorialDePrestamos()) {
                LocalDate fpr = pr.getFechaInicio();
                int semana = fpr.get(wf.weekOfWeekBasedYear());
                int anio   = fpr.get(wf.weekBasedYear());
                if (semana == semanaObjetivo && anio == anioObjetivo) {
                    conteo[fpr.getDayOfWeek().getValue() - 1]++; // MONDAY=1 ... SUNDAY=7
                }
            }

            String[] dias = {"Lunes","Martes","Miércoles","Jueves","Viernes","Sábado","Domingo"};
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            for (int i = 0; i < 7; i++)
                dataset.addValue(conteo[i], "Reservas", dias[i]);

            JFreeChart chart = ChartFactory.createLineChart(
                    "Reservas Semana " + semanaObjetivo, "Día de la Semana", "Número Reservas", dataset);

            contenedor.removeAll();
            contenedor.add(new ChartPanel(chart), BorderLayout.CENTER);
            contenedor.revalidate(); contenedor.repaint();
            mostrarMsg(lblMsg, "Semana " + semanaObjetivo + " / " + anioObjetivo + " graficada.", COLOR_EXITO);
        });

        p.add(top, BorderLayout.NORTH);
        p.add(contenedor, BorderLayout.CENTER);
        p.add(sur(lblMsg), BorderLayout.SOUTH);
        return p;
    }

    // =========================================================
    // CARGA DE DATOS
    // =========================================================

    private void cargarEmpleados(DefaultTableModel model) {
        model.setRowCount(0);
        java.util.List<Empleado> empleados = app.getCafe().getEmpleados();
        if (empleados == null) return;
        int i = 1;
        for (Empleado emp : empleados) {
            String tipo = (emp instanceof Mesero) ? "Mesero" : "Cocinero";
            model.addRow(new Object[]{i++, emp.getLogin(), tipo, emp.getCodigoDescuento()});
        }
    }

    // =========================================================
    // FÁBRICA DE COMPONENTES
    // =========================================================

    private JPanel panelContenido(String titulo) {
        JPanel p = new JPanel(new BorderLayout(0, 8));
        p.setBackground(COLOR_FONDO);
        p.setBorder(new EmptyBorder(16, 16, 16, 16));
        JLabel lbl = new JLabel(titulo);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 17));
        lbl.setForeground(COLOR_PRIMARIO);
        lbl.setBorder(new EmptyBorder(0, 0, 8, 0));
        p.add(lbl, BorderLayout.NORTH);
        return p;
    }

    private JPanel sur(JLabel lblMsg, JButton... botones) {
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 6));
        sur.setBackground(COLOR_FONDO);
        sur.add(lblMsg);
        for (JButton b : botones) sur.add(b);
        return sur;
    }

    private JLabel mensajeEstado() {
        JLabel lbl = new JLabel(" ");
        lbl.setFont(FUENTE_SMALL);
        lbl.setPreferredSize(new Dimension(320, 20));
        return lbl;
    }

    private JLabel etiqueta(String texto) {
        JLabel lbl = new JLabel(texto);
        lbl.setFont(FUENTE_NORMAL);
        lbl.setForeground(COLOR_TEXTO);
        return lbl;
    }

    private JButton boton(String texto, Color fondo) {
        JButton btn = new JButton(texto);
        btn.setFont(FUENTE_NORMAL);
        btn.setBackground(fondo);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorder(new EmptyBorder(7, 18, 7, 18));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        return btn;
    }

    private JButton botonSidebar(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        btn.setForeground(new Color(189, 195, 199));
        btn.setBackground(new Color(44, 62, 80));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setHorizontalAlignment(SwingConstants.LEFT);
        btn.setBorder(new EmptyBorder(10, 16, 10, 16));
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 42));
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(52, 73, 94));
                btn.setForeground(Color.WHITE);
            }
            public void mouseExited(java.awt.event.MouseEvent e) {
                btn.setBackground(new Color(44, 62, 80));
                btn.setForeground(new Color(189, 195, 199));
            }
        });
        return btn;
    }

    private void estilizarCampo(JTextField campo) {
        campo.setFont(FUENTE_NORMAL);
        campo.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(189, 195, 199)),
            new EmptyBorder(5, 8, 5, 8)));
    }

    private void estilizarTabla(JTable tabla) {
        tabla.setFont(FUENTE_NORMAL);
        tabla.setRowHeight(26);
        tabla.getTableHeader().setFont(FUENTE_TITULO);
        tabla.getTableHeader().setBackground(COLOR_PRIMARIO);
        tabla.getTableHeader().setForeground(Color.WHITE);
        tabla.setSelectionBackground(new Color(174, 214, 241));
        tabla.setGridColor(new Color(220, 220, 220));
        tabla.setShowGrid(true);
    }

    private static void mostrarMsg(JLabel lbl, String texto, Color color) {
        lbl.setText(texto);
        lbl.setForeground(color);
    }
}