package domain;

public class Coche extends Vehiculo{
	private int numPuertas;
	
	public Coche() {
		super();
	}

	public Coche(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
	}

	public Coche(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, int numPuertas) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
		this.numPuertas = numPuertas;
	}

	public Coche(int id, String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, int numPuertas) {
		super(id, matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
		this.numPuertas = numPuertas;
	}

	public int getNumPuertas() {
		return numPuertas;
	}
	
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}

	@Override
	public String toString() {
		 return super.toString() + "\n" +
	               "Número de Puertas: " + numPuertas;
	}

	public void mostrarInformacion() {
        System.out.println("Coche Estándar - Marca: " + super.getMarca() + ", Modelo: " + super.getModelo());
    }

    public void alquilar() {
        System.out.println("Alquilando coche estándar...");
    }

    public void devolver() {
        System.out.println("Devolviendo coche estándar...");
    }

	@Override
	public String getTipo() {
		return "COCHE";
	}
}
