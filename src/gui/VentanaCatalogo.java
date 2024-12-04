package gui;

import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.Marca;
import domain.TipoCajaCambios;
import domain.TipoCombustible;
import domain.Vehiculo;

public class VentanaCatalogo extends JFrame {

    private static final long serialVersionUID = 1L;
    private List<Vehiculo> listaVehiculos;
    private JPanel panelCatalogo;
    
    public VentanaCatalogo() {    	
    	
        this.listaVehiculos = Vehiculo.cargarVehiculosDesdeBD("resource/db/concesionario.db");

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

        JCheckBox checkAutomatico = new JCheckBox("Automático");

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
        panelFiltros.add(checkAutomatico);
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
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });
        comboMarca.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });
        comboModelo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });
        sliderPrecio.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                labelPrecioValor.setText("Valor máximo: " + sliderPrecio.getValue());
                String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });
        
        checkAutomatico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });
        
        comboCombustible.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tipoSeleccionado = (String) comboTipo.getSelectedItem();
                String marcaSeleccionada = (String) comboMarca.getSelectedItem();
                String modeloSeleccionado = (String) comboModelo.getSelectedItem();
                int precioMaximo = sliderPrecio.getValue();
                boolean esAutomatico = checkAutomatico.isSelected();
                String tipoCombustible = (String) comboCombustible.getSelectedItem();
                
                actualizarCatalogoConFiltros(tipoSeleccionado, marcaSeleccionada, modeloSeleccionado, precioMaximo, esAutomatico, tipoCombustible);
            }
        });

        setVisible(true);
    }

    private void cargarVehiculosEnCatalogo() {   	
    	
    	VentanaCarrito ventanaCarrito = new VentanaCarrito();
    	ventanaCarrito.setVisible(false);
    	
        for (Vehiculo vehiculo : listaVehiculos) {
            JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
            botonVehiculo.setPreferredSize(new Dimension(200, 100));
            
            ImageIcon iconoVehiculo = getIconoPorTipo(vehiculo);
            botonVehiculo.setIcon(iconoVehiculo);
            
            botonVehiculo.addActionListener(e -> {
                String mensaje = "Marca: " + vehiculo.getMarca() + "\n" +
            	        "Modelo: " + vehiculo.getModelo() + "\n" +
            	        "Precio: " + vehiculo.getPrecio() + "\n" +
            	        "Tipo: " + vehiculo.getTipo() + "\n" +
            	        "Combustible: " + vehiculo.gettCombustible() + "\n" +
            	        "Caja de Cambios: " + vehiculo.gettCajaCambios() + "\n" +
            	        "Número de plazas: " + vehiculo.getNumPlazas();
                Object[] opciones = {"Comprar", "Alquilar", "Cerrar"};
                
                String modelo = vehiculo.getModelo();
                String matricula = vehiculo.getMatricula();

                LocalDate fechaInicio = LocalDate.now();
                LocalDate fechaFin = fechaInicio.plusDays(7);
                
                int opcion = JOptionPane.showOptionDialog(
                    this, mensaje, "Información del Vehículo", JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[2]           
                );
                if (opcion == 0) {
                    JOptionPane.showMessageDialog(this, "Vehículo COMPRADO");
                } else if(opcion == 1) {
                	JOptionPane.showMessageDialog(this, "Vehículo ALQUILADO");
                    ventanaCarrito.agregarVehiculo(modelo, matricula, fechaInicio, fechaFin);
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
            if (vehiculo.getPrecio() > 0) {
                if (vehiculo.getPrecio() < 15000) {
                    icon = new ImageIcon("resource/img/cocheGamaBaja.png");
                } else if (vehiculo.getPrecio() <= 30000) {
                    icon = new ImageIcon("resource/img/cocheGamaEstandar.png");
                } else {
                    icon = new ImageIcon("resource/img/cocheGamaAlta.png");
                }
            }
        } else if (vehiculo.getTipo().equals("MOTO")) {
            if (vehiculo.getPrecio() > 0) {
                if (vehiculo.getPrecio() < 15000) {
                    icon = new ImageIcon("resource/img/motoGamaBaja.png");
                } else if (vehiculo.getPrecio() <= 30000) {
                    icon = new ImageIcon("resource/img/motoGamaBaja.png");
                } else {
                    icon = new ImageIcon("resource/img/motoGamaBaja.png");
                }
            }
        } else if (vehiculo.getTipo().equals("FURGONETA")) {
            if (vehiculo.getPrecio() > 0) {
                if (vehiculo.getPrecio() < 15000) {
                    icon = new ImageIcon("resource/img/furgonetaGamaBaja.png");
                } else if (vehiculo.getPrecio() <= 30000) {
                    icon = new ImageIcon("resource/img/furgonetaGamaBaja.png");
                } else {
                    icon = new ImageIcon("resource/img/furgonetaGamaBaja.png");
                }
            }
        }

        if (icon != null) {
            Image img = icon.getImage();
            Image nuevaImagen = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            icon = new ImageIcon(nuevaImagen);
        }

        return icon;
    }


    private void actualizarCatalogoConFiltros(
    	    String tipoSeleccionado, 
    	    String marcaSeleccionada, 
    	    String modeloSeleccionado, 
    	    int precioMaximo, 
    	    boolean esAutomatico, 
    	    String tipoCombustible
    	) {
    	    panelCatalogo.removeAll();
    	    
    	    for (Vehiculo vehiculo : listaVehiculos) {
    	        boolean coincideTipo = tipoSeleccionado.equals("Todos") || vehiculo.getTipo().equalsIgnoreCase(tipoSeleccionado);
    	        boolean coincideMarca = marcaSeleccionada.equals("Todas") || vehiculo.getMarca() == Marca.valueOf(marcaSeleccionada.toUpperCase());
    	        boolean coincideModelo = modeloSeleccionado.equals("Todos") || vehiculo.getModelo().equalsIgnoreCase(modeloSeleccionado);
    	        boolean coincidePrecio = vehiculo.getPrecio() <= precioMaximo;
    	        boolean coincideTransmision = !esAutomatico || vehiculo.gettCajaCambios() == TipoCajaCambios.valueOf("AUTOMATICO");
    	        boolean coincideCombustible = tipoCombustible.equals("Todos") || vehiculo.gettCombustible() == TipoCombustible.valueOf(tipoCombustible);

    	        if (coincideTipo && coincideMarca && coincideModelo && coincidePrecio && coincideTransmision && coincideCombustible) {
    	            JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
    	            botonVehiculo.setPreferredSize(new Dimension(200, 100));
    	            ImageIcon iconoVehiculo = getIconoPorTipo(vehiculo);
    	            botonVehiculo.setIcon(iconoVehiculo);

    	            botonVehiculo.addActionListener(e -> {
    	                String mensaje = "Marca: " + vehiculo.getMarca() + "\n" +
    	                        "Modelo: " + vehiculo.getModelo() + "\n" +
    	                        "Precio: " + vehiculo.getPrecio() + "\n" +
    	                        "Tipo: " + vehiculo.getTipo() + "\n" +
    	                        "Combustible: " + vehiculo.gettCombustible() + "\n" +
    	                        "Caja de Cambios: " + vehiculo.gettCajaCambios() + "\n" +
    	                        "Número de plazas: " + vehiculo.getNumPlazas();
    	                Object[] opciones = {"Alquilar", "Cerrar"};
    	                int opcion = JOptionPane.showOptionDialog(
    	                    this, mensaje, "Información del Vehículo", JOptionPane.DEFAULT_OPTION,
    	                    JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[1]
    	                );
    	                if (opcion == 0) {
    	                    JOptionPane.showMessageDialog(this, "Vehículo ALQUILADO");
    	                } else if (opcion == 1) {
    	                    JOptionPane.showMessageDialog(this, "Operación cancelada.");
    	                }
    	            });
    	            panelCatalogo.add(botonVehiculo);
    	        }
    	    }
    	    panelCatalogo.revalidate();
    	    panelCatalogo.repaint();
    	}

}
