package domain;

public class Alquiler {
    protected Cliente cliente;
    protected Vehiculo vehiculo;
    protected String fechaInicio;
    protected String fechaFin;
    
    
    
    
    
    
    
    
	public Alquiler(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin) {
		super();
		this.cliente = cliente;
		this.vehiculo = vehiculo;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
	}
	public Alquiler() {
		super();
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
		return "Alquiler [cliente=" + cliente + ", vehiculo=" + vehiculo + ", fechaInicio=" + fechaInicio
				+ ", fechaFin=" + fechaFin + "]";
	}
    
    
}
