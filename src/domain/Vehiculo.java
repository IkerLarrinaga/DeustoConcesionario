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
	
	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.numPlazas = numPlazas;
	}

	public Vehiculo(int id, String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas) {
		super();
		this.matricula = matricula;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.numPlazas = numPlazas;
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
	
	@Override
	public String toString() {
		return "Vehiculo [matricula=" + matricula + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio
				+ ", tCombustible=" + tCombustible + ", tCajaCambios=" + tCajaCambios + ", numPlazas=" + numPlazas
				+ "]";
	}
	
	//TODO CORREGIR ESTE METODO PARA QUE CARGUE LOS VEHICULOS CON LA NUEVA ACTUALIZACION, ES DECIR, REDUCIR LA INFORMACIÓN DEL FICHERO TXT
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
                	vehiculo = new Coche(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, numPuertas);
                	
                } else if(datos[0].equals("Furgoneta")) {
                	float cargaMax = Float.parseFloat(datos[12]);
                	int capacidadCarga = Integer.parseInt(datos[14]);
                	
                	vehiculo = new Furgoneta(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, cargaMax, capacidadCarga);
                	
                } else {
                	boolean baul = Boolean.parseBoolean(datos[12]);
                	int cilindrada = Integer.parseInt(datos[13]);
                	
                	vehiculo = new Moto(matricula, marca, modelo, precio, tipoComb, tipoCajCam, numPlazas, baul, cilindrada);
                	
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
	
	public abstract String getTipo();
	
	public abstract void mostrarInformacion();
	
    public abstract void alquilar();
    
    public abstract void devolver();
}
