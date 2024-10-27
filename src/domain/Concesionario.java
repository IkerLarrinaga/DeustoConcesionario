package domain;
import java.util.ArrayList;
import java.util.List;

public class Concesionario {
	private String nombre;
	private String direccion;
	private List<Vehiculo> listaCochesDisponibles;
	private List<Cliente> listaClientes;
	private List<Alquiler> historialAlquileres;

	public Concesionario() {
		super();
	}
	
	public Concesionario(String nombre, String direccion) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.listaCochesDisponibles = new ArrayList<>();
        this.listaClientes = new ArrayList<>();
        this.historialAlquileres = new ArrayList<>();
    }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public List<Vehiculo> getListaCochesDisponibles() {
		return listaCochesDisponibles;
	}

	public void setListaCochesDisponibles(List<Vehiculo> listaCochesDisponibles) {
		this.listaCochesDisponibles = listaCochesDisponibles;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}

	public List<Alquiler> getHistorialAlquileres() {
		return historialAlquileres;
	}

	public void setHistorialAlquileres(List<Alquiler> historialAlquileres) {
		this.historialAlquileres = historialAlquileres;
	}
	
	@Override
	public String toString() {
		return "Concesionario [nombre=" + nombre + ", direccion=" + direccion + ", listaCochesDisponibles="
				+ listaCochesDisponibles + ", listaClientes=" + listaClientes + ", historialAlquileres="
				+ historialAlquileres + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public void añadirCoche(Vehiculo coche) {
        listaCochesDisponibles.add(coche);
        System.out.println("Coche añadido a la flota: " + coche);
    }

    public void eliminarCoche(Vehiculo coche) {
        listaCochesDisponibles.remove(coche);
        System.out.println("Coche eliminado de la flota: " + coche);
    }

    public void registrarCliente(Cliente cliente) {
        listaClientes.add(cliente);
        System.out.println("Cliente registrado: " + cliente);
    }

    public Vehiculo consultarCocheDisponible() {
        for (Vehiculo coche : listaCochesDisponibles) {
            System.out.println(coche);
        }
        return listaCochesDisponibles.isEmpty() ? null : listaCochesDisponibles.get(0);
    }

    public void realizarAlquiler(Cliente cliente, Vehiculo coche, String fechaInicio, String fechaFin) {
        if (listaCochesDisponibles.contains(coche)) {
            Alquiler nuevoAlquiler = new Alquiler(cliente, coche, fechaInicio, fechaFin);
            historialAlquileres.add(nuevoAlquiler);
            listaCochesDisponibles.remove(coche);
            System.out.println("Alquiler realizado. Cliente: " + cliente + ", Vehículo: " + coche);
        } else {
            System.out.println("El coche no está disponible.");
        }
    }

    public void devolverCoche(Vehiculo coche) {
        listaCochesDisponibles.add(coche);
        System.out.println("Coche devuelto: " + coche);
    }
}
