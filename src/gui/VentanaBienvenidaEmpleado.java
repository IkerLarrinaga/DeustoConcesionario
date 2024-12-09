package gui;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import domain.Alquiler;
import domain.Cliente;
import domain.Coche;
import domain.Furgoneta;
import domain.Marca;
import domain.Moto;
import domain.TipoVehiculo;
import domain.Vehiculo;

public class VentanaBienvenidaEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;

    public VentanaBienvenidaEmpleado() {
        ArrayList<Alquiler> lAlquileres = alquileresUsuario("resource/data/clientes.txt");

        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Datos de alquiler");
        setLayout(new BorderLayout());

        String[] columnNames = {"Nombre de Usuario", "Matrícula", "Marca", "Fecha de Inicio", "Fecha Fin", "Días Restantes"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

       for (Alquiler alquiler : lAlquileres) {
            String nombreUsuario = alquiler.getCliente().getNombre() + " " + alquiler.getCliente().getPrimerApellido() + " " + alquiler.getCliente().getSegundoApellido();
            String matricula = null;
            String marca = null;
            
            if(alquiler.getVehiculoCoche() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	marca = alquiler.getVehiculoCoche().getMarca().toString();
            } else if (alquiler.getVehiculoFurgoneta() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	marca = alquiler.getVehiculoCoche().getMarca().toString();
            } else if (alquiler.getVehiculoMoto() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	marca = alquiler.getVehiculoCoche().getMarca().toString();
            }          
            
            LocalDate fechaInicio = alquiler.getFechaInicio();
            LocalDate fechaFin = alquiler.getFechaFin();

            long diasRestantes = calcularDiasRestantes(fechaFin);

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
            Marca marca;
            while ((linea = br.readLine()) != null) {
                System.out.println("Leyendo línea: " + linea);
                String[] datos = linea.split(";");
                if (datos.length == 7) {
                    try {
                        Cliente cliente = new Cliente();
                        cliente.setNombre(datos[0]);
                        cliente.setPrimerApellido(datos[1]);
                        cliente.setSegundoApellido(datos[2]);

                        marca = Marca.valueOf(datos[3].toUpperCase());
                        Vehiculo vehiculo = crearVehiculo(datos[4], marca, datos[5], datos[6]);
                        
                        //Cambios momentaneos
                        LocalDate fechaInicio = LocalDate.parse(datos[5], formatter);
                        LocalDate fechaFin = LocalDate.parse(datos[6], formatter);

                        Alquiler alquiler = new Alquiler(0, cliente, vehiculo, null, fechaInicio, fechaFin);
                        alquileres.add(alquiler);
                        System.out.println("Alquiler creado: " + alquiler);
                    } catch (DateTimeParseException e) {
                        System.out.println("Error al parsear la fecha: " + e.getMessage());
                    }
                } else {
                    System.out.println("Formato incorrecto: " + linea);
                }
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }

        return alquileres;
    }

    private Vehiculo crearVehiculo(String tipoVehiculo, Marca marca, String modelo, String año) {
        Vehiculo vehiculo;
        if (tipoVehiculo.equals("Coche")) {
            vehiculo = new Coche();
        } else if (tipoVehiculo.equals("Furgoneta")) {
            vehiculo = new Furgoneta();
        } else {
            vehiculo = new Moto();
        }
        vehiculo.setMarca(marca);
        vehiculo.setModelo(modelo);
        return vehiculo;
    }

    //IAG: Copilot
    private long calcularDiasRestantes(LocalDate fechaFin) {
        long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), fechaFin);
        return diasRestantes < 0 ? 0 : diasRestantes;
    }
}