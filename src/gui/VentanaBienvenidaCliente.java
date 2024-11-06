package gui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.EmptyBorder;

public class VentanaBienvenidaCliente extends JFrame{

	private static final long serialVersionUID = 1L;

	public VentanaBienvenidaCliente() {
        
        setTitle("Bienvenida");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton botonCatalogo = new JButton("Catálogo");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(new JLabel(" "), gbc);
        
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        botonCatalogo.setPreferredSize(new Dimension(250, 30));
        botonCerrarSesion.setPreferredSize(new Dimension(250, 30));
        
        panel.add(botonCatalogo, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(botonCerrarSesion, gbc);
        
        add(panel);
        
        setVisible(true);
        
        

        botonCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();           	
                JOptionPane.showMessageDialog(new JProgressBar(), "Bienvenido al Catálogo");
                new VentanaCatalogo();
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaIncio();
                dispose();
            }
        });
        
	}
}
