package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import db.DataBaseManager;
import domain.Marca;
import domain.Vehiculo;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class VentanaMarcas extends JFrame {

	private Color colorPersonalizado = new Color(92, 184, 255);
	private JPanel panelImagenes;
	private DataBaseManager dbManager = new DataBaseManager();

	private static final long serialVersionUID = 1L;

	public VentanaMarcas() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Marcas");
		setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
		setLayout(new BorderLayout());

		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new BorderLayout());
		add(panelSuperior, BorderLayout.NORTH);

		JTextField buscador = new JTextField("Buscar marca...");
		buscador.setPreferredSize(new Dimension(200, 30));

		buscador.setForeground(Color.LIGHT_GRAY);
		buscador.setFont(new Font("Arial", Font.ITALIC, 14));

		buscador.addFocusListener(new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				if (buscador.getText().equals("Buscar marca...")) {
					buscador.setText("");
					buscador.setForeground(Color.BLACK);
					buscador.setFont(new Font("Arial", Font.PLAIN, 14));
				}
			}

			@Override
			public void focusLost(FocusEvent e) {
				if (buscador.getText().isEmpty()) {
					buscador.setText("Buscar marca...");
					buscador.setForeground(Color.LIGHT_GRAY);
					buscador.setFont(new Font("Arial", Font.ITALIC, 14));
				}
			}
		});

		panelSuperior.add(buscador, BorderLayout.CENTER);

		JButton botonCerrarSesion = new JButton("Cerrar Sesión");
		botonCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaIncio();
				dispose();
			}
		});

		JPanel panelCerrarSesion = new JPanel();
		panelCerrarSesion.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panelCerrarSesion.add(botonCerrarSesion);
		panelSuperior.add(panelCerrarSesion, BorderLayout.EAST);

		String[] opciones = { "Todas", "Vehiculo", "Moto", "Furgoneta" };
		JComboBox<String> comboBox = new JComboBox<>(opciones);
		JPanel panelOpcion = new JPanel();
		panelOpcion.add(comboBox);
		panelSuperior.add(panelOpcion, BorderLayout.WEST);

		panelImagenes = new JPanel();
		panelImagenes.setLayout(new GridLayout(0, 3, 10, 10));
		panelImagenes.setBackground(colorPersonalizado);
		JScrollPane scrollPanel = new JScrollPane(panelImagenes);
		scrollPanel.setBackground(colorPersonalizado);
		add(scrollPanel, BorderLayout.CENTER);

		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualizarPanelImagenes((String) comboBox.getSelectedItem(), buscador.getText());
			}
		};
		comboBox.addActionListener(listener);

		buscador.getDocument().addDocumentListener(new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				actualizarPanelImagenes((String) comboBox.getSelectedItem(), buscador.getText());
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				actualizarPanelImagenes((String) comboBox.getSelectedItem(), buscador.getText());
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				actualizarPanelImagenes((String) comboBox.getSelectedItem(), buscador.getText());
			}
		});

		configurarBoton(botonCerrarSesion, new Color(255, 80, 80), new Color(255, 10, 30));

		listener.actionPerformed(null);

		setVisible(true);
	}

	private void configurarBoton(JButton boton, Color colorAntes, Color colorDespues) {
		boton.setBackground(colorAntes);
		boton.setForeground(Color.WHITE);
		boton.setFocusable(false);
		boton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				boton.setBackground(colorDespues);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				boton.setBackground(colorAntes);
			}
		});
	}

	private void actualizarPanelImagenes(String seleccion, String textoBusqueda) {
		panelImagenes.removeAll();
		dbManager.conexion("resource/db/concesionario.db");

		List<Vehiculo> listaVehiculos = new ArrayList<>();

		if (seleccion.equals("Todas")) {
			listaVehiculos.addAll(dbManager.obtenerTodosCoches());
			listaVehiculos.addAll(dbManager.obtenerTodasMotos());
			listaVehiculos.addAll(dbManager.obtenerTodasFurgonetas());
		} else if (seleccion.equals("Vehiculo")) {
			listaVehiculos = new ArrayList<>(dbManager.obtenerTodosCoches());
		} else if (seleccion.equals("Moto")) {
			listaVehiculos = new ArrayList<>(dbManager.obtenerTodasMotos());
		} else if (seleccion.equals("Furgoneta")) {
			listaVehiculos = new ArrayList<>(dbManager.obtenerTodasFurgonetas());
		}

		if (textoBusqueda.equals("Buscar marca...")) {
			textoBusqueda = "";
		}

		Set<Marca> marcasAnnadidas = new HashSet<>();

		for (Vehiculo vehiculo : listaVehiculos) {
			Marca marca = vehiculo.getMarca();
			if (marca != null && !marcasAnnadidas.contains(marca)
					&& marca.name().toLowerCase().contains(textoBusqueda.toLowerCase())) {
				marcasAnnadidas.add(marca);
				agregarImagenMarca(panelImagenes, marca, textoBusqueda, seleccion);
			}
		}

		panelImagenes.revalidate();
		panelImagenes.repaint();
		dbManager.desconexion();
	}

	private void agregarImagenMarca(JPanel panelImagenes, Marca marca, String textoBusqueda, String seleccion) {
		String nombreMarca = marca.name();
		String rutaImagen = "resource/img/" + nombreMarca + ".png";
		File archivo = new File(rutaImagen);

		ImageIcon icono;
		if (archivo.exists()) {
			icono = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		} else {
			icono = new ImageIcon(
					new ImageIcon("car-icon.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
		}

		String nombreMarcaResaltado = nombreMarca;
		if (!textoBusqueda.isEmpty()) {
			String lowerCaseBusqueda = textoBusqueda.toLowerCase();
			String lowerCaseMarca = nombreMarca.toLowerCase();
			int index = lowerCaseMarca.indexOf(lowerCaseBusqueda);

			if (index != -1) {
				nombreMarcaResaltado = nombreMarca.substring(0, index) + "<span style='background-color: yellow;'>"
						+ nombreMarca.substring(index, index + textoBusqueda.length()) + "</span>"
						+ nombreMarca.substring(index + textoBusqueda.length());
			}
		}

		JPanel panelMarca = new JPanel();
		panelMarca.setLayout(new BoxLayout(panelMarca, BoxLayout.Y_AXIS));
		panelMarca.setPreferredSize(new Dimension(100, 150));
		panelMarca.setBackground(colorPersonalizado);
		
		// IAG ChatGPT
		// Se ha pedido a ChatGPT que nos haga un programa en el que ponga una sombra
		// detrás de un panel, después se ha adaptado a nuestro programa
		panelMarca.setBorder(
				BorderFactory.createCompoundBorder(BorderFactory.createMatteBorder(0, 0, 5, 5, new Color(0, 0, 0, 100)),
						BorderFactory.createLineBorder(Color.WHITE, 2)));

		JLabel imagenLabel = new JLabel(icono);
		imagenLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelMarca.add(imagenLabel);

		JLabel nombreLabel = new JLabel("<html>" + nombreMarcaResaltado + "</html>", JLabel.CENTER);
		nombreLabel.setForeground(Color.WHITE);
		nombreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelMarca.add(nombreLabel);

		JButton botonSeleccionar = new JButton("Seleccionar");
		botonSeleccionar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonSeleccionar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaCatalogo(nombreMarca);
			}
		});
		panelMarca.add(botonSeleccionar);

		panelImagenes.add(panelMarca);
	}
}
