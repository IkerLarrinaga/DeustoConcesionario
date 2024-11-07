package gui;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import domain.Alquiler;
import domain.Cliente;
import domain.Vehiculo;

public class VentanaBienvenidaEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaBienvenidaEmpleado() {
        ArrayList<Alquiler> lAlquileres = alquileresUsuario("resource/data/registro.txt");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Datos de alquiler");
        setLayout(new BorderLayout());

        String[] columnNames = {"Nombre de Usuario", "Matrícula", "Marca", "Fecha de Inicio", "Fecha Fin", "Días Restantes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Alquiler alquiler : lAlquileres) {
            String nombreUsuario = alquiler.getCliente().getNombre() + " " + alquiler.getCliente().getPrimerApellido() + " " + alquiler.getCliente().getSegundoApellido();
            String matricula = alquiler.getVehiculo().getMatricula();
            String marca = alquiler.getVehiculo().getMarca();
            String fechaInicio = alquiler.getFechaInicio();
            String fechaFin = alquiler.getFechaFin();

            LocalDate fechaFinDate = LocalDate.parse(fechaFin, formatter);
            long diasRestantes = LocalDate.now().until(fechaFinDate, java.time.temporal.ChronoUnit.DAYS);

            Object[] data = {nombreUsuario, matricula, marca, fechaInicio, fechaFin, diasRestantes};
            tableModel.addRow(data);
        }

        JTable table = new JTable(tableModel);
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

    public static void main(String[] args) {
        new VentanaBienvenidaEmpleado();
    }
}
