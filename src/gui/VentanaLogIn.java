package gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		Color colorPersonalizado = new Color(92, 184, 255);
        panel.setBackground(colorPersonalizado);
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
	
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String emailIntro = email.getText();
				String contrasennaIntro = String.valueOf(contrasenia.getPassword());
				
				String id = verificacion(emailIntro, contrasennaIntro);
				if(!id.equals("")) {
					if (id.equals("1")) {
						dispose();
                        new VentanaCatalogo();
                    } else if (id.equals("2")) { 
                        new VentanaBienvenidaEmpleado();
                    }
                    dispose();
					
					
				} else {
					JOptionPane.showMessageDialog(null, "Se ha introducido el usuario y/o la contraseña incorrectamente.", "Error", JOptionPane.ERROR_MESSAGE);
					email.setText("");
                    contrasenia.setText("");
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
		
		
		//IAG ChatGPT
		//Se ha preguntado como añadir un KeyListener para que actue como si dieras al boton confirma, se ha copia el codigo integro
		
		KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmar.doClick();
                }
            }
        };
        
        email.addKeyListener(enterListener);
        contrasenia.addKeyListener(enterListener);
	}
	
	private String verificacion(String emailIntro, String contrasennaIntro) {
		 try (BufferedReader reader = new BufferedReader(new FileReader("resource/data/registro.txt"))) {
	            String linea;
	            while ((linea = reader.readLine()) != null) {
	                String[] datos = linea.split(";");
	                
	                String id = datos[0];
	                String email = datos[5];
                    String contrasenna = datos[6];
                    if (email.equals(emailIntro) && contrasenna.equals(contrasennaIntro)) {
                        return id;
                    }
	            }
	        } catch (IOException ex) {
	            ex.printStackTrace();
	            JOptionPane.showMessageDialog(null, "Error al leer los datos", "Error", JOptionPane.ERROR_MESSAGE);
	        }
		 return "";
	}
}
