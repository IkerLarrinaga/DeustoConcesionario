package gui;

import java.awt.*;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import db.DataBaseManager;
import domain.Vehiculo;


public class VentanaCatalogo extends JFrame {
    private static final long serialVersionUID = 1L;
    private List<Vehiculo> listaVehiculosOriginal;
    private List<Vehiculo> listaVehiculos;
    private JPanel panelCatalogo;
    private DataBaseManager dbManager = new DataBaseManager();
    private JComboBox<String> comboModelo;

    public VentanaCatalogo(String marcaSeleccionada, String seleccion) {
        dbManager.conexion("resource/db/concesionario.db");
        listaVehiculosOriginal = dbManager.obtenerTodosVehiculo();
        listaVehiculos = new ArrayList<>(listaVehiculosOriginal);

        if (marcaSeleccionada != null && !marcaSeleccionada.isEmpty()) {
            listaVehiculos = filtrarPorMarca(marcaSeleccionada);
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
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTipo = new JLabel("Tipo de Vehículo:");
        labelTipo.setForeground(Color.WHITE);
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Todos", "Coche", "Furgoneta", "Moto"});
        gbc.gridx = 0; gbc.gridy = 0;
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
        JComboBox<String> comboCombustible = new JComboBox<>(new String[]{"Todos", "Gasolina", "Diesel", "Híbrido", "Eléctrico"});
        gbc.gridy = 7;
        panelFiltros.add(labelCombustible, gbc);
        gbc.gridy = 8;
        panelFiltros.add(comboCombustible, gbc);

        JCheckBox checkAutomatico = new JCheckBox("Automático");
        gbc.gridy = 9;
        panelFiltros.add(checkAutomatico, gbc);

        panelCatalogo = new JPanel(new GridLayout(0, 3, 10, 10));
        panelCatalogo.setBackground(colorPersonalizado);
        JScrollPane scrollPane = new JScrollPane(panelCatalogo);

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        panelPrincipal.add(panelFiltros, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        cargarVehiculosEnCatalogo();

        setVisible(true);
        dbManager.desconexion();
    }

    private List<Vehiculo> filtrarPorMarca(String marca) {
        List<Vehiculo> filtrados = new ArrayList<>();
        for (Vehiculo vehiculo : listaVehiculosOriginal) {
            if (vehiculo.getMarca().name().equalsIgnoreCase(marca)) {
                filtrados.add(vehiculo);
            }
        }
        return filtrados;
    }

    private void actualizarModelosDisponibles() {
        comboModelo.removeAllItems();
        comboModelo.addItem("Todos");
        Set<String> modelos = new HashSet<>();
        for (Vehiculo vehiculo : listaVehiculosOriginal) {
            modelos.add(vehiculo.getModelo());
        }
        for (String modelo : modelos) {
            comboModelo.addItem(modelo);
        }
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
