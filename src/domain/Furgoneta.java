package domain;

public class Furgoneta extends Vehiculo {
	private float cargaMax;
	private int capacidadCarga;

    public Furgoneta() {
        super();
    }

	public Furgoneta(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, float cargaMax, int capacidadCarga) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas);
		this.cargaMax = cargaMax;
		this.capacidadCarga = capacidadCarga;
	}

	public float getCargaMax() {
        return cargaMax;
    }

    public void setCargaMax(float cargaMax) {
        this.cargaMax = cargaMax;
    }

    public int getCapacidadCarga() {
        return capacidadCarga;
    }

    public void setCapacidadCarga(int capacidadCarga) {
        this.capacidadCarga = capacidadCarga;
    }

    @Override
	public String toString() {
		return "Furgoneta [cargaMax=" + cargaMax + ", capacidadCarga=" + capacidadCarga + ", getMatricula()="
				+ getMatricula() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getPrecio()="
				+ getPrecio() + ", gettCombustible()=" + gettCombustible() + ", gettCajaCambios()=" + gettCajaCambios()
				+ ", getNumPlazas()=" + getNumPlazas() + "]";
	}

	@Override
    public void mostrarInformacion() {
        System.out.println("Furgoneta - Marca: " + super.getMarca() + 
                ", Modelo: " + super.getModelo() + 
                ", Precio: " + super.getPrecio() +  
                ", Combustible: " + super.gettCombustible() + 
                ", Caja de Cambios: " + super.gettCajaCambios() + 
                ", Número de Plazas: " + super.getNumPlazas() +  
                ", Carga Máxima: " + cargaMax + 
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
	public String getTipo() {
		return "FURGONETA";
	}
}