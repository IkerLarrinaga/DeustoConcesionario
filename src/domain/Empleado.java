package domain;

import java.util.GregorianCalendar;

public class Empleado extends Persona {
    private String puesto;
    private double salario;

	public Empleado() {
		super();
	}

	public Empleado(String usuario, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna) {
		super(usuario, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
	}

	public Empleado(String usuario, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna, String puesto, double salario) {
		super(usuario, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
		this.puesto = puesto;
		this.salario = salario;
	}

	public String getPuesto() {
		return puesto;
	}

	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	@Override
	public String toString() {
		return "Empleado [puesto=" + puesto + ", salario=" + salario + ", getUsuario()=" + getUsuario()
				+ ", getNombre()=" + getNombre() + ", getPrimerApellido()=" + getPrimerApellido()
				+ ", getSegundoApellido()=" + getSegundoApellido() + ", getDni()=" + getDni()
				+ ", getFechaNacimiento()=" + getFechaNacimiento() + ", getEmail()=" + getEmail()
				+ ", getContrasenna()=" + getContrasenna() + "]";
	}

	public void mostrarInformacion() {
        System.out.println("Empleado: " + getNombre() + " " + getPrimerApellido() + " " + getSegundoApellido() + " - Puesto: " + puesto);
    }

    public void gestionarAlquiler(Cliente cliente, Vehiculo coche) {
        System.out.println("El empleado está gestionando el alquiler del coche " + coche + " para el cliente " + cliente);
    }

}
