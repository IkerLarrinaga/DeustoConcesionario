package domain;

public class Furgoneta extends Vehiculo{
	
	//Atributos
	private float cargaMax;
	private float litrosMaletero;
	
	public Furgoneta() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Furgoneta(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, float cargaMax, float litrosMaletero) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas);
		// TODO Auto-generated constructor stub
		this.cargaMax = cargaMax;
		this.litrosMaletero = litrosMaletero;
	}
	
	//Getters y Setters
	public float getCargaMax() {
		return cargaMax;
	}
	public void setCargaMax(float cargaMax) {
		this.cargaMax = cargaMax;
	}
	public float getLitrosMaletero() {
		return litrosMaletero;
	}
	public void setLitrosMaletero(float litrosMaletero) {
		this.litrosMaletero = litrosMaletero;
	}
	

}
