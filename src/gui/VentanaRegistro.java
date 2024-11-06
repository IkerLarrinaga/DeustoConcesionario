package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTextField fieldNombre = new JTextField();
	public JTextField fieldPrimerApellido = new JTextField();
	public JTextField fieldSegundoApellido = new JTextField();
	public JPasswordField fieldContrasenna = new JPasswordField();
	public JTextField fieldDni = new JTextField();
	public JTextField fieldEmail = new JTextField();
	public JDateChooser fieldFechaNacimiento = new JDateChooser();
	public JRadioButton cliente = new JRadioButton("Cliente");
	public JRadioButton trabajador = new JRadioButton("Trabajador");
	
	public VentanaRegistro() {
		setSize(480, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registrarse");

		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		JLabel labelNombre = new JLabel("Introduzca su nombre:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelPrimerApellido = new JLabel("Introduzca su primer apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelSegundoApellido = new JLabel("Introduzca su segundo apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelContrasenna = new JLabel("Introduzca su contraseña:");
		fieldContrasenna.setPreferredSize(new Dimension(300, 20));
		JLabel labelFechaNacimiento = new JLabel("Fecha nacimiento: ");
		fieldFechaNacimiento.setDateFormatString("yyyy-MM-dd");
		JLabel labelDni = new JLabel("Introduzaca su DNI: ");
		fieldDni.setPreferredSize(new Dimension(300, 20));
		JLabel labelEmail = new JLabel("Introduzca su email: ");
		fieldEmail.setPreferredSize(new Dimension(300, 20));

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(cliente);
		grupo.add(trabajador);

		panel.add(labelNombre, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldNombre, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelPrimerApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldPrimerApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelSegundoApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldSegundoApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelFechaNacimiento, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldFechaNacimiento, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelDni, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldDni, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelEmail, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldEmail, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelContrasenna, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldContrasenna, gbc);
		panel.add(new JLabel(" "), gbc);


		JPanel rolPanel = new JPanel();
		rolPanel.setLayout(new GridBagLayout());
		Border lineaBorder = BorderFactory.createLineBorder(Color.DARK_GRAY);
		Border tituloBorder = BorderFactory.createTitledBorder(lineaBorder, "Elige tu rol");
		rolPanel.setBorder(tituloBorder);
		rolPanel.add(cliente, gbc);
		rolPanel.add(trabajador, gbc);
		panel.add(rolPanel);
		
		for (int i = 0; i < 3; i++) {
			panel.add(new JLabel(" "), gbc);
		}

		JButton confirmar = new JButton("Confirmar");
		JButton cancelar = new JButton("Cancelar");
		
		panel.add(confirmar, gbc);
		panel.add(cancelar, gbc);
		
		add(panel);
		setVisible(true);
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!fieldNombre.getText().isEmpty() && !fieldPrimerApellido.getText().isEmpty() && !fieldSegundoApellido.getText().isEmpty() && !fieldDni.getText().isEmpty()
						&& !fieldEmail.getText().isEmpty() && !String.valueOf(fieldContrasenna.getPassword()).isEmpty() && !(fieldFechaNacimiento == null) && (cliente.isSelected() || trabajador.isSelected())) {
					
					String nombre = fieldNombre.getText();
					String primerApellido = fieldPrimerApellido.getText();
					String segundoApellido = fieldSegundoApellido.getText();
					String dni = fieldDni.getText();
					String email = fieldEmail.getText();
					String contrasenna = String.valueOf(fieldContrasenna.getPassword());
					Date fechaNacimiento = fieldFechaNacimiento.getDate();
					GregorianCalendar calendar = new GregorianCalendar();
					calendar.setTime(fechaNacimiento);
					
					String fechaNacimientoString = new SimpleDateFormat("yyyy-MM-dd").format(fechaNacimiento);
					
					String id = null;
					if(cliente.isSelected()) {
						id = "1";
					} else if(trabajador.isSelected()) {
						id = "2";
					}
					
					String datos = id + ";" + nombre + ";" + primerApellido + ";" + segundoApellido + ";" + dni + ";" + email + ";" + contrasenna + ";" + fechaNacimientoString;
					
					//IAG ChatGPT
					//Se ha preguntado porque solo guarda un registro en el documento de texto, entonces ha corregido el código para que se guarden más de uno
					
					 try (BufferedWriter writer = new BufferedWriter(new FileWriter("resource/data/registro.txt", true))) {
			                writer.write(datos);
			                writer.newLine();
			                JOptionPane.showMessageDialog(null, "Se ha registrado correctamente.", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
			                new VentanaIncio();
			                dispose();
			                
			            } catch (IOException ex) {
			                ex.printStackTrace();
			                JOptionPane.showMessageDialog(null, "Error al guardar los datos.", "Error", JOptionPane.ERROR_MESSAGE);
			            }
					
				} else {
					JOptionPane.showMessageDialog(null, "Todos los campos deben estar rellenados.", "Advertencia", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		
		
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaIncio();
				dispose();
			}
		});
	}
}