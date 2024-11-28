package domain;

public class Coche extends Vehiculo{
	private int numPuertas;
	private Traccion tipoTraccion;
	
	public Coche() {
		super();
	}
	
	public Coche(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, int numPuertas, Traccion tipoTraccion) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas);
		this.numPuertas = numPuertas;
		this.tipoTraccion = tipoTraccion;
	}

	public int getNumPuertas() {
		return numPuertas;
	}
	
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}
	
	public Traccion getTipoTraccion() {
		return tipoTraccion;
	}
	
	public void setTipoTraccion(Traccion tipoTraccion) {
		this.tipoTraccion = tipoTraccion;
	}
	
	@Override
	public String toString() {
		return "Coche [numPuertas=" + numPuertas + ", tipoTraccion=" + tipoTraccion + ", getMatricula()="
				+ getMatricula() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getPrecio()="
				+ getPrecio() + ", gettCombustible()=" + gettCombustible() + ", gettCajaCambios()=" + gettCajaCambios()
				+ ", getNumPlazas()=" + getNumPlazas() + "]";
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
