package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends Persona {
	private String licenciaConducir;
	private ArrayList<Alquiler> historialAlquileres;

	public Cliente() {
		super();
	}

	public Cliente(String usuario, String nombre, String apellidos, String dni, GregorianCalendar fechaNacimiento,
			int numTelefono, String email) {
		super(usuario, nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
	}

	public Cliente(String usuario, String nombre, String apellidos, String dni, GregorianCalendar fechaNacimiento,
			int numTelefono, String email, String licenciaConducir, ArrayList<Alquiler> historialAlquileres) {
		super(usuario, nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
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

	@Override
	public String toString() {
		return "Cliente [licenciaConducir=" + licenciaConducir + ", historialAlquileres=" + historialAlquileres
				+ ", getNombre()=" + getNombre() + ", getApellidos()=" + getApellidos() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getNumTelefono()=" + getNumTelefono()
				+ ", getEmail()=" + getEmail() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
   public void mostrarInformacion() {
        System.out.println("Cliente: " + getNombre() + " " + getApellidos() + " - Licencia: " + licenciaConducir);
    }

    public void alquilarCoche(Vehiculo coche) {
        System.out.println("El cliente " + getNombre() + " ha alquilado el coche " + coche);
    }

}
