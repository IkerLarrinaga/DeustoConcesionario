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

public class VentanaMisAlquileres extends JFrame {

    private static final long serialVersionUID = 3124689292218739168L;

    private Cliente cliente;
    private DataBaseManager dbManager = new DataBaseManager();

    private JTable tabla;
    private JLabel labelTotal;

    public VentanaMisAlquileres(Cliente cliente) {
        this.cliente = cliente;
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
            new VentanaMarcas(cliente);
        });
        panelSuperior.add(botonCerrar, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"Marca", "Modelo", "Matrícula", "Precio"};
        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        labelTotal = new JLabel("Total: 0€");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 16));
        panelInferior.add(labelTotal);
        add(panelInferior, BorderLayout.SOUTH);

        cargarAlquileresPorCliente();

        setVisible(true);
    }

    private void cargarAlquileresPorCliente() {
        List<Alquiler> alquileres = dbManager.obtenerAlquileresPorCliente(cliente);

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0);
        double total = 0;
        for (Alquiler alquiler : alquileres) {
            String marca = "", modelo = "", matricula = "";
            double precio = 0;

            if (alquiler.getVehiculoCoche() != null) {
                marca = alquiler.getVehiculoCoche().getMarca().getNombre();
                modelo = alquiler.getVehiculoCoche().getModelo();
                matricula = alquiler.getVehiculoCoche().getMatricula();
                precio = alquiler.getVehiculoCoche().getPrecio();
            } else if (alquiler.getVehiculoFurgoneta() != null) {
                marca = alquiler.getVehiculoFurgoneta().getMarca().getNombre();
                modelo = alquiler.getVehiculoFurgoneta().getModelo();
                matricula = alquiler.getVehiculoFurgoneta().getMatricula();
                precio = alquiler.getVehiculoFurgoneta().getPrecio();
            } else if (alquiler.getVehiculoMoto() != null) {
                marca = alquiler.getVehiculoMoto().getMarca().getNombre();
                modelo = alquiler.getVehiculoMoto().getModelo();
                matricula = alquiler.getVehiculoMoto().getMatricula();
                precio = alquiler.getVehiculoMoto().getPrecio();
            }

            if (!marca.isEmpty()) {
                modeloTabla.addRow(new Object[]{marca, modelo, matricula, precio});
                total += precio;
            }
        }

        labelTotal.setText("Total: " + total + "€.");
    }
}
