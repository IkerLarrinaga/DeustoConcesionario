package domain;

public class Empleado extends Persona {
    private String puesto;
    private double salario;

    public Empleado(String nombre, String apellidos, String DNI, String fechaNacimiento, String puesto, double salario) {
        super(nombre, apellidos, DNI, fechaNacimiento);
        this.puesto = puesto;
        this.salario = salario;
    }

   
}
