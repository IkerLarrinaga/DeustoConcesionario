package domain;

public class Moto extends Vehiculo{
	protected boolean baul;
	protected int cilindrada;
	
	
	public Moto() {
		super();
	}

	public Moto(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
	}
	
	public Moto(String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, boolean baul, int cilindrada) {
		super(matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
		this.baul = baul;
		this.cilindrada = cilindrada;
	}

	public Moto(int id, String matricula, Marca marca, String modelo, float precio, TipoCombustible tCombustible,
			TipoCajaCambios tCajaCambios, int numPlazas, boolean alquilado, boolean baul, int cilindrada) {
		super(id, matricula, marca, modelo, precio, tCombustible, tCajaCambios, numPlazas, alquilado);
		this.baul = baul;
		this.cilindrada = cilindrada;
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
        System.out.println("Moto - Marca: " + super.getMarca() + 
                ", Modelo: " + super.getModelo() + 
                ", Precio: " + super.getPrecio() + 
                ", Combustible: " + super.gettCombustible() + 
                ", Caja de Cambios: " + super.gettCajaCambios() + 
                ", Número de Plazas: " + super.getNumPlazas() + 
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
		return super.toString() + "\n" + 
	            "Baúl: " + (baul ? "Sí" : "No") + "\n" + 
				"Cilindrada: " + cilindrada;
	}

	@Override
	public String getTipo() {
		return "MOTO";
	}

}
