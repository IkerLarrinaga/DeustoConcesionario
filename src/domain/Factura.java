package domain;

public class Factura {
    private String IDFactura;
    private Alquiler alquiler;
    private double importeTotal;
    private String fechaFactura;

    public Factura() {
		super();
	}
    
    public Factura(String IDFactura, Alquiler alquiler, double importeTotal, String fechaFactura) {
        this.IDFactura = IDFactura;
        this.alquiler = alquiler;
        this.importeTotal = importeTotal;
        this.fechaFactura = fechaFactura;
    }
    
	public String getIDFactura() {
		return IDFactura;
	}

	public void setIDFactura(String iDFactura) {
		IDFactura = iDFactura;
	}

	public Alquiler getAlquiler() {
		return alquiler;
	}

	public void setAlquiler(Alquiler alquiler) {
		this.alquiler = alquiler;
	}

	public double getImporteTotal() {
		return importeTotal;
	}

	public void setImporteTotal(double importeTotal) {
		this.importeTotal = importeTotal;
	}

	public String getFechaFactura() {
		return fechaFactura;
	}

	public void setFechaFactura(String fechaFactura) {
		this.fechaFactura = fechaFactura;
	}

	@Override
	public String toString() {
		return "Factura [IDFactura=" + IDFactura + ", alquiler=" + alquiler + ", importeTotal=" + importeTotal
				+ ", fechaFactura=" + fechaFactura + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}
	
	public void generarFactura() {
        System.out.println("Factura Generada:");
        System.out.println("ID: " + IDFactura);
        System.out.println("Cliente: " + alquiler.getCliente());
        System.out.println("Vehículo: " + alquiler.getVehiculo());
        System.out.println("Importe Total: " + importeTotal + "€");
        System.out.println("Fecha: " + fechaFactura);
    }

    public void mostrarFactura() {
        System.out.println("Factura ID: " + IDFactura);
        System.out.println("Cliente: " + alquiler.getCliente());
        System.out.println("Vehículo: " + alquiler.getVehiculo());
        System.out.println("Importe Total: " + importeTotal + "€");
        System.out.println("Fecha de Factura: " + fechaFactura);
    }
}
