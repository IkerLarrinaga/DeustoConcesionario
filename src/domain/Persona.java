package domain;

import java.time.LocalDate;

public abstract class Persona {
    private int id = -1;
    private String nombre;
    private String primerApellido;
    private String segundoApellido;
    private String dni;
    private LocalDate fechaNacimiento;
    private String email;
    private String contrasenna;

    public Persona() {
        super();
    }

    public Persona(String nombre, String primerApellido, String segundoApellido, String dni,
                   LocalDate fechaNacimiento, String email, String contrasenna) {
        super();
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenna = contrasenna;
    }

    public Persona(int id, String nombre, String primerApellido, String segundoApellido, String dni,
                   LocalDate fechaNacimiento, String email, String contrasenna) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.dni = dni;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.contrasenna = contrasenna;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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