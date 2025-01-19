package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.temporal.ChronoUnit;
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

    private static final long serialVersionUID = 1L;

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

        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Mis Coches", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 20));
        titulo.setForeground(Color.WHITE);
        panelSuperior.add(titulo, BorderLayout.CENTER);
        Color colorPersonalizado = new Color(92, 184, 255);
        panelSuperior.setBackground(colorPersonalizado);

        JButton botonCerrar = new JButton("Cerrar");
        botonCerrar.addActionListener(e -> {
            dispose();
            new VentanaMarcas(cliente);
        });
        configurarBoton(botonCerrar, new Color(255, 80, 80), new Color(255, 10, 30));

        panelSuperior.add(botonCerrar, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        String[] columnas = {"Marca", "Modelo", "Matrícula", "Tiempo del alquiler", "Precio/Día", "Total"};        

        DefaultTableModel modeloTabla = new DefaultTableModel(columnas, 0) {
            private static final long serialVersionUID = 1L;
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tabla = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(scrollPane, BorderLayout.CENTER);
        tabla.setGridColor(Color.WHITE); 
        tabla.setForeground(Color.BLACK); 
        tabla.getTableHeader().setBackground(colorPersonalizado.darker());
        tabla.getTableHeader().setForeground(Color.WHITE);

        JPanel panelInferior = new JPanel(new BorderLayout());

        JLabel labelInfo = new JLabel("   Click en los títulos para ordenar ascendentemente.");
        labelInfo.setFont(new Font("Arial", Font.PLAIN, 12));
        labelInfo.setForeground(Color.WHITE);
        panelInferior.add(labelInfo, BorderLayout.WEST);

        labelTotal = new JLabel("Total: 0.00€");
        labelTotal.setFont(new Font("Arial", Font.BOLD, 16));
        labelTotal.setForeground(Color.WHITE);
        panelInferior.add(labelTotal, BorderLayout.EAST);

        panelInferior.setBackground(colorPersonalizado);
        add(panelInferior, BorderLayout.SOUTH);

        cargarAlquileresPorCliente();
        configurarOrdenamiento();

        setVisible(true);
    }

    private void configurarBoton(JButton boton, Color colorAntes, Color colorDespues) {
        boton.setBackground(colorAntes);
        boton.setForeground(Color.WHITE);
        boton.setFocusable(false);
        boton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                boton.setBackground(colorDespues);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                boton.setBackground(colorAntes);
            }
        });
    }

    private void cargarAlquileresPorCliente() {
        dbManager.conexion("resource/db/concesionario.db");
        List<Alquiler> alquileres = dbManager.obtenerAlquileresPorCliente(cliente);

        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0);
        double total = 0;
        for (Alquiler alquiler : alquileres) {
            String marca = "", modelo = "", matricula = "";
            int dias = 0;
            double precio = 0.00d, precioTotal = 0.00d;

            if (alquiler.getVehiculoCoche() != null) {
                marca = alquiler.getVehiculoCoche().getMarca().getNombre();
                modelo = alquiler.getVehiculoCoche().getModelo();
                matricula = alquiler.getVehiculoCoche().getMatricula();
                dias = (int) ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
                precio = alquiler.getVehiculoCoche().getPrecio();
                precioTotal = precio * dias;

            } else if (alquiler.getVehiculoFurgoneta() != null) {
                marca = alquiler.getVehiculoFurgoneta().getMarca().getNombre();
                modelo = alquiler.getVehiculoFurgoneta().getModelo();
                matricula = alquiler.getVehiculoFurgoneta().getMatricula();
                dias = (int) ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
                precio = alquiler.getVehiculoFurgoneta().getPrecio();
                precioTotal = precio * dias;
            } else if (alquiler.getVehiculoMoto() != null) {
                marca = alquiler.getVehiculoMoto().getMarca().getNombre();
                modelo = alquiler.getVehiculoMoto().getModelo();
                matricula = alquiler.getVehiculoMoto().getMatricula();
                dias = (int) ChronoUnit.DAYS.between(alquiler.getFechaInicio(), alquiler.getFechaFin());
                precio = alquiler.getVehiculoMoto().getPrecio();
                precioTotal = precio * dias;
            }

            if (!marca.isEmpty()) {
                modeloTabla.addRow(new Object[]{marca, modelo, matricula, dias, precio, precioTotal});
                total += precioTotal;
            }
        }

        labelTotal.setText("Total: " + total + "€   ");
        dbManager.desconexion();
    }

    private void ordenarTablaRecursivamente(int columna, boolean ascendente, int inicio) {
        DefaultTableModel modeloTabla = (DefaultTableModel) tabla.getModel();
        int rowCount = modeloTabla.getRowCount();

        if (inicio >= rowCount - 1) {
            return;
        }

        for (int i = inicio + 1; i < rowCount; i++) {
            Object valor1 = modeloTabla.getValueAt(inicio, columna);
            Object valor2 = modeloTabla.getValueAt(i, columna);

            boolean condicion;

            if (valor1 instanceof Comparable && valor2 instanceof Comparable) {
                @SuppressWarnings("unchecked")
                Comparable<Object> comparador1 = (Comparable<Object>) valor1;
                @SuppressWarnings("unchecked")
                Comparable<Object> comparador2 = (Comparable<Object>) valor2;

                condicion = ascendente ? comparador1.compareTo(comparador2) > 0 : comparador1.compareTo(comparador2) < 0;

                if (condicion) {
                    intercambiarFilas(modeloTabla, inicio, i);
                }
            }
        }

        ordenarTablaRecursivamente(columna, ascendente, inicio + 1);
    }

    private void intercambiarFilas(DefaultTableModel modeloTabla, int fila1, int fila2) {
        int columnCount = modeloTabla.getColumnCount();
        Object[] tempRow = new Object[columnCount];

        for (int col = 0; col < columnCount; col++) {
            tempRow[col] = modeloTabla.getValueAt(fila1, col);
        }

        for (int col = 0; col < columnCount; col++) {
            modeloTabla.setValueAt(modeloTabla.getValueAt(fila2, col), fila1, col);
            modeloTabla.setValueAt(tempRow[col], fila2, col);
        }
    }

    private void configurarOrdenamiento() {
        tabla.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columna = tabla.columnAtPoint(e.getPoint());
                boolean ascendente = true;
                ordenarTablaRecursivamente(columna, ascendente, 0);
            }
        });
    }
}
