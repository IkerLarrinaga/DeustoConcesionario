package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

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
	private int indiceSeleccionador = -1;
    private JButton[] lBotones = new JButton[2];
	
	public VentanaRegistro() {
		setSize(480, 800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Registrarse");
		
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

		JLabel labelNombre = new JLabel("Introduzca su nombre:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelPrimerApellido = new JLabel("Introduzca su primer apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelSegundoApellido = new JLabel("Introduzca su segundo apellido:");
		fieldNombre.setPreferredSize(new Dimension(300, 20));
		JLabel labelContrasenna = new JLabel("Introduzca su contraseña:");
		fieldContrasenna.setPreferredSize(new Dimension(300, 20));
		JLabel labelFechaNacimiento = new JLabel("Introduzca su fecha nacimiento: ");
		fieldFechaNacimiento.setDateFormatString("yyyy-MM-dd");
		JLabel labelDni = new JLabel("Introduzaca su DNI: ");
		fieldDni.setPreferredSize(new Dimension(300, 20));
		JLabel labelEmail = new JLabel("Introduzca su email: ");
		fieldEmail.setPreferredSize(new Dimension(300, 20));

		ButtonGroup grupo = new ButtonGroup();
		cliente.setBackground(colorPersonalizado);
		cliente.setForeground(Color.WHITE);
		trabajador.setBackground(colorPersonalizado);
		trabajador.setForeground(Color.WHITE);
		grupo.add(cliente);
		grupo.add(trabajador);
		
		labelNombre.setForeground(Color.WHITE);
		labelPrimerApellido.setForeground(Color.WHITE);
		labelSegundoApellido.setForeground(Color.WHITE);
		labelDni.setForeground(Color.WHITE);
		labelFechaNacimiento.setForeground(Color.WHITE);
		labelEmail.setForeground(Color.WHITE);
		labelContrasenna.setForeground(Color.WHITE);
		
		panel.add(labelNombre, gbc);
		panel.add(fieldNombre, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelPrimerApellido, gbc);
		panel.add(fieldPrimerApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelSegundoApellido, gbc);
		panel.add(fieldSegundoApellido, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelFechaNacimiento, gbc);
		panel.add(fieldFechaNacimiento, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelDni, gbc);
		panel.add(fieldDni, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelEmail, gbc);
		panel.add(fieldEmail, gbc);
		panel.add(new JLabel(" "), gbc);
		panel.add(labelContrasenna, gbc);
		panel.add(fieldContrasenna, gbc);
		panel.add(new JLabel(" "), gbc);
		

		JPanel rolPanel = new JPanel();
		rolPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 50, 0));
		rolPanel.setBackground(colorPersonalizado);
		rolPanel.setForeground(Color.WHITE);
		Border lineaBorder = BorderFactory.createLineBorder(Color.WHITE);
		TitledBorder tituloBorder = BorderFactory.createTitledBorder(lineaBorder, "Escoja su rol");
		tituloBorder.setTitleColor(Color.WHITE);
		rolPanel.setBorder(tituloBorder);
		rolPanel.add(cliente);
		rolPanel.add(trabajador);
		panel.add(rolPanel);
		
		for (int i = 0; i < 2; i++) {
			panel.add(new JLabel(" "), gbc);
		}

		JButton confirmar = new JButton("Confirmar");
		JButton cancelar = new JButton("Cancelar");
		lBotones[0] = confirmar;
		lBotones[1] = cancelar;
		
		Color colorLoginAntes = new Color(40, 150, 255);
        Color colorLoginDespues = new Color(20, 100, 220);
        
        confirmar.setBackground(colorLoginAntes);
        confirmar.setForeground(Color.WHITE);
        confirmar.setFocusable(false);
        confirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                confirmar.setBackground(colorLoginDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                confirmar.setBackground(colorLoginAntes);
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
			new VentanaLogIn();
			dispose();
			break;
		case 1:
			new VentanaRegistro();
            dispose();
            break;
		case 2:
			System.exit(0);
			break;
		default:
			break;
		}
    }
}