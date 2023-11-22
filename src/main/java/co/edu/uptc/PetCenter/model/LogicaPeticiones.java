package co.edu.uptc.PetCenter.model;
import org.json.JSONArray;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import java.io.OutputStream;
import javax.swing.table.DefaultTableModel;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class LogicaPeticiones {
	 public DefaultTableModel modeloTabla;
	 private static final String SERVER_URL = "http://localhost:8081/pets";
	 // Método para obtener y mostrar las mascotas
    public void obtenerYMostrarMascotas() {
        try {
            // Realiza una solicitud HTTP GET para obtener mascotas desde la aplicación Spring Boot
            URL url = new URL(SERVER_URL);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("GET");

            // Lee la respuesta
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            // Parsea la respuesta JSON y agrega las filas a la tabla
            JSONArray arrayMascotas = new JSONArray(respuesta.toString());

            DefaultTableModel modeloTabla = new DefaultTableModel();
            modeloTabla.addColumn("ID");
            modeloTabla.addColumn("Nombre");
            modeloTabla.addColumn("Tipo");
            modeloTabla.addColumn("Estado");

            for (int i = 0; i < arrayMascotas.length(); i++) {
                JSONObject mascota = arrayMascotas.getJSONObject(i);
                Long id = mascota.getLong("id");
                String nombre = mascota.optString("name", "");  // Corregir el nombre de la propiedad
                String tipo = mascota.optString("category", "");  // Corregir el nombre de la propiedad
                boolean estado = mascota.optBoolean("status");
                modeloTabla.addRow(new Object[]{id, nombre, tipo, estado});
            }

            setModeloTabla(modeloTabla);
            conexion.disconnect();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void actualizarMascota(Long id, Pet pet) {
        try {
            // Construir la URL para la actualización de la mascota
            URL url = new URL(SERVER_URL +"/"+ id);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("PUT");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            // Convertir el objeto Pet a formato JSON
            String jsonInputString = convertirPetAJson(pet);

            // Enviar la solicitud con los datos actualizados
            try (OutputStream os = conexion.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
            StringBuilder respuesta = new StringBuilder();
            String linea;
            while ((linea = lector.readLine()) != null) {
                respuesta.append(linea);
            }
            lector.close();

            // Imprimir la respuesta del servidor
            System.out.println("Respuesta del servidor: " + respuesta.toString());

            conexion.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String convertirPetAJson(Pet pet) throws IOException {
        // Convertir el objeto Pet a formato JSON usando Jackson
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(pet);
    }
    
    public void eliminarMascota(Long id) {
        try {
            // Verificar si la mascota con el ID proporcionado existe
            if (!existeMascota(id)) {
                System.out.println("La mascota con ID " + id + " no existe.");
                return;
            }
    
            // Construir la URL para la eliminación de la mascota
            URL url = new URL(SERVER_URL + "/" + id);
            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("DELETE");
    
            // Leer la respuesta del servidor
            int responseCode = conexion.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                // Éxito al eliminar la mascota
                BufferedReader lector = new BufferedReader(new InputStreamReader(conexion.getInputStream()));
                StringBuilder respuesta = new StringBuilder();
                String linea;
                while ((linea = lector.readLine()) != null) {
                    respuesta.append(linea);
                }
                lector.close();
    
                // Imprimir la respuesta del servidor
                System.out.println("Respuesta del servidor: " + respuesta.toString());
    
                // Mensaje de éxito después de eliminar la mascota de la base de datos
                System.out.println("Mascota con ID " + id + " eliminada exitosamente.");
    
            } else {
                // Error al eliminar la mascota
                System.out.println("Error al eliminar la mascota. Código de respuesta: " + responseCode);
            }
    
            conexion.disconnect();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
  
    public void crearNuevaMascota(String nombre, String categoria, boolean estadoGuardado) {
        try {
            URL url = new URL(SERVER_URL);

            JSONObject nuevaMascota = new JSONObject();
            nuevaMascota.put("name", nombre);
            nuevaMascota.put("category", categoria);
            nuevaMascota.put("status", true);

            HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
            conexion.setRequestMethod("POST");
            conexion.setRequestProperty("Content-Type", "application/json");
            conexion.setDoOutput(true);

            try (OutputStream os = conexion.getOutputStream()) {
                byte[] input = nuevaMascota.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            handleServerResponse(conexion);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void handleServerResponse(HttpURLConnection connection) {
        try {
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_CREATED) {
                try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
                    StringBuilder response = new StringBuilder();
                    String responseLine;
                    while ((responseLine = br.readLine()) != null) {
                        response.append(responseLine.trim());
                    }
                    System.out.println("Respuesta del servidor: " + response.toString());
                }
            } else {
                System.out.println(responseCode);
                System.out.println("Error en la solicitud. Código de respuesta: " + responseCode);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            connection.disconnect();
        }
    }

    
    public void setModeloTabla(DefaultTableModel modelo) {
        this.modeloTabla = modelo;
    }
    public DefaultTableModel getModeloTabla() {
        return modeloTabla;
    }

  public boolean existeMascota(long id) {
    try {
        // Realiza una solicitud HTTP GET para verificar la existencia de la mascota con el ID proporcionado
        URL url = new URL(SERVER_URL + "/" + id);
        HttpURLConnection conexion = (HttpURLConnection) url.openConnection();
        conexion.setRequestMethod("GET");

        // Leer la respuesta
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conexion.getInputStream()))) {
            String response = reader.lines().collect(Collectors.joining());
            System.out.println("Respuesta del servidor: " + response);
        }

        // Verificar el código de respuesta
        int responseCode = conexion.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // La mascota con el ID proporcionado existe
            return true;
        } else if (responseCode == HttpURLConnection.HTTP_NOT_FOUND) {
            // La mascota con el ID proporcionado no existe
            return false;
        } else {
            // Otro código de respuesta, manejar según sea necesario
            System.out.println("Error al verificar la existencia de la mascota. Código de respuesta: " + responseCode);
            return false;
        }
    } catch (IOException ex) {
        ex.printStackTrace();
        return false;
    }
}

    
}
