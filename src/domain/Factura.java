package domain;

import java.time.LocalDate;

public class Factura {
    private int id = -1;
    private Alquiler alquiler;
    private double importeTotal;
    private LocalDate fechaFactura;

    public Factura() {
        super();
    }

    public Factura(Alquiler alquiler, double importeTotal, LocalDate fechaFactura) {
        super();
        this.alquiler = alquiler;
        this.importeTotal = importeTotal;
        this.fechaFactura = fechaFactura;
    }

    public Factura(int id, Alquiler alquiler, double importeTotal, LocalDate fechaFactura) {
        super();
        this.id = id;
        this.alquiler = alquiler;
        this.importeTotal = importeTotal;
        this.fechaFactura = fechaFactura;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public LocalDate getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(LocalDate fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    @Override
    public String toString() {
        return "Factura [IDFactura=" + id + ", alquiler=" + alquiler + ", importeTotal=" + importeTotal
                + ", fechaFactura=" + fechaFactura + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    public void generarFactura() {
        System.out.println("Factura Generada:");
        System.out.println("ID: " + id);
        System.out.println("Cliente: " + alquiler.getCliente());
        System.out.println("Vehículo: " + alquiler.getVehiculoCoche());
        System.out.println("Vehículo: " + alquiler.getVehiculoFurgoneta());
        System.out.println("Vehículo: " + alquiler.getVehiculoMoto());
        System.out.println("Importe Total: " + importeTotal + "€");
        System.out.println("Fecha: " + fechaFactura);
    }

    public void mostrarFactura() {
        System.out.println("Factura ID: " + id);
        System.out.println("Cliente: " + alquiler.getCliente());
        System.out.println("Vehículo: " + alquiler.getVehiculoCoche());
        System.out.println("Vehículo: " + alquiler.getVehiculoFurgoneta());
        System.out.println("Vehículo: " + alquiler.getVehiculoMoto());
        System.out.println("Importe Total: " + importeTotal + "€");
        System.out.println("Fecha de Factura: " + fechaFactura);
    }
}