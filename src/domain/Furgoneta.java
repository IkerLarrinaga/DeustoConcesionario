package domain;

public class Furgoneta extends Vehiculo{
	
	//Atributos
	private float cargaMax;
	private boolean techoAlto;
	
	public Furgoneta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Furgoneta(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, float cargaMax, boolean techoAlto) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas);
		// TODO Auto-generated constructor stub
		this.cargaMax = cargaMax;
		this.techoAlto = techoAlto;
	}
	
	//Getters y Setters
	public float getCargaMax() {
		return cargaMax;
	}
	public void setCargaMax(float cargaMax) {
		this.cargaMax = cargaMax;
	}
	

}
