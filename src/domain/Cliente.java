package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends Persona {
	private String licenciaConducir;
	private ArrayList<Alquiler> historialAlquileres;

	public Cliente() {
		super();
	}

	public Cliente(String usuario, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, int numTelefono, String email, String contrasenna) {
		super(usuario, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, numTelefono, email, contrasenna);
	}

	public Cliente(String usuario, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, int numTelefono, String email, String contrasenna,
			String licenciaConducir, ArrayList<Alquiler> historialAlquileres) {
		super(usuario, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, numTelefono, email, contrasenna);
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
				+ ", getUsuario()=" + getUsuario() + ", getNombre()=" + getNombre() + ", getPrimerApellido()="
				+ getPrimerApellido() + ", getSegundoApellido()=" + getSegundoApellido() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getNumTelefono()=" + getNumTelefono()
				+ ", getEmail()=" + getEmail() + ", getContrasenna()=" + getContrasenna() + "]";
	}

   public void mostrarInformacion() {
        System.out.println("Cliente: " + getNombre() + " " + getPrimerApellido() + " " + getSegundoApellido() + " - Licencia: " + licenciaConducir);
    }

    public void alquilarCoche(Vehiculo coche) {
        System.out.println("El cliente " + getNombre() + " ha alquilado el coche " + coche);
    }

}
