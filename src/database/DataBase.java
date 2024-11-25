package database;

import javax.swing.JOptionPane;

public class DataBase {
	
	protected static final String DRIVER_NAME = "org.sqlite.JDBC";
	protected static final String DATABASE_FILE = "resources/db/database.db";
	protected static final String CONNECTION_STRING = "jdbc:sqlite:" + DATABASE_FILE;
	
	public DataBase() {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Error al cargar el driver de la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
