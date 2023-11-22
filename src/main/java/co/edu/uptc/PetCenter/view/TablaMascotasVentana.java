package co.edu.uptc.PetCenter.view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.Image;

public class TablaMascotasVentana extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tabla;

    public TablaMascotasVentana(DefaultTableModel modeloTabla) {
        // Configura el diseño de la ventana de la tabla
        setLayout(new BorderLayout());

        // Crea la tabla con el modelo proporcionado
        tabla = new JTable(modeloTabla);

        // Personaliza el renderizador de celdas para establecer el color de fondo
        DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer();
        renderizador.setBackground(new Color(134, 155, 207));
        tabla.setDefaultRenderer(Object.class, renderizador);

        // Agrega la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(tabla);

        // Agrega el JScrollPane al centro de la ventana
        add(scrollPane, BorderLayout.CENTER);

        // Crea un JLabel con un ImageIcon que representa la imagen
        ImageIcon imagenOriginal = new ImageIcon("PetCenter\\images\\tabla.jpg"); // Reemplaza con la ruta de tu imagen
        Image imagenEscalada = imagenOriginal.getImage().getScaledInstance(150, 200, Image.SCALE_SMOOTH);
        ImageIcon imagenEscaladaIcon = new ImageIcon(imagenEscalada);
        JLabel imagenLabel = new JLabel(imagenEscaladaIcon);

        // Agrega el JLabel con la imagen en la parte inferior del panel
        add(imagenLabel, BorderLayout.SOUTH);

        // Configura el tamaño y la ubicación de la ventana
        setSize(600, 500); // Aumenté la altura para dar espacio a la imagen
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Cierra solo la ventana actual al presionar la "X"
    }
}
