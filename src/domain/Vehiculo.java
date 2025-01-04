package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public abstract class Vehiculo {
	private int id = -1;
	private String matricula;
	private Marca marca;
	private String modelo;
	private float precio;
	private TipoCombustible tCombustible;
	private TipoCajaCambios tCajaCambios;
	private int numPlazas;
	private boolean alquilado = false;
	
	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.numPlazas = numPlazas;
		this.alquilado = alquilado;
	}
	
	public Vehiculo(int id, String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.numPlazas = numPlazas;
		this.alquilado = alquilado;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public Marca getMarca() {
		return this.marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public TipoCombustible gettCombustible() {
		return tCombustible;
	}

	public void settCombustible(TipoCombustible tCombustible) {
		this.tCombustible = tCombustible;
	}

	public TipoCajaCambios gettCajaCambios() {
		return tCajaCambios;
	}

	public void settCajaCambios(TipoCajaCambios tCajaCambios) {
		this.tCajaCambios = tCajaCambios;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public boolean isAlquilado() {
		return alquilado;
	}

	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	@Override
	public String toString() {
		return "Vehiculo [id=" + id + ", matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo
				+ ", precio=" + precio + ", tCombustible=" + tCombustible + ", tCajaCambios=" + tCajaCambios
				+ ", numPlazas=" + numPlazas + ", alquilado=" + alquilado + "]";
	}

	public static List<Vehiculo> cargarVehiculos(String archivo) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;            
            while ((linea = br.readLine()) != null) { 
                String[] datos = linea.split(";");
                String matricula = datos[1];
            	Marca marca = Marca.valueOf(datos[3].toUpperCase());	
                String modelo = datos[4];
                float precio = Float.parseFloat(datos[5]);
                String tCombustible = datos[7];
                String tCajaCambios = datos[8];
                int numPlazas = Integer.parseInt(datos[10]);
                
                TipoCombustible tipoComb = TipoCombustible.valueOf(tCombustible.toUpperCase());
                TipoCajaCambios tipoCajCam = TipoCajaCambios.valueOf(tCajaCambios.toUpperCase());
                
                Vehiculo vehiculo = null;
                
                if (datos[0].equals("Coche")) {
                	int numPuertas = Integer.parseInt(datos[12]);
                	String tipoTraccion = datos[13];
                	vehiculo = new Coche(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, false, numPuertas);
                	
                } else if(datos[0].equals("Furgoneta")) {
                	float cargaMax = Float.parseFloat(datos[12]);
                	int capacidadCarga = Integer.parseInt(datos[14]);
                	
                	vehiculo = new Furgoneta(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, false, cargaMax, capacidadCarga);
                	
                } else {
                	boolean baul = Boolean.parseBoolean(datos[12]);
                	int cilindrada = Integer.parseInt(datos[13]);
                	
                	vehiculo = new Moto(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, false, baul, cilindrada);
                	
                }

                if (vehiculo != null) {
                        listaVehiculos.add(vehiculo);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listaVehiculos;
    }
    

//	public static List<Vehiculo> cargarVehiculosDesdeBD() {
//	    List<Vehiculo> listaVehiculos = new ArrayList<>();
//	    DataBaseManager dbManager = new DataBaseManager();
//	    
//	    dbManager.conexion("resource/db/concesionario.db");
//	    
//	    for (Coche coche : dbManager.obtenerTodosCoches()) {
//	    	System.out.println("estoy aqui");
//	    	String matricula = coche.getMatricula();
//            Marca marca = coche.getMarca();
//            String modelo = coche.getModelo();
//            float precio = coche.getPrecio();
//            TipoCombustible tipoComb = coche.gettCombustible();
//            TipoCajaCambios tipoCajaCambios = coche.gettCajaCambios();
//            int numPlazas = coche.getNumPlazas();
//            int numPuertas = coche.getNumPuertas();
//
//            Coche coche1 = new Coche(matricula, marca, modelo, precio, tipoComb, tipoCajaCambios, numPlazas, numPuertas);
//            listaVehiculos.add(coche1);
//	    }
	    
//	    for (Furgoneta furgoneta : dbManager.obtenerTodasFurgonetas()) {
//            listaVehiculos.add(furgoneta);
//	    }
//	    
//	    for (Moto moto : dbManager.obtenerTodasMotos()) {
//            listaVehiculos.add(moto);
//	    }
	    
//	    dbManager.desconexion();
//	    return listaVehiculos;
	    

//	    try (Connection conexion = DriverManager.getConnection(urlBD)) {
//	        
//	        // Consultar coches
//	        String consultaCoches = "SELECT matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, numPuertas FROM coche";
//	        try (PreparedStatement stmt = conexion.prepareStatement(consultaCoches);
//	             ResultSet rs = stmt.executeQuery()) {
//	            while (rs.next()) {
//	                String matricula = rs.getString("matricula");
//	                Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
//	                String modelo = rs.getString("modelo");
//	                float precio = rs.getFloat("precio");
//	                TipoCombustible tipoComb = TipoCombustible.valueOf(rs.getString("tCombustible").toUpperCase());
//	                TipoCajaCambios tipoCajaCambios = TipoCajaCambios.valueOf(rs.getString("tCajaCambios").toUpperCase());
//	                int numPlazas = rs.getInt("numPlazas");
//	                int numPuertas = rs.getInt("numPuertas");
//
//	                Coche coche = new Coche(matricula, marca, modelo, precio, tipoComb, tipoCajaCambios, numPlazas, numPuertas);
//	                listaVehiculos.add(coche);
//	            }
//	        }
//
//	        // Consultar furgonetas
//	        String consultaFurgonetas = "SELECT matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, cargaMax, capacidadCarga FROM furgoneta";
//	        try (PreparedStatement stmt = conexion.prepareStatement(consultaFurgonetas);
//	             ResultSet rs = stmt.executeQuery()) {
//	            while (rs.next()) {
//	                String matricula = rs.getString("matricula");
//	                Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
//	                String modelo = rs.getString("modelo");
//	                float precio = rs.getFloat("precio");
//	                TipoCombustible tipoComb = TipoCombustible.valueOf(rs.getString("tCombustible").toUpperCase());
//	                TipoCajaCambios tipoCajaCambios = TipoCajaCambios.valueOf(rs.getString("tCajaCambios").toUpperCase());
//	                int numPlazas = rs.getInt("numPlazas");
//	                float cargaMax = rs.getFloat("cargaMax");
//	                int capacidadCarga = rs.getInt("capacidadCSarga");
//
//	                Furgoneta furgoneta = new Furgoneta(matricula, marca, modelo, precio, tipoComb, tipoCajaCambios, numPlazas, cargaMax, capacidadCarga);
//	                listaVehiculos.add(furgoneta);
//	            }
//	        }
//
//	        // Consultar motos
//	        String consultaMotos = "SELECT matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, baul, cilindrada FROM moto";
//	        try (PreparedStatement stmt = conexion.prepareStatement(consultaMotos);
//	             ResultSet rs = stmt.executeQuery()) {
//	            while (rs.next()) {
//	                String matricula = rs.getString("matricula");
//	                Marca marca = Marca.valueOf(rs.getString("marca").toUpperCase());
//	                String modelo = rs.getString("modelo");
//	                float precio = rs.getFloat("precio");
//	                TipoCombustible tipoComb = TipoCombustible.valueOf(rs.getString("tCombustible").toUpperCase());
//	                TipoCajaCambios tipoCajaCambios = TipoCajaCambios.valueOf(rs.getString("tCajaCambios").toUpperCase());
//	                int numPlazas = rs.getInt("numPSlazas");
//	                boolean baul = rs.getBoolean("baul");
//	                int cilindrada = rs.getInt("cilindrada");
//
//	                Moto moto = new Moto(matricula, marca, modelo, precio, tipoComb, tipoCajaCambios, numPlazas, baul, cilindrada);
//	                listaVehiculos.add(moto);
//	            }
//	        }
//
//	    } catch (SQLException e) {
//	        e.printStackTrace();
//	    }

	
//	}


	
	public abstract String getTipo();
	
	public abstract void mostrarInformacion();
	
    public abstract void alquilar();
    
    public abstract void devolver();
}
