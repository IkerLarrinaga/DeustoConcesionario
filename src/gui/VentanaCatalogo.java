package gui;

import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.Gama;
import domain.Vehiculo;

public class VentanaCatalogo extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Vehiculo> listaVehiculos;
    private JPanel panelCatalogo;

    public VentanaCatalogo() {
        this.listaVehiculos = Vehiculo.cargarVehiculos("resource/data/vehiculos.txt");

        setTitle("Catálogo");
        setSize(950, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Color colorPersonalizado = new Color(92, 184, 255);

        JButton botonCarrito = new JButton("Ver Carrito");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setBackground(colorPersonalizado);
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(botonCarrito);
        panel.add(botonCerrarSesion);

        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(0);
        barra.setString("Cargando catálogo, por favor espere...");
        barra.setStringPainted(true);

        JOptionPane pane = new JOptionPane(barra, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = pane.createDialog(null, "Cargando");

        Thread hilo = new Thread(new Runnable() {
            @Override
            public void run() {
                int contador = 0;
                try {
                    while (contador <= 100) {
                        barra.setValue(contador);
                        Thread.sleep(10);
                        contador++;
                    }
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            dialog.setVisible(false);
                            dialog.dispose();
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        hilo.start();
        dialog.setVisible(true);

        panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10));
        panelCatalogo.setBackground(colorPersonalizado);
        JScrollPane scrollPane = new JScrollPane(panelCatalogo);
        scrollPane.setPreferredSize(new Dimension(800, 500));
        add(scrollPane, BorderLayout.CENTER);

        cargarVehiculosEnCatalogo();

        JPanel panelFiltros = new JPanel();
        panelFiltros.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelFiltros.setBackground(colorPersonalizado);
        panelFiltros.setLayout(new BoxLayout(panelFiltros, BoxLayout.Y_AXIS));
        panelFiltros.setPreferredSize(new Dimension(200, 600));

        JLabel labelTipo = new JLabel("Tipo de Vehículo");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Todos", "Coche", "Furgoneta", "Moto"});
        comboTipo.setMaximumSize(comboTipo.getPreferredSize());

        JLabel labelMarca = new JLabel("Marca:");
        JComboBox<String> comboMarca = new JComboBox<>(new String[]{"Todas", "Toyota", "Honda", "Ford", "Tesla", "Chevrolet", "BMW", "Mercedes-Benz", "Renault", "Peugeot", "Fiat", "Yamaha", "Kawasaki", "Suzuki"});
        comboMarca.setMaximumSize(comboMarca.getPreferredSize());

        JLabel labelModelo = new JLabel("Modelo:");
        JComboBox<String> comboModelo = new JComboBox<>(new String[]{"Todos", "Sedan", "SUV", "Hatchback", "Coupe"});
        comboModelo.setMaximumSize(comboModelo.getPreferredSize());

        JSlider sliderPrecio = new JSlider(JSlider.HORIZONTAL, 0, 100000, 50000);
        sliderPrecio.setMajorTickSpacing(10000);
        sliderPrecio.setMinorTickSpacing(10000);
        sliderPrecio.setPaintTicks(true);
        JLabel labelPrecioValor = new JLabel("Valor máximo: " + sliderPrecio.getValue());

        JLabel labelAño = new JLabel("Año de fabricación:");
        JTextField textAño = new JTextField(10);
        textAño.setMaximumSize(new Dimension(60, 30));
        JCheckBox checkNuevo = new JCheckBox("Automático");

        JLabel labelCombustible = new JLabel("Tipo de Combustible:");
        JComboBox<String> comboCombustible = new JComboBox<>(new String[]{"Todos", "Gasolina", "Diesel", "Híbrido", "Eléctrico"});
        comboCombustible.setMaximumSize(comboCombustible.getPreferredSize());

        panelFiltros.add(labelTipo);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(comboTipo);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(labelMarca);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(comboMarca);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(labelModelo);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(comboModelo);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(labelPrecioValor);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(sliderPrecio);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(labelAño);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(textAño);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(checkNuevo);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(labelCombustible);
        panelFiltros.add(Box.createVerticalStrut(10));
        panelFiltros.add(comboCombustible);

        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelFiltros, BorderLayout.WEST);
        panelPrincipal.add(scrollPane, BorderLayout.CENTER);

        getContentPane().add(panelPrincipal);

        botonCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al Carrito");
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

        sliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelPrecioValor.setText("Valor máximo: " + sliderPrecio.getValue());
                actualizarCatalogoConFiltros(
                        (String) comboTipo.getSelectedItem(),
                        (String) comboMarca.getSelectedItem(),
                        (String) comboModelo.getSelectedItem(),
                        sliderPrecio.getValue(),
                        textAño.getText().trim()
                );
            }
        });

        comboTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(
                        (String) comboTipo.getSelectedItem(),
                        (String) comboMarca.getSelectedItem(),
                        (String) comboModelo.getSelectedItem(),
                        sliderPrecio.getValue(),
                        textAño.getText().trim()
                );
            }
        });

        comboMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(
                        (String) comboTipo.getSelectedItem(),
                        (String) comboMarca.getSelectedItem(),
                        (String) comboModelo.getSelectedItem(),
                        sliderPrecio.getValue(),
                        textAño.getText().trim()
                );
            }
        });

        comboModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(
                        (String) comboTipo.getSelectedItem(),
                        (String) comboMarca.getSelectedItem(),
                        (String) comboModelo.getSelectedItem(),
                        sliderPrecio.getValue(),
                        textAño.getText().trim()
                );
            }
        });

        textAño.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarCatalogoConFiltros(
                        (String) comboTipo.getSelectedItem(),
                        (String) comboMarca.getSelectedItem(),
                        (String) comboModelo.getSelectedItem(),
                        sliderPrecio.getValue(),
                        textAño.getText().trim()
                );
            }
        });

        setVisible(true);
    }

    private void cargarVehiculosEnCatalogo() {
        for (Vehiculo vehiculo : listaVehiculos) {
            JLabel etiquetaVehiculo = new JLabel(vehiculo.getNombre());
            panelCatalogo.add(etiquetaVehiculo);
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }

    private void actualizarCatalogoConFiltros(String tipo, String marca, String modelo, int precio, String anio) {
        panelCatalogo.removeAll();
        for (Vehiculo vehiculo : listaVehiculos) {
            if (vehiculo.coincideConFiltros(tipo, marca, modelo, precio, anio)) {
                JLabel etiquetaVehiculo = new JLabel(vehiculo.getNombre());
                panelCatalogo.add(etiquetaVehiculo);
            }
        }
        panelCatalogo.revalidate();
        panelCatalogo.repaint();
    }
}
