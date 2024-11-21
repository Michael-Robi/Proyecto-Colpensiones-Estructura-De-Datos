package cache;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import modelo.Solicitante;

public class ArchivoProcessor {
	 
    private static String rutaBase = System.getProperty("user.dir") + "\\src\\files\\Procesados\\";
    private Map<String, LinkedList<Solicitante>> cacheCotizantes = new HashMap<>();
    
    // Metodo para cargar todos los archivos CSV de la carpeta "Procesados"
    public void cargarArchivos() {
        File carpeta = new File(rutaBase);
        if (carpeta.exists() && carpeta.isDirectory()) {
            File[] archivos = carpeta.listFiles((dir, name) -> name.endsWith(".csv"));
            
            if (archivos != null) {
                for (File archivo : archivos) {
                    LinkedList<Solicitante> cotizantes = leerArchivo(archivo);
                    cacheCotizantes.put(archivo.getName(), cotizantes);
                    System.out.println("Archivo cargado: " + archivo.getName());
                }
            }
        } else {
            System.err.println("La carpeta no existe o no es un directorio.");
        }
    }

    // Leer los datos de un archivo CSV y almacenarlos en un LinkedList
    private LinkedList<Solicitante> leerArchivo(File archivo) {
        LinkedList<Solicitante> listaCotizantes = new LinkedList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String line;
            br.readLine(); // Omitir la primera l�nea (cabecera)
            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");
                if (campos.length == 10) {
                    Solicitante solicitante = new Solicitante(
                            Integer.parseInt(campos[0]),
                            Integer.parseInt(campos[1]),
                            campos[2],
                            Integer.parseInt(campos[3]),
                            campos[4],
                            campos[5],
                            Integer.parseInt(campos[6]),
                            Boolean.parseBoolean(campos[7]),
                            campos[8],
                            campos[9]
                    );
                    listaCotizantes.add(solicitante);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + archivo.getName());
        }
        return listaCotizantes;
    }

    // Metodo para mostrar todos los cotizantes cargados en la cache
    public void mostrarCotizantesEnCache() {
        if (cacheCotizantes.isEmpty()) {
            System.out.println("No hay cotizantes cargados en la cache.");
            return;
        }

        // Recorrer la cache y mostrar los cotizantes de cada archivo
        for (Map.Entry<String, LinkedList<Solicitante>> entry : cacheCotizantes.entrySet()) {
            System.out.println("Archivo: " + entry.getKey());
            LinkedList<Solicitante> cotizantes = entry.getValue();
            if (cotizantes.isEmpty()) {
                System.out.println("No hay cotizantes en este archivo.");
            } else {
                for (Solicitante cotizante : cotizantes) {
                    System.out.println(cotizante); // Suponiendo que tienes un toString() en Solicitante
                }
            }
            System.out.println("--------------------------------------------------");
        }
    }

    // Getter para la cache de cotizantes
    public Map<String, LinkedList<Solicitante>> getCacheCotizantes() {
        return cacheCotizantes;
    }
}

