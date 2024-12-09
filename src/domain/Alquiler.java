package domain;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Alquiler {
    private int id = -1;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private TipoVehiculo tipoVehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private Coche vehiculoCoche;
    private Furgoneta vehiculoFurgoneta;
    private Moto vehiculoMoto;

    public Alquiler() {
        super();
    }

	public Alquiler(Cliente cliente, Vehiculo vehiculo, TipoVehiculo tipoVehiculo, LocalDate fechaInicio,
			LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.tipoVehiculo = tipoVehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}

	public Alquiler(int id, Cliente cliente, Vehiculo vehiculo, TipoVehiculo tipoVehiculo, LocalDate fechaInicio,
			LocalDate fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.tipoVehiculo = tipoVehiculo;
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

	public TipoVehiculo getTipoVehiculo() {
		return tipoVehiculo;
	}

	public void setTipoVehiculo(TipoVehiculo tipoVehiculo) {
		this.tipoVehiculo = tipoVehiculo;
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
		return "Alquiler [id=" + id + ", cliente=" + cliente + ", vehiculo=" + vehiculo + ", tipoVehiculo="
				+ tipoVehiculo + ", fechaInicio=" + fechaInicio + ", fechaFin=" + fechaFin + "]";
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
    
    public void setVehiculoCoche(Coche coche) {
        this.vehiculoCoche = coche;
        this.vehiculo = coche;
    }

    public void setVehiculoFurgoneta(Furgoneta furgoneta) {
        this.vehiculoFurgoneta = furgoneta;
        this.vehiculo = furgoneta;
    }

    public void setVehiculoMoto(Moto moto) {
        this.vehiculoMoto = moto;
        this.vehiculo = moto;
    }

    public Coche getVehiculoCoche() {
        return vehiculoCoche;
    }

    public Furgoneta getVehiculoFurgoneta() {
        return vehiculoFurgoneta;
    }

    public Moto getVehiculoMoto() {
        return vehiculoMoto;
    }

}
    
    //TODO Hacer metodo que haga descuento si el numero de meses es mayor a X meses
