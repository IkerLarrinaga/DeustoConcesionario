package domain;

public class Moto extends Vehiculo{
	private int cilindrada;
	private boolean baul;
	
	public Moto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Moto(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama, int cilindrada, boolean baul) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
		// TODO Auto-generated constructor stub
		this.cilindrada = cilindrada;
		this.baul = baul;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public boolean isBaul() {
		return baul;
	}

	public void setBaul(boolean baul) {
		this.baul = baul;
	}

}
