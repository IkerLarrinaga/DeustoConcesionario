package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaCatalogo extends JFrame{
	
	public VentanaCatalogo() {
		
		setTitle("Catálogo");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JButton botonCarrito = new JButton("Ver Carrito");
        JButton botonCerrarSesion = new JButton("Cerrar Sesión");
        
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        getContentPane().add(panel, BorderLayout.SOUTH);
        panel.add(botonCarrito);
        panel.add(botonCerrarSesion);
        
        JProgressBar barra = new JProgressBar(0, 100);
        barra.setValue(0);
        barra.setString("Cargando catálogo, por favor espere...");
        barra.setStringPainted(true);
        
        JOptionPane pane = new JOptionPane(barra, JOptionPane.INFORMATION_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
        JDialog dialog = pane.createDialog(null, "Cargando");
        
        Thread hilo = new Thread(() -> {
            int contador = 0;
            try {
                while (true) {
                	if(contador == 100) {
                		SwingUtilities.invokeLater(() -> {
                            dialog.setVisible(false);
                            dialog.dispose();
                        });
                		break;
                	}
                    barra.setValue(contador);
                    Thread.sleep(10);
                    contador += 1;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        
        hilo.start();
        dialog.setVisible(true);
        
      
        
        botonCarrito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	dispose();
                JOptionPane.showMessageDialog(null, "Bienvenido al Carrito");
                new VentanaCarrito();
            }
        });
        
        botonCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VentanaIncio();
                dispose();
            }
        });
        
        setVisible(true);
	}
}
