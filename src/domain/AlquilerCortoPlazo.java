package domain;
public class AlquilerCortoPlazo extends Alquiler {
    private double precioPorDia;

    
    
    
    
	public AlquilerCortoPlazo(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin,
			double precioPorDia) {
		super(cliente, vehiculo, fechaInicio, fechaFin);
		this.precioPorDia = precioPorDia;
	}
	
	

	public AlquilerCortoPlazo() {
		super();
	}



	public AlquilerCortoPlazo(Cliente cliente, Vehiculo vehiculo, String fechaInicio, String fechaFin) {
		super(cliente, vehiculo, fechaInicio, fechaFin);
		// TODO Auto-generated constructor stub
	}



	public double getPrecioPorDia() {
		return precioPorDia;
	}

	public void setPrecioPorDia(double precioPorDia) {
		this.precioPorDia = precioPorDia;
	}



	@Override
	public String toString() {
		return "AlquilerCortoPlazo [precioPorDia=" + precioPorDia + ", getCliente()=" + getCliente()
				+ ", getVehiculo()=" + getVehiculo() + ", getFechaInicio()=" + getFechaInicio() + ", getFechaFin()="
				+ getFechaFin() + ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + "]";
	}


    
}
