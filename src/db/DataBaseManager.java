package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class DataBaseManager {
	
	private Connection conexion = null;
	private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	
	public GregorianCalendar stringAFecha(String str) {
		GregorianCalendar fecha = new GregorianCalendar();
		
		try {
			fecha.setTime(dateFormat.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return fecha;
	}
	
	public String fechaAString(GregorianCalendar fecha) {
		return dateFormat.format(fecha.getTime());
	}
	
	//TODO: CONECTAR LA CREACION DE TABLAS
	public void conexion(String dbPath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	
	public void desconexion() {
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCliente() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS cliente (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR, primerApellido VARCHAR,"
					+ "segundoApellido VARCHAR, dni VARCHAR, fechaNacimiento TEXT, email VARCHAR, contrasena VARCHAR, licenciaConducir VARCHAR)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaEmpleado() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS empleado (id INTEGER PRIMARY KEY AUTOINCREMENT, nombre VARCHAR, primerApellido VARCHAR,"
					+ "segundoApellido VARCHAR, dni VARCHAR, fechaNacimiento TEXT, email VARCHAR, contrasena VARCHAR, puesto VARCHAR, salario REAL)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaAlquiler() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS alquiler (id INTEGER PRIMARY KEY AUTOINCREMENT, idCliente INTEGER, idVehiculo INTEGER,"
					+ "fechaInicio TEXT, fechaFinal TEXT, FOREIGN KEY(idCliente) REFERENCES cliente(id), FOREIGN KEY(matricula) REFERENCES coche(matricula),"
					+ "FOREIGN KEY(matricula) REFERENCES furgoneta(matricula), FOREIGN KEY(matricula) REFERENCES moto(matricula)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCoche() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS coche (id INTEGER PRIMARY KEY AUTOINCREMENT, matricula VARCHAR, marca VARCHAR,"
					+ "modelo VARCHAR, precio REAL, tCombustible VARCHAR, tCajaCambios VARCHAR, numPlazas INTEGER, numPuertas INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFurgoneta() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS furgoneta (id INTEGER PRIMARY KEY AUTOINCREMENT, matricula VARCHAR, marca VARCHAR,"
					+ "modelo VARCHAR, precio REAL, tCombustible VARCHAR, tCajaCambios VARCHAR, numPlazas INTEGER, cargaMax REAL, capacidadCarga INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaMoto() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS moto (id INTEGER PRIMARY KEY AUTOINCREMENT, matricula VARCHAR, marca VARCHAR,"
					+ "modelo VARCHAR, precio REAL, tCombustible VARCHAR, tCajaCambios VARCHAR, numPlazas INTEGER, baul BOOLEAN, cilindrada INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFactura() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS factura (id INTEGER PRIMARY KEY AUTOINCREMENT, idAlquiler INTEGER, importeTotal REAL,"
					+ "fechaFactura TEXT, FOREIGN KEY(idAlquiler) REFERENCES alquiler(id)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
