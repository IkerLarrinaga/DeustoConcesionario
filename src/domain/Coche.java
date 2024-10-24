package domain;

public class Coche extends Vehiculo{
	protected int numPuertas;
	protected String tipoTraccion;
	
	
	
	
	
	public Coche() {
		super();
	}
	public Coche(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
	}
	public Coche(int numPuertas, String tipoTraccion) {
		super();
		this.numPuertas = numPuertas;
		this.tipoTraccion = tipoTraccion;
	}
	public int getNumPuertas() {
		return numPuertas;
	}
	public void setNumPuertas(int numPuertas) {
		this.numPuertas = numPuertas;
	}
	public String getTipoTraccion() {
		return tipoTraccion;
	}
	public void setTipoTraccion(String tipoTraccion) {
		this.tipoTraccion = tipoTraccion;
	}
	@Override
	public String toString() {
		return "Coche [numPuertas=" + numPuertas + ", tipoTraccion=" + tipoTraccion + ", getKilometros()="
				+ getKilometros() + ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getPrecio()="
				+ getPrecio() + ", getAño()=" + getAño() + ", gettCombustible()=" + gettCombustible()
				+ ", gettCajaCambios()=" + gettCajaCambios() + ", getPotencia()=" + getPotencia() + ", getNumPlazas()="
				+ getNumPlazas() + ", getGama()=" + getGama() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	public void mostrarInformacion() {
        System.out.println("Coche Estándar - Marca: " + super.marca + ", Modelo: " + super.modelo);
    }

    public void alquilar() {
        System.out.println("Alquilando coche estándar...");
    }

    public void devolver() {
        System.out.println("Devolviendo coche estándar...");
    }


}
