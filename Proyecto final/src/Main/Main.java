package Main;

import java.io.File;
import services.EvaluadorCotizantes;

public class Main {
    public static void main(String[] args) {
        
        // Ruta del proyecto dinamica
        String rutaDinamicaProyecto = System.getProperty("user.dir");

        // Imprimir la ruta base para depuracion
        System.out.println("Ruta base del proyecto: " + rutaDinamicaProyecto);

        // Ruta de los archivos CSV, usando File.separator para compatibilidad con distintos sistemas operativos
        String solicitanteFile = rutaDinamicaProyecto + File.separator + "src" + File.separator + "files" + File.separator + "solicitante.csv";
        String caracterizacionFile = rutaDinamicaProyecto + File.separator + "src" + File.separator + "files" + File.separator + "caracterizacion.csv";
        
        // Imprimir las rutas de los archivos para asegurarse de que son correctas
        System.out.println("Ruta archivo solicitantes: " + solicitanteFile);
        System.out.println("Ruta archivo caracterizacion: " + caracterizacionFile);

        // Crear una instancia del EvaluadorCotizantes
        EvaluadorCotizantes evaluador = new EvaluadorCotizantes();

        // Cargar los datos desde los archivos CSV
        evaluador.cargarDatos(solicitanteFile, caracterizacionFile);

        // Imprimir los resultados de la clasificacion
        evaluador.imprimirResultados();
        
        // Aqui podrias agregar el codigo de ProcesarSolicitud si es necesario, o eliminarlo si no lo usas
//        ProcesarSolicitud solicitudNueva = new ProcesarSolicitud();
//        solicitudNueva.moverSolicitantesAProcesados();
    }
}

