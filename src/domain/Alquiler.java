package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private int id = -1;
    private Cliente cliente;
    private Vehiculo vehiculoCoche;
    private Vehiculo vehiculoFurgoneta;
    private Vehiculo vehiculoMoto;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;

    public Alquiler() {
        super();
    }

    public Alquiler(Cliente cliente, Vehiculo vehiculoCoche, Vehiculo vehiculoFurgoneta, Vehiculo vehiculoMoto,
			LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculoCoche = vehiculoCoche;
		this.vehiculoFurgoneta = vehiculoFurgoneta;
		this.vehiculoMoto = vehiculoMoto;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

    public Alquiler(int id, Cliente cliente, Vehiculo vehiculoCoche, Vehiculo vehiculoFurgoneta, Vehiculo vehiculoMoto,
			LocalDate fechaInicio, LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculoCoche = vehiculoCoche;
		this.vehiculoFurgoneta = vehiculoFurgoneta;
		this.vehiculoMoto = vehiculoMoto;
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

    public Vehiculo getVehiculoCoche() {
		return vehiculoCoche;
	}

	public void setVehiculoCoche(Vehiculo vehiculoCoche) {
		this.vehiculoCoche = vehiculoCoche;
	}

	public Vehiculo getVehiculoFurgoneta() {
		return vehiculoFurgoneta;
	}

	public void setVehiculoFurgoneta(Vehiculo vehiculoFurgoneta) {
		this.vehiculoFurgoneta = vehiculoFurgoneta;
	}

	public Vehiculo getVehiculoMoto() {
		return vehiculoMoto;
	}

	public void setVehiculoMoto(Vehiculo vehiculoMoto) {
		this.vehiculoMoto = vehiculoMoto;
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
		return "Alquiler [id=" + id + ", cliente=" + cliente + ", vehiculoCoche=" + vehiculoCoche
				+ ", vehiculoFurgoneta=" + vehiculoFurgoneta + ", vehiculoMoto=" + vehiculoMoto + ", fechaInicio="
				+ fechaInicio + ", fechaFin=" + fechaFin + "]";
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

    public int calcularDias() {
        return (int) ChronoUnit.DAYS.between(this.fechaInicio, this.fechaFin);
    }

    public void finalizarAlquiler() {
        System.out.println("Alquiler finalizado para el cliente " + cliente);
    }
}
    
    //TODO Hacer metodo que haga descuento si el numero de meses es mayor a X meses
