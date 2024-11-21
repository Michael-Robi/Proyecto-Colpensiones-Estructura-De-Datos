package services;

import cache.ArchivoProcessor;
import modelo.Solicitante;
import modelo.Caracterizacion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.Scanner;
import modelo.ProcesarSolicitud;

public class EvaluadorCotizantes {

    private LinkedList<Solicitante> rechazados = new LinkedList<>();
    private LinkedList<Solicitante> procesarCivil = new LinkedList<>(); // lista auxiliar
    private LinkedList<Solicitante> listaNegra = new LinkedList<>();

    // Lista Auxiliar
    private LinkedList<Solicitante> listaSolicitante = new LinkedList<>();

    private PriorityQueue<Solicitante> aceptados = new PriorityQueue<>(new Comparator<Solicitante>() {
        @Override
        public int compare(Solicitante s1, Solicitante s2) {
            // Prioridad por edad (menores de 35 primero)
            if (s1.getEdad() < 35 && s2.getEdad() >= 35) {
                return -1; // s1 tiene mayor prioridad
            } else if (s1.getEdad() >= 35 && s2.getEdad() < 35) {
                return 1; // s2 tiene mayor prioridad
            }

            // Prioridad si no declaran renta (s1 no declara renta, s2 s�)
            if (!s1.isDeclaraRenta() && s2.isDeclaraRenta()) {
                return -1; // s1 tiene mayor prioridad
            } else if (s1.isDeclaraRenta() && !s2.isDeclaraRenta()) {
                return 1; // s2 tiene mayor prioridad
            }

            // Si tienen la misma prioridad, se ordenan por edad
            return Integer.compare(s1.getEdad(), s2.getEdad());
        }
    });

    // Ruta base donde se guardaran los archivos procesados
    static String rutaBase = System.getProperty("user.dir") + "\\src\\files\\Procesados\\";

    private ArchivoProcessor archivoProcessor = new ArchivoProcessor();
    public void cargarDatos(String datosBasicosFile, String caracterizacionFile) {
    	archivoProcessor.cargarArchivos(); 
    	leerDatossolicitud(datosBasicosFile);
        leerCaracterizaciones(caracterizacionFile);
    }

    private void leerDatossolicitud(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Esta linea omite la primera linea (cabecera)

            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");

                if (campos.length != 10) {
                    System.err.println("Linea invalida en datos basicos: " + line);
                    continue; // Ignorar esta l�nea
                }

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

                clasificarCotizante(solicitante);
                listaSolicitante.add(solicitante);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void leerCaracterizaciones(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            br.readLine(); // Esta linea omite la primera linea (cabecera)

            while ((line = br.readLine()) != null) {
                String[] campos = line.split(",");

                if (campos.length != 11) {
                    System.err.println("Linea invalida en caracterizacion: " + line);
                    continue; // Ignorar esta linea
                }

                Caracterizacion caracterizacionSolicitante = new Caracterizacion(
                        Integer.parseInt(campos[0]),
                        Integer.parseInt(campos[1]),
                        campos[2],
                        campos[3],
                        Boolean.parseBoolean(campos[4]),
                        Boolean.parseBoolean(campos[5]),
                        Boolean.parseBoolean(campos[6]),
                        Boolean.parseBoolean(campos[7]),
                        Boolean.parseBoolean(campos[8]),
                        campos[9],
                        Boolean.parseBoolean(campos[10])
                );

                Iterator<Solicitante> iter = listaSolicitante.iterator();
                while (iter.hasNext()) {
                    Solicitante cotizante = iter.next();
                    if (cotizante.getCedula() == caracterizacionSolicitante.getCedula()) {
                        if ((caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Minsalud") && caracterizacionSolicitante.isSanciones()) ||
                                (caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Mininterior") && caracterizacionSolicitante.isSanciones())) {
                            listaNegra.add(cotizante);
                            cotizante.setEstadoSolicitud("Rechazado");
                        }
                        if ((caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Minsalud") && !caracterizacionSolicitante.isSanciones()) ||
                                (caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Mininterior") && !caracterizacionSolicitante.isSanciones())) {
                            aceptados.add(cotizante);
                            cotizante.setFondoDePensiones("Colpensiones");
                            cotizante.setEstadoSolicitud("Aprobado");
                        }
                        if ((caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Armada") && caracterizacionSolicitante.isCondecoraciones()) ||
                                (caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Inpec") && (caracterizacionSolicitante.isCondecoraciones() || caracterizacionSolicitante.isHijosEntidadPublica()))) {
                            aceptados.add(cotizante);
                            cotizante.setFondoDePensiones("Colpensiones");
                            cotizante.setEstadoSolicitud("Aprobado");
                        }
                        if (caracterizacionSolicitante.getTipoInstitucionLegal().equalsIgnoreCase("Contraloria") ||
                                caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Procuraduria") ||
                                caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Fiscalia")) {
                            procesarCivil.add(cotizante);
                        }
                        if (caracterizacionSolicitante.getEntidadPublica().equalsIgnoreCase("Policia") &&
                                caracterizacionSolicitante.isFamiliaPolicia() && caracterizacionSolicitante.isMayorEdadFamiliarPolicia()) {
                            aceptados.add(cotizante);
                            cotizante.setFondoDePensiones("Colpensiones");
                            cotizante.setEstadoSolicitud("Aprobado");
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    private void clasificarCotizante(Solicitante solicitante) {
        if (solicitante.getEdad() < 35 || !solicitante.isDeclaraRenta()) {
            aceptados.add(solicitante);
            solicitante.setFondoDePensiones("Colpensiones");
            solicitante.setEstadoSolicitud("Aprobado");
        } else {
            rechazados.add(solicitante);
            solicitante.setEstadoSolicitud("Rechazado");
        }

        String[] ciudad = {"Bogota", "Medellin", "Cali", "Tunja"};
        Boolean validarCiudad = solicitante.getCiudad().equalsIgnoreCase(ciudad[0]) || solicitante.getCiudad().equalsIgnoreCase(ciudad[1]) 
                || solicitante.getCiudad().equalsIgnoreCase(ciudad[2]) || solicitante.getCiudad().equalsIgnoreCase(ciudad[3]);

        if (solicitante.getEdad() > 35 || validarCiudad || solicitante.getEntidadPublica() == null || solicitante.getEntidadPublica().equalsIgnoreCase("Null") || solicitante.isEsPrePensionado()) {
            rechazados.add(solicitante);
        } else {
            aceptados.add(solicitante);
            solicitante.setFondoDePensiones("Colpensiones");
            solicitante.setEstadoSolicitud("Aprobado");
        }
    }

    private void guardarCotizantesEnArchivo(LinkedList<Solicitante> cotizantes, String archivoNombre) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaBase + archivoNombre))) {
            // Escribir el encabezado
            bw.write("id_cotizante,cedula,nombre,edad,genero,ciudad,semanasCotizadas,declaraRenta,fondoPensiones,entidadPublica,estadoSolicitud");
            bw.newLine();

            // Escribir los cotizantes
            for (Solicitante cotizante : cotizantes) {
                String registro = cotizante.getIdCotizante() + "," +
                        cotizante.getCedula() + "," +
                        cotizante.getNombre() + "," +
                        cotizante.getEdad() + "," +
                        cotizante.getGenero() + "," +
                        cotizante.getCiudad() + "," +
                        cotizante.getSemanasCotizadas() + "," +
                        cotizante.isDeclaraRenta() + "," +
                        cotizante.getFondoDePensiones() + "," +
                        cotizante.getEntidadPublica() + "," +
                        cotizante.getEstadoSolicitud();
                bw.write(registro);
                bw.newLine();
            }
            System.out.println("Archivo " + archivoNombre + " guardado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el archivo " + archivoNombre + ": " + e.getMessage());
        }
    }
    public void imprimirResultados() {
        String opcion = "";
        Scanner entradaPorTeclado = new Scanner(System.in);
        do{
                System.err.println("Opciones:");
                System.out.println("0. Limpiar Pantalla:");
                System.out.println("1. Cotizantes Aceptados:");
                System.out.println("2. Cotizantes Rechazados");
                System.out.println("3. Cotizantes a Procesar como Civil");
                System.out.println("4. Lista Negra");
                System.out.println("5. Cache"); //ProcesarSolicitante
                System.out.println("6. Salir");
                System.err.print("Seleccione una opcion: ");
                opcion = entradaPorTeclado.nextLine();

                switch(opcion){
                    case "0":
                        for (int i = 0; i < 50; i++) {
                            System.out.println();
                        }
                        break;
                    case "1":
                        System.out.println("--------------------------------------------------");
                        System.err.println("Cotizantes Aceptados:");
                        if (!aceptados.isEmpty()) {
                            
                            for (Solicitante aceptado : aceptados) {
                                System.out.println(aceptado); // Imprime cada cotizante aceptado
                            }
                        } else {
                            System.out.println("No hay cotizantes aceptados.");
                        }
                        break;
                    case "2":
                        System.out.println("--------------------------------------------------");
                        System.err.println("Cotizantes Rechazados:");
                        if (!rechazados.isEmpty()) {
                            LinkedHashSet<Solicitante> setSinDuplicados = new LinkedHashSet<>(rechazados);
                            rechazados = new LinkedList<>(setSinDuplicados);
    
                            for (Solicitante rechazado : rechazados) {
                                System.out.println(rechazado); // Imprime cada cotizante rechazado
                            }
                        } else {
                            System.out.println("No hay cotizantes rechazados.");
                        }
                        System.out.println("--------------------------------------------------");
                        break;
                    case "3":
                        System.err.println("Cotizantes a Procesar como Civil:");
                        if (!procesarCivil.isEmpty()) {
                            for (Solicitante civil : procesarCivil) {
                                System.out.println(civil); // Imprime cada cotizante a procesar
                            }
                        } else {
                            System.out.println("No hay cotizantes a procesar como civiles.");
                        }
                        System.out.println("--------------------------------------------------");
                        break;
                    case "4":
                        System.err.println("Lista Negra:");
                        if (!listaNegra.isEmpty()) {
                            for (Solicitante negra : listaNegra) {
                                System.out.println(negra); // Imprime cada cotizante en la lista negra
                            }
                        } else {
                            System.out.println("No hay cotizantes en la lista negra.");
                        }
                        System.out.println("--------------------------------------------------");
                        break;
                    case "5":
                        // Guardar los resultados en archivos CSV
                        System.err.println("Cache:");
                        guardarResultados();
                        
                        // Aqui podrias agregar el codigo de ProcesarSolicitud si es necesario, o eliminarlo si no lo usas
                        ProcesarSolicitud solicitudNueva = new ProcesarSolicitud();
                        solicitudNueva.moverSolicitantesAProcesados();
                        break;
                    case "6":
                        System.out.println("Gracias por utilizar nuestro servicio");
                        opcion="salir";
                        break;
                    default:
                        System.out.println("Operación no válida");
                }
            }
            while(!opcion.equals("salir"));
    }
    
    
    public void guardarResultados() { 
        guardarCotizantesEnArchivo(new LinkedList<>(aceptados), "Aceptados.csv");
        guardarCotizantesEnArchivo(rechazados, "Rechazados.csv");
        guardarCotizantesEnArchivo(listaNegra, "ListaNegra.csv");
        guardarCotizantesEnArchivo(procesarCivil, "ProcesarCivil.csv");

        // Imprimir el contenido de la cache
        System.out.println("--------------------------------------------------");
        System.err.println("Contenido de la cache (Aceptados):");
        imprimirCache(aceptados);
        
        System.out.println("--------------------------------------------------");
        System.err.println("Contenido de la cache (Aceptados):");
        imprimirCache(rechazados);
        
        System.out.println("--------------------------------------------------");
        System.err.println("Contenido de la cache (Lista Negra):");
        imprimirCache(listaNegra);
        
        System.out.println("--------------------------------------------------");
        System.err.println("Contenido de la cache (Procesar Civil):");
        imprimirCache(procesarCivil);
        System.out.println("--------------------------------------------------");
    }

    private void imprimirCache(Collection<Solicitante> collection) {
        if (!collection.isEmpty()) {
            for (Solicitante solicitante : collection) {
                System.out.println(solicitante); // Imprime cada cotizante en la cach�
            }
        } else {
            System.out.println("La cache este vacia.");
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}