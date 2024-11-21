package modelo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class ProcesarSolicitud {

    public void moverSolicitantesAProcesados(){
        String rutaDinamicaProyecto = System.getProperty("user.dir");
        
        Path origen = Paths.get(rutaDinamicaProyecto+"\\src\\files\\solicitante.csv");
        Path destino = Paths.get(rutaDinamicaProyecto+"\\src\\files\\Procesados\\solicitante.csv");

        try {
            Files.move(origen, destino, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Solicitued proceadas movidas con exito");
        } catch (Exception e) {
            System.out.println("Error al mover el archivo: " + e.getMessage());
        }
    }

}
