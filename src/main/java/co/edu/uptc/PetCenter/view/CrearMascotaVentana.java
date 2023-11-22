package co.edu.uptc.PetCenter.view;

import javax.swing.*;

import co.edu.uptc.PetCenter.model.LogicaPeticiones;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CrearMascotaVentana extends JFrame {
    private JLabel avisoLabel;
    private JTextField nombreTextField;
    private JTextField categoriaTextField;
    private JComboBox<String> estadoComboBox; // JComboBox para el estado
    private JButton guardarButton;

    private String nombreGuardado;
    private String categoriaGuardada;
    private boolean estadoGuardado; // Nuevo campo para el estado

    public CrearMascotaVentana() {
        // Configuración de la ventana
        setTitle("Crear Mascota");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setResizable(false); // Deshabilita la redimensión de la ventana

        // Crear componentes
        avisoLabel = new JLabel("   Crear Mascota");
        nombreTextField = new JTextField(20); // Establece el tamaño fijo
        categoriaTextField = new JTextField(20); // Establece el tamaño fijo
        estadoComboBox = new JComboBox<>(new String[]{"Activo", "Inactivo"}); // Opciones de estado
        guardarButton = new JButton("Guardar");

        // Configurar el diseño con GridBagLayout
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Configurar JLabel y JTextField para Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Nombre:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nombreTextField, gbc);

        // Configurar JLabel y JTextField para Categoría
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Categoría:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(categoriaTextField, gbc);

        // Configurar JLabel y JComboBox para Estado
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Estado:"), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(estadoComboBox, gbc);

        // Configurar JButton
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(guardarButton, gbc);

        // Configurar el manejador de eventos para el botón de guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMascota();

                // Cerrar la ventana actual
                dispose();
            }
        });

        // Agregar componentes a la ventana
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(avisoLabel, gbc);

        // Agregar color de fondo al panel principal
        getContentPane().setBackground(new Color(134, 155, 207));
    }

    private void guardarMascota() {
        // Obtener el texto de los campos de texto y el estado seleccionado
        String nombreGuardado = nombreTextField.getText();
        String categoriaGuardada = categoriaTextField.getText();
        boolean estadoGuardado = estadoComboBox.getSelectedItem().equals("Activo");

        // Llama a tu lógica para crear una nueva mascota
        LogicaPeticiones logicaPeticiones = new LogicaPeticiones();
        logicaPeticiones.crearNuevaMascota(nombreGuardado, categoriaGuardada, estadoGuardado);

        // Muestra un mensaje de éxito
        JOptionPane.showMessageDialog(this, "Mascota guardada", "Éxito", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CrearMascotaVentana().setVisible(true);
            }
        });
    }
}
