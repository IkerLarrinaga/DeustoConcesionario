package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import domain.Alquiler;
import domain.Cliente;
import domain.Coche;
import domain.Empleado;
import domain.Factura;
import domain.Furgoneta;
import domain.Marca;
import domain.Moto;
import domain.TipoCajaCambios;
import domain.TipoCombustible;

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
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS cliente ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nombre VARCHAR, "
					+ "primerApellido VARCHAR,"
					+ "segundoApellido VARCHAR, "
					+ "dni VARCHAR, "
					+ "fechaNacimiento TEXT, "
					+ "email VARCHAR, "
					+ "contrasena VARCHAR, "
					+ "licenciaConducir VARCHAR)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaEmpleado() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS empleado ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "nombre VARCHAR, "
					+ "primerApellido VARCHAR,"
					+ "segundoApellido VARCHAR, "
					+ "dni VARCHAR, "
					+ "fechaNacimiento TEXT, "
					+ "email VARCHAR, "
					+ "contrasena VARCHAR, "
					+ "puesto VARCHAR, "
					+ "salario REAL)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void crearTablaAlquiler() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS alquiler ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "idCliente INTEGER, "
					+ "idCoche INTEGER, "
					+ "idFurgoneta INTEGER, "
					+ "idMoto INTEGER,"
					+ "fechaInicio TEXT, "
					+ "fechaFinal TEXT, "
					+ "FOREIGN KEY(idCliente) REFERENCES cliente(id), "
					+ "FOREIGN KEY(idCoche) REFERENCES coche(id),"
					+ "FOREIGN KEY(idFurgoneta) REFERENCES furgoneta(id), "
					+ "FOREIGN KEY(idMoto) REFERENCES coche(idMoto))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCoche() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS coche ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "matricula VARCHAR, "
					+ "marca VARCHAR, "
					+ "modelo VARCHAR, "
					+ "precio REAL, "
					+ "tCombustible VARCHAR,"
					+ "tCajaCambios VARCHAR, "
					+ "numPlazas INTEGER, "
					+ "numPuertas INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFurgoneta() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS furgoneta ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "matricula VARCHAR, "
					+ "marca VARCHAR, "
					+ "modelo VARCHAR, "
					+ "precio REAL, "
					+ "tCombustible VARCHAR, "
					+ "tCajaCambios VARCHAR, "
					+ "numPlazas INTEGER, "
					+ "cargaMax REAL, "
					+ "capacidadCarga INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaMoto() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS moto ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
					+ "matricula VARCHAR, "
					+ "marca VARCHAR, "
					+ "modelo VARCHAR, "
					+ "precio REAL, "
					+ "tCombustible VARCHAR,"
					+ "tCajaCambios VARCHAR, "
					+ "numPlazas INTEGER, "
					+ "baul BOOLEAN, "
					+ "cilindrada INTEGER)"
		        );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFactura() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS factura ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "idAlquiler INTEGER, "
					+ "importeTotal REAL,"
					+ "fechaFactura TEXT, "
					+ "FOREIGN KEY(idAlquiler) REFERENCES alquiler(id))");
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
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO cliente ("
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido,"
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "licenciaConducir) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				
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
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO empleado ("
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido, "
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "puesto, "
				+ "salario)"
				+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
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
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO alquiler ("
				+ "idCliente, "
				+ "idCoche, "
				+ "idFurgoneta, "
				+ "idMoto, "
				+ "fechaInicio, "
				+ "fechaFinal)"
				+ "VALUES (?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			try {
				pstatement.setInt(1, alquiler.getCliente().getId());
				if(alquiler.getVehiculo() instanceof Coche) {
					pstatement.setInt(2, alquiler.getVehiculo().getId());
					//IAG ChatGPT
					//Se ha pedido a ChatGPT como utilizar el setNull
					pstatement.setNull(3, Types.INTEGER);
					pstatement.setNull(4, Types.INTEGER);
				} else if(alquiler.getVehiculo() instanceof Furgoneta) {
					pstatement.setNull(2, Types.INTEGER);
					pstatement.setInt(3, alquiler.getVehiculo().getId());
					pstatement.setNull(4, Types.INTEGER);
				} else if(alquiler.getVehiculo() instanceof Moto) {
					pstatement.setNull(2, Types.INTEGER);
					pstatement.setInt(3, Types.INTEGER);
					pstatement.setNull(4, alquiler.getVehiculo().getId());
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			pstatement.setString(5, fechaAString(alquiler.getFechaInicio()));
			pstatement.setString(6, fechaAString(alquiler.getFechaFin()));
			
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
	
	public void almacenarCoche(Coche coche) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO coche ("
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "numPuertas) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, coche.getMatricula());
			pstatement.setString(2, coche.getMarca().name());
			pstatement.setString(3, coche.getModelo());
			pstatement.setFloat(4, coche.getPrecio());
			pstatement.setString(5, coche.gettCombustible().name());
			pstatement.setString(6, coche.gettCajaCambios().name());
			pstatement.setInt(7, coche.getNumPlazas());
			pstatement.setInt(8, coche.getNumPuertas());
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM coche");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				coche.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarFurgoneta(Furgoneta furgoneta) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO furgoneta ("
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "cargaMax, "
				+ "capacidadCarga)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, furgoneta.getMatricula());
			pstatement.setString(2, furgoneta.getMarca().name());
			pstatement.setString(3, furgoneta.getModelo());
			pstatement.setFloat(4, furgoneta.getPrecio());
			pstatement.setString(5, furgoneta.gettCombustible().name());
			pstatement.setString(6, furgoneta.gettCajaCambios().name());
			pstatement.setInt(7, furgoneta.getNumPlazas());
			pstatement.setDouble(8, furgoneta.getCargaMax());
			pstatement.setInt(9, furgoneta.getCapacidadCarga());
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM furgoneta");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				furgoneta.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarMoto(Moto moto) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO moto ("
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "baul, "
				+ "cilindrada)"
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, moto.getMatricula());
			pstatement.setString(2, moto.getMarca().name());
			pstatement.setString(3, moto.getModelo());
			pstatement.setFloat(4, moto.getPrecio());
			pstatement.setString(5, moto.gettCombustible().name());
			pstatement.setString(6, moto.gettCajaCambios().name());
			pstatement.setInt(7, moto.getNumPlazas());
			pstatement.setBoolean(8, moto.isBaul());
			pstatement.setInt(9, moto.getCilindrada());
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM moto");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				moto.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarFactura(Factura factura) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO factura ("
				+ "idAlquiler, "
				+ "importeTotal, "
				+ "fechaFactura)"
				+ "VALUES (?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			try {
				pstatement.setInt(1, factura.getAlquiler().getId());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			pstatement.setDouble(2, factura.getImporteTotal());
			pstatement.setString(3, fechaAString(factura.getFechaFactura()));
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM moto");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				factura.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Cliente obtenerCliente(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido, "
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "licenciaConducir "
				+ "FROM cliente WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(resultSet.getInt("id"));
				cliente.setNombre(resultSet.getString("nombre"));
				cliente.setPrimerApellido(resultSet.getString("primerApellido"));
				cliente.setSegundoApellido(resultSet.getString("segundoApellido"));
				cliente.setDni(resultSet.getString("dni"));
				cliente.setFechaNacimiento(stringAFecha(resultSet.getString("fechaNacimiento")));
				cliente.setEmail(resultSet.getString("email"));
				cliente.setContrasenna(resultSet.getString("contrasena"));
				cliente.setLicenciaConducir(resultSet.getString("licenciaConducir"));
				cliente.setHistorialAlquileres(obtenerTodosAlquileresPorId(resultSet.getInt("id")));
				
				return cliente;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Empleado obtenerEmpleado(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido, "
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "puesto,"
				+ "salario "
				+ "FROM empleado WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Empleado empleado = new Empleado();
				empleado.setId(resultSet.getInt("id"));
				empleado.setNombre(resultSet.getString("nombre"));
				empleado.setPrimerApellido(resultSet.getString("primerApellido"));
				empleado.setSegundoApellido(resultSet.getString("segundoApellido"));
				empleado.setDni(resultSet.getString("dni"));
				empleado.setFechaNacimiento(stringAFecha(resultSet.getString("fechaNacimiento")));
				empleado.setEmail(resultSet.getString("email"));
				empleado.setContrasenna(resultSet.getString("contrasena"));
				empleado.setPuesto(resultSet.getString("puesto"));
				empleado.setSalario(resultSet.getDouble("salario"));
				
				return empleado;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public Alquiler obtenerAlquiler(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "idCliente, "
				+ "idCoche, "
				+ "idFurgoneta, "
				+ "idMoto, "
				+ "fechaInicio, "
				+ "fechaFinal "
				+ "FROM alquiler WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Alquiler alquiler = new Alquiler();
				alquiler.setId(resultSet.getInt("id"));
				alquiler.setCliente(obtenerCliente(resultSet.getInt("idCliente")));
				
				if(resultSet.getObject("idCoche") != null) {
					alquiler.setVehiculo(obtenerCoche(resultSet.getInt("idCoche")));
				} else if(resultSet.getObject("idFurgoneta") != null) {
					alquiler.setVehiculo(obtenerFurgoneta(resultSet.getInt("idFurgoneta")));
				} else if(resultSet.getObject("idMoto") != null) {
					alquiler.setVehiculo(obtenerMoto(resultSet.getInt("idMoto")));
				}
				
				alquiler.setFechaInicio(stringAFecha(resultSet.getString("fechaInicio")));
				alquiler.setFechaFin(stringAFecha(resultSet.getString("fechaFinal")));
				
				return alquiler;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Coche obtenerCoche(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "numPuertas"
				+ "FROM coche WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Coche coche = new Coche();
				coche.setId(resultSet.getInt("id"));
				coche.setMatricula(resultSet.getString("matricula"));
				coche.setMarca(Marca.valueOf(resultSet.getString("marca")));
				coche.setModelo(resultSet.getString("modelo"));
				coche.setPrecio(resultSet.getFloat("precio"));
				coche.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
				coche.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				coche.setNumPlazas(resultSet.getInt("numPlazas"));
				coche.setNumPuertas(resultSet.getInt("numPuertas"));
				
				return coche;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Furgoneta obtenerFurgoneta(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "cargaMax, "
				+ "capacidadCarga"
				+ "FROM furgoneta WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Furgoneta furgoneta = new Furgoneta();
				furgoneta.setId(resultSet.getInt("id"));
				furgoneta.setMatricula(resultSet.getString("matricula"));
				furgoneta.setMarca(Marca.valueOf(resultSet.getString("marca")));
				furgoneta.setModelo(resultSet.getString("modelo"));
				furgoneta.setPrecio(resultSet.getFloat("precio"));
				furgoneta.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
				furgoneta.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				furgoneta.setNumPlazas(resultSet.getInt("numPlazas"));
				furgoneta.setCargaMax(resultSet.getInt("cargaMax"));
				furgoneta.setCapacidadCarga(resultSet.getInt("capacidadCarga"));
				
				return furgoneta;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Moto obtenerMoto(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "baul, "
				+ "cilindrada"
				+ "FROM moto WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Moto moto = new Moto();
				moto.setId(resultSet.getInt("id"));
				moto.setMatricula(resultSet.getString("matricula"));
				moto.setMarca(Marca.valueOf(resultSet.getString("marca")));
				moto.setModelo(resultSet.getString("modelo"));
				moto.setPrecio(resultSet.getFloat("precio"));
				moto.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
				moto.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				moto.setNumPlazas(resultSet.getInt("numPlazas"));
				moto.setBaul(resultSet.getBoolean("baul"));
				moto.setCilindrada(resultSet.getInt("cilindrada"));
				
				return moto;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Factura obtenerFactura(int id) {
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "idAlquiler, "
				+ "importeTotal, "
				+ "fechaFactura"
				+ "FROM factura WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Factura factura = new Factura();
				factura.setId(resultSet.getInt("id"));
				factura.setAlquiler(obtenerAlquiler(resultSet.getInt("idAlquiler")));
				factura.setImporteTotal(resultSet.getFloat("importeTotal"));
				factura.setFechaFactura(stringAFecha(resultSet.getString("fechaFactura")));
				
				return factura;
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Cliente> obtenerTodosClientes() {
		List<Cliente> lClientes = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
					+ "id, "
					+ "nombre, "
					+ "primerApellido, "
					+ "segundoApellido, "
					+ "dni, "
					+ "fechaNacimiento, "
					+ "email, "
					+ "contrasena, "
					+ "licenciaConducir "
					+ "FROM cliente");
			
			while(resultSet.next()) {
				lClientes.add(obtenerCliente(resultSet.getInt("id")));
			}
			
			return lClientes;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Empleado> obtenerTodosEmpleados() {
		List<Empleado> lEmpleados = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
				+ "id, "
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido, "
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "puesto,"
				+ "salario "
				+ "FROM empleado");
			
			while(resultSet.next()) {
				lEmpleados.add(obtenerEmpleado(resultSet.getInt("id")));
			}
			
			return lEmpleados;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Alquiler> obtenerTodosAlquileres() {
		List<Alquiler> lAlquileres = new ArrayList<>();
		
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
				+ "id, "
				+ "idCliente, "
				+ "idCoche, "
				+ "idFurgoneta, "
				+ "idMoto, "
				+ "fechaInicio, "
				+ "fechaFinal "
				+ "FROM alquiler");
			
			while (resultSet.next()) {
				lAlquileres.add(obtenerAlquiler(resultSet.getInt("id")));
			}
			
			return lAlquileres;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	public List<Coche> obtenerTodosCoches() {
		List<Coche> lCoches = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
				+ "id, "
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "numPuertas"
				+ "FROM coche");
			
			while(resultSet.next()) {
				lCoches.add(obtenerCoche(resultSet.getInt("id")));
			}
					
			return lCoches;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Furgoneta> obtenerTodasFurgonetas() {
		List<Furgoneta> lFurgonetas = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
				+ "id, "
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas, "
				+ "cargaMax, "
				+ "capacidadCarga"
				+ "FROM furgoneta");
			
			while(resultSet.next()) {
				lFurgonetas.add(obtenerFurgoneta(resultSet.getInt("id")));
			}
				
			return lFurgonetas;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Moto> obtenerTodasMotos() {
		List<Moto> lMotos = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
					+ "id, "
					+ "matricula, "
					+ "marca, "
					+ "modelo, "
					+ "precio, "
					+ "tCombustible, "
					+ "tCajaCambios, "
					+ "numPlazas, "
					+ "baul, "
					+ "cilindrada");
			
			while (resultSet.next()) {
				lMotos.add(obtenerMoto(resultSet.getInt("id")));
			}
			
			return lMotos;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Factura> obtenerTodasFacturas() {
		List<Factura> lFacturas = new ArrayList<>();
		try (Statement statement = conexion.createStatement()) {
			ResultSet resultSet = statement.executeQuery("SELECT "
				+ "id, "
				+ "idAlquiler, "
				+ "importeTotal, "
				+ "fechaFactura"
				+ "FROM factura");
			
			while(resultSet.next()) {
				lFacturas.add(obtenerFactura(resultSet.getInt("id")));
			}
				
			return lFacturas;

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//TODO: Corregir este metodo
	public ArrayList<Alquiler> obtenerTodosAlquileresPorId(int id) {
		ArrayList<Alquiler> lAlquileres = new ArrayList<>();
		
		try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
				+ "id, "
				+ "idCliente, "
				+ "idCoche, "
				+ "idFurgoneta, "
				+ "idMoto, "
				+ "fechaInicio, "
				+ "fechaFinal "
				+ "FROM alquiler WHERE id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			while (resultSet.next()) {
				lAlquileres.add(obtenerAlquiler(resultSet.getInt("id")));
			}
			
			return lAlquileres;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}	
	}

	public void eliminarCliente(Cliente cliente) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM cliente WHERE id = ?")) {
			pstatement.setInt(1, cliente.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarEmpleado(Empleado empleado) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM empleado WHERE id = ?")) {
			pstatement.setInt(1, empleado.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarAlquiler(Alquiler alquiler) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM alquiler WHERE id = ?")) {
			pstatement.setInt(1, alquiler.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarCoche(Coche coche) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM coche WHERE id = ?")) {
			pstatement.setInt(1, coche.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarFurgoneta(Furgoneta furgoneta) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM furgoneta WHERE id = ?")) {
			pstatement.setInt(1, furgoneta.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarMoto(Moto moto) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM moto WHERE id = ?")) {
			pstatement.setInt(1, moto.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void eliminarFactura(Factura factura) {
		try (PreparedStatement pstatement = conexion.prepareStatement("DELETE FROM factura WHERE id = ?")) {
			pstatement.setInt(1, factura.getId());
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarCliente(Cliente cliente) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE cliente SET "
				+ "nombre = ?, "
				+ "primerApellido = ?, "
				+ "segundoApellido = ?, "
				+ "dni = ?, "
				+ "fechaNacimiento = ?, "
				+ "email = ?, "
				+ "contrasena = ?, "
				+ "licenciaConducir = ? "
				+ "WHERE id = ?")) {
			pstatement.setString(1, cliente.getNombre());
			pstatement.setString(2, cliente.getPrimerApellido());
			pstatement.setString(3, cliente.getSegundoApellido());
			pstatement.setString(4, cliente.getDni());
			pstatement.setString(5, fechaAString(cliente.getFechaNacimiento()));
			pstatement.setString(6, cliente.getEmail());
			pstatement.setString(7, cliente.getContrasenna());
			pstatement.setString(8, cliente.getLicenciaConducir());
			pstatement.setInt(9, cliente.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarEmpleado(Empleado empleado) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE empleado SET "
				+ "nombre = ?, "
				+ "primerApellido = ?, "
				+ "segundoApellido = ?, "
				+ "dni = ?, "
				+ "fechaNacimiento = ?, "
				+ "email = ?, "
				+ "contrasena = ?, "
				+ "puesto = ?, "
				+ "salario = ? "
				+ "WHERE id = ?")) {
			pstatement.setString(1, empleado.getNombre());
			pstatement.setString(2, empleado.getPrimerApellido());
			pstatement.setString(3, empleado.getSegundoApellido());
			pstatement.setString(4, empleado.getDni());
			pstatement.setString(5, fechaAString(empleado.getFechaNacimiento()));
			pstatement.setString(6, empleado.getEmail());
			pstatement.setString(7, empleado.getContrasenna());
			pstatement.setString(8, empleado.getPuesto());
			pstatement.setDouble(9, empleado.getSalario());
			pstatement.setInt(10, empleado.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarAlquiler(Alquiler alquiler) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE alquiler SET "
				+ "idCliente = ?, "
				+ "idCoche = ?, "
				+ "idFurgoneta = ?, "
				+ "idMoto = ?, "
				+ "fechaInicio = ?, "
				+ "fechaFinal = ? "
				+ "WHERE id = ?")) {
			try {
				pstatement.setInt(1, alquiler.getCliente().getId());
				if (alquiler.getVehiculo() instanceof Coche) {
					pstatement.setInt(2, alquiler.getVehiculo().getId());
					pstatement.setNull(3, Types.INTEGER);
					pstatement.setNull(4, Types.INTEGER);
				} else if (alquiler.getVehiculo() instanceof Furgoneta) {
					pstatement.setInt(2, Types.INTEGER);
					pstatement.setNull(3, alquiler.getVehiculo().getId());
					pstatement.setNull(4, Types.INTEGER);
				} else if (alquiler.getVehiculo() instanceof Moto) {
					pstatement.setInt(2, Types.INTEGER);
					pstatement.setNull(3, Types.INTEGER);
					pstatement.setNull(4, alquiler.getVehiculo().getId());
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			pstatement.setString(5, fechaAString(alquiler.getFechaInicio()));
			pstatement.setString(6, fechaAString(alquiler.getFechaFin()));
			pstatement.setInt(7, alquiler.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void actualizarCoche(Coche coche) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE coche SET "
				+ "matricula = ?, "
				+ "marca = ?, "
				+ "modelo = ?, "
				+ "precio = ?, "
				+ "tCombustible = ?, "
				+ "tCajaCambios = ?, "
				+ "numPlazas = ?, "
				+ "numPuertas = ? "
				+ "WHERE id = ?")) {
			
			pstatement.setString(1, coche.getMatricula());
			pstatement.setString(2, coche.getMarca().toString());
			pstatement.setString(3, coche.getModelo());
			pstatement.setFloat(4, coche.getPrecio());
			pstatement.setString(5, coche.gettCombustible().toString());
			pstatement.setString(6, coche.gettCajaCambios().toString());
			pstatement.setInt(7, coche.getNumPlazas());
			pstatement.setInt(8, coche.getNumPlazas());
			pstatement.setInt(9, coche.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarFurgoneta(Furgoneta furgoneta) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE furgoneta SET "
				+ "matricula = ?, "
				+ "marca = ?, "
				+ "modelo = ?, "
				+ "precio = ?, "
				+ "tCombustible = ?, "
				+ "tCajaCambios = ?, "
				+ "numPlazas = ?, "
				+ "cargaMax = ?, "
				+ "capacidadCarga = ? "
				+ "WHERE id = ?")) {
			
			pstatement.setString(1, furgoneta.getMatricula());
			pstatement.setString(2, furgoneta.getMarca().toString());
			pstatement.setString(3, furgoneta.getModelo());
			pstatement.setFloat(4, furgoneta.getPrecio());
			pstatement.setString(5, furgoneta.gettCombustible().toString());
			pstatement.setString(6, furgoneta.gettCajaCambios().toString());
			pstatement.setInt(7, furgoneta.getNumPlazas());
			pstatement.setFloat(8, furgoneta.getCargaMax());
			pstatement.setInt(9, furgoneta.getCapacidadCarga());
			pstatement.setInt(10, furgoneta.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarMoto(Moto moto) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE moto SET "
				+ "matricula = ?, "
				+ "marca = ?, "
				+ "modelo = ?, "
				+ "precio = ?, "
				+ "tCombustible = ?, "
				+ "tCajaCambios = ?, "
				+ "numPlazas = ?, "
				+ "baul = ?, "
				+ "cilindrada = ? "
				+ "WHERE id = ?")) {
			
			pstatement.setString(1, moto.getMatricula());
			pstatement.setString(2, moto.getMarca().toString());
			pstatement.setString(3, moto.getModelo());
			pstatement.setFloat(4, moto.getPrecio());
			pstatement.setString(5, moto.gettCombustible().toString());
			pstatement.setString(6, moto.gettCajaCambios().toString());
			pstatement.setInt(7, moto.getNumPlazas());
			pstatement.setBoolean(8, moto.isBaul());
			pstatement.setInt(9, moto.getCilindrada());
			pstatement.setInt(10, moto.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void actualizarFactura(Factura factura) {
		try(PreparedStatement pstatement = conexion.prepareStatement("UPDATE factura SET "
				+ "idAlquiler = ?, "
				+ "importeTotal = ?, "
				+ "fechaFactura = ? "
				+ "WHERE id = ?")) {
			
			try {
				pstatement.setInt(1, factura.getAlquiler().getId());
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
			
			pstatement.setDouble(2, factura.getImporteTotal());
			pstatement.setString(3, fechaAString(factura.getFechaFactura()));
			pstatement.setInt(4, factura.getId());
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}