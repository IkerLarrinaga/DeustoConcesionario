package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaLogIn extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public JTextField email = new JTextField();
	public JPasswordField contrasenna = new JPasswordField();
	public JButton confirmar = new JButton("Confirmar");
	public JButton cancelar = new JButton("Cancelar");
	private int indiceSeleccionador = -1;
    private JButton[] lBotones = new JButton[2];
	
	public VentanaLogIn() {
		setSize(500, 580);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Iniciar Sesión");
		
		ImageIcon foto = new ImageIcon("resource/img/DeustoConcesionarioInicio.png");
        Image fotoEscala = foto.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon fotoFinal = new ImageIcon(fotoEscala); 
        JLabel fondo = new JLabel(fotoFinal);
        
        Color colorPersonalizado = new Color(92, 184, 255);
        
        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder(new EmptyBorder(50, 20, 0, 20));
        panelImagen.setBackground(colorPersonalizado);
        panelImagen.add(fondo, BorderLayout.CENTER);
        add(panelImagen, BorderLayout.NORTH);
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(colorPersonalizado);
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		lBotones[0] = confirmar;
		lBotones[1] = cancelar;
		
		JLabel labelEmail = new JLabel("Introduzca su email:");
		email.setPreferredSize(new Dimension(300, 20));
		JLabel labelContrasenia = new JLabel("Introduzca su contraseña:");
		contrasenna.setPreferredSize(new Dimension(300, 20));
		
		labelEmail.setForeground(Color.WHITE);
		labelContrasenia.setForeground(Color.WHITE);
		
		panel.add(labelEmail, gbc);
		panel.add(email, gbc);
		panel.add(new Label(" "), gbc);
		panel.add(labelContrasenia, gbc);
		panel.add(contrasenna, gbc);
		
		for(int i = 0; i < 2; i++) {
			panel.add(new Label(" "), gbc);
		}
		
		Color colorConfirmarAntes = new Color(40, 150, 255);
        Color colorConfirmarDespues = new Color(20, 100, 220);
        
        confirmar.setBackground(colorConfirmarAntes);
        confirmar.setForeground(Color.WHITE);
        confirmar.setFocusable(false);
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                confirmar.setBackground(colorConfirmarDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                confirmar.setBackground(colorConfirmarAntes);
            }
        });
        
        Color colorCancelarAntes = new Color(255, 80, 80);
        Color colorCancelarDespues = new Color(255, 10, 30);
        
        cancelar.setBackground(colorCancelarAntes);
        cancelar.setForeground(Color.WHITE);
        cancelar.setFocusable(false);
        cancelar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                cancelar.setBackground(colorCancelarDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                cancelar.setBackground(colorCancelarAntes);
            }
        });
		
		panel.add(confirmar, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(cancelar, gbc);

		
		add(panel);
		
		SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                requestFocusInWindow();
            }
        });
		
		addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    indiceSeleccionador = (indiceSeleccionador + 1) % lBotones.length;
                    seleccionarBoton(indiceSeleccionador, lBotones);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    indiceSeleccionador = (indiceSeleccionador - 1 + lBotones.length) % lBotones.length;
                    seleccionarBoton(indiceSeleccionador, lBotones);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                	obtenerAccionDependiendoBoton(indiceSeleccionador);
                }
            }
        });
		
		setVisible(true);
	
		
		confirmar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String emailIntro = email.getText();
				String contrasennaIntro = String.valueOf(contrasenna.getPassword());
				
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
                    contrasenna.setText("");
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
        contrasenna.addKeyListener(enterListener);
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
	
	private void seleccionarBoton(int indice, JButton[] botones) {
        for (int i = 0; i < botones.length; i++) {
            if (i == indice) {
                botones[i].requestFocusInWindow();
                botones[i].setBackground(botones[i].getBackground().darker());
            } else {
                if (botones[i] == lBotones[0]) {
                    botones[i].setBackground(new Color(40, 150, 255));
                } else if (botones[i] == lBotones[1]) {
                    botones[i].setBackground(new Color(255, 80, 80));
                }
            }
        }
    }
    
    private void obtenerAccionDependiendoBoton(int indice) {
        switch (indice) {
		case 0: 
			String emailIntro = email.getText();
			String contrasennaIntro = String.valueOf(contrasenna.getPassword());
			
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
                contrasenna.setText("");
			}
			
			break;
		case 1:
			new VentanaIncio();
            dispose();
            break;
		default:
			break;
		}
    }
}
