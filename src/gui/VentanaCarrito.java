package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Alquiler;
import domain.Cliente;
import domain.Marca;
import domain.Vehiculo;

public class VentanaCarrito extends JFrame {

	private DefaultTableModel tableModel; 
	private JButton btnVolver;
    private static final long serialVersionUID = 1L;

    public VentanaCarrito() {
        ArrayList<Alquiler> lAlquileres = alquileresUsuario("resource/data/registro.txt");

        setTitle("Ventana Carrito");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        String[] columnas = {"Modelo", "Matricula", "Fecha Inicio", "Fecha Fin"};
        tableModel = new DefaultTableModel(columnas, 0);
        
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        
        table.getTableHeader().setReorderingAllowed(false);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (Alquiler alquiler : lAlquileres) {
        	String matricula = null;
            String modelo = null;
            
            if(alquiler.getVehiculoCoche() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	modelo = alquiler.getVehiculoCoche().getModelo().toString();
            } else if (alquiler.getVehiculoFurgoneta() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	modelo = alquiler.getVehiculoCoche().getModelo().toString();
            } else if (alquiler.getVehiculoMoto() != null) {
            	matricula = alquiler.getVehiculoCoche().getMatricula();
            	modelo = alquiler.getVehiculoCoche().getModelo().toString();
            }   
            LocalDate fechaInicio = alquiler.getFechaInicio();
            LocalDate fechaFin = alquiler.getFechaFin();

            Object[] data = {modelo, matricula, fechaInicio, fechaFin};
            tableModel.addRow(data);         
        }
        
        btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
                dispose();
            	new VentanaCatalogo();                                        
            }
        });  
        
        JPanel panelBoton = new JPanel();
        panelBoton.add(btnVolver);
        add(panelBoton, BorderLayout.SOUTH);
        add(scrollPane);

        setVisible(true);
    }
    
    public void agregarVehiculo(String modelo, String matricula, LocalDate fechaInicio, LocalDate fechaFin) {
    	System.out.println("Agregando vehículo: " + modelo + ", " + matricula);
        Object[] data = {modelo, matricula, fechaInicio.toString(), fechaFin.toString()};
        tableModel.addRow(data);
        
        revalidate();
        repaint();
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

                    Marca marca;
                    try {
                        marca = Marca.valueOf(datos[3].toUpperCase());
                    } catch (IllegalArgumentException e) {
                        System.err.println("Marca inválida encontrada: " + datos[3]);
                        continue;
                    }

                    Vehiculo vehiculo = new Vehiculo() {
                        public void mostrarInformacion() {}
                        public void alquilar() {}
                        public void devolver() {}
                        @Override
                        public String getTipo() {
                            return marca.toString();
                        }
                    };

                    LocalDate fechaInicio = LocalDate.parse(datos[5], formatter);
                    LocalDate fechaFin = LocalDate.parse(datos[6], formatter);

                    Alquiler alquiler = new Alquiler(cliente, vehiculo, null, null, fechaInicio, fechaFin);
                    alquileres.add(alquiler);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return alquileres;
    }
}