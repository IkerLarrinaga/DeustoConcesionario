package domain;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class Vehiculo {
	private String matricula;
	private int kilometros;
	private Marca marca;
	private String modelo;
	private float precio;
	private int anno;
	private TipoCombustible tCombustible;
	private TipoCajaCambios tCajaCambios;
	private int potencia;
	private int numPlazas;
	private Gama gama;
	
	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, int kilometros, Marca marca, String modelo, float precio, int anno,
			TipoCombustible tCombustible, TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super();
		this.matricula = matricula;
		this.kilometros = kilometros;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.anno = anno;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.potencia = potencia;
		this.numPlazas = numPlazas;
		this.gama = gama;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public Marca getMarca() {
		return marca;
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

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
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

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public Gama getGama() {
		return gama;
	}

	public void setGama(Gama gama) {
		this.gama = gama;
	}

	@Override
	public String toString() {
		return "Vehiculo [kilometros=" + kilometros + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio
				+ ", año=" + anno + ", tCombustible=" + tCombustible + ", tCajaCambios=" + tCajaCambios + ", potencia="
				+ potencia + ", numPlazas=" + numPlazas + ", gama=" + gama + "]";
	}
	
	public static List<Vehiculo> cargarVehiculos(String archivo) {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) { 
                String[] datos = linea.split(";");
                String matricula = datos[1];
            	int kilometros = Integer.parseInt(datos[2]);
            	Marca marca = Marca.valueOf(datos[3].toUpperCase());	
                String modelo = datos[4];
                float precio = Float.parseFloat(datos[5]);
                int anno = Integer.parseInt(datos[6]);
                String tCombustible = datos[7];
                String tCajaCambios = datos[8];
                int potencia = Integer.parseInt(datos[9]);
                int numPlazas = Integer.parseInt(datos[10]);
                String gama = datos[11];
                
                TipoCombustible tipoComb = TipoCombustible.valueOf(tCombustible.toUpperCase());
                TipoCajaCambios tipoCajCam = TipoCajaCambios.valueOf(tCajaCambios.toUpperCase());
                Gama gam = Gama.valueOf(gama.toUpperCase());
                
                Vehiculo vehiculo = null;
                
                if (datos[0].equals("Coche")) {
                	int numPuertas = Integer.parseInt(datos[12]);
                	String tipoTraccion = datos[13];
                	Traccion traccion = Traccion.valueOf(tipoTraccion.toUpperCase());
                	vehiculo = new Coche(matricula, kilometros, marca, modelo, precio, anno, 
                			tipoComb, tipoCajCam, potencia, numPlazas, gam, numPuertas, traccion);
                	
                } else if(datos[0].equals("Furgoneta")) {
                	float cargaMax = Float.parseFloat(datos[12]);
                	boolean techoAlto = Boolean.parseBoolean(datos[13]);
                	int capacidadCarga = Integer.parseInt(datos[14]);
                	
                	vehiculo = new Furgoneta(matricula, kilometros, marca, modelo, precio, anno, 
                			tipoComb, tipoCajCam, potencia, numPlazas, gam, cargaMax, techoAlto, capacidadCarga);
                	
                } else {
                	boolean baul = Boolean.parseBoolean(datos[12]);
                	int cilindrada = Integer.parseInt(datos[13]);
                	
                	vehiculo = new Moto(matricula, kilometros, marca, modelo, precio, anno, 
                			tipoComb, tipoCajCam, potencia, numPlazas, gam, baul, cilindrada);
                	
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
