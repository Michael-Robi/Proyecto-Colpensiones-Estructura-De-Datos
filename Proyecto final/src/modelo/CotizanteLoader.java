package modelo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class CotizanteLoader {

    public static void cargarDatos(String filePath, PriorityQueue<Cotizante> descartarCotizante, 
        LinkedList<Cotizante> listaNegra, Queue<Cotizante> transpasos) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        
        while ((line = br.readLine()) != null) {
            String[] datos = line.split(",");
            String nombre = datos[0];
            String id = datos[1];
            int semanasCotizadas = Integer.parseInt(datos[2]);
            String estado = datos[3];
            
            Cotizante cotizante = new Cotizante(nombre, id, semanasCotizadas, estado);
            
            if (estado.equalsIgnoreCase("No Apto")) {
                descartarCotizante.add(cotizante);
            } else if (estado.equalsIgnoreCase("Apto")) {
                cotizanteEmbargado(cotizante, listaNegra, transpasos);
            }
        }
        
        br.close();
    }

    private static void cotizanteEmbargado(Cotizante cotizante, LinkedList<Cotizante> listaNegra, Queue<Cotizante> transpasos) {
        listaNegra.add(cotizante);
        transpasos.add(cotizante);
    }
}
