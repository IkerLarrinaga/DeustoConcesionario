package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class VentanaBienvenida extends JFrame{
	
	public VentanaBienvenida() {
        
        setTitle("Bienvenida");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton botonCatalogo = new JButton("Catálogo");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        
        JPanel panel = new JPanel();
        
        panel.setLayout(new FlowLayout());
        
        panel.add(botonCatalogo);
        panel.add(botonCerrarSesion);
        
        add(panel);
        
        setVisible(true);

        botonCatalogo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al Catálogo");
                new VentanaCatalogo();
            }
        });

        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaIncio();
            }
        });
        
	}
}
