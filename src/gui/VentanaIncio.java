package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaIncio extends JFrame {
	
    private static final long serialVersionUID = 1L;
    private int indiceSeleccionador = -1;
    private JButton[] lBotones = new JButton[3];

    public VentanaIncio() {
        setSize(480, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Bienvenido");
        
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
        panel.setBorder(new EmptyBorder(0, 0, 50, 0));
        panel.setBackground(colorPersonalizado);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(new JLabel(" "), gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JButton login = new JButton("Iniciar Sesión");
        JButton registro = new JButton("Registrarse");
        JButton salir = new JButton("Salir");
        
        lBotones[0] = login;
        lBotones[1] = registro;
        lBotones[2] = salir;
        
        Color colorLoginAntes = new Color(40, 150, 255);
        Color colorLoginDespues = new Color(20, 100, 220);
        
        login.setPreferredSize(new Dimension(250, 30));
        login.setBackground(colorLoginAntes);
        login.setForeground(Color.WHITE);
        login.setFocusable(false);
        login.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                login.setBackground(colorLoginDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                login.setBackground(colorLoginAntes);
            }
        });
        
        login.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaLogIn();
                dispose();
			}
		});
        
        Color colorRegistroAntes = new Color(25, 130, 215);
        Color colorRegistroDespues = new Color(15, 80, 190);
                
        registro.setPreferredSize(new Dimension(250, 30));
        registro.setBackground(colorRegistroAntes);
        registro.setForeground(Color.WHITE);
        registro.setFocusable(false);
        registro.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                registro.setBackground(colorRegistroDespues);
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                registro.setBackground(colorRegistroAntes);
            }
        });
        
        registro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaRegistro();
                dispose();
            }
        });
        
        Color colorSalirAntes = new Color(255, 80, 80);
        Color colorSalirDespues = new Color(255, 10, 30);
        
        salir.setPreferredSize(new Dimension(250, 30));
        salir.setBackground(colorSalirAntes);
        salir.setForeground(Color.WHITE);
        salir.setFocusable(false);
        
        salir.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                salir.setBackground(colorSalirDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                salir.setBackground(colorSalirAntes);
            }
        });
        
        salir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
        panel.add(login, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(registro, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(salir, gbc);
        
        add(panel);

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                requestFocusInWindow();
            }
        });
        
        //IAG ChatGPT
        //Se ha pedido un ejemplos sobre pulsar la tecla hacia abajo seleccione el boton, despues se ha modificado para nuestro proyecto.

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
    }
    
    //IAG ChatGPT
    //Se ha pedido un ejemplos sobre pulsar la tecla hacia abajo seleccione el boton, despues se ha modificado para nuestro proyecto.
    
    private void seleccionarBoton(int indice, JButton[] botones) {
        for (int i = 0; i < botones.length; i++) {
            if (i == indice) {
                botones[i].requestFocusInWindow();
                botones[i].setBackground(botones[i].getBackground().darker());
            } else {
                if (botones[i] == lBotones[0]) {
                    botones[i].setBackground(new Color(40, 150, 255));
                } else if (botones[i] == lBotones[1]) {
                    botones[i].setBackground(new Color(25, 130, 215));
                } else if (botones[i] == lBotones[2]) {
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
