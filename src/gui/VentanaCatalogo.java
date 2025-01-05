package gui;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import db.DataBaseManager;
import domain.*;

public class VentanaCatalogo extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Vehiculo> listaVehiculos;
    private JPanel panelCatalogo;
    private DataBaseManager dbManager = new DataBaseManager();
    private JComboBox<String> comboModelo;
    private String marcaSeleccionada;

    public VentanaCatalogo(String marcaSeleccionada, String seleccion) {
        this.marcaSeleccionada = marcaSeleccionada;
        dbManager.conexion("resource/db/concesionario.db");

        listaVehiculos = dbManager.obtenerTodosVehiculo();

        if (marcaSeleccionada != null && !marcaSeleccionada.isEmpty()) {
            listaVehiculos = filtrarPorMarca(listaVehiculos, marcaSeleccionada);
        }

        setTitle("Catálogo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());

        Color colorPersonalizado = new Color(92, 184, 255);

        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        JButton botonAtras = new JButton("Atrás");
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBotones.setBackground(colorPersonalizado);
        panelBotones.setLayout(new GridLayout(0, 3, 10, 10));
        panelBotones.add(botonAtras);
        panelBotones.add(botonCerrarSesion);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBackground(colorPersonalizado);
        panelFiltros.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTipo = new JLabel("Tipo de Vehículo:");
        labelTipo.setForeground(Color.WHITE);
        JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Todos", "Coche", "Furgoneta", "Moto" });
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFiltros.add(labelTipo, gbc);
        gbc.gridy = 1;
        panelFiltros.add(comboTipo, gbc);

        JLabel labelModelo = new JLabel("Modelo:");
        labelModelo.setForeground(Color.WHITE);
        comboModelo = new JComboBox<>();
        actualizarModelosDisponibles();
        gbc.gridy = 2;
        panelFiltros.add(labelModelo, gbc);
        gbc.gridy = 3;
        panelFiltros.add(comboModelo, gbc);

        JLabel labelPrecio = new JLabel("Precio Máximo:");
        labelPrecio.setForeground(Color.WHITE);
        JSlider sliderPrecio = new JSlider(JSlider.HORIZONTAL, 0, 50000, 25000);
        sliderPrecio.setMajorTickSpacing(10000);
        sliderPrecio.setMinorTickSpacing(5000);
        sliderPrecio.setPaintTicks(true);
        JLabel labelPrecioValor = new JLabel("Valor máximo: " + sliderPrecio.getValue());
        labelPrecioValor.setForeground(Color.WHITE);
        gbc.gridy = 4;
        panelFiltros.add(labelPrecio, gbc);
        gbc.gridy = 5;
        panelFiltros.add(sliderPrecio, gbc);
        gbc.gridy = 6;
        panelFiltros.add(labelPrecioValor, gbc);

        JLabel labelCombustible = new JLabel("Tipo de Combustible:");
        labelCombustible.setForeground(Color.WHITE);
        JComboBox<String> comboCombustible = new JComboBox<>(new String[] { "Todos", "Gasolina", "Diesel", "Híbrido", "Eléctrico" });
        gbc.gridy = 7;
        panelFiltros.add(labelCombustible, gbc);
        gbc.gridy = 8;
        panelFiltros.add(comboCombustible, gbc);

        JCheckBox checkAutomatico = new JCheckBox("Automático");
        gbc.gridy = 9;
        panelFiltros.add(checkAutomatico, gbc);

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10));
        panelCatalogo.setBackground(colorPersonalizado);
        JScrollPane scrollPane = new JScrollPane(panelCatalogo);
        scrollPane.setPreferredSize(new Dimension(800, 500));

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFiltros, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        cargarVehiculosEnCatalogo();

        botonCerrarSesion.addActionListener(e -> {
            new VentanaIncio();
            dispose();
        });

        botonAtras.addActionListener(e -> {
            new VentanaMarcas();
            dispose();
        });

        comboTipo.addActionListener(e -> {
            actualizarModelosDisponibles();
            aplicarFiltros(comboTipo, comboModelo, sliderPrecio, comboCombustible, checkAutomatico);
        });
        comboModelo.addActionListener(e -> aplicarFiltros(comboTipo, comboModelo, sliderPrecio, comboCombustible, checkAutomatico));
        sliderPrecio.addChangeListener(e -> {
            labelPrecioValor.setText("Valor máximo: " + sliderPrecio.getValue());
            aplicarFiltros(comboTipo, comboModelo, sliderPrecio, comboCombustible, checkAutomatico);
        });
        comboCombustible.addActionListener(e -> aplicarFiltros(comboTipo, comboModelo, sliderPrecio, comboCombustible, checkAutomatico));
        checkAutomatico.addActionListener(e -> aplicarFiltros(comboTipo, comboModelo, sliderPrecio, comboCombustible, checkAutomatico));

        setVisible(true);
        dbManager.desconexion();
    }

    private void aplicarFiltros(JComboBox<String> comboTipo, JComboBox<String> comboModelo, JSlider sliderPrecio,
                                 JComboBox<String> comboCombustible, JCheckBox checkAutomatico) {
        List<Vehiculo> vehiculosFiltrados = listaVehiculos;

        String tipoSeleccionado = (String) comboTipo.getSelectedItem();
        if (!"Todos".equals(tipoSeleccionado)) {
            vehiculosFiltrados = filtrarPorTipo(vehiculosFiltrados, tipoSeleccionado);
        }

        String modeloSeleccionado = (String) comboModelo.getSelectedItem();
        if (!"Todos".equals(modeloSeleccionado)) {
            vehiculosFiltrados = filtrarPorModelo(vehiculosFiltrados, modeloSeleccionado);
        }

        int precioMaximo = sliderPrecio.getValue();
        vehiculosFiltrados = filtrarPorPrecio(vehiculosFiltrados, precioMaximo);

        String combustibleSeleccionado = (String) comboCombustible.getSelectedItem();
        if (!"Todos".equals(combustibleSeleccionado)) {
            vehiculosFiltrados = filtrarPorCombustible(vehiculosFiltrados, combustibleSeleccionado);
        }

        if (checkAutomatico.isSelected()) {
            vehiculosFiltrados = filtrarPorAutomatico(vehiculosFiltrados);
        }

        listaVehiculos = vehiculosFiltrados;
        cargarVehiculosEnCatalogo();
    }

    private void actualizarModelosDisponibles() {
        comboModelo.removeAllItems();
        comboModelo.addItem("Todos");

        String tipoSeleccionado = (String) comboModelo.getSelectedItem();
        Set<String> modelos = new HashSet<>();

        for (Vehiculo vehiculo : listaVehiculos) {
            if ("Todos".equals(tipoSeleccionado) || vehiculo.getTipo().equalsIgnoreCase(tipoSeleccionado)) {
                modelos.add(vehiculo.getModelo());
            }
        }

        for (String modelo : modelos) {
            comboModelo.addItem(modelo);
        }
    }

    private List<Vehiculo> filtrarPorMarca(List<Vehiculo> vehiculos, String marca) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getMarca().name().equalsIgnoreCase(marca)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private List<Vehiculo> filtrarPorTipo(List<Vehiculo> vehiculos, String tipo) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getTipo().equalsIgnoreCase(tipo)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private List<Vehiculo> filtrarPorModelo(List<Vehiculo> vehiculos, String modelo) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getModelo().equalsIgnoreCase(modelo)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private List<Vehiculo> filtrarPorPrecio(List<Vehiculo> vehiculos, int precioMaximo) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.getPrecio() <= precioMaximo) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private List<Vehiculo> filtrarPorCombustible(List<Vehiculo> vehiculos, String combustible) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.gettCombustible().name().equalsIgnoreCase(combustible)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private List<Vehiculo> filtrarPorAutomatico(List<Vehiculo> vehiculos) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : vehiculos) {
            if (vehiculo.gettCajaCambios().equals(TipoCajaCambios.AUTOMATICO)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private void cargarVehiculosEnCatalogo() {
        panelCatalogo.removeAll();
        for (Vehiculo vehiculo : listaVehiculos) {
            JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
            botonVehiculo.setEnabled(!vehiculo.isAlquilado());
            panelCatalogo.add(botonVehiculo);
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
}
