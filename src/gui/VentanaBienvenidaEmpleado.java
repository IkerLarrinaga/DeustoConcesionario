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

    public VentanaBienvenidaEmpleado() {
        dbManager.conexion("resource/db/concesionario.db");

        List<Alquiler> lAlquileres = dbManager.obtenerTodosAlquileres();

        setSize(1000, 700); // Aumentar el tamaño de la ventana
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Datos de Alquiler");
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());
        setLayout(new BorderLayout());

        Color colorPersonalizado = new Color(92, 184, 255);
        
        // Encabezado con un diseño atractivo
        JPanel panelSuperior = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("Lista de Alquileres Activos", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.WHITE); // Color gris oscuro
        panelSuperior.add(headerLabel, BorderLayout.CENTER);
        panelSuperior.setBackground(colorPersonalizado); // Color de fondo azul
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

        // Poblar la tabla con datos
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

            Double precioAlquiler = calcularPrecioAlquiler(alquiler);

            // Crear una JProgressBar para los días restantes
            JProgressBar progressBar = new JProgressBar(0, (int) diasTotales);
            progressBar.setValue((int) diasRestantes);
            progressBar.setStringPainted(true);
            progressBar.setForeground(new Color(34, 139, 34)); // Verde para días restantes
            progressBar.setBackground(new Color(220, 220, 220)); // Color de fondo gris claro

            Object[] data = {nombreUsuario, matricula, marca, fechaInicio, fechaFin, progressBar, precioAlquiler};
            tableModel.addRow(data);
        }

        // Crear la tabla
        JTable table = new JTable(tableModel) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 5) {  // La columna de los días restantes (barra de progreso)
                    return JProgressBar.class;
                }
                return super.getColumnClass(column);
            }
        };

        // Deshabilitar el movimiento de columnas y la edición de celdas
        table.getTableHeader().setReorderingAllowed(false);  // Desactivar movimiento de columnas
        table.setDefaultEditor(Object.class, null);  // Desactivar edición de celdas
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);  // Ajustar altura de fila para mayor legibilidad

        // Colorear las filas alternadas para mejorar la legibilidad
        table.setFillsViewportHeight(true);
        table.setBackground(new Color(245, 245, 245)); // Fondo de la tabla claro
        table.setGridColor(new Color(220, 220, 220)); // Color de la cuadrícula
        table.setGridColor(Color.WHITE); 
        table.setForeground(Color.BLACK); 
        table.getTableHeader().setBackground(colorPersonalizado.darker());
        table.getTableHeader().setForeground(Color.WHITE);

        // Aplicar borde a la tabla
        Border tableBorder = BorderFactory.createLineBorder(new Color(100, 100, 100));
        table.setBorder(tableBorder);

        // Crear el JScrollPane para que la tabla sea desplazable
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);

        // Establecer un renderizador para la barra de progreso en la columna "Días Restantes"
        table.getColumnModel().getColumn(5).setCellRenderer(new TableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JProgressBar progressBar = (JProgressBar) value;
                return progressBar;
            }
        });

        setVisible(true);
    }

    private Double calcularPrecioAlquiler(Alquiler alquiler) {
        double precioPorDia = 0.0;

        // Definir los precios por día para cada tipo de vehículo
        if (alquiler.getVehiculoCoche() != null) {
            precioPorDia = alquiler.getVehiculoCoche().getPrecio();
        } else if (alquiler.getVehiculoFurgoneta() != null) {
            precioPorDia = alquiler.getVehiculoFurgoneta().getPrecio();
        } else if (alquiler.getVehiculoMoto() != null) {
            precioPorDia = alquiler.getVehiculoMoto().getPrecio();
        }

        // Calcular los días restantes
        long diasRestantes = calcularDiasRestantes(alquiler.getFechaInicio(), alquiler.getFechaFin());

        // Calcular el precio total
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
}
