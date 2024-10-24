package domain;

import java.util.ArrayList;

public class Cliente extends Persona {
	
	protected String licenciaConducir;
	protected ArrayList<Alquiler> historialAlquileres;
    
    
	public Cliente(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono, String email) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
	}

	public Cliente(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono, String email,
			String licenciaConducir, ArrayList<Alquiler> historialAlquileres) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
		this.licenciaConducir = licenciaConducir;
		this.historialAlquileres = new ArrayList<>();
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

	@Override
	public String toString() {
		return "Cliente [licenciaConducir=" + licenciaConducir + ", historialAlquileres=" + historialAlquileres
				+ ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getNumTelefono()=" + getNumTelefono()
				+ ", getEmail()=" + getEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
   public void mostrarInformacion() {
        System.out.println("Cliente: " + nombre + " " + apellidos + " - Licencia: " + licenciaConducir);
    }

    public void alquilarCoche(Vehiculo coche) {
        System.out.println("El cliente " + nombre + " ha alquilado el coche " + coche);
    }

}
