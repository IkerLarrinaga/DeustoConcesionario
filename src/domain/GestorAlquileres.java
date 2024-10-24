package domain;
import java.util.ArrayList;
import java.util.List;

public class GestorAlquileres {
    protected List<Alquiler> listaAlquileres;
    protected List<Cliente> listaClientes;
    protected List<Vehiculo> listaCoches;

    public GestorAlquileres() {
        this.listaAlquileres = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.listaCoches = new ArrayList<>();
    }

    public void gestionarAlquiler(Cliente cliente, Vehiculo coche, String fechaInicio, String fechaFin) {
        Alquiler alquiler = new Alquiler(cliente, coche, fechaInicio, fechaFin);
        listaAlquileres.add(alquiler);
        listaClientes.add(cliente);
        listaCoches.add(coche);
        System.out.println("Alquiler gestionado: Cliente " + cliente + " ha alquilado el coche " + coche);
    }

    public Alquiler consultarAlquilerPorCliente(Cliente cliente) {
        for (Alquiler alquiler : listaAlquileres) {
            if (alquiler.cliente.equals(cliente)) {
                return alquiler;
            }
        }
        System.out.println("No se encontró un alquiler para el cliente: " + cliente);
        return null;
    }

    public Vehiculo consultarCocheAlquilado(Cliente cliente) {
        for (Alquiler alquiler : listaAlquileres) {
            if (alquiler.cliente.equals(cliente)) {
                return alquiler.vehiculo;
            }
        }
        System.out.println("El cliente no ha alquilado un coche.");
        return null;
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
    
    
}
