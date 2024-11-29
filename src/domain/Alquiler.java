package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.GregorianCalendar;

public class Alquiler {
	private int id = -1;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private GregorianCalendar fechaInicio;
    private GregorianCalendar fechaFin;
    
    public Alquiler() {
		super();
	}
	
	public Alquiler(Cliente cliente, Vehiculo vehiculo, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	
	public Alquiler(int id, Cliente cliente, Vehiculo vehiculo, GregorianCalendar fechaInicio, GregorianCalendar fechaFin) {
		super();
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
	
	public GregorianCalendar getFechaInicio() {
		return fechaInicio;
	}
	
	public void setFechaInicio(GregorianCalendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	public GregorianCalendar getFechaFin() {
		return fechaFin;
	}
	
	public void setFechaFin(GregorianCalendar fechaFin) {
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
	
	//IAG ChatGPT
	//Se ha pedido a ChatGPT que cambie el formato de la fecha de LocalDate a GregorianClaendar    
	public long calcularMeses() {
        LocalDate inicio = LocalDate.of(fechaInicio.get(GregorianCalendar.YEAR), fechaInicio.get(GregorianCalendar.MONTH) + 1, fechaInicio.get(GregorianCalendar.DAY_OF_MONTH));
        LocalDate fin = LocalDate.of(fechaFin.get(GregorianCalendar.YEAR), fechaFin.get(GregorianCalendar.MONTH) + 1, fechaFin.get(GregorianCalendar.DAY_OF_MONTH));

        return ChronoUnit.MONTHS.between(inicio, fin);
    }

    public long calcularDias() {
        LocalDate inicio = LocalDate.of(fechaInicio.get(GregorianCalendar.YEAR), fechaInicio.get(GregorianCalendar.MONTH) + 1, fechaInicio.get(GregorianCalendar.DAY_OF_MONTH));
        LocalDate fin = LocalDate.of(fechaFin.get(GregorianCalendar.YEAR), fechaFin.get(GregorianCalendar.MONTH) + 1, fechaFin.get(GregorianCalendar.DAY_OF_MONTH));

        return ChronoUnit.DAYS.between(inicio, fin);
    }

    public void finalizarAlquiler() {
        System.out.println("Alquiler finalizado para el cliente " + cliente);
    }
    
    //TODO Hacer metodo que haga descuento si el numero de meses es mayor a X meses
}
