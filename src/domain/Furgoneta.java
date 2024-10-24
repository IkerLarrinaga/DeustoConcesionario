package domain;

public class Furgoneta extends Vehiculo {

    // Atributos
	protected float cargaMax;
	protected boolean techoAlto;
	protected int capacidadCarga; // Nuevo atributo

    public Furgoneta() {
        super();
    }

    public Furgoneta(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
                     TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama, float cargaMax, boolean techoAlto, int capacidadCarga) {
        super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
        this.cargaMax = cargaMax;
        this.techoAlto = techoAlto;
        this.capacidadCarga = capacidadCarga; // Inicializar nuevo atributo
    }

    // Getters y Setters
    public float getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(float cargaMax) {
        this.cargaMax = cargaMax;
    }

    public boolean isTechoAlto() {
        return techoAlto;
    }

    public void setTechoAlto(boolean techoAlto) {
        this.techoAlto = techoAlto;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Furgoneta - Marca: " + super.marca + 
                ", Modelo: " + super.modelo + 
                ", Precio: " + super.precio + 
                ", Año: " + super.año + 
                ", Kilometros: " + super.kilometros + 
                ", Combustible: " + super.tCombustible + 
                ", Caja de Cambios: " + super.tCajaCambios + 
                ", Potencia: " + super.potencia + 
                ", Número de Plazas: " + super.numPlazas + 
                ", Gama: " + super.gama + 
                ", Carga Máxima: " + cargaMax + 
                ", Techo Alto: " + techoAlto + 
                ", Capacidad de Carga: " + capacidadCarga + "kg");}

    @Override
    public void alquilar() {
        System.out.println("Alquilando furgoneta...");
    }

    @Override
    public void devolver() {
        System.out.println("Devolviendo furgoneta...");
    }

	@Override
	public String toString() {
		return "Furgoneta [cargaMax=" + cargaMax + ", techoAlto=" + techoAlto + ", capacidadCarga=" + capacidadCarga
				+ ", getKilometros()=" + getKilometros() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo()
				+ ", getPrecio()=" + getPrecio() + ", getAño()=" + getAño() + ", gettCombustible()=" + gettCombustible()
				+ ", gettCajaCambios()=" + gettCajaCambios() + ", getPotencia()=" + getPotencia() + ", getNumPlazas()="
				+ getNumPlazas() + ", getGama()=" + getGama() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}
    
}