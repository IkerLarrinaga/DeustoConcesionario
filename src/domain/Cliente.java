package domain;

import java.util.ArrayList;

public class Cliente extends Persona {
	
    private String licenciaConducir;
    private ArrayList<Alquiler> historialAlquileres;
    
    
	public Cliente(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono, String email) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
	}

	public Cliente(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono, String email,
			String licenciaConducir, ArrayList<Alquiler> historialAlquileres) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
		this.licenciaConducir = licenciaConducir;
		this.historialAlquileres = historialAlquileres;
	}

	public String getLicenciaConducir() {
		return licenciaConducir;
	}

	public void setLicenciaConducir(String licenciaConducir) {
		this.licenciaConducir = licenciaConducir;
	}

	public ArrayList<Alquiler> getHistorialAlquileres() {
		return historialAlquileres;
	}

	public void setHistorialAlquileres(ArrayList<Alquiler> historialAlquileres) {
		this.historialAlquileres = historialAlquileres;
	}
}
