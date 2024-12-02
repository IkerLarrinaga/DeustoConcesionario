package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private int id = -1;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Alquiler() {
        super();
    }

    public Alquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        super();
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Alquiler(int id, Cliente cliente, Vehiculo vehiculo, LocalDate fechaInicio, LocalDate fechaFin) {
        super();
        this.id = id;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(LocalDate fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Override
    public String toString() {
        return ("Alquiler [cliente=" + cliente + ", vehiculo=" + vehiculo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]");
    }

    public void registrarAlquiler() {
        cliente.getHistorialAlquileres().add(this);
    }

    public double calcularPrecio() {
        return 0;
    }

    public long calcularMeses() {
        return ChronoUnit.MONTHS.between(fechaInicio, fechaFin);
    }

    public long calcularDias() {
        return ChronoUnit.DAYS.between(fechaInicio, fechaFin);
    }

    public void finalizarAlquiler() {
        System.out.println("Alquiler finalizado para el cliente " + cliente);
    }
}
    
    //TODO Hacer metodo que haga descuento si el numero de meses es mayor a X meses
