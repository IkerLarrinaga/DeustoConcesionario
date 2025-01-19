package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.List;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import db.DataBaseManager;
import domain.Alquiler;

public class VentanaBienvenidaEmpleado extends JFrame {

    private static final long serialVersionUID = 1L;
    DataBaseManager dbManager = new DataBaseManager();
    private JTable table;

    public VentanaBienvenidaEmpleado() {
        dbManager.conexion("resource/db/concesionario.db");

        List<Alquiler> lAlquileres = dbManager.obtenerTodosAlquileres();

        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Datos de Alquiler");
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
        setLayout(new BorderLayout());

        Color colorPersonalizado = new Color(92, 184, 255);
        
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Lista de Alquileres Activos", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE);
        panelSuperior.add(headerLabel, BorderLayout.CENTER);
        panelSuperior.setBackground(colorPersonalizado);
        panelSuperior.setOpaque(true);
        
        JButton botonCerrarSesion = new JButton("Cerrar sesion");
        botonCerrarSesion.addActionListener(e -> {
        	dispose();
        	new VentanaIncio();
        });
        configurarBoton(botonCerrarSesion, new Color(255, 80, 80), new Color(255, 10, 30));
        panelSuperior.add(botonCerrarSesion, BorderLayout.EAST);
        add(panelSuperior, BorderLayout.NORTH);

        String[] columnNames = {
            "Nombre de Usuario", "Matrícula", "Marca", "Fecha de Inicio", 
            "Fecha Fin", "Días Restantes", "Precio Alquiler"
        };

        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Alquiler alquiler : lAlquileres) {
            String nombreUsuario = alquiler.getCliente().getNombre() + " " + alquiler.getCliente().getPrimerApellido() + " " + alquiler.getCliente().getSegundoApellido();
            String matricula = alquiler.getVehiculoCoche() != null ? alquiler.getVehiculoCoche().getMatricula() :
                               alquiler.getVehiculoFurgoneta() != null ? alquiler.getVehiculoFurgoneta().getMatricula() :
                               alquiler.getVehiculoMoto() != null ? alquiler.getVehiculoMoto().getMatricula() : "N/A";
            String marca = alquiler.getVehiculoCoche() != null ? alquiler.getVehiculoCoche().getMarca().toString() :
                           alquiler.getVehiculoFurgoneta() != null ? alquiler.getVehiculoFurgoneta().getMarca().toString() :
                           alquiler.getVehiculoMoto() != null ? alquiler.getVehiculoMoto().getMarca().toString() : "N/A";

            LocalDate fechaInicio = alquiler.getFechaInicio();
            LocalDate fechaFin = alquiler.getFechaFin();

            long diasRestantes = calcularDiasRestantes(alquiler.getFechaInicio(), alquiler.getFechaFin());
            long diasTotales = java.time.temporal.ChronoUnit.DAYS.between(fechaInicio, fechaFin);

            String precioAlquiler = calcularPrecioAlquiler(alquiler) + "€";

            JProgressBar progressBar = new JProgressBar(0, (int) diasTotales);
            progressBar.setValue((int) diasRestantes);
            progressBar.setStringPainted(true);
            progressBar.setForeground(new Color(34, 139, 34));
            progressBar.setBackground(new Color(220, 220, 220));

            Object[] data = {nombreUsuario, matricula, marca, fechaInicio, fechaFin, progressBar, precioAlquiler};
            tableModel.addRow(data);
        }

        table = new JTable(tableModel) {
			private static final long serialVersionUID = 1L;

			@Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {
                    return JProgressBar.class;
                }
                return super.getColumnClass(column);
            }
        };

        table.getTableHeader().setReorderingAllowed(false);
        table.setDefaultEditor(Object.class, null);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);

        table.setFillsViewportHeight(true);
        table.setBackground(new Color(245, 245, 245));
        table.setGridColor(new Color(220, 220, 220));
        table.setGridColor(Color.WHITE); 
        table.setForeground(Color.BLACK); 
        table.getTableHeader().setBackground(colorPersonalizado.darker());
        table.getTableHeader().setForeground(Color.WHITE);

        Border tableBorder = BorderFactory.createLineBorder(new Color(100, 100, 100));
        table.setBorder(tableBorder);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JProgressBar progressBar = (JProgressBar) value;
                return progressBar;
            }
        });
        configurarOrdenamiento();
        setVisible(true);
    }

    private Double calcularPrecioAlquiler(Alquiler alquiler) {
        double precioPorDia = 0.0;

        if (alquiler.getVehiculoCoche() != null) {
            precioPorDia = alquiler.getVehiculoCoche().getPrecio();
        } else if (alquiler.getVehiculoFurgoneta() != null) {
            precioPorDia = alquiler.getVehiculoFurgoneta().getPrecio();
        } else if (alquiler.getVehiculoMoto() != null) {
            precioPorDia = alquiler.getVehiculoMoto().getPrecio();
        }

        long diasRestantes = calcularDiasRestantes(alquiler.getFechaInicio(), alquiler.getFechaFin());

        return precioPorDia * diasRestantes;
    }

    private long calcularDiasRestantes(LocalDate fechaInicio, LocalDate fechaFin) {
        LocalDate hoy = LocalDate.now();

        if (fechaInicio == null || fechaFin == null) {
            System.out.println("Una de las fechas es nula.");
            return 0;
        }

        if (hoy.isBefore(fechaInicio)) {
            return java.time.temporal.ChronoUnit.DAYS.between(hoy, fechaInicio);
        }

        if (!hoy.isBefore(fechaInicio) && !hoy.isAfter(fechaFin)) {
            return java.time.temporal.ChronoUnit.DAYS.between(hoy, fechaFin);
        }
        return 0;
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
    
    private void ordenarTablaRecursivamente(int columna, boolean ascendente, int inicio) {
        DefaultTableModel modeloTabla = (DefaultTableModel) table.getModel();
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
        table.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int columna = table.columnAtPoint(e.getPoint());
                boolean ascendente = true;
                ordenarTablaRecursivamente(columna, ascendente, 0);
            }
        });
    }
}
