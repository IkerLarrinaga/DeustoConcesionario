package domain;

public abstract class Vehiculo {
	private String matricula;
	private int kilometros;
	private String marca;
	private String modelo;
	private float precio;
	private int anno;
	private TipoCombustible tCombustible;
	private TipoCajaCambios tCajaCambios;
	private int potencia;
	private int numPlazas;
	private Gama gama;
	
	public Vehiculo() {
		
	}

	public Vehiculo(String matricula, int kilometros, String marca, String modelo, float precio, int anno,
			TipoCombustible tCombustible, TipoCajaCambios tCajaCambios, int potencia, int numPlazas, Gama gama) {
		super();
		this.matricula = matricula;
		this.kilometros = kilometros;
		this.marca = marca;
		this.modelo = modelo;
		this.precio = precio;
		this.anno = anno;
		this.tCombustible = tCombustible;
		this.tCajaCambios = tCajaCambios;
		this.potencia = potencia;
		this.numPlazas = numPlazas;
		this.gama = gama;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public int getKilometros() {
		return kilometros;
	}

	public void setKilometros(int kilometros) {
		this.kilometros = kilometros;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public int getAnno() {
		return anno;
	}

	public void setAnno(int anno) {
		this.anno = anno;
	}

	public TipoCombustible gettCombustible() {
		return tCombustible;
	}

	public void settCombustible(TipoCombustible tCombustible) {
		this.tCombustible = tCombustible;
	}

	public TipoCajaCambios gettCajaCambios() {
		return tCajaCambios;
	}

	public void settCajaCambios(TipoCajaCambios tCajaCambios) {
		this.tCajaCambios = tCajaCambios;
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}

	public int getNumPlazas() {
		return numPlazas;
	}

	public void setNumPlazas(int numPlazas) {
		this.numPlazas = numPlazas;
	}

	public Gama getGama() {
		return gama;
	}

	public void setGama(Gama gama) {
		this.gama = gama;
	}

	@Override
	public String toString() {
		return "Vehiculo [kilometros=" + kilometros + ", marca=" + marca + ", modelo=" + modelo + ", precio=" + precio
				+ ", año=" + anno + ", tCombustible=" + tCombustible + ", tCajaCambios=" + tCajaCambios + ", potencia="
				+ potencia + ", numPlazas=" + numPlazas + ", gama=" + gama + "]";
	}
	
	public abstract void mostrarInformacion();
	
    public abstract void alquilar();
    
    public abstract void devolver();
}
