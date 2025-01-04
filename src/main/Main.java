package main;

import javax.swing.JFrame;

import db.DataBase;
import gui.VentanaIncio;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	
    public static void main(String[] args) {    	
    	new VentanaIncio();

//    	DataBase db = new DataBase();
//    	db.cargarDatosPrueba();    	
    }
}
