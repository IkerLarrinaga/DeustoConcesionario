package gui;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.util.List;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import db.DataBaseManager;
import domain.Cliente;
import domain.Coche;
import domain.Furgoneta;
import domain.Moto;
import domain.TipoCajaCambios;
import domain.TipoCombustible;
import domain.Vehiculo;

@SuppressWarnings("unused")
public class VentanaCatalogo extends JFrame {
	private static final long serialVersionUID = 1L;
	private Cliente cliente; 
	private List<Vehiculo> listaVehiculos;
	private JPanel panelCatalogo;
	private DataBaseManager dbManager = new DataBaseManager();
	private List<Vehiculo> listaVehiculosOriginal;
	private JComboBox<String> comboModelo;

	public VentanaCatalogo(String marcaSeleccionada, Cliente cliente) {
		this.cliente = cliente;
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
		configurarBoton(botonAtras, new Color(25, 130, 215), new Color(15, 80, 190));
		configurarBoton(botonCerrarSesion, new Color(255, 80, 80), new Color(255, 10, 30));

		JPanel panelBotones = new JPanel();
		FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER);
		panelBotones.setLayout(flowLayout);
		panelBotones.setBorder(new EmptyBorder(10, 10, 10, 10));
		panelBotones.setBackground(colorPersonalizado);
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
		JComboBox<String> comboTipo = new JComboBox<>(new String[] { "Todos", "Coche", "Furgoneta", "Moto" });
		gbc.gridx = 0;
		gbc.gridy = 0;
		panelFiltros.add(labelTipo, gbc);
		gbc.gridy = 1;
		panelFiltros.add(comboTipo, gbc);

		JLabel labelModelo = new JLabel("Modelo:");
		labelModelo.setForeground(Color.WHITE);
		comboModelo = new JComboBox<>();
		comboModelo.addItem("Todos");
		for (Vehiculo vehiculo : filtrarPorMarca(marcaSeleccionada)) {
			if (!modeloYaAgregado(comboModelo, vehiculo.getModelo())) {
				comboModelo.addItem(vehiculo.getModelo());
			}
		}
		comboModelo.setSelectedIndex(0);
		gbc.gridy = 2;
		panelFiltros.add(labelModelo, gbc);
		gbc.gridy = 3;
		panelFiltros.add(comboModelo, gbc);

		JLabel labelPrecio = new JLabel("Precio Máximo:");
		labelPrecio.setForeground(Color.WHITE);
		JSlider sliderPrecio = new JSlider(JSlider.HORIZONTAL, 0, 150, 150);
		sliderPrecio.setMajorTickSpacing(15);
		sliderPrecio.setPaintTicks(true);
		JLabel labelPrecioValor = new JLabel("Valor máximo: " + sliderPrecio.getValue() + " €/dia");
		labelPrecioValor.setForeground(Color.WHITE);
		labelPrecioValor.setFont(new Font("Arial", Font.ITALIC, 14));
		gbc.gridy = 4;
		panelFiltros.add(labelPrecio, gbc);
		gbc.gridy = 5;
		panelFiltros.add(sliderPrecio, gbc);
		gbc.gridy = 6;
		panelFiltros.add(labelPrecioValor, gbc);

		JLabel labelCombustible = new JLabel("Tipo de Combustible:");
		labelCombustible.setForeground(Color.WHITE);
		JComboBox<String> comboCombustible = new JComboBox<>(
				new String[] { "Todos", "Gasolina", "Diesel", "Hibrido", "Electrico" });
		gbc.gridy = 7;
		panelFiltros.add(labelCombustible, gbc);
		gbc.gridy = 8;
		panelFiltros.add(comboCombustible, gbc);

		JCheckBox checkAutomatico = new JCheckBox("Automático");
		checkAutomatico.setBackground(colorPersonalizado);
		checkAutomatico.setForeground(Color.WHITE);
		gbc.gridy = 9;
		panelFiltros.add(checkAutomatico, gbc);

		panelCatalogo = new JPanel(new GridLayout(0, 3, 10, 10));
		panelCatalogo.setBackground(colorPersonalizado);
		JScrollPane scrollPane = new JScrollPane(panelCatalogo);
		add(scrollPane, BorderLayout.CENTER);

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		panelPrincipal.add(panelFiltros, BorderLayout.WEST);
		panelPrincipal.add(scrollPane, BorderLayout.CENTER);
		getContentPane().add(panelPrincipal);
		getContentPane().add(panelBotones, BorderLayout.SOUTH);

		cargarVehiculosEnCatalogo();

		botonCerrarSesion.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaIncio();
			}
		});

		botonAtras.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				new VentanaMarcas(cliente);
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
				actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico,
						tipoCombustible);
			}
		});

		comboModelo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoSeleccionado = (String) comboTipo.getSelectedItem();
				String modeloSeleccionado = (String) comboModelo.getSelectedItem();
				int precioMaximo = sliderPrecio.getValue();
				boolean esAutomatico = checkAutomatico.isSelected();
				String tipoCombustible = (String) comboCombustible.getSelectedItem();
				actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico,
						tipoCombustible);
			}
		});

		sliderPrecio.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				labelPrecioValor.setText("Valor máximo: " + sliderPrecio.getValue() + " €/dia");
				String tipoSeleccionado = (String) comboTipo.getSelectedItem();
				String modeloSeleccionado = (String) comboModelo.getSelectedItem();
				int precioMaximo = sliderPrecio.getValue();
				boolean esAutomatico = checkAutomatico.isSelected();
				String tipoCombustible = (String) comboCombustible.getSelectedItem();
				actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico,
						tipoCombustible);
			}
		});

		checkAutomatico.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoSeleccionado = (String) comboTipo.getSelectedItem();
				String modeloSeleccionado = (String) comboModelo.getSelectedItem();
				int precioMaximo = sliderPrecio.getValue();
				boolean esAutomatico = checkAutomatico.isSelected();
				String tipoCombustible = (String) comboCombustible.getSelectedItem();
				actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico,
						tipoCombustible);
			}
		});

		comboCombustible.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String tipoSeleccionado = (String) comboTipo.getSelectedItem();
				String modeloSeleccionado = (String) comboModelo.getSelectedItem();
				int precioMaximo = sliderPrecio.getValue();
				boolean esAutomatico = checkAutomatico.isSelected();
				String tipoCombustible = (String) comboCombustible.getSelectedItem();
				actualizarCatalogoConFiltros(tipoSeleccionado, modeloSeleccionado, precioMaximo, esAutomatico,
						tipoCombustible);
			}
		});

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

	private void actualizarCatalogoConFiltros(String tipoSeleccionado, String modeloSeleccionado, int precioMaximo,
			boolean esAutomatico, String tipoCombustible) {
		panelCatalogo.removeAll();

		for (Vehiculo vehiculo : listaVehiculos) {
			boolean coincideTipo = tipoSeleccionado.equals("Todos")
					|| vehiculo.getTipo().equalsIgnoreCase(tipoSeleccionado);
			boolean coincideModelo = modeloSeleccionado.equals("Todos")
					|| vehiculo.getModelo().equalsIgnoreCase(modeloSeleccionado);
			boolean coincidePrecio = vehiculo.getPrecio() <= precioMaximo;
			boolean coincideTransmision = !esAutomatico
					|| vehiculo.gettCajaCambios() == TipoCajaCambios.valueOf("AUTOMATICO");
			boolean coincideCombustible = tipoCombustible.equals("Todos")
					|| vehiculo.gettCombustible() == TipoCombustible.valueOf(tipoCombustible.toUpperCase());

			if (coincideTipo && coincideModelo && coincidePrecio && coincideTransmision && coincideCombustible) {
				String textoBoton = "<html><center>" + "<b>" + vehiculo.getMarca() + " " + vehiculo.getModelo()
						+ "</b><br>" + "Matrícula: " + vehiculo.getMatricula()
						+ "</b><br>" + "Precio: " + (int) vehiculo.getPrecio() + "€/dia" + "<br>" + "<span style='color:"
						+ (vehiculo.isAlquilado() ? "red" : "green") + ";'>"
						+ (vehiculo.isAlquilado() ? "Alquilado" : "No alquilado") + "</span></center></html>";

				JButton botonVehiculo = new JButton(textoBoton);
				botonVehiculo.setPreferredSize(new Dimension(100, 100));

				botonVehiculo.addActionListener(e -> {
					String mensaje = "Marca: " + vehiculo.getMarca() + "\n" + "Modelo: " + vehiculo.getModelo() + "\n"
							+ "Precio: " + vehiculo.getPrecio() + " €/dia\n" + "Tipo: " + vehiculo.getTipo() + "\n"
							+ "Combustible: " + vehiculo.gettCombustible() + "\n" + "Caja de Cambios: "
							+ vehiculo.gettCajaCambios() + "\n" + "Número de plazas: " + vehiculo.getNumPlazas();

					if (vehiculo instanceof Coche) {
						Coche coche = (Coche) vehiculo;
						mensaje += "\nNúmero de puertas: " + coche.getNumPuertas();
					} else if (vehiculo instanceof Furgoneta) {
						Furgoneta furgoneta = (Furgoneta) vehiculo;
						mensaje += "\nCapacidad de carga máximo: " + furgoneta.getCargaMax() + " kg\n"
								+ "Volumen de Carga: " + furgoneta.getCapacidadCarga() + " m³";
					} else if (vehiculo instanceof Moto) {
						Moto moto = (Moto) vehiculo;
						mensaje += "\nBaúl: " + moto.isBaul() + "\nCilindrada: " + moto.getCilindrada();
					}
					
					Object[] opciones = { "Alquilar", "Cerrar" };
					int opcion = JOptionPane.showOptionDialog(this, mensaje, "Información del Vehículo",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
					if (opcion == 0) {
						if (vehiculo.isAlquilado()) {
							JOptionPane.showMessageDialog(this, "Este vehículo ya está alquilado.", "No disponible",
									JOptionPane.WARNING_MESSAGE);
						} else {
							vehiculo.setAlquilado(true);
							JOptionPane.showMessageDialog(this, "Has alquilado el vehículo con éxito.", "Éxito",
									JOptionPane.INFORMATION_MESSAGE);
							cargarVehiculosEnCatalogo();
						}
					}
				});
				panelCatalogo.add(botonVehiculo);
			}
		}
		panelCatalogo.revalidate();
		panelCatalogo.repaint();
	}

	private void cargarVehiculosEnCatalogo() {
		panelCatalogo.removeAll();
		for (Vehiculo vehiculo : listaVehiculos) {
			String textoBoton = "<html><center>" + "<b>" + vehiculo.getMarca() + " " + vehiculo.getModelo()
			+ "</b><br>" + "Matrícula: " + vehiculo.getMatricula()
			+ "</b><br>" + "Precio: " + (int)vehiculo.getPrecio() + "€/dia" + "<br>" + "<span style='color:"
			+ (vehiculo.isAlquilado() ? "red" : "green") + ";'>"
			+ (vehiculo.isAlquilado() ? "Alquilado" : "No alquilado") + "</span></center></html>";

			JButton botonVehiculo = new JButton(textoBoton);

			botonVehiculo.addActionListener(e -> {
				String mensaje = "Marca: " + vehiculo.getMarca() + "\n" + "Modelo: " + vehiculo.getModelo() + "\n"
						+ "Precio: " + vehiculo.getPrecio() + " €/dia\n" + "Tipo: " + vehiculo.getTipo() + "\n"
						+ "Combustible: " + vehiculo.gettCombustible() + "\n" + "Caja de Cambios: "
						+ vehiculo.gettCajaCambios() + "\n" + "Número de plazas: " + vehiculo.getNumPlazas();
				Object[] opciones = { "Alquilar", "Cerrar" };
				int opcion = JOptionPane.showOptionDialog(this, mensaje, "Información del Vehículo",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
				if (opcion == 0) {
					if (vehiculo.isAlquilado()) {
						JOptionPane.showMessageDialog(this, "Este vehículo ya está alquilado.", "No disponible",
								JOptionPane.WARNING_MESSAGE);
					} else {
						vehiculo.setAlquilado(true);
						JOptionPane.showMessageDialog(this, "Has alquilado el vehículo con éxito.", "Éxito",
								JOptionPane.INFORMATION_MESSAGE);
						cargarVehiculosEnCatalogo();
					}
				}
			});

			panelCatalogo.add(botonVehiculo);
		}
		panelCatalogo.revalidate();
		panelCatalogo.repaint();
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
	
	private boolean modeloYaAgregado(JComboBox<String> combo, String modelo) {
		for (int i = 0; i < combo.getItemCount(); i++) {
			if (combo.getItemAt(i).equalsIgnoreCase(modelo)) {
				return true;
			}
		}
		return false;
	}
}
