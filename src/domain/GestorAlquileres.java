package domain;
import java.time.LocalDate;
import java.util.List;

public class GestorAlquileres {
    private List<Alquiler> listaAlquileres;
    private List<Cliente> listaClientes;
    private List<Vehiculo> listaCoches;
    
    public GestorAlquileres() {
		super();
	}

	public GestorAlquileres(List<Alquiler> listaAlquileres, List<Cliente> listaClientes, List<Vehiculo> listaCoches) {
		super();
		this.listaAlquileres = listaAlquileres;
		this.listaClientes = listaClientes;
		this.listaCoches = listaCoches;
	}

	public List<Alquiler> getListaAlquileres() {
		return listaAlquileres;
	}

	public void setListaAlquileres(List<Alquiler> listaAlquileres) {
		this.listaAlquileres = listaAlquileres;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Vehiculo> getListaCoches() {
		return listaCoches;
	}

	public void setListaCoches(List<Vehiculo> listaCoches) {
		this.listaCoches = listaCoches;
	}

	@Override
	public String toString() {
		return "GestorAlquileres [listaAlquileres=" + listaAlquileres + ", listaClientes=" + listaClientes
				+ ", listaCoches=" + listaCoches + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ ", toString()=" + super.toString() + "]";
	}

    public void gestionarAlquiler(Cliente cliente, Vehiculo coche, LocalDate fechaInicio, LocalDate fechaFin) {
        Alquiler alquiler = new Alquiler(cliente, coche, fechaInicio, fechaFin);
        listaAlquileres.add(alquiler);
        listaClientes.add(cliente);
        listaCoches.add(coche);
        System.out.println("Alquiler gestionado: Cliente " + cliente + " ha alquilado el coche " + coche);
    }

    public Alquiler consultarAlquilerPorCliente(Cliente cliente) {
        for (Alquiler alquiler : listaAlquileres) {
            if (alquiler.getCliente().equals(cliente)) {
                return alquiler;
            }
        }
        System.out.println("No se encontró un alquiler para el cliente: " + cliente);
        return null;
    }

    public Vehiculo consultarCocheAlquilado(Cliente cliente) {
        for (Alquiler alquiler : listaAlquileres) {
            if (alquiler.getCliente().equals(cliente)) {
                return alquiler.getVehiculo();
            }
        }
        System.out.println("El cliente no ha alquilado un coche.");
        return null;
    }
}
