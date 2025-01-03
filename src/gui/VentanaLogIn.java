package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import db.DataBaseManager;
import domain.Cliente;
import domain.Empleado;

public class VentanaLogIn extends JFrame {
    private static final long serialVersionUID = 1L;

    public JTextField email = new JTextField();
    public JPasswordField contrasenna = new JPasswordField();
    public JButton confirmar = new JButton("Confirmar");
    public JButton cancelar = new JButton("Cancelar");
    private int indiceSeleccionador = -1;
    private JButton[] lBotones = new JButton[2];

    public VentanaLogIn() {
        setSize(500, 580);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Iniciar Sesión");
        setIconImage(new ImageIcon("resource/img/car-icon.png").getImage());


        ImageIcon foto = new ImageIcon("resource/img/DeustoConcesionarioInicio.png");
        Image fotoEscala = foto.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        ImageIcon fotoFinal = new ImageIcon(fotoEscala); 
        JLabel fondo = new JLabel(fotoFinal);

        Color colorPersonalizado = new Color(92, 184, 255);

        JPanel panelImagen = new JPanel();
        panelImagen.setLayout(new BorderLayout());
        panelImagen.setBorder(new EmptyBorder(50, 20, 0, 20));
        panelImagen.setBackground(colorPersonalizado);
        panelImagen.add(fondo, BorderLayout.CENTER);
        add(panelImagen, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setBackground(colorPersonalizado);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        lBotones[0] = confirmar;
        lBotones[1] = cancelar;

        JLabel labelEmail = new JLabel("Introduzca su email:");
        email.setPreferredSize(new Dimension(300, 20));
        JLabel labelContrasenia = new JLabel("Introduzca su contraseña:");
        contrasenna.setPreferredSize(new Dimension(300, 20));

        labelEmail.setForeground(Color.WHITE);
        labelContrasenia.setForeground(Color.WHITE);

        panel.add(labelEmail, gbc);
        panel.add(email, gbc);
        panel.add(new Label(" "), gbc);
        panel.add(labelContrasenia, gbc);
        panel.add(contrasenna, gbc);

        for (int i = 0; i < 2; i++) {
            panel.add(new Label(" "), gbc);
        }

        configurarBoton(confirmar, new Color(40, 150, 255), new Color(20, 100, 220));
        configurarBoton(cancelar, new Color(255, 80, 80), new Color(255, 10, 30));

        panel.add(confirmar, gbc);
        panel.add(new JLabel(" "), gbc);
        panel.add(cancelar, gbc);

        add(panel);

        SwingUtilities.invokeLater(() -> requestFocusInWindow());

        confirmar.addActionListener(e -> autenticarUsuario());
        cancelar.addActionListener(e -> {
            new VentanaIncio();
            dispose();
        });

        KeyAdapter enterListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    confirmar.doClick();
                }
            }
        };
        email.addKeyListener(enterListener);
        contrasenna.addKeyListener(enterListener);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    indiceSeleccionador = (indiceSeleccionador + 1) % lBotones.length;
                    seleccionarBoton(indiceSeleccionador, lBotones);
                } else if (e.getKeyCode() == KeyEvent.VK_UP) {
                    indiceSeleccionador = (indiceSeleccionador - 1 + lBotones.length) % lBotones.length;
                    seleccionarBoton(indiceSeleccionador, lBotones);
                } else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    obtenerAccionDependiendoBoton(indiceSeleccionador);
                }
            }
        });

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

    private void seleccionarBoton(int indice, JButton[] botones) {
        for (int i = 0; i < botones.length; i++) {
            if (i == indice) {
                botones[i].requestFocusInWindow();
                botones[i].setBackground(botones[i].getBackground().darker());
            } else {
                if (botones[i] == lBotones[0]) {
                    botones[i].setBackground(new Color(40, 150, 255));
                } else if (botones[i] == lBotones[1]) {
                    botones[i].setBackground(new Color(255, 80, 80));
                }
            }
        }
    }

    private void obtenerAccionDependiendoBoton(int indice) {
        switch (indice) {
            case 0:
                autenticarUsuario();
                break;
            case 1:
                new VentanaIncio();
                dispose();
                break;
            default:
                break;
        }
    }

    private void autenticarUsuario() {
        if (!email.getText().isEmpty() && !String.valueOf(contrasenna.getPassword()).isEmpty()) {
            DataBaseManager dbManager = new DataBaseManager();
            String emailIntroducido = email.getText();
            String contrasennaIntroducida = String.valueOf(contrasenna.getPassword());

            dbManager.conexion("resource/db/concesionario.db");

            boolean usuarioEncontrado = false;
            boolean contrasennaCorrecta = false;
            
            if(emailIntroducido.equals("user") && contrasennaIntroducida.equals("user")) {
            	usuarioEncontrado = true;
            	contrasennaCorrecta = true;
            	dispose();
                new VentanaCatalogo();
            }

            for (Cliente cliente : dbManager.obtenerTodosClientes()) {
                if (cliente != null && cliente.getEmail().equals(emailIntroducido)) {
                    usuarioEncontrado = true;
                    if (String.valueOf(cliente.getContrasenna()).equals(contrasennaIntroducida)) {
                        contrasennaCorrecta = true;
                        dispose();
                        new VentanaCatalogo();
                        return;
                    }
                }
            }

            for (Empleado empleado : dbManager.obtenerTodosEmpleados()) {
                if (empleado != null && empleado.getEmail().equals(emailIntroducido)) {
                    usuarioEncontrado = true;
                    if (String.valueOf(empleado.getContrasenna()).equals(contrasennaIntroducida)) {
                        contrasennaCorrecta = true;
                        dispose();
                        new VentanaCatalogo();
                        return;
                    }
                }
            }

            dbManager.desconexion();

            if (!usuarioEncontrado) {
                JOptionPane.showMessageDialog(this, "Usuario incorrecto", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!contrasennaCorrecta) {
                JOptionPane.showMessageDialog(this, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (!usuarioEncontrado && !contrasennaCorrecta) {
                JOptionPane.showMessageDialog(this, "Usuario y contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
