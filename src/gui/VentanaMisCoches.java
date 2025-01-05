package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import db.DataBaseManager;
import domain.Alquiler;
import domain.Cliente;

public class VentanaMisCoches extends JFrame {

    private DataBaseManager dbManager = new DataBaseManager();

    public VentanaMisCoches(Cliente cliente) {
        setTitle("Mis Coches");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
        setLayout(new BorderLayout());

        // Panel superior
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Mis Coches", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        panelSuperior.add(titulo, BorderLayout.CENTER);

        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> {
            dispose();
            new VentanaMarcas(); // Regresa a la ventana de marcas
        });
        panelSuperior.add(botonCerrar, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        // Tabla para mostrar los coches
        String[] columnas = {"Marca", "Modelo", "Año", "Precio"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        JTable tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior para mostrar el total
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JLabel etiquetaTotal = new JLabel("Total: $0");
        etiquetaTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelInferior.add(etiquetaTotal);
        add(panelInferior, BorderLayout.SOUTH);

        cargarAlquileresPorCliente(cliente);

        setVisible(true);
    }

    private void cargarAlquileresPorCliente(Cliente cliente) {
        // Obtener los alquileres del cliente
        List<Alquiler> alquileres = dbManager.obtenerAlquileresPorCliente(cliente);

        // Obtener el modelo de la tabla
        DefaultTableModel modeloTabla = (DefaultTableModel) ((JTable) getComponentAt(0, 1)).getModel();

        // Limpiar la tabla antes de agregar nuevos datos
        modeloTabla.setRowCount(0);

        // Agregar los vehículos alquilados a la tabla
        for (Alquiler alquiler : alquileres) {
            String marca = "", modelo = "", matricula = "", tipoVehiculo = "";
            double precio = 0;

            // Verificar si el alquiler tiene un coche
            if (alquiler.getVehiculoCoche() != null) {
                tipoVehiculo = "Coche";
                marca = alquiler.getVehiculoCoche().getMarca().getNombre();
                modelo = alquiler.getVehiculoCoche().getModelo();
                matricula = alquiler.getVehiculoCoche().getMatricula();  // Cambiado a matrícula
                precio = alquiler.getVehiculoCoche().getPrecio();
            } else if (alquiler.getVehiculoFurgoneta() != null) {
                // Verificar si el alquiler tiene una furgoneta
                tipoVehiculo = "Furgoneta";
                marca = alquiler.getVehiculoFurgoneta().getMarca().getNombre();
                modelo = alquiler.getVehiculoFurgoneta().getModelo();
                matricula = alquiler.getVehiculoFurgoneta().getMatricula();  // Cambiado a matrícula
                precio = alquiler.getVehiculoFurgoneta().getPrecio();
            } else if (alquiler.getVehiculoMoto() != null) {
                // Verificar si el alquiler tiene una moto
                tipoVehiculo = "Moto";
                marca = alquiler.getVehiculoMoto().getMarca().getNombre();
                modelo = alquiler.getVehiculoMoto().getModelo();
                matricula = alquiler.getVehiculoMoto().getMatricula();  // Cambiado a matrícula
                precio = alquiler.getVehiculoMoto().getPrecio();
            }

            // Si no se ha encontrado un vehículo en el alquiler, no agregamos la fila
            if (!marca.isEmpty()) {
                // Agregar la fila a la tabla con la matrícula en lugar del año
                Object[] fila = {marca, modelo, matricula, precio};
                modeloTabla.addRow(fila);
            }
        }

        // Actualizar el total (puedes agregar el cálculo que desees)
        double total = alquileres.stream()
            .mapToDouble(alquiler -> {
                if (alquiler.getVehiculoCoche() != null) {
                    return alquiler.getVehiculoCoche().getPrecio();
                } else if (alquiler.getVehiculoFurgoneta() != null) {
                    return alquiler.getVehiculoFurgoneta().getPrecio();
                } else if (alquiler.getVehiculoMoto() != null) {
                    return alquiler.getVehiculoMoto().getPrecio();
                }
                return 0;
            }).sum();

        JLabel etiquetaTotal = (JLabel) ((JPanel) getComponentAt(0, 2)).getComponent(0);
        etiquetaTotal.setText("Total: $" + total);
    }


}
