package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.ImageIcon;
import javax.swing.JButton;
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

        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
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
