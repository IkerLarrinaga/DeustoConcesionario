package domain;

public class Furgoneta extends Vehiculo {
	private float cargaMax;
	private boolean techoAlto;
	private int capacidadCarga;

    public Furgoneta() {
        super();
    }

    public Furgoneta(int kilometros, String marca, String modelo, float precio, int anno, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super(kilometros, marca, modelo, precio, anno, tCombustible, tCajaCambios, potencia, numPlazas, gama);
	}

	public Furgoneta(int kilometros, String marca, String modelo, float precio, int anno, TipoCombustible tCombustible,
                     TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama, float cargaMax, boolean techoAlto, int capacidadCarga) {
        super(kilometros, marca, modelo, precio, anno, tCombustible, tCajaCambios, potencia, numPlazas, gama);
        this.cargaMax = cargaMax;
        this.techoAlto = techoAlto;
        this.capacidadCarga = capacidadCarga;
    }

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
	public String toString() {
		return "Furgoneta [cargaMax=" + cargaMax + ", techoAlto=" + techoAlto + ", capacidadCarga=" + capacidadCarga
				+ ", getKilometros()=" + getKilometros() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo()
				+ ", getPrecio()=" + getPrecio() + ", getAño()=" + getAnno() + ", gettCombustible()=" + gettCombustible()
				+ ", gettCajaCambios()=" + gettCajaCambios() + ", getPotencia()=" + getPotencia() + ", getNumPlazas()="
				+ getNumPlazas() + ", getGama()=" + getGama() + ", toString()=" + super.toString() + ", getClass()="
				+ getClass() + ", hashCode()=" + hashCode() + "]";
	}

    @Override
    public void mostrarInformacion() {
        System.out.println("Furgoneta - Marca: " + super.getMarca() + 
                ", Modelo: " + super.getModelo() + 
                ", Precio: " + super.getPrecio() + 
                ", Año: " + super.getAnno() + 
                ", Kilometros: " + super.getKilometros() + 
                ", Combustible: " + super.gettCombustible() + 
                ", Caja de Cambios: " + super.gettCajaCambios() + 
                ", Potencia: " + super.getPotencia() + 
                ", Número de Plazas: " + super.getNumPlazas() + 
                ", Gama: " + super.getGama() + 
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
}