package gui;

import java.awt.BorderLayout;
import java.time.LocalDate;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import db.DataBaseManager;
import domain.Alquiler;

public class VentanaBienvenidaEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    DataBaseManager dbManager = new DataBaseManager();

    
    public VentanaBienvenidaEmpleado(){    	
        dbManager.conexion("resource/db/concesionario.db");

        List<Alquiler> lAlquileres = dbManager.obtenerTodosAlquileres();

        
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Datos de alquiler");
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
        setLayout(new BorderLayout());

        String[] columnNames = {"Nombre de Usuario", "Matrícula", "Marca", "Fecha de Inicio", "Fecha Fin", "Días Restantes", "Precio Alquiler"}; //tabla con coche, cliente, fechainicio, fechafin, progressbar con los días que quedan y el precio del alquiler

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

       for (Alquiler alquiler : lAlquileres) {
            String nombreUsuario = alquiler.getCliente().getNombre() + " " + alquiler.getCliente().getPrimerApellido() + " " + alquiler.getCliente().getSegundoApellido();
            String matricula = null;
            String marca = null;
            
            if (alquiler.getVehiculoCoche() != null) {
                matricula = alquiler.getVehiculoCoche().getMatricula();
                marca = alquiler.getVehiculoCoche().getMarca().toString();
            } else if (alquiler.getVehiculoFurgoneta() != null) {
                matricula = alquiler.getVehiculoFurgoneta().getMatricula(); 
                marca = alquiler.getVehiculoFurgoneta().getMarca().toString(); 
            } else if (alquiler.getVehiculoMoto() != null) {
                matricula = alquiler.getVehiculoMoto().getMatricula(); 
                marca = alquiler.getVehiculoMoto().getMarca().toString(); 
            }
            
            LocalDate fechaInicio = alquiler.getFechaInicio();
            LocalDate fechaFin = alquiler.getFechaFin();

            long diasRestantes = calcularDiasRestantes(fechaFin);
            
            Double precioAlquiler = alquiler.calcularPrecio();

            Object[] data = {nombreUsuario, matricula, marca, fechaInicio, fechaFin, diasRestantes, precioAlquiler};
            tableModel.addRow(data);
        }

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
    }

    //IAG: Copilot
    private long calcularDiasRestantes(LocalDate fechaFin) {
        long diasRestantes = java.time.temporal.ChronoUnit.DAYS.between(LocalDate.now(), fechaFin);
        return diasRestantes < 0 ? 0 : diasRestantes;
    }
} 