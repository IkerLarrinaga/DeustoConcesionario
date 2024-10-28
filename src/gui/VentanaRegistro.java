package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Vector;
import java.util.logging.Logger;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.toedter.calendar.JDateChooser;

public class VentanaRegistro extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTextField fieldNombreUsuario = new JTextField();
	public JTextField fieldNombre = new JTextField();
	public JTextField fieldPrimerApellido = new JTextField();
	public JTextField fieldSegundoApellido = new JTextField();
	public JPasswordField fieldContrasenna = new JPasswordField();
	public JDateChooser fechaNacimiento = new JDateChooser();
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

		JLabel labelUsuario = new JLabel("Introduzca su nombre de usuario:");
		fieldNombreUsuario.setPreferredSize(new Dimension(300, 20));
		JLabel labelNombre = new JLabel("Introduzca su nombre:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelPrimerApellido = new JLabel("Introduzca su primer apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelSegundoApellido = new JLabel("Introduzca su segundo apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelContrasenna = new JLabel("Introduzca su contraseña:");
		fieldContrasenna.setPreferredSize(new Dimension(300, 20));
		JLabel labelFechaNacimiento = new JLabel("Fecha nacimiento: ");
		fechaNacimiento.setDateFormatString("yyyy-MM-dd");

		ButtonGroup grupo = new ButtonGroup();
		grupo.add(cliente);
		grupo.add(trabajador);

		panel.add(labelUsuario, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldNombreUsuario, gbc);
		panel.add(new JLabel(" "), gbc);
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
		panel.add(labelContrasenna, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fieldContrasenna, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelFechaNacimiento, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(fechaNacimiento, gbc);
		panel.add(new JLabel(" "), gbc);


		JPanel rolPanel = new JPanel();
		rolPanel.setLayout(new GridBagLayout());
		Border lineaBorder = BorderFactory.createLineBorder(Color.RED);
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
		
		
		cancelar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaIncio();
				dispose();
			}
		});
	}
}