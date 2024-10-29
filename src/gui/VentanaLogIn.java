package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class VentanaLogIn extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTextField email = new JTextField();
	public JPasswordField contrasenia = new JPasswordField();
	public JButton confirmar = new JButton("Confirmar");
	public JButton cancelar = new JButton("Cancelar");
	
	public VentanaLogIn() {
		setSize(480, 560);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Iniciar Sesión");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		JLabel labelEmail = new JLabel("Introduzca su email:");
		email.setPreferredSize(new Dimension(300, 20));
		JLabel labelContrasenia = new JLabel("Introduzca su contraseña:");
		contrasenia.setPreferredSize(new Dimension(300, 20));
		
		panel.add(labelEmail, gbc);
		panel.add(new Label(" "), gbc);
		panel.add(email, gbc);
		panel.add(new Label(" "), gbc);
		panel.add(labelContrasenia, gbc);
		panel.add(new Label(" "), gbc);
		panel.add(contrasenia, gbc);
		
		for(int i = 0; i < 3; i++) {
			panel.add(new Label(" "), gbc);
		}
		
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
