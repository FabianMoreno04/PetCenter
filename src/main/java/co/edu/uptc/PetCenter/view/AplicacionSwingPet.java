package co.edu.uptc.PetCenter.view;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import co.edu.uptc.PetCenter.model.*;

public class AplicacionSwingPet extends JFrame {

    private static final long serialVersionUID = 1L;
    LogicaPeticiones logicaPeticiones = new LogicaPeticiones();

    public AplicacionSwingPet() {
        // Configura el diseño del JFrame
        setLayout(new BorderLayout());

        // Panel izquierdo para los botones
        JPanel panelIzquierdo = new JPanel(new GridLayout(4, 1));
        JButton botonCrearMascota = new JButton("Crear Mascota");
        botonCrearMascota.setBackground(Color.BLACK);
        botonCrearMascota.setForeground(Color.WHITE);
        
        JButton botonObtenerMascotas = new JButton("Obtener Mascotas");
        botonObtenerMascotas.setBackground(Color.BLACK);
        botonObtenerMascotas.setForeground(Color.WHITE);
        
        JButton botonActualizarMascotas = new JButton("Actualizar Mascotas");
        botonActualizarMascotas.setBackground(Color.BLACK);
        botonActualizarMascotas.setForeground(Color.WHITE);
        
        JButton botonBorrarMascota = new JButton("Borrar Mascota");
        botonBorrarMascota.setBackground(Color.BLACK);
        botonBorrarMascota.setForeground(Color.WHITE);
        
        // Agrega acciones a los botones (similar al que ya tienes)
        // Puedes reutilizar la lógica que ya tenías aquí
        botonObtenerMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logicaPeticiones.obtenerYMostrarMascotas();
                TablaMascotasVentana tablaMascotasVentana = new TablaMascotasVentana(logicaPeticiones.getModeloTabla());
                tablaMascotasVentana.setVisible(true);
            }
        });

        botonCrearMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CrearMascotaVentana crearMascotaVentana = new CrearMascotaVentana();
                crearMascotaVentana.setVisible(true);
            }
        });

        botonActualizarMascotas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ActualizarMascotasVentana actualizarMascotasVentana = new ActualizarMascotasVentana();
                actualizarMascotasVentana.setVisible(true);
            }
        });

        botonBorrarMascota.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EliminarMascotaVentana eliminarMascotaVentana = new EliminarMascotaVentana();
                eliminarMascotaVentana.setVisible(true);
            }
        });
        // Agrega los botones al panel izquierdo
        panelIzquierdo.add(botonCrearMascota);
        panelIzquierdo.add(botonObtenerMascotas);
        panelIzquierdo.add(botonActualizarMascotas);
        panelIzquierdo.add(botonBorrarMascota);

        // Panel central con imagen de fondo
        JPanel panelCentral = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon imagenFondo = new ImageIcon("images/Fondo.jpg");
                g.drawImage(imagenFondo.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

       /*  // JLabel que muestra un mensaje al hacer clic
        JLabel labelDescripcion = new JLabel("Aquí va la descripción");
        labelDescripcion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(null,
                        "Este es una tienda de mascotas virtual. "
                                + "Puedes agregar, actualizar, listar y borrar a tu mascota del sistema",
                        "Descripción", JOptionPane.INFORMATION_MESSAGE);
            }
        });
*/
        // Agrega los paneles al JFrame
        add(panelIzquierdo, BorderLayout.WEST);
        add(panelCentral, BorderLayout.CENTER);
       // add(labelDescripcion, BorderLayout.EAST);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplicacionSwingPet());
    }
}
