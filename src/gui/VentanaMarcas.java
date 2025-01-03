package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import domain.Marca;

public class VentanaMarcas extends JFrame {

	private static final long serialVersionUID = 1L;
	public VentanaMarcas() {
		setSize(800, 600);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    setTitle("Marcas");
	    setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
	    setLayout(new BorderLayout());
	    
	    String[] opciones = { "Todas", "Vehiculo", "Moto", "Furgoneta" };
        JComboBox<String> comboBox = new JComboBox<>(opciones);
        JPanel panelOpcion = new JPanel();
        panelOpcion.add(comboBox);
        add(panelOpcion, BorderLayout.WEST);

        JPanel panelImagenes = new JPanel();
        panelImagenes.setLayout(new GridLayout(0, 3, 10, 10));
        JScrollPane scrollPanel = new JScrollPane(panelImagenes);
        add(scrollPanel, BorderLayout.CENTER);

        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelImagenes.removeAll();
                String seleccion = (String) comboBox.getSelectedItem();

                if (seleccion.equals("Todas")) {
                	for (Marca marca : Marca.values()) {
                        agregarImagenMarca(panelImagenes, marca);
                    }
                } else if (seleccion.equals("Vehiculo")) {
                    
                } else if (seleccion.equals("Moto")) {
                    
                } else if (seleccion.equals("Furgoneta")) {
                	
                }

                panelImagenes.revalidate();
                panelImagenes.repaint();
            }
        };

        comboBox.addActionListener(listener);

        listener.actionPerformed(null);

        setVisible(true);
		
	}
	
	private void agregarImagenMarca(JPanel panelImagenes, Marca marca) {
	    String rutaImagen = "resource/img/" + marca.name() + ".png";
	    File archivo = new File(rutaImagen);

	    ImageIcon icono;
	    if (archivo.exists()) {
	        icono = new ImageIcon(new ImageIcon(rutaImagen).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	    } else {
	        icono = new ImageIcon(new ImageIcon("car-icon.png").getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
	    }

	    JPanel panelMarca = new JPanel();
	    panelMarca.setLayout(new BorderLayout());
	    panelMarca.setPreferredSize(new Dimension(100, 100));

	    JLabel imagenLabel = new JLabel(icono);
	    panelMarca.add(imagenLabel, BorderLayout.CENTER);

	    JLabel nombreLabel = new JLabel(marca.name(), JLabel.CENTER);
	    panelMarca.add(nombreLabel, BorderLayout.SOUTH);

	    panelImagenes.add(panelMarca);
	}


	
	 public static void main(String[] args) {
		 new VentanaMarcas();      
	 }
}



