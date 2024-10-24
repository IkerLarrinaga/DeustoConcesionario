package domain;

public class AlquilerLargoPlazo extends Alquiler {
	protected double precioMensual;
	protected double descuentoLargoPlazo;
	
    
    
    
    @Override
    public double calcularPrecio() {
        // Cálculo para alquiler a largo plazo
        return precioMensual * this.calcularMeses() - descuentoLargoPlazo;  // Ejemplo para 3 meses con descuento
    }

    
    
    public AlquilerLargoPlazo(double precioMensual, double descuentoLargoPlazo) {
		super();
		this.precioMensual = precioMensual;
		this.descuentoLargoPlazo = descuentoLargoPlazo;
	}
	public AlquilerLargoPlazo(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin,
			double precioMensual, double descuentoLargoPlazo) {
		super(cliente, vehiculo, fechaInicio, fechaFin);
		this.precioMensual = precioMensual;
		this.descuentoLargoPlazo = descuentoLargoPlazo;
	}
	public double getPrecioMensual() {
		return precioMensual;
	}
	public void setPrecioMensual(double precioMensual) {
		this.precioMensual = precioMensual;
	}
	public double getDescuentoLargoPlazo() {
		return descuentoLargoPlazo;
	}
	public void setDescuentoLargoPlazo(double descuentoLargoPlazo) {
		this.descuentoLargoPlazo = descuentoLargoPlazo;
	}
	@Override
	public String toString() {
		return "AlquilerLargoPlazo [precioMensual=" + precioMensual + ", descuentoLargoPlazo=" + descuentoLargoPlazo
				+ ", getCliente()=" + getCliente() + ", getVehiculo()=" + getVehiculo() + ", getFechaInicio()="
				+ getFechaInicio() + ", getFechaFin()=" + getFechaFin() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + "]";
	}

    
}
