package domain;

import java.util.GregorianCalendar;

public abstract class Persona {
	private String usuario;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String dni;
    private GregorianCalendar fechaNacimiento;
    private int numTelefono;
    private String email; 
    private String contrasenna;
	
    public Persona() {
		super();
	}

	public Persona(String usuario, String nombre, String primerApellido, String segundoApellido, String dni,
			GregorianCalendar fechaNacimiento, int numTelefono, String email, String contrasenna) {
		super();
		this.usuario = usuario;
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.dni = dni;
		this.fechaNacimiento = fechaNacimiento;
		this.numTelefono = numTelefono;
		this.email = email;
		this.contrasenna = contrasenna;
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

	public String getPrimerApellido() {
		return primerApellido;
	}

	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}

	public String getSegundoApellido() {
		return segundoApellido;
	}

	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
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

	public String getContrasenna() {
		return contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	public abstract void mostrarInformacion();

}
