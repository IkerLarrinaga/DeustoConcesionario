package domain;

import java.util.ArrayList;
import java.util.GregorianCalendar;

public class Cliente extends Persona {
	private String licenciaConducir;
	private ArrayList<Alquiler> historialAlquileres;

	public Cliente() {
		super();
	}

	public Cliente(int id, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna) {
		super(id, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
	}

	public Cliente(int id, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna, String licenciaConducir,
			ArrayList<Alquiler> historialAlquileres) {
		super(id, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
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
				+ ", getNombre()=" + getNombre() + ", getPrimerApellido()=" + getPrimerApellido()
				+ ", getSegundoApellido()=" + getSegundoApellido() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getEmail()=" + getEmail()
				+ ", getContrasenna()=" + getContrasenna() + "]";
	}

   public void mostrarInformacion() {
        System.out.println("Cliente: " + getNombre() + " " + getPrimerApellido() + " " + getSegundoApellido() + " - Licencia: " + licenciaConducir);
    }

    public void alquilarCoche(Vehiculo coche) {
        System.out.println("El cliente " + getNombre() + " ha alquilado el coche " + coche);
    }

}
