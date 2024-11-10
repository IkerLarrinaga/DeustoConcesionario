package gui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Alquiler;
import domain.Cliente;
import domain.Vehiculo;

public class VentanaCarrito extends JFrame{
	
	private static final long serialVersionUID = 1L;

	public VentanaCarrito(){
		
		ArrayList<Alquiler> lAlquileres = alquileresUsuario("resource/data/registro.txt");
		
		setTitle("Ventana Carrito");
	    setSize(500, 400);
	    setLocationRelativeTo(null);
	    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    String[] columnas = {"Marca", "Matricula", "Fecha Inicio", "Fecha Fin"};
	    DefaultTableModel tablemodel = new DefaultTableModel(columnas, 0);
	    
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        for (Alquiler alquiler : lAlquileres) {
            String marca = alquiler.getVehiculo().getMarca();
            String matricula = alquiler.getVehiculo().getMatricula();
            String fechaInicio = alquiler.getFechaInicio();
            String fechaFin = alquiler.getFechaFin();


            Object[] data = {marca, matricula, fechaInicio, fechaFin};
            tablemodel.addRow(data);
        }

        JTable table = new JTable(tablemodel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
        
	    setVisible(true);
	}
	
	
	
	private ArrayList<Alquiler> alquileresUsuario(String rutaArchivo) {
        ArrayList<Alquiler> alquileres = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 7) {
                    Cliente cliente = new Cliente();
                    cliente.setNombre(datos[0]);
                    cliente.setPrimerApellido(datos[1]);

                    Vehiculo vehiculo = new Vehiculo(datos[2], 0, datos[3], datos[4], 0, 0, null, null, 0, 0, null) {
                        public void mostrarInformacion() {}
                        public void alquilar() {}
                        public void devolver() {}
						@Override
						public String getTipo() {
							return null;
						}
                    };

                    Alquiler alquiler = new Alquiler(cliente, vehiculo, datos[5], datos[6]);
                    alquileres.add(alquiler);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

}
