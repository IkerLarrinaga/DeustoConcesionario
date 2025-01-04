package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.DataBaseManager;
import domain.TipoCajaCambios;
import domain.TipoCombustible;
import domain.Vehiculo;

public class VentanaCatalogo extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Vehiculo> listaVehiculos;
    private JPanel panelCatalogo;
    private DataBaseManager dbManager = new DataBaseManager();

    public VentanaCatalogo(String marcaSeleccionada, String seleccion) {
    	
    	dbManager.conexion("resource/db/concesionario.db");

        if (marcaSeleccionada != null && !marcaSeleccionada.isEmpty()) {
            List<Vehiculo> listaFiltrada = new ArrayList<>();
            for (Vehiculo vehiculo : dbManager.obtenerTodosVehiculo()) {
                if (vehiculo.getMarca().equals(marcaSeleccionada)) {
                    listaFiltrada.add(vehiculo);
                }
            }
            listaVehiculos = listaFiltrada;
        }

        setTitle("Catálogo");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());

        Color colorPersonalizado = new Color(92, 184, 255);

        JButton botonCarrito = new JButton("Ver Carrito");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        JPanel panelBotones = new JPanel();
        panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelBotones.setBackground(colorPersonalizado);
        panelBotones.add(botonCarrito);
        panelBotones.add(botonCerrarSesion);

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBackground(colorPersonalizado);
        GridBagLayout gridBagLayout = new GridBagLayout();
        panelFiltros.setLayout(gridBagLayout);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel labelTipo = new JLabel("Tipo de Vehículo:");
        labelTipo.setForeground(Color.WHITE);
        JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Todos", "Coche", "Furgoneta", "Moto" });
        comboTipo.setMaximumSize(comboTipo.getPreferredSize());
        gbc.gridx = 0;
        gbc.gridy = 0;
        panelFiltros.add(labelTipo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        panelFiltros.add(comboTipo, gbc);

        JLabel labelModelo = new JLabel("Modelo:");
        labelModelo.setForeground(Color.WHITE);
        JComboBox<String> comboModelo = new JComboBox<>(new String[] { "Todos", "Sedan", "SUV", "Hatchback", "Coupe" });
        comboModelo.setMaximumSize(comboModelo.getPreferredSize());
        gbc.gridx = 0;
        gbc.gridy = 2;
        panelFiltros.add(labelModelo, gbc);
        gbc.gridx = 0;
        gbc.gridy = 3;
        panelFiltros.add(comboModelo, gbc);

        JLabel labelPrecio = new JLabel("Precio Máximo:");
        labelPrecio.setForeground(Color.WHITE);
        JSlider sliderPrecio = new JSlider(JSlider.HORIZONTAL, 0, 50000, 25000);
        sliderPrecio.setMajorTickSpacing(10000);
        sliderPrecio.setMinorTickSpacing(10000);
        sliderPrecio.setPaintTicks(true);
        JLabel labelPrecioValor = new JLabel("Valor máximo: " + sliderPrecio.getValue());
        labelPrecioValor.setFont(new Font("Arial", Font.ITALIC, 12));
        labelPrecioValor.setForeground(new Color(250, 250, 250));
        gbc.gridx = 0;
        gbc.gridy = 4;
        panelFiltros.add(labelPrecio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 5;
        panelFiltros.add(sliderPrecio, gbc);
        gbc.gridx = 0;
        gbc.gridy = 6;
        panelFiltros.add(labelPrecioValor, gbc);

        JLabel labelCombustible = new JLabel("Tipo de Combustible:");
        labelCombustible.setForeground(Color.WHITE);
        JComboBox<String> comboCombustible = new JComboBox<>(new String[] { "Todos", "Gasolina", "Diesel", "Híbrido", "Eléctrico" });
        comboCombustible.setMaximumSize(comboCombustible.getPreferredSize());
        gbc.gridx = 0;
        gbc.gridy = 7;
        panelFiltros.add(labelCombustible, gbc);
        gbc.gridx = 0;
        gbc.gridy = 8;
        panelFiltros.add(comboCombustible, gbc);

        JCheckBox checkAutomatico = new JCheckBox("Automático");
        gbc.gridx = 0;
        gbc.gridy = 9;
        panelFiltros.add(checkAutomatico, gbc);

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10));
        panelCatalogo.setBackground(colorPersonalizado);
        JScrollPane scrollPane = new JScrollPane(panelCatalogo);
        scrollPane.setPreferredSize(new Dimension(800, 500));

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelFiltros, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);

        cargarVehiculosEnCatalogo();

        botonCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new VentanaCarrito();
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaIncio();
                dispose();
            }
        });

        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });

        comboModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(comboTipo.getSelectedItem().toString(), comboModelo.getSelectedItem().toString(), sliderPrecio.getValue(),
                        checkAutomatico.isSelected(), comboCombustible.getSelectedItem().toString());
            }
        });

        sliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelPrecioValor.setText("Valor máximo: " + sliderPrecio.getValue());
                actualizarCatalogoConFiltros(comboTipo.getSelectedItem().toString(),
                        comboModelo.getSelectedItem().toString(),
                        sliderPrecio.getValue(),
                        checkAutomatico.isSelected(),
                        comboCombustible.getSelectedItem().toString());
            }
        });

        checkAutomatico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(comboTipo.getSelectedItem().toString(),
                        comboModelo.getSelectedItem().toString(),
                        sliderPrecio.getValue(),
                        checkAutomatico.isSelected(),
                        comboCombustible.getSelectedItem().toString());
            }
        });

        comboCombustible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(comboTipo.getSelectedItem().toString(),
                        comboModelo.getSelectedItem().toString(),
                        sliderPrecio.getValue(),
                        checkAutomatico.isSelected(),
                        comboCombustible.getSelectedItem().toString());
            }
        });
        
        setVisible(true);
        dbManager.desconexion();
    }

    private void cargarVehiculosEnCatalogo() {
        for (Vehiculo vehiculo : listaVehiculos) {
            JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
            botonVehiculo.setPreferredSize(new Dimension(200, 100));

            ImageIcon iconoVehiculo = getIconoPorTipo(vehiculo);
            botonVehiculo.setIcon(iconoVehiculo);

            botonVehiculo.addActionListener(e -> {
                String mensaje = "Marca: " + vehiculo.getMarca() + "\n" + "Modelo: " + vehiculo.getModelo() + "\n"
                        + "Precio: " + vehiculo.getPrecio() + "\n" + "Tipo: " + vehiculo.getTipo() + "\n"
                        + "Combustible: " + vehiculo.gettCombustible() + "\n" + "Caja de Cambios: "
                        + vehiculo.gettCajaCambios() + "\n" + "Número de plazas: " + vehiculo.getNumPlazas();
                Object[] opciones = { "Comprar", "Alquilar", "Cerrar" };

                int opcion = JOptionPane.showOptionDialog(this, mensaje, "Información del Vehículo",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[2]);
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(this, "Vehículo COMPRADO");
                } else if (opcion == 1) {
                    JOptionPane.showMessageDialog(this, "Vehículo ALQUILADO");
                } else {
                    JOptionPane.showMessageDialog(this, "Operación cancelada.");
                }
            });
            panelCatalogo.add(botonVehiculo);
        }
    }

    private ImageIcon getIconoPorTipo(Vehiculo vehiculo) {
        ImageIcon icon = null;
        if (vehiculo.getTipo().equals("COCHE")) {
            if (vehiculo.getPrecio() < 15000) {
                icon = new ImageIcon("resource/img/cocheGamaBaja.png");
            } else if (vehiculo.getPrecio() <= 30000) {
                icon = new ImageIcon("resource/img/cocheGamaEstandar.png");
            } else {
                icon = new ImageIcon("resource/img/cocheGamaAlta.png");
            }
        }
        if (icon != null) {
            Image img = icon.getImage();
            Image nuevaImagen = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(nuevaImagen);
        }
        return icon;
    }

    private void actualizarCatalogoConFiltros(String tipoSeleccionado, String modeloSeleccionado, int precioMaximo, boolean esAutomatico, String tipoCombustible) {
        panelCatalogo.removeAll();
        for (Vehiculo vehiculo : listaVehiculos) {
            boolean coincideTipo = tipoSeleccionado.equals("Todos") || vehiculo.getTipo().equalsIgnoreCase(tipoSeleccionado);
            boolean coincideModelo = modeloSeleccionado.equals("Todos") || vehiculo.getModelo().equalsIgnoreCase(modeloSeleccionado);
            boolean coincidePrecio = vehiculo.getPrecio() <= precioMaximo;
            boolean coincideTransmision = !esAutomatico || (vehiculo.gettCajaCambios() != null && vehiculo.gettCajaCambios() == TipoCajaCambios.AUTOMATICO);
            boolean coincideCombustible = tipoCombustible.equals("Todos") || (validarCombustible(tipoCombustible) && vehiculo.gettCombustible() == TipoCombustible.valueOf(tipoCombustible.toUpperCase()));

            if (coincideTipo && coincideModelo && coincidePrecio && coincideTransmision && coincideCombustible) {
                JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
                botonVehiculo.setPreferredSize(new Dimension(200, 100));
                ImageIcon iconoVehiculo = getIconoPorTipo(vehiculo);
                botonVehiculo.setIcon(iconoVehiculo);

                botonVehiculo.addActionListener(e -> {
                    String mensaje = "Marca: " + vehiculo.getMarca() + "\n" + "Modelo: " + vehiculo.getModelo() + "\n"
                            + "Precio: " + vehiculo.getPrecio() + "\n" + "Tipo: " + vehiculo.getTipo() + "\n"
                            + "Combustible: " + vehiculo.gettCombustible() + "\n" + "Caja de Cambios: "
                            + vehiculo.gettCajaCambios() + "\n" + "Número de plazas: " + vehiculo.getNumPlazas();
                    Object[] opciones = { "Comprar", "Alquilar", "Cerrar" };

                    int opcion = JOptionPane.showOptionDialog(this, mensaje, "Información del Vehículo",
                            JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[2]);
                    if (opcion == 0) {
                        JOptionPane.showMessageDialog(this, "Vehículo COMPRADO");
                    } else if (opcion == 1) {
                        JOptionPane.showMessageDialog(this, "Vehículo ALQUILADO");
                    } else {
                        JOptionPane.showMessageDialog(this, "Operación cancelada.");
                    }
                });
                panelCatalogo.add(botonVehiculo);
            }
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }

    private boolean validarCombustible(String tipoCombustible) {
        try {
            TipoCombustible.valueOf(tipoCombustible.toUpperCase());
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
    
}
