package co.edu.uptc.PetCenter.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.PetCenter.model.LogicaPeticiones;

public class EliminarMascotaVentana extends JFrame {

    private static final long serialVersionUID = 1L;
    private JLabel avisoLabel;
    private JTextField idTextField;
    private JButton eliminarButton;

    public EliminarMascotaVentana() {
        // Configuración de la ventana
        setSize(300, 150);
        setLocationRelativeTo(null);

        avisoLabel = new JLabel("Eliminar Mascota");
        Font font = new Font(avisoLabel.getFont().getName(), Font.PLAIN, 18);
        avisoLabel.setFont(font);
        idTextField = new JTextField(20);
        idTextField.setText("Ingrese ID");
        idTextField.setForeground(Color.GRAY);
        eliminarButton = new JButton("Eliminar");

        // Añadir el FocusListener para gestionar el texto de descripción
        idTextField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (idTextField.getText().equals("Ingrese ID")) {
                    idTextField.setText("");
                    idTextField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (idTextField.getText().isEmpty()) {
                    idTextField.setText("Ingrese ID");
                    idTextField.setForeground(Color.GRAY);
                }
            }
        });

        // Configurar el diseño
        setLayout(new BorderLayout());

        // Crear un nuevo panel con un color de fondo
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(134, 155, 207)); // Establecer el color de fondo
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(avisoLabel);
        panel.add(idTextField);
        panel.add(eliminarButton);

        // Configurar el manejador de eventos para el botón de eliminar
        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogicaPeticiones logicaPeticiones = new LogicaPeticiones();

                // Verificar si se ha ingresado el ID
                if (idTextField.getText().equals("Ingrese ID") || idTextField.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese un ID válido", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Sale del método si el ID no está ingresado
                }

                long id = Long.parseLong(idTextField.getText());

                // Verificar si el ID existe antes de intentar eliminar
                if (!logicaPeticiones.existeMascota(id)) {
                    JOptionPane.showMessageDialog(null, "La mascota con ID " + id + " no existe", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    return; // Sale del método si el ID no existe
                }

                // Realizar la eliminación si el ID existe
                logicaPeticiones.eliminarMascota(id);
                JOptionPane.showMessageDialog(null, "Mascota id: " + id + " eliminada", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Agregar componentes a la ventana
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new EliminarMascotaVentana();
        frame.setVisible(true);
    }
}
