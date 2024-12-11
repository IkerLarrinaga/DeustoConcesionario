package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import domain.Alquiler;
import domain.Cliente;
import domain.Coche;
import domain.Empleado;
import domain.Factura;
import domain.Furgoneta;
import domain.Marca;
import domain.Moto;
import domain.Persona;
import domain.TipoCajaCambios;
import domain.TipoCombustible;
import domain.Vehiculo;

//FUENTE-EXTERNA
//https://github.com/erikcoruna/Rebote
//Se ha adaptado el proyecto para el uso de las funciones de nuestras base de datos en el proyecto

public class DataBaseManager {
	
	private Connection conexion = null;
	private DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
	
	public LocalDate stringAFecha(String str) {
	    LocalDate fecha = null;
	    try {
	        fecha = LocalDate.parse(str, dateFormat);
	    } catch (DateTimeParseException e) {
	        e.printStackTrace();
	    }
	    return fecha;
	}
	
	public String fechaAString(LocalDate fecha) {
	    return dateFormat.format(fecha);
	}

	
	public void conexion(String dbPath) {
		try {
			Class.forName("org.sqlite.JDBC");
			conexion = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			crearTablaAlquiler();
			crearTablaFactura();
			crearTablaPersona();
			crearTablaCliente();
			crearTablaEmpleado();
			crearTablaVehiculo();
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
	
	public void crearTablaPersona() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS persona (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "nombre TEXT, " +
                    "primerApellido TEXT, " +
                    "segundoApellido TEXT, " +
                    "dni TEXT, " +
                    "fechaNacimiento TEXT, " +
                    "email TEXT, " +
                    "contrasenna TEXT, " +
                    "licenciaConducir TEXT)");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCliente() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS cliente ("
					+ "id INTEGER PRIMARY KEY, "
					+ "licenciaConducir VARCHAR, "
					+ "FOREIGN KEY(id) REFERENCES persona(id))");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaEmpleado() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS empleado ("
					+ "id INTEGER PRIMARY KEY, "
					+ "puesto VARCHAR, "
					+ "salario REAL, "
					+ "FOREIGN KEY(id) REFERENCES persona(id))");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void crearTablaAlquiler() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS alquiler ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "idCliente INTEGER, "
					+ "idVehiculo INTEGER, "
					+ "tipoVehiculo TEXT, "
					+ "fechaInicio TEXT, "
					+ "fechaFinal TEXT, "
					+ "FOREIGN KEY(idCliente) REFERENCES cliente(id), "
					+ "FOREIGN KEY(idVehiculo) REFERENCES vehiculo(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaVehiculo() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS vehiculo ("
					+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
					+ "matricula VARCHAR, "
					+ "marca VARCHAR, "
					+ "modelo VARCHAR, "
					+ "precio REAL, "
					+ "tCombustible VARCHAR,"
					+ "tCajaCambios VARCHAR, "
					+ "numPlazas INTEGER)");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaCoche() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS coche ("
					+ "id INTEGER PRIMARY KEY, "
					+ "numPuertas INTEGER, "
					+ "FOREIGN KEY(id) REFERENCES vehiculo(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaFurgoneta() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS furgoneta ("
					+ "id INTEGER PRIMARY KEY, "
					+ "cargaMax REAL, "
					+ "capacidadCarga INTEGER, "
					+ "FOREIGN KEY(id) REFERENCES vehiculo(id))");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void crearTablaMoto() {
		try (Statement statement = conexion.createStatement()) {
			statement.executeUpdate("CREATE TABLE IF NOT EXISTS moto ("
					+ "id INTEGER PRIMARY KEY,"
					+ "baul BOOLEAN, "
					+ "cilindrada INTEGER, "
					+ "FOREIGN KEY(id) REFERENCES vehiculo(id))");
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
	
	public void almacenarPersona(Persona persona) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO persona ("
				+ "nombre, "
				+ "primerApellido, "
				+ "segundoApellido,"
				+ "dni, "
				+ "fechaNacimiento, "
				+ "email, "
				+ "contrasena, "
				+ "licenciaConducir) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, persona.getNombre());
			pstatement.setString(2, persona.getPrimerApellido());
			pstatement.setString(3, persona.getSegundoApellido());
			pstatement.setString(4, persona.getDni());
			pstatement.setString(5, fechaAString(persona.getFechaNacimiento()));
			pstatement.setString(6, persona.getEmail());
			pstatement.setString(7, persona.getContrasenna());
			
			pstatement.executeUpdate();
			
			ResultSet resulSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM persona");
			if (resulSet.next()) {
				int newId = resulSet.getInt("id");
				persona.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarCliente(Cliente cliente) {
		almacenarPersona(cliente);
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO cliente ("
				+ "id, "
				+ "licenciaConducir) "
				+ "VALUES (?, ?)");
				
			) {
			pstatement.setInt(1, cliente.getId());
			pstatement.setString(2, cliente.getLicenciaConducir());
			
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarEmpleado(Empleado empleado) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO empleado ("
				+ "id, "
				+ "puesto, "
				+ "salario)"
				+ " VALUES (?, ?, ?)");
			) {
			pstatement.setInt(1, empleado.getId());
			pstatement.setString(2, empleado.getPuesto());
			pstatement.setDouble(3, empleado.getSalario());
			
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarAlquiler(Alquiler alquiler) {
        try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO alquiler ("
                + "idCliente, "
                + "idVehiculo, "
                + "tipoVehiculo, "
                + "fechaInicio, "
                + "fechaFinal)"
                + "VALUES (?, ?, ?, ?, ?)");

             Statement statement = conexion.createStatement()) {
            pstatement.setInt(1, alquiler.getCliente().getId());
            pstatement.setInt(2, alquiler.getVehiculo().getId());
            pstatement.setString(3, alquiler.getVehiculo().getTipo());
            pstatement.setString(4, fechaAString(alquiler.getFechaInicio()));
            pstatement.setString(5, fechaAString(alquiler.getFechaFin()));

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
	
	public void almacenarVehiculo(Vehiculo vehiculo) {
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO vehiculo ("
				+ "matricula, "
				+ "marca, "
				+ "modelo, "
				+ "precio, "
				+ "tCombustible, "
				+ "tCajaCambios, "
				+ "numPlazas) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?)");
				
				Statement statement = conexion.createStatement()) {
			pstatement.setString(1, vehiculo.getMatricula());
			pstatement.setString(2, vehiculo.getMarca().name());
			pstatement.setString(3, vehiculo.getModelo());
			pstatement.setFloat(4, vehiculo.getPrecio());
			pstatement.setString(5, vehiculo.gettCombustible().name());
			pstatement.setString(6, vehiculo.gettCajaCambios().name());
			pstatement.setInt(7, vehiculo.getNumPlazas());
			
			pstatement.executeUpdate();
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM vehiculo");
			if (resultSet.next()) {
				int newId = resultSet.getInt("id");
				vehiculo.setId(newId);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarCoche(Coche coche) {
		almacenarVehiculo(coche);
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO coche ("
				+ "id, "
				+ "numPuertas) "
				+ "VALUES (?, ?)");
			) {
			pstatement.setInt(1, coche.getId());
			pstatement.setInt(2, coche.getNumPuertas());
			
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarFurgoneta(Furgoneta furgoneta) {
		almacenarVehiculo(furgoneta);
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO furgoneta ("
				+ "id, "
				+ "cargaMax, "
				+ "capacidadCarga)"
				+ "VALUES (?, ?, ?)");
			) {
			pstatement.setInt(1, furgoneta.getId());
			pstatement.setDouble(2, furgoneta.getCargaMax());
			pstatement.setInt(3, furgoneta.getCapacidadCarga());
			
			pstatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void almacenarMoto(Moto moto) {
		almacenarVehiculo(moto);
		try (PreparedStatement pstatement = conexion.prepareStatement("INSERT INTO moto ("
				+ "id, "
				+ "baul, "
				+ "cilindrada)"
				+ "VALUES (?, ?, ?)");
			) {
			pstatement.setInt(1, moto.getId());
			pstatement.setBoolean(2, moto.isBaul());
			pstatement.setInt(3, moto.getCilindrada());
			
			pstatement.executeUpdate();

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
			
			ResultSet resultSet = statement.executeQuery("SELECT last_insert_rowid() AS id FROM factura");
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
				+ "p.id, "
				+ "p.nombre, "
				+ "p.primerApellido, "
				+ "p.segundoApellido, "
				+ "p.dni, "
				+ "p.fechaNacimiento, "
				+ "p.email, "
				+ "p.contrasena, "
				+ "c.licenciaConducir "
				+ "FROM persona p "
				+ "JOIN cliente c ON p.id = c.id "
				+ "WHERE p.id = ?")) {
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
				+ "p.id, "
				+ "p.nombre, "
				+ "p.primerApellido, "
				+ "p.segundoApellido, "
				+ "p.dni, "
				+ "p.fechaNacimiento, "
				+ "p.email, "
				+ "p.contrasena, "
				+ "e.puesto,"
				+ "e.salario "
				+ "FROM persona p "
				+ "JOIN empleado e ON p.id = e.id "
				+ "WHERE p.id = ?")) {
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
	                + "idVehiculo, "
	                + "tipoVehiculo, "
	                + "fechaInicio, "
	                + "fechaFinal "
	                + "FROM alquiler WHERE id = ?")) {
	            pstatement.setInt(1, id);

	            ResultSet resultSet = pstatement.executeQuery();
	            if (resultSet.next()) {
	                Alquiler alquiler = new Alquiler();
	                alquiler.setId(resultSet.getInt("id"));
	                alquiler.setCliente(obtenerCliente(resultSet.getInt("idCliente")));

	                String tipoVehiculo = resultSet.getString("tipoVehiculo");
	                if ("Coche".equals(tipoVehiculo)) {
	                    alquiler.setVehiculoCoche(obtenerCoche(resultSet.getInt("idVehiculo")));
	                } else if ("Furgoneta".equals(tipoVehiculo)) {
	                    alquiler.setVehiculoFurgoneta(obtenerFurgoneta(resultSet.getInt("idVehiculo")));
	                } else if ("Moto".equals(tipoVehiculo)) {
	                    alquiler.setVehiculoMoto(obtenerMoto(resultSet.getInt("idVehiculo")));
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
				+ "v.id, "
				+ "v.matricula, "
				+ "v.marca, "
				+ "v.modelo, "
				+ "v.precio, "
				+ "v.tCombustible, "
				+ "v.tCajaCambios, "
				+ "v.numPlazas, "
				+ "c.numPuertas "
				+ "FROM vehiculo v "
				+ "JOIN coche c ON v.id = c.id "
				+ "WHERE v.id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Coche coche = new Coche();
				coche.setId(resultSet.getInt("id"));
				coche.setMatricula(resultSet.getString("matricula"));
				//IAG Gemini
				//Sugerido por Gemini para el correcto funcionamiento
				try {
					coche.setMarca(Marca.valueOf(resultSet.getString("marca")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
				coche.setModelo(resultSet.getString("modelo"));
				coche.setPrecio(resultSet.getFloat("precio"));
				try {
					coche.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
					coche.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
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
				+ "v.id, "
				+ "v.matricula, "
				+ "v.marca, "
				+ "v.modelo, "
				+ "v.precio, "
				+ "v.tCombustible, "
				+ "v.tCajaCambios, "
				+ "v.numPlazas, "
				+ "f.cargaMax, "
				+ "f.capacidadCarga "
				+ "FROM vehiculo v "
				+ "JOIN furgoneta f ON v.id = f.id"
				+ "WHERE v.id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Furgoneta furgoneta = new Furgoneta();
				furgoneta.setId(resultSet.getInt("id"));
				furgoneta.setMatricula(resultSet.getString("matricula"));
				try {
					furgoneta.setMarca(Marca.valueOf(resultSet.getString("marca")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
				furgoneta.setModelo(resultSet.getString("modelo"));
				furgoneta.setPrecio(resultSet.getFloat("precio"));
				try {
					furgoneta.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
					furgoneta.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
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
				+ "v.id, "
				+ "v.matricula, "
				+ "v.marca, "
				+ "v.modelo, "
				+ "v.precio, "
				+ "v.tCombustible, "
				+ "v.tCajaCambios, "
				+ "v.numPlazas, "
				+ "m.baul, "
				+ "m.cilindrada "
				+ "FROM vehiculo v "
				+ "JOIN moto m ON v.id = m.id "
				+ "WHERE v.id = ?")) {
			pstatement.setInt(1, id);
			
			ResultSet resultSet = pstatement.executeQuery();
			if (resultSet.next()) {
				Moto moto = new Moto();
				moto.setId(resultSet.getInt("id"));
				moto.setMatricula(resultSet.getString("matricula"));
				try {
					moto.setMarca(Marca.valueOf(resultSet.getString("marca")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
				moto.setModelo(resultSet.getString("modelo"));
				moto.setPrecio(resultSet.getFloat("precio"));
				try {
					moto.settCombustible(TipoCombustible.valueOf(resultSet.getString("tCombustible")));
					moto.settCajaCambios(TipoCajaCambios.valueOf(resultSet.getString("tCajaCambios")));
				} catch (IllegalArgumentException | NullPointerException e) {
					e.printStackTrace();
				}
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
				+ "fechaFactura "
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
				+ "numPuertas "
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
				+ "capacidadCarga "
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
					+ "cilindrada "
					+ "FROM moto");
			
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
				+ "fechaFactura "
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
	    try (PreparedStatement pstatement = conexion.prepareStatement("UPDATE alquiler SET "
	            + "idCliente = ?, "
	            + "idVehiculo = ?, "
	            + "tipoVehiculo = ?, "
	            + "fechaInicio = ?, "
	            + "fechaFinal = ? "
	            + "WHERE id = ?")) {
	        pstatement.setInt(1, alquiler.getCliente().getId());
	        pstatement.setInt(2, alquiler.getVehiculo().getId());
	        pstatement.setString(3, alquiler.getVehiculo().getTipo());
	        pstatement.setString(4, fechaAString(alquiler.getFechaInicio()));
	        pstatement.setString(5, fechaAString(alquiler.getFechaFin()));
	        pstatement.setInt(6, alquiler.getId());

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
			pstatement.setInt(8, coche.getNumPuertas());
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
	
	public List<Alquiler> obtenerAlquileresPorCliente(Cliente cliente) {
        List<Alquiler> lAlquileres = new ArrayList<>();

        try (PreparedStatement pstatement = conexion.prepareStatement("SELECT "
                + "id, "
                + "idCliente, "
                + "idVehiculo, "
                + "tipoVehiculo, "
                + "fechaInicio, "
                + "fechaFinal "
                + "FROM alquiler WHERE idCliente = ?")) {
            pstatement.setInt(1, cliente.getId());

            ResultSet resultSet = pstatement.executeQuery();
            while (resultSet.next()) {
                Alquiler alquiler = new Alquiler();
                alquiler.setId(resultSet.getInt("id"));
                alquiler.setCliente(obtenerCliente(resultSet.getInt("idCliente")));

                String tipoVehiculo = resultSet.getString("tipoVehiculo");
                if ("Coche".equals(tipoVehiculo)) {
                    alquiler.setVehiculoCoche(obtenerCoche(resultSet.getInt("idVehiculo")));
                } else if ("Furgoneta".equals(tipoVehiculo)) {
                    alquiler.setVehiculoFurgoneta(obtenerFurgoneta(resultSet.getInt("idVehiculo")));
                } else if ("Moto".equals(tipoVehiculo)) {
                    alquiler.setVehiculoMoto(obtenerMoto(resultSet.getInt("idVehiculo")));
                }

                alquiler.setFechaInicio(stringAFecha(resultSet.getString("fechaInicio")));
                alquiler.setFechaFin(stringAFecha(resultSet.getString("fechaFinal")));
                lAlquileres.add(alquiler);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lAlquileres;
    }
}