package domain;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private Cliente cliente;
    private Vehiculo vehiculo;
    private String fechaInicio;
    private String fechaFin;
    
    public Alquiler() {
		super();
	}
    
	public Alquiler(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
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
	
	public String getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(String fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public String getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(String fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	@Override
	public String toString() {
		return ("Alquiler [cliente=" + cliente + ", vehiculo=" + vehiculo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]");
	}
	
	public double calcularPrecio() {
		return 0;
	}
    
    public double calcularMeses() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
        LocalDate fin = LocalDate.parse(fechaFin, formatter);

        int mes = (int) (ChronoUnit.DAYS.between(inicio, fin) / 30);
        return mes;
    }
    
    public double calcularDias() {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate inicio = LocalDate.parse(fechaInicio, formatter);
        LocalDate fin = LocalDate.parse(fechaFin, formatter);

        double dias = ChronoUnit.DAYS.between(inicio, fin);
        return dias;
    }

    public void finalizarAlquiler() {
        System.out.println("Alquiler finalizado para el cliente " + cliente);
    }
}
