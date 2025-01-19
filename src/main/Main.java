package main;

import javax.swing.JFrame;

import db.DataBase;
import gui.VentanaIncio;
import io.ConfigReader;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public static void main(String[] args) {    	
    	
    	
    	ConfigReader.cargarConfiguracion();  
    	
    	if(ConfigReader.baseDatosInicial == true) {
    		DataBase.cargarDatosPrueba();
    	}
    	
    	new VentanaIncio();
    }
}
