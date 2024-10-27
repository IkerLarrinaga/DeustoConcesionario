package domain;

public class Moto extends Vehiculo{
	protected int cilindrada;
	protected boolean baul;
	
	public Moto() {
		super();
	}
	
	public Moto(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
	}

	public Moto(int kilometros, String marca, String modelo, float precio, int año, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama, int cilindrada, boolean baul) {
		super(kilometros, marca, modelo, precio, año, tCombustible, tCajaCambios, potencia, numPlazas, gama);
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
	@Override
    public void mostrarInformacion() {
        System.out.println("Moto - Marca: " + super.marca + 
                ", Modelo: " + super.modelo + 
                ", Precio: " + super.precio + 
                ", Año: " + super.año + 
                ", Kilometros: " + super.kilometros + 
                ", Combustible: " + super.tCombustible + 
                ", Caja de Cambios: " + super.tCajaCambios + 
                ", Potencia: " + super.potencia + 
                ", Número de Plazas: " + super.numPlazas + 
                ", Gama: " + super.gama + 
                ", Cilindrada: " + cilindrada + 
                ", Baul: " + baul);}

	@Override
    public void alquilar() {
        System.out.println("Alquilando Moto...");
    }

    @Override
    public void devolver() {
        System.out.println("Devolviendo Moto...");
    }

	@Override
	public String toString() {
		return "Moto [cilindrada=" + cilindrada + ", baul=" + baul + ", getKilometros()=" + getKilometros()
				+ ", getMarca()=" + getMarca() + ", getModelo()=" + getModelo() + ", getPrecio()=" + getPrecio()
				+ ", getAño()=" + getAño() + ", gettCombustible()=" + gettCombustible() + ", gettCajaCambios()="
				+ gettCajaCambios() + ", getPotencia()=" + getPotencia() + ", getNumPlazas()=" + getNumPlazas()
				+ ", getGama()=" + getGama() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + "]";
	}

}
