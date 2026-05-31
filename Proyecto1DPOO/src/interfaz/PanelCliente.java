package interfaz;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import cafe.Cafe;
import cafe.Mesa;
import cafe.Producto;
import dataBase.Cliente;
import dataBase.DataBase;
import dataBase.Usuario;
import generals.DulcesNDados;
import generals.generadorID;
import tiendaDeJuegos.InventarioJuegos;
import tiendaDeJuegos.JuegoDeMesa;
import tiendaDeJuegos.JuegoDeMesaFisico;
import tiendaDeJuegos.Prestamo;
import tiendaDeJuegos.TipoInventario;

public class PanelCliente extends JPanel {

    private static final long serialVersionUID = 1L;

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
    private Cliente clienteActivo = null;

    private CardLayout cardLayout;
    private JPanel     cardPanel;

    private JLabel lblBienvenida;
    private JLabel lblMesaActiva;
    private JLabel lblPuntos;

    public PanelCliente(DulcesNDados app) {
        this.app = app;
        setLayout(new BorderLayout());
        setBackground(COLOR_FONDO);

        cardLayout = new CardLayout();
        cardPanel  = new JPanel(cardLayout);
        cardPanel.setBackground(COLOR_FONDO);

        cardPanel.add(construirPanelAuth(),      "auth");
        cardPanel.add(construirPanelPrincipal(), "principal");

        add(cardPanel, BorderLayout.CENTER);
        cardLayout.show(cardPanel, "auth");
    }

    // =========================================================
    // PANEL AUTH
    // =========================================================

    private JPanel construirPanelAuth() {
        JPanel outer = new JPanel(new GridBagLayout());
        outer.setBackground(COLOR_FONDO);

        JPanel card = new JPanel(new BorderLayout(0, 18));
        card.setBackground(COLOR_CARD);
        card.setBorder(new EmptyBorder(32, 40, 32, 40));
        card.setPreferredSize(new Dimension(420, 460));

        JLabel titulo = new JLabel("Dulces N Dados", SwingConstants.CENTER);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titulo.setForeground(COLOR_PRIMARIO);

        JLabel subtitulo = new JLabel("Area del Cliente", SwingConstants.CENTER);
        subtitulo.setFont(FUENTE_NORMAL);
        subtitulo.setForeground(Color.GRAY);

        JPanel cabecera = new JPanel();
        cabecera.setLayout(new BoxLayout(cabecera, BoxLayout.Y_AXIS));
        cabecera.setBackground(COLOR_CARD);
        cabecera.add(titulo);
        cabecera.add(Box.createVerticalStrut(4));
        cabecera.add(subtitulo);

        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(COLOR_CARD);

        form.add(etiqueta("Usuario:"));
        JTextField txtLogin = new JTextField();
        estilizarCampo(txtLogin);
        form.add(txtLogin);

        form.add(etiqueta("Contrasena:"));
        JPasswordField txtPass = new JPasswordField();
        estilizarCampo(txtPass);
        form.add(txtPass);

        JButton btnLogin    = boton("Iniciar sesion", COLOR_PRIMARIO);
        JButton btnRegistro = boton("Registrarse",    COLOR_ACENTO);
        form.add(btnLogin);
        form.add(btnRegistro);

        JLabel lblMensaje = new JLabel(" ", SwingConstants.CENTER);
        lblMensaje.setFont(FUENTE_SMALL);

        card.add(cabecera,   BorderLayout.NORTH);
        card.add(form,       BorderLayout.CENTER);
        card.add(lblMensaje, BorderLayout.SOUTH);

        btnLogin.addActionListener(e -> {
            String login = txtLogin.getText().trim();
            String pass  = new String(txtPass.getPassword()).trim();

            if (login.isEmpty() || pass.isEmpty()) {
                mostrarMsg(lblMensaje, "Completa usuario y contrasena.", COLOR_ACENTO);
                return;
            }

            for (Usuario u : app.getDataBase().getUsuarios()) {
                if (u instanceof Cliente
                        && u.getLogin().equalsIgnoreCase(login)
                        && u.getContrasenia().equalsIgnoreCase(pass)) {
                    clienteActivo = (Cliente) u;
                    txtLogin.setText("");
                    txtPass.setText("");
                    actualizarHeader();
                    cardLayout.show(cardPanel, "principal");
                    return;
                }
            }
            mostrarMsg(lblMensaje, "Usuario o contrasena incorrectos.", COLOR_ACENTO);
        });

        btnRegistro.addActionListener(e -> mostrarDialogoRegistro(lblMensaje));

        outer.add(card);
        return outer;
    }

    private void mostrarDialogoRegistro(JLabel lblMensajeAuth) {
        JDialog dlg = new JDialog(
            (Frame) SwingUtilities.getWindowAncestor(this),
            "Registro de cliente", true);
        dlg.setLayout(new BorderLayout(0, 10));
        dlg.setSize(380, 280);
        dlg.setLocationRelativeTo(this);

        JPanel form = new JPanel(new GridLayout(3, 2, 8, 8));
        form.setBorder(new EmptyBorder(16, 20, 8, 20));
        form.setBackground(COLOR_CARD);

        form.add(etiqueta("Usuario:"));
        JTextField txtU = new JTextField(); estilizarCampo(txtU); form.add(txtU);

        form.add(etiqueta("Contrasena:"));
        JPasswordField txtP = new JPasswordField(); estilizarCampo(txtP); form.add(txtP);

        form.add(etiqueta("Juegos favoritos (comas):"));
        JTextField txtFav = new JTextField(); estilizarCampo(txtFav); form.add(txtFav);

        JLabel lblMsg = new JLabel(" ", SwingConstants.CENTER);
        lblMsg.setFont(FUENTE_SMALL);

        JButton btnOk = boton("Crear cuenta", COLOR_EXITO);
        JPanel sur = new JPanel(new FlowLayout(FlowLayout.CENTER));
        sur.setBackground(COLOR_CARD);
        sur.add(btnOk);

        dlg.add(lblMsg, BorderLayout.NORTH);
        dlg.add(form,   BorderLayout.CENTER);
        dlg.add(sur,    BorderLayout.SOUTH);

        btnOk.addActionListener(e -> {
            String login = txtU.getText().trim();
            String pass  = new String(txtP.getPassword()).trim();

            if (login.isEmpty() || pass.isEmpty()) {
                mostrarMsg(lblMsg, "Usuario y contrasena son obligatorios.", COLOR_ACENTO);
                return;
            }

            for (Usuario u : app.getDataBase().getUsuarios()) {
                if (u.getLogin().equalsIgnoreCase(login)) {
                    mostrarMsg(lblMsg, "El usuario ya existe.", COLOR_ACENTO);
                    return;
                }
            }

            ArrayList<String> favs = new ArrayList<>();
            for (String f : txtFav.getText().split(",")) {
                String s = f.trim();
                if (!s.isEmpty()) favs.add(s);
            }

            String idCliente = generadorID.generarIDUsuario(login);
            Cliente nuevo = new Cliente(login, favs, pass);
            nuevo.setIdCliente(idCliente);
            nuevo.setPuntosFidelidad(0);
            nuevo.setJuegosPrestados(new ArrayList<>());
            nuevo.setJuegosComprados(new ArrayList<>());
            app.getDataBase().getUsuarios().add(nuevo);

            clienteActivo = nuevo;
            dlg.dispose();
            mostrarMsg(lblMensajeAuth, "Registro exitoso. Sesion iniciada como " + login, COLOR_EXITO);
            actualizarHeader();
            cardLayout.show(cardPanel, "principal");
        });

        dlg.setVisible(true);
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

        lblBienvenida = new JLabel("Bienvenido/a");
        lblBienvenida.setFont(FUENTE_TITULO);
        lblBienvenida.setForeground(Color.WHITE);

        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 0));
        infoPanel.setOpaque(false);

        lblMesaActiva = new JLabel("Sin mesa");
        lblMesaActiva.setFont(FUENTE_NORMAL);
        lblMesaActiva.setForeground(Color.WHITE);

        lblPuntos = new JLabel("0 pts");
        lblPuntos.setFont(FUENTE_NORMAL);
        lblPuntos.setForeground(Color.WHITE);

        JButton btnCerrar = boton("Cerrar sesion", COLOR_ACENTO);
        btnCerrar.addActionListener(e -> cerrarSesion());

        infoPanel.add(lblMesaActiva);
        infoPanel.add(lblPuntos);
        infoPanel.add(btnCerrar);

        header.add(lblBienvenida, BorderLayout.WEST);
        header.add(infoPanel,     BorderLayout.EAST);

        // Sidebar
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
        sidebar.setBackground(new Color(44, 62, 80));
        sidebar.setBorder(new EmptyBorder(16, 0, 16, 0));
        sidebar.setPreferredSize(new Dimension(180, 0));

        // Contenido
        CardLayout cl = new CardLayout();
        JPanel contenido = new JPanel(cl);
        contenido.setBackground(COLOR_FONDO);

        contenido.add(crearPanelReservarMesa(),    "mesa");
        contenido.add(crearPanelSolicitarPrestamo(),"prestamo");
        contenido.add(crearPanelComprarJuego(),    "comprarJuego");
        contenido.add(crearPanelComprarPlatillo(), "comprarPlatillo");
        contenido.add(crearPanelCatalogo(),        "catalogo");
        contenido.add(crearPanelVerMenu(),         "menu");
        contenido.add(crearPanelFidelidad(),       "fidelidad");

        String[][] items = {
            {"Reservar mesa",       "mesa"},
            {"Pedir juego prestado","prestamo"},
            {"Comprar juego",       "comprarJuego"},
            {"Comprar platillo",    "comprarPlatillo"},
            {"Catalogo de juegos",  "catalogo"},
            {"Ver menu",            "menu"},
            {"Mis puntos",          "fidelidad"},
        };

        for (String[] item : items) {
            JButton btn = botonSidebar(item[0]);
            String card = item[1];
            btn.addActionListener(e -> cl.show(contenido, card));
            sidebar.add(btn);
            sidebar.add(Box.createVerticalStrut(2));
        }

        panel.add(header,   BorderLayout.NORTH);
        panel.add(sidebar,  BorderLayout.WEST);
        panel.add(contenido, BorderLayout.CENTER);
        return panel;
    }

    // =========================================================
    // SECCIONES
    // =========================================================

    private JPanel crearPanelReservarMesa() {
        JPanel p = panelContenido("Reservar mesa");

        JPanel form = new JPanel(new GridLayout(5, 2, 10, 10));
        form.setBackground(COLOR_CARD);
        form.setBorder(new EmptyBorder(16, 16, 16, 16));

        form.add(etiqueta("Numero de personas:"));
        JSpinner spnPersonas = new JSpinner(new SpinnerNumberModel(1, 1, 20, 1));
        form.add(spnPersonas);

        JCheckBox chkNinos   = new JCheckBox("Hay ninos?");
        JCheckBox chkJovenes = new JCheckBox("Hay jovenes (mayores de edad)?");
        JCheckBox chkBebida  = new JCheckBox("Desea bebida caliente?");
        chkNinos.setBackground(COLOR_CARD);
        chkJovenes.setBackground(COLOR_CARD);
        chkBebida.setBackground(COLOR_CARD);

        form.add(new JLabel()); form.add(chkNinos);
        form.add(new JLabel()); form.add(chkJovenes);
        form.add(new JLabel()); form.add(chkBebida);

        JLabel  lblMsg = mensajeEstado();
        JButton btnOk  = boton("Confirmar reserva", COLOR_EXITO);

        btnOk.addActionListener(e -> {
            if (!validarSesion(lblMsg)) return;
            if (clienteActivo.getMesa() != null) {
                mostrarMsg(lblMsg, "Ya tiene la mesa " + clienteActivo.getMesa().getIdMesa(), COLOR_ACENTO);
                return;
            }
            Cafe cafe  = app.getCafe();
            int  pers  = (int) spnPersonas.getValue();
            String id  = "Mesa-" + (cafe.getMesas().size() + 1);
            cafe.agregarMesa(id, pers, chkJovenes.isSelected(),
                chkNinos.isSelected(), chkBebida.isSelected(),
                new ArrayList<>(), clienteActivo);
            for (Mesa m : cafe.getMesas()) {
                if (m.getIdMesa().equals(id)) { clienteActivo.setMesa(m); break; }
            }
            actualizarHeader();
            mostrarMsg(lblMsg, "Mesa '" + id + "' reservada para " + pers + " persona(s).", COLOR_EXITO);
        });

        p.add(form, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnOk), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelSolicitarPrestamo() {
        JPanel p = panelContenido("Solicitar juego prestado");

        String[] cols = {"#", "Juego", "Stock prestamo"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JLabel  lblMsg  = mensajeEstado();
        JButton btnRef  = boton("Actualizar lista",   COLOR_PRIMARIO);
        JButton btnPed  = boton("Solicitar prestamo", COLOR_EXITO);

        btnRef.addActionListener(e -> cargarInventario(model, TipoInventario.PRESTAMO));
        btnPed.addActionListener(e -> {
            if (!validarSesion(lblMsg)) return;
            if (!validarMesa(lblMsg))   return;
            int fila = tabla.getSelectedRow();
            if (fila < 0) { mostrarMsg(lblMsg, "Seleccione un juego.", COLOR_ACENTO); return; }
            String nombre = (String) model.getValueAt(fila, 1);
            try {
                InventarioJuegos tienda = app.getTiendaDeJuegos();
                tienda.validarDisponibilidadJuegos(nombre, 1, TipoInventario.PRESTAMO);
                JuegoDeMesaFisico juego = tienda.eliminarPrimerJuego(nombre, TipoInventario.PRESTAMO);
                juego.setPrestado(true);
                LocalDate hoy = LocalDate.now();
                Prestamo pr = new Prestamo(juego.getIdJuego(),
                    clienteActivo.getMesa().getIdMesa(), hoy, hoy.plusDays(1));
                tienda.agregarPrestamoAHistorial(pr);
                if (clienteActivo.getJuegosPrestados() == null)
                    clienteActivo.setJuegosPrestados(new ArrayList<>());
                clienteActivo.getJuegosPrestados().add(juego);
                cargarInventario(model, TipoInventario.PRESTAMO);
                mostrarMsg(lblMsg, "'" + nombre + "' prestado. Devolver el " + hoy.plusDays(1), COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        cargarInventario(model, TipoInventario.PRESTAMO);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef, btnPed), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelComprarJuego() {
        JPanel p = panelContenido("Comprar juego");

        String[] cols = {"#", "Juego", "Precio", "Stock"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar lista", COLOR_PRIMARIO);
        JButton btnCom = boton("Comprar",          COLOR_EXITO);

        btnRef.addActionListener(e -> cargarVenta(model));
        btnCom.addActionListener(e -> {
            if (!validarSesion(lblMsg)) return;
            int fila = tabla.getSelectedRow();
            if (fila < 0) { mostrarMsg(lblMsg, "Seleccione un juego.", COLOR_ACENTO); return; }
            String nombre = (String) model.getValueAt(fila, 1);
            try {
                InventarioJuegos tienda = app.getTiendaDeJuegos();
                tienda.validarDisponibilidadJuegos(nombre, 1, TipoInventario.VENTAS);
                JuegoDeMesaFisico juego = tienda.eliminarPrimerJuego(nombre, TipoInventario.VENTAS);
                if (clienteActivo.getJuegosComprados() == null)
                    clienteActivo.setJuegosComprados(new ArrayList<>());
                clienteActivo.getJuegosComprados().add(juego);
                double pts = juego.getPrecio() / 1000.0;
                clienteActivo.setPuntosFidelidad(clienteActivo.getPuntosFidelidad() + pts);
                actualizarHeader();
                cargarVenta(model);
                mostrarMsg(lblMsg, String.format("'%s' comprado por $%.0f. +%.2f pts.", nombre, juego.getPrecio(), pts), COLOR_EXITO);
            } catch (Exception ex) {
                mostrarMsg(lblMsg, "Error: " + ex.getMessage(), COLOR_ACENTO);
            }
        });

        cargarVenta(model);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef, btnCom), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelComprarPlatillo() {
        JPanel p = panelContenido("Comprar platillo");

        String[] cols = {"#", "Producto", "Precio"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar menu", COLOR_PRIMARIO);
        JButton btnOrd = boton("Ordenar",         COLOR_EXITO);

        btnRef.addActionListener(e -> cargarMenu(model));
        btnOrd.addActionListener(e -> {
            if (!validarSesion(lblMsg)) return;
            if (!validarMesa(lblMsg))   return;
            int fila = tabla.getSelectedRow();
            if (fila < 0) { mostrarMsg(lblMsg, "Seleccione un platillo.", COLOR_ACENTO); return; }
            Cafe cafe = app.getCafe();
            Producto sel = cafe.getCatalogoPlatillos().get(fila);
            clienteActivo.getMesa().getProductosOrdenados().add(sel);
            double pts = sel.getPrecio() / 500.0;
            clienteActivo.setPuntosFidelidad(clienteActivo.getPuntosFidelidad() + pts);
            actualizarHeader();
            mostrarMsg(lblMsg, String.format("'%s' agregado a tu mesa. +%.2f pts.", sel.getNombre(), pts), COLOR_EXITO);
        });

        cargarMenu(model);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef, btnOrd), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelCatalogo() {
        JPanel p = panelContenido("Catalogo de juegos");

        String[] cols = {"Juego", "Tipo", "Jugadores", "Edad min.", "Precio", "Dificultad", "Stock prestamo", "Stock venta"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JTextArea detalle = new JTextArea(4, 0);
        detalle.setEditable(false);
        detalle.setFont(FUENTE_SMALL);
        detalle.setBorder(new EmptyBorder(8, 8, 8, 8));
        JScrollPane scrollDet = new JScrollPane(detalle);
        scrollDet.setBorder(new TitledBorder("Caracteristicas"));

        tabla.getSelectionModel().addListSelectionListener(ev -> {
            if (ev.getValueIsAdjusting()) return;
            int fila = tabla.getSelectedRow();
            if (fila < 0) { detalle.setText(""); return; }
            String nombre = (String) model.getValueAt(fila, 0);
            Map<String, JuegoDeMesa> cat = app.getTiendaDeJuegos().getCatalogoJuegos();
            if (cat != null && cat.containsKey(nombre)) {
                JuegoDeMesa j = cat.get(nombre);
                detalle.setText(
                    "Nombre: "          + j.getNombre()           + "\n" +
                    "Empresa: "         + j.getEmpresaProduccion() + "\n" +
                    "Caracteristicas: " + j.getCaracteristicas()   + "\n" +
                    "Ano publicacion: " + j.getAnoPublicacion()    + "\n" +
                    "Dificil: "         + (j.isDificil() ? "Si" : "No"));
            }
        });

        JSplitPane split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
            new JScrollPane(tabla), scrollDet);
        split.setResizeWeight(0.7);
        split.setDividerSize(4);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar", COLOR_PRIMARIO);
        btnRef.addActionListener(e -> cargarCatalogo(model));
        cargarCatalogo(model);

        p.add(split, BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef), BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelVerMenu() {
        JPanel p = panelContenido("Menu del Cafe");

        String[] cols = {"#", "Producto", "Precio"};
        DefaultTableModel model = new DefaultTableModel(cols, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabla = new JTable(model);
        estilizarTabla(tabla);

        JLabel lblCap = new JLabel();
        lblCap.setFont(FUENTE_NORMAL);
        lblCap.setBorder(new EmptyBorder(6, 0, 6, 0));

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar", COLOR_PRIMARIO);
        btnRef.addActionListener(e -> {
            cargarMenu(model);
            lblCap.setText("Capacidad del cafe: " + app.getCafe().getCapacidadMaxClientes() + " personas");
        });

        lblCap.setText("Capacidad del cafe: " + app.getCafe().getCapacidadMaxClientes() + " personas");
        cargarMenu(model);

        p.add(lblCap,                 BorderLayout.NORTH);
        p.add(new JScrollPane(tabla), BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef),    BorderLayout.SOUTH);
        return p;
    }

    private JPanel crearPanelFidelidad() {
        JPanel p = panelContenido("Mis puntos de fidelidad");

        JPanel resumen = new JPanel(new GridLayout(2, 2, 12, 8));
        resumen.setBackground(COLOR_CARD);
        resumen.setBorder(new EmptyBorder(16, 16, 16, 16));

        JLabel lblNomTit = etiqueta("Cliente:");
        JLabel lblNom    = new JLabel("---");
        JLabel lblPtsTit = etiqueta("Puntos acumulados:");
        JLabel lblPtsVal = new JLabel("---");
        lblPtsVal.setFont(new Font("Segoe UI", Font.BOLD, 18));
        lblPtsVal.setForeground(new Color(241, 196, 15));

        resumen.add(lblNomTit); resumen.add(lblNom);
        resumen.add(lblPtsTit); resumen.add(lblPtsVal);

        String[] colsC = {"Juego comprado", "Precio"};
        DefaultTableModel modelComp = new DefaultTableModel(colsC, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaComp = new JTable(modelComp);
        estilizarTabla(tablaComp);
        JScrollPane spComp = new JScrollPane(tablaComp);
        spComp.setBorder(new TitledBorder("Juegos comprados"));

        String[] colsP = {"Juego prestado", "Estado"};
        DefaultTableModel modelPrest = new DefaultTableModel(colsP, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tablaPrest = new JTable(modelPrest);
        estilizarTabla(tablaPrest);
        JScrollPane spPrest = new JScrollPane(tablaPrest);
        spPrest.setBorder(new TitledBorder("Juegos en prestamo"));

        JSplitPane split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, spComp, spPrest);
        split.setResizeWeight(0.5);
        split.setDividerSize(4);

        JLabel  lblMsg = mensajeEstado();
        JButton btnRef = boton("Actualizar", COLOR_PRIMARIO);
        btnRef.addActionListener(e -> cargarFidelidad(lblNom, lblPtsVal, modelComp, modelPrest, lblMsg));
        cargarFidelidad(lblNom, lblPtsVal, modelComp, modelPrest, lblMsg);

        p.add(resumen, BorderLayout.NORTH);
        p.add(split,   BorderLayout.CENTER);
        p.add(sur(lblMsg, btnRef), BorderLayout.SOUTH);
        return p;
    }

    // =========================================================
    // CARGA DE DATOS
    // =========================================================

    private void cargarInventario(DefaultTableModel model, TipoInventario tipo) {
        model.setRowCount(0);
        InventarioJuegos tienda = app.getTiendaDeJuegos();
        Map<String, ArrayList<JuegoDeMesaFisico>> inv =
            tipo == TipoInventario.PRESTAMO
                ? tienda.getInventarioPrestamo()
                : tienda.getInventarioVenta();
        if (inv == null) return;
        int i = 1;
        for (Map.Entry<String, ArrayList<JuegoDeMesaFisico>> e : inv.entrySet())
            if (!e.getValue().isEmpty())
                model.addRow(new Object[]{i++, e.getKey(), e.getValue().size()});
    }

    private void cargarVenta(DefaultTableModel model) {
        model.setRowCount(0);
        Map<String, ArrayList<JuegoDeMesaFisico>> inv = app.getTiendaDeJuegos().getInventarioVenta();
        if (inv == null) return;
        int i = 1;
        for (Map.Entry<String, ArrayList<JuegoDeMesaFisico>> e : inv.entrySet())
            if (!e.getValue().isEmpty())
                model.addRow(new Object[]{i++, e.getKey(),
                    String.format("$%.0f", e.getValue().get(0).getPrecio()),
                    e.getValue().size()});
    }

    private void cargarMenu(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Producto> platillos = app.getCafe().getCatalogoPlatillos();
        if (platillos == null) return;
        int i = 1;
        for (Producto prod : platillos)
            model.addRow(new Object[]{i++, prod.getNombre(), String.format("$%.0f", prod.getPrecio())});
    }

    private void cargarCatalogo(DefaultTableModel model) {
        model.setRowCount(0);
        Map<String, JuegoDeMesa> cat = app.getTiendaDeJuegos().getCatalogoJuegos();
        if (cat == null) return;
        for (JuegoDeMesa j : cat.values()) {
            int sP = 0, sV = 0;
            Map<String, ArrayList<JuegoDeMesaFisico>> invP = app.getTiendaDeJuegos().getInventarioPrestamo();
            Map<String, ArrayList<JuegoDeMesaFisico>> invV = app.getTiendaDeJuegos().getInventarioVenta();
            if (invP != null && invP.containsKey(j.getNombre())) sP = invP.get(j.getNombre()).size();
            if (invV != null && invV.containsKey(j.getNombre())) sV = invV.get(j.getNombre()).size();
            model.addRow(new Object[]{j.getNombre(), j.getTipoDeJuego(),
                j.getMinJugadores() + "-" + j.getMaxJugadores(),
                j.getEdadMinima(), String.format("$%.0f", j.getPrecio()),
                j.isDificil() ? "Alta" : "Normal", sP, sV});
        }
    }

    private void cargarFidelidad(JLabel lblNom, JLabel lblPts,
                                  DefaultTableModel mComp, DefaultTableModel mPrest,
                                  JLabel lblMsg) {
        if (clienteActivo == null) {
            mostrarMsg(lblMsg, "Debe iniciar sesion primero.", COLOR_ACENTO);
            return;
        }
        lblNom.setText(clienteActivo.getLogin());
        lblPts.setText(String.format("%.2f", clienteActivo.getPuntosFidelidad()));

        mComp.setRowCount(0);
        if (clienteActivo.getJuegosComprados() != null)
            for (JuegoDeMesaFisico j : clienteActivo.getJuegosComprados())
                mComp.addRow(new Object[]{j.getNombre(), String.format("$%.0f", j.getPrecio())});

        mPrest.setRowCount(0);
        if (clienteActivo.getJuegosPrestados() != null)
            for (JuegoDeMesaFisico j : clienteActivo.getJuegosPrestados())
                mPrest.addRow(new Object[]{j.getNombre(), j.isPrestado() ? "En prestamo" : "Devuelto"});
    }

    // =========================================================
    // AUXILIARES
    // =========================================================

    private void cerrarSesion() {
        clienteActivo = null;
        cardLayout.show(cardPanel, "auth");
    }

    private void actualizarHeader() {
        if (clienteActivo == null) return;
        lblBienvenida.setText("Bienvenido/a, " + clienteActivo.getLogin());
        lblMesaActiva.setText(clienteActivo.getMesa() != null
            ? "Mesa: " + clienteActivo.getMesa().getIdMesa() : "Sin mesa");
        lblPuntos.setText(String.format("%.0f pts", clienteActivo.getPuntosFidelidad()));
    }

    private boolean validarSesion(JLabel lbl) {
        if (clienteActivo != null) return true;
        mostrarMsg(lbl, "Debe iniciar sesion primero.", COLOR_ACENTO);
        return false;
    }

    private boolean validarMesa(JLabel lbl) {
        if (clienteActivo.getMesa() != null) return true;
        mostrarMsg(lbl, "Debe reservar una mesa primero.", COLOR_ACENTO);
        return false;
    }

    private static void mostrarMsg(JLabel lbl, String texto, Color color) {
        lbl.setText(texto);
        lbl.setForeground(color);
    }

    // =========================================================
    // FABRICA DE COMPONENTES
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
}