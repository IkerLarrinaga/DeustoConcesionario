package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
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
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import domain.Vehiculo;

public class VentanaCatalogo extends JFrame{
	
	private List<Vehiculo> listaVehiculos;
	
	public VentanaCatalogo() {
		
		this.listaVehiculos = Vehiculo.cargarVehiculos("vehiculos.txt");
		
		setTitle("Catálogo");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton botonCarrito = new JButton("Ver Carrito");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
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
        
        JPanel panelCatalogo = new JPanel();
        panelCatalogo.setLayout(new GridLayout(0, 3, 10, 10)); // 3 filas, 3 columnas, espacio de 10 entre cada botón

        // Añadir 9 coches como botones
        for (int i = 1; i <= 15; i++) {
            JButton botonCoche = new JButton("Coche " + i);
            botonCoche.setPreferredSize(new Dimension(200, 150)); // Tamaño fijo para los botones
            panelCatalogo.add(botonCoche); // Agregar el botón al panel
        }

        JScrollPane scrollPane = new JScrollPane(panelCatalogo);
        scrollPane.setPreferredSize(new Dimension(800, 500)); // Establecer el tamaño del JScrollPane

        // Crear panel de filtros a la izquierda
        JPanel panelFiltros = new JPanel();
        panelFiltros.setLayout(new BoxLayout(panelFiltros, BoxLayout.Y_AXIS));
        panelFiltros.setPreferredSize(new Dimension(200, 600)); // Ancho fijo para el panel de filtros

        // Filtros de búsqueda
        JLabel labelMarca = new JLabel("Marca:");
        JComboBox<String> comboMarca = new JComboBox<>(new String[]{"Todas", "Toyota", "Honda", 
        		"Ford", "Tesla","Chevrolet", "BMW", "Mercedes-Benz", "Renault", "Peugeot", "Fiat", 
        		"Yamaha", "Kawasaki", "Suzuki"}); //recorrer lista de todas las marcas
        JLabel labelModelo = new JLabel("Modelo:");
        JComboBox<String> comboModelo = new JComboBox<>(new String[]{"Todos", "Sedan", "SUV", "Hatchback", "Coupe"}); //recorrer lista de todos los modelos
        JLabel labelPrecio = new JLabel("Precio máximo:");
        JTextField textPrecio = new JTextField(10);
        JLabel labelAño = new JLabel("Año de fabricación:");
        JTextField textAño = new JTextField(10);
        JCheckBox checkNuevo = new JCheckBox("Solo coches nuevos");
        JCheckBox checkUsado = new JCheckBox("Solo coches usados");

        // Añadir filtros al panel
        panelFiltros.add(labelMarca);
        panelFiltros.add(comboMarca);
        panelFiltros.add(labelModelo);
        panelFiltros.add(comboModelo);
        panelFiltros.add(labelPrecio);
        panelFiltros.add(textPrecio);
        panelFiltros.add(labelAño);
        panelFiltros.add(textAño);
        panelFiltros.add(checkNuevo);
        panelFiltros.add(checkUsado);

        // Crear un panel para contener los filtros y el catálogo
        JPanel panelPrincipal = new JPanel();
        panelPrincipal.setLayout(new BorderLayout());
        panelPrincipal.add(panelFiltros, BorderLayout.WEST); // Añadir los filtros a la izquierda
        panelPrincipal.add(scrollPane, BorderLayout.CENTER); // Añadir el catálogo a la derecha

        // Añadir el panel principal al contenedor de la ventana
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
        
        setVisible(true);
	}
	
	
}
