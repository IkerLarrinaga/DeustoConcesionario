package domain;

public class Coche extends Vehiculo{
	private int numPuertas;
	private String tipoTraccion;
	
	public Coche() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Coche(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama, int numPuertas, String tipoTraccion) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
		// TODO Auto-generated constructor stub
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

}
