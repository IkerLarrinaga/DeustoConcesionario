package domain;

public class Furgoneta extends Vehiculo {
	private float cargaMax;
	private int capacidadCarga;

    public Furgoneta() {
        super();
    }

	public Furgoneta(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
	}

	public Furgoneta(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, float cargaMax, int capacidadCarga) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
		this.cargaMax = cargaMax;
		this.capacidadCarga = capacidadCarga;
	}

	public Furgoneta(int id, String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, float cargaMax, int capacidadCarga) {
		super(id, matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
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
		return super.toString() + "\n" +
	               "Peso Máximo de Carga: " + cargaMax + " kg\n" +
	               "Volumen de Carga: " + capacidadCarga + " m³";
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