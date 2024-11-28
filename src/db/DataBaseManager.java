package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import domain.Alquiler;
import domain.Cliente;
import domain.Empleado;

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
	
	public void conexion(String dbPath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			crearTablaAlquiler();
			crearTablaFactura();
			crearTablaCliente();
			crearTablaEmpleado();
			crearTablaCoche();
			crearTablaFurgoneta();
			crearTablaMoto();
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
	//TODO: Corregir esta tabla
	public void crearTablaAlquiler() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS alquiler (id INTEGER PRIMARY KEY AUTOINCREMENT, idCliente INTEGER, matricula VARCHAR,"
					+ "fechaInicio TEXT, fechaFinal TEXT, FOREIGN KEY(idCliente) REFERENCES cliente(id), FOREIGN KEY(matricula) REFERENCES coche(matricula),"
					+ "FOREIGN KEY(matricula) REFERENCES furgoneta(matricula), FOREIGN KEY(matricula) REFERENCES moto(matricula))");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCoche() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS coche (matricula VARCHAR PRIMARY KEY, marca VARCHAR, modelo VARCHAR, precio REAL, tCombustible VARCHAR,"
					+ "tCajaCambios VARCHAR, numPlazas INTEGER, numPuertas INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFurgoneta() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS furgoneta (matricula VARCHAR PRIMARY KEY, marca VARCHAR, modelo VARCHAR, precio REAL, tCombustible VARCHAR, "
					+ "tCajaCambios VARCHAR, numPlazas INTEGER, cargaMax REAL, capacidadCarga INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaMoto() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS moto (matricula VARCHAR PRIMARY KEY, marca VARCHAR, modelo VARCHAR, precio REAL, tCombustible VARCHAR,"
					+ "tCajaCambios VARCHAR, numPlazas INTEGER, baul BOOLEAN, cilindrada INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFactura() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS factura (id INTEGER PRIMARY KEY AUTOINCREMENT, idAlquiler INTEGER, importeTotal REAL,"
					+ "fechaFactura TEXT, FOREIGN KEY(idAlquiler) REFERENCES alquiler(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarTabla(String nombre) {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("DROP TABLE IF EXISTS " + nombre);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarCliente(Cliente cliente) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO cliente (nombre, primerApellido, segundoApellido,"
				+ " dni, fechaNacimiento, email, contrasena, licenciaConducir) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			Statement statement = conexion.createStatement()) {
			pstatement.setString(1, cliente.getNombre());
			pstatement.setString(2, cliente.getPrimerApellido());
			pstatement.setString(3, cliente.getSegundoApellido());
			pstatement.setString(4, cliente.getDni());
			pstatement.setString(5, fechaAString(cliente.getFechaNacimiento()));
			pstatement.setString(6, cliente.getEmail());
			pstatement.setString(7, cliente.getContrasenna());
			pstatement.setString(8, cliente.getLicenciaConducir());
			
			pstatement.executeUpdate();
			
			ResultSet resulSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM cliente");
			if (resulSet.next()) {
				int newId = resulSet.getInt("id");
				cliente.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarEmpleado(Empleado empleado) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO empleado (nombre, primerApellido, segundoApellido, dni, "
				+ "fechaNacimiento, email, contrasena, puesto, salario) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, empleado.getNombre());
			pstatement.setString(2, empleado.getPrimerApellido());
			pstatement.setString(3, empleado.getSegundoApellido());
			pstatement.setString(4, empleado.getDni());
			pstatement.setString(5, fechaAString(empleado.getFechaNacimiento()));
			pstatement.setString(6, empleado.getEmail());
			pstatement.setString(7, empleado.getContrasenna());
			pstatement.setString(8, empleado.getPuesto());
			pstatement.setDouble(9, empleado.getSalario());
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM empleado");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				empleado.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarAlquiler(Alquiler alquiler) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO alquiler (idCliente, matricula, fechaInicio, fechaFinal)"
				+ "VALUES (?, ?, ?, ?)");
				Statement statement = conexion.createStatement()) {
			try {
				pstatement.setInt(1, alquiler.getCliente().getId());
				pstatement.setString(2, alquiler.getVehiculo().getMatricula());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			pstatement.setString(3, fechaAString(alquiler.getFechaInicio()));
			pstatement.setString(4, fechaAString(alquiler.getFechaFin()));
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM alquiler");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				alquiler.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
