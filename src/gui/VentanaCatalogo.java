package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import domain.Gama;
import domain.Vehiculo;

public class VentanaCatalogo extends JFrame{

	private static final long serialVersionUID = 1L;
	private List<Vehiculo> listaVehiculos;
	private JPanel panelCatalogo;
	
	public VentanaCatalogo() {
		
		this.listaVehiculos = Vehiculo.cargarVehiculos("resource/data/vehiculos.txt");
		
		setTitle("Catálogo");
        setSize(900, 500);
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
        
        Thread hilo = new Thread(() -> {
			int contador = 0;
			try {
				while (contador <= 100) {
					barra.setValue(contador);
					Thread.sleep(10);
					contador++;
				}
				SwingUtilities.invokeLater(() -> {
					dialog.setVisible(false);
					dialog.dispose();
				});
			} catch (InterruptedException e) {
				e.printStackTrace();
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
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Todos","Coche", "Furgoneta", "Moto"});
        comboTipo.setMaximumSize(minimumSize());
        
        JLabel labelMarca = new JLabel("Marca:");
        JComboBox<String> comboMarca = new JComboBox<>(new String[]{"Todas", "Toyota", "Honda", 
        		"Ford", "Tesla","Chevrolet", "BMW", "Mercedes-Benz", "Renault", "Peugeot", "Fiat", 
        		"Yamaha", "Kawasaki", "Suzuki"});
        comboMarca.setMaximumSize(minimumSize());
        
        JLabel labelModelo = new JLabel("Modelo:");
        JComboBox<String> comboModelo = new JComboBox<>(new String[]{"Todos", "Sedan", "SUV", "Hatchback", "Coupe"}); //recorrer lista de todos los modelos
        comboModelo.setMaximumSize(minimumSize());
        
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
        JComboBox<String> comboCombustible = new JComboBox<>(new String[] {"Todos", "Gasolina", "Diesel", "Híbrido", "Eléctrico"});
        comboCombustible.setMaximumSize(minimumSize());
        
        //IAG ChatGPT
        //Se ha pedido que haga un menu con diferentes opciones de busqueda, después su ha modificado para nuestro proyecto
       
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
            }
        });
        
        setVisible(true);
	}
	
	private void cargarVehiculosEnCatalogo() {
        for (Vehiculo vehiculo : listaVehiculos) {
            JButton botonVehiculo = new JButton(vehiculo.getMarca() + " " + vehiculo.getModelo());
            botonVehiculo.setPreferredSize(new Dimension(200, 100));
            
            ImageIcon iconoVehiculo = getIconoPorTipo(vehiculo);
            botonVehiculo.setIcon(iconoVehiculo);
            
            botonVehiculo.addActionListener(e -> {
                String mensaje = "Marca: " + vehiculo.getMarca() + "\n" +
            	        "Modelo: " + vehiculo.getModelo() + "\n" +
            	        "Precio: " + vehiculo.getPrecio() + "\n" +
            	        "Gama: " + vehiculo.getGama() + "\n" +
            	        "Tipo: " + vehiculo.getTipo() + "\n" +
            	        "Año: " + vehiculo.getAnno() + "\n" +
            	        "Kilómetros: " + vehiculo.getKilometros() + " km\n" +
            	        "Combustible: " + vehiculo.gettCombustible() + "\n" +
            	        "Caja de Cambios: " + vehiculo.gettCajaCambios() + "\n" +
            	        "Potencia: " + vehiculo.getPotencia() + "CV\n" +
            	        "Número de plazas: " + vehiculo.getNumPlazas();

                Object[] opciones = {"Comprar", "Alquilar", "Cerrar"};

                int opcion = JOptionPane.showOptionDialog(
                    this, mensaje, "Información del Vehículo", JOptionPane.DEFAULT_OPTION, 
                    JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[2]           
                );

                if (opcion == 0) {
                    JOptionPane.showMessageDialog(this, "Vehículo COMPRADO");
                } else if(opcion == 1) {
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
        	if (vehiculo.getGama() != null) {
                if (vehiculo.getGama().equals(Gama.ALTA)) {
                    icon = new ImageIcon("resource/img/cocheGamaAlta.png");
                } else if (vehiculo.getGama().equals(Gama.ESTANDAR)) {
                    icon = new ImageIcon("resource/img/cocheGamaEstandar.png");
                } else {
                    icon = new ImageIcon("resource/img/cocheGamaBaja.png");
                }
            }
        } else if (vehiculo.getTipo().equals("MOTO")) {
        	if (vehiculo.getGama() != null) {
        		if (vehiculo.getGama() != null) {
                    if (vehiculo.getGama().equals(Gama.ALTA)) {
                        icon = new ImageIcon("resource/img/motoGamaAlta.png");
                    } else if (vehiculo.getGama().equals(Gama.ESTANDAR)) {
                        icon = new ImageIcon("resource/img/motoGamaEstandar.png");
                    } else {
                        icon = new ImageIcon("resource/img/motoGamaBaja.png");
                    }
                }
            }
        } else if (vehiculo.getTipo().equals("FURGONETA")) {
        	if (vehiculo.getGama() != null) {
        		if (vehiculo.getGama() != null) {
                    if (vehiculo.getGama().equals(Gama.ALTA)) {
                        icon = new ImageIcon("resource/img/furgonetaGamaAlta.png");
                    } else if (vehiculo.getGama().equals(Gama.ESTANDAR)) {
                        icon = new ImageIcon("resource/img/furgonetaGamaEstandar.png");
                    } else {
                        icon = new ImageIcon("resource/img/furgonetaGamaBaja.png");
                    }
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
}
