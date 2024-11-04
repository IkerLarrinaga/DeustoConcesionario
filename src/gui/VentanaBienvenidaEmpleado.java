package gui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

public class VentanaBienvenidaEmpleado extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTable tablaUsuario;
	
	public VentanaBienvenidaEmpleado() {
		setSize(800, 600);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Datos de alquiler");
		
		JPanel panelTabla = new JPanel();
		panelTabla.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.anchor = GridBagConstraints.CENTER;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new VentanaBienvenidaEmpleado();
	}
	
}
