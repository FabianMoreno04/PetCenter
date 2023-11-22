package co.edu.uptc.PetCenter.view;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import co.edu.uptc.PetCenter.model.LogicaPeticiones;
import co.edu.uptc.PetCenter.model.Pet;

public class ActualizarMascotasVentana extends JFrame {
    private static final long serialVersionUID = 1L;
    private JLabel avisoLabel;
    private JTextField nombreTextField;
    private JTextField categoriaTextField;
    private JTextField idTextField;
    private JCheckBox estadoCheckBox;
    private JButton guardarButton;
    private LogicaPeticiones logicaPeticiones;

    public ActualizarMascotasVentana() {
        logicaPeticiones = new LogicaPeticiones();
        setTitle("Actualizar Mascota");
        setSize(300, 270);
        setLocationRelativeTo(null);

        // Crear componentes
        avisoLabel = new JLabel("Actualizar Mascota");
        nombreTextField = new JTextField(20);
        categoriaTextField = new JTextField(20);
        idTextField = new JTextField(20);
        estadoCheckBox = new JCheckBox("disponible");
        guardarButton = new JButton("Actualizar");

        // Configurar el diseño
        setLayout(new BorderLayout());

        // Crear el panel y configurar el fondo de color
        JPanel panel = new JPanel();
        panel.setBackground(new Color(134, 155, 207));  
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.add(new JLabel("ID:"));
        panel.add(idTextField);
        panel.add(new JLabel("Nombre:"));
        panel.add(nombreTextField);
        panel.add(new JLabel("Categoría:"));
        panel.add(categoriaTextField);
        panel.add(new JLabel("Estado:"));
        panel.add(estadoCheckBox);
        panel.add(new JLabel());
        panel.add(guardarButton);

        // Configurar el manejador de eventos para el botón de guardar
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long id = Long.parseLong(idTextField.getText());
                boolean estado = estadoCheckBox.isSelected();
                String nombre = nombreTextField.getText();
                String categoria = categoriaTextField.getText();

                Pet mascotaActualizada = new Pet();
                mascotaActualizada.setName(nombre);
                mascotaActualizada.setCategory(categoria);
                mascotaActualizada.setStatus(estado);
                logicaPeticiones.actualizarMascota(id, mascotaActualizada);
                JOptionPane.showMessageDialog(null, "Mascota id: " + id + " actualizada", "Éxito",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        // Agregar componentes a la ventana
        add(avisoLabel, BorderLayout.NORTH);
        add(panel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        JFrame frame = new ActualizarMascotasVentana();
        frame.setVisible(true);
    }
}
