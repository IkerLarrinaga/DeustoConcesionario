package main;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;

import gui.VentanaIncio;


public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
	static JFrame frame = new JFrame("Cargando...");
	static JPanel panel = new JPanel();
	static JProgressBar progressBar = new JProgressBar();
	
    public static void main(String[] args) {    	
    	new VentanaIncio();
    }
}
