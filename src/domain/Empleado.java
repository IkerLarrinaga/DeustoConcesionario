package domain;

import java.util.GregorianCalendar;

public class Empleado extends Persona {
    private String puesto;
    private double salario;

	public Empleado() {
		super();
	}

	public Empleado(String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna) {
		super(nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
	}

	public Empleado(String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna, String puesto, double salario) {
		super(nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
		this.puesto = puesto;
		this.salario = salario;
	}

	public Empleado(int id, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, String email, String contrasenna, String puesto, double salario) {
		super(id, nombre, primerApellido, segundoApellido, dni, fechaNacimiento, email, contrasenna);
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
		return "Empleado [puesto=" + puesto + ", salario=" + salario + ", getNombre()=" + getNombre()
				+ ", getPrimerApellido()=" + getPrimerApellido() + ", getSegundoApellido()=" + getSegundoApellido()
				+ ", getDni()=" + getDni() + ", getFechaNacimiento()=" + getFechaNacimiento() + ", getEmail()="
				+ getEmail() + ", getContrasenna()=" + getContrasenna() + "]";
	}

	public void mostrarInformacion() {
        System.out.println("Empleado: " + getNombre() + " " + getPrimerApellido() + " " + getSegundoApellido() + " - Puesto: " + puesto);
    }

    public void gestionarAlquiler(Cliente cliente, Vehiculo coche) {
        System.out.println("El empleado está gestionando el alquiler del coche " + coche + " para el cliente " + cliente);
    }

}
