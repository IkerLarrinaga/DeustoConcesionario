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
        JButton register = new JButton("Registrarse");
        
        login.setPreferredSize(new Dimension(200, 50));
        register.setPreferredSize(new Dimension(200, 50));
        login.setBackground(new Color(240, 196, 170));
        register.setBackground(new Color(240, 183, 170));
        panel.add(login, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(register, gbc);
        
        add(panel);
        setVisible(true);

//        login.addActionListener(new ActionListener() {
//        	@Override
//        	public void actionPerformed(ActionEvent e) {
//        		new VentanaLogIn();
//        		dispose();
//			}
//		});
        
//        register.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				new WindowRegister();
//        		dispose();
//        		logger.info("Pulsado el botón Register.");
//			}
//		});
    }
}
