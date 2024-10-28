package domain;

import java.util.GregorianCalendar;

public abstract class Persona {
	private String usuario;
    private String nombre;
    private String apellidos;
    private String dni;
    private GregorianCalendar fechaNacimiento;
    private int numTelefono;
    private String email; 
	
    public Persona() {
		super();
	}

	public Persona(String usuario, String nombre, String apellidos, String dni, GregorianCalendar fechaNacimiento,
			int numTelefono, String email) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.numTelefono = numTelefono;
		this.email = email;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellidos() {
		return apellidos;
	}

	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public GregorianCalendar getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(GregorianCalendar fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getNumTelefono() {
		return numTelefono;
	}

	public void setNumTelefono(int numTelefono) {
		this.numTelefono = numTelefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public abstract void mostrarInformacion();

}
