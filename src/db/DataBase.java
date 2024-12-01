package db;

public class DataBase {
	
	public static void cargarDatosPrueb() {
		DataBaseManager dbManager = new DataBaseManager();
		
		try {
			dbManager.conexion("resources/db/rebote.db");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
