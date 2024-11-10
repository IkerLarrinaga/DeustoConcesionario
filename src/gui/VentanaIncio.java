package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;


public class VentanaIncio extends JFrame {
	
	//FUENTE-EXTERNA
	//URL: https://github.com/erikcoruna/Rebote/tree/main/src/gui
	//El codigo ha sido obtenido del proyecto del año pasado para la adpataciopn de las ventanas con el GridBagConstraints, 
	//después ha sido adaptado para el correcto funcionamiento de este proyecto. 
	
    private static final long serialVersionUID = 1L;

	public VentanaIncio() {
    	setSize(480, 560);
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
        
        login.setPreferredSize(new Dimension(250, 30));
        registro.setPreferredSize(new Dimension(250, 30));
        salir.setPreferredSize(new Dimension(250, 30));
//        login.setBackground(new Color(240, 196, 170));
//        registro.setBackground(new Color(240, 183, 170));
//        salir.setBackground(new Color(255, 0, 0));
        panel.add(login, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(registro, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(salir, gbc);
        
        add(panel);
        setVisible(true);

        login.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new VentanaLogIn();
        		dispose();
			}
		});
        
        registro.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				new VentanaRegistro();
        		dispose();
			}
		});
        
        salir.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
    }
}
