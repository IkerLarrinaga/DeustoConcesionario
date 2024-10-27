package domain;

public class Empleado extends Persona {
    private String puesto;
    private double salario;
    
    
    public Empleado(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono,
			String email) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
	}

	public Empleado(String nombre, String apellidos, String dni, String fechaNacimiento, int numTelefono, String email,
			String puesto, double salario) {
		super(nombre, apellidos, dni, fechaNacimiento, numTelefono, email);
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
				+ ", getApellidos()=" + getApellidos() + ", getDni()=" + getDni() + ", getFechaNacimiento()="
				+ getFechaNacimiento() + ", getNumTelefono()=" + getNumTelefono() + ", getEmail()=" + getEmail()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()=" + super.toString()
				+ "]";
	}
    public void mostrarInformacion() {
        System.out.println("Empleado: " + getNombre() + " " + getApellidos() + " - Puesto: " + puesto);
    }

    public void gestionarAlquiler(Cliente cliente, Vehiculo coche) {
        System.out.println("El empleado está gestionando el alquiler del coche " + coche + " para el cliente " + cliente);
    }

}
