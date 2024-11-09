package domain;

public class Coche extends Vehiculo{
	private int numPuertas;
	private Traccion tipoTraccion;
	
	public Coche() {
		super();
	}

	public Coche(String matricula, int kilometros, String marca, String modelo, float precio, int anno,
			TipoCombustible tCombustible, TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super(matricula, kilometros, marca, modelo, precio, anno, tCombustible, tCajaCambios, potencia, numPlazas,
				gama);
	}

	public Coche(String matricula, int kilometros, String marca, String modelo, float precio, int anno,
			TipoCombustible tCombustible, TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama,
			int numPuertas, Traccion tipoTraccion) {
		super(matricula, kilometros, marca, modelo, precio, anno, tCombustible, tCajaCambios, potencia, numPlazas,
				gama);
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
		return "Coche [numPuertas=" + numPuertas + ", tipoTraccion=" + tipoTraccion + ", getKilometros()="
				+ getKilometros() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getPrecio()="
				+ getPrecio() + ", getAño()=" + getAnno() + ", gettCombustible()=" + gettCombustible()
				+ ", gettCajaCambios()=" + gettCajaCambios() + ", getPotencia()=" + getPotencia() + ", getNumPlazas()="
				+ getNumPlazas() + ", getGama()=" + getGama() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
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
		// TODO Auto-generated method stub
		return "COCHE";
	}
}
