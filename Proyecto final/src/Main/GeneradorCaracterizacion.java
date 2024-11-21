package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorCaracterizacion {
    static String rutaDinamicaProyecto = System.getProperty("user.dir");
    static String rutaArchivo = rutaDinamicaProyecto+"\\src\\files\\caracterizacion.csv";
    
    static String rutaArchivoContraloria = rutaDinamicaProyecto+"\\src\\files\\Contraloria.csv";
    static String rutaArchivoFiscalia = rutaDinamicaProyecto+"\\src\\files\\Fiscalia.csv";
    static String rutaArchivoProcuraduria = rutaDinamicaProyecto+"\\src\\files\\Procuraduria.csv";
    
    public static void main(String[] args) {
        
        limpiarCsvCaracterizaciones(rutaArchivo);
        
        limpiarCsvCaracterizaciones(rutaArchivoProcuraduria);
        limpiarCsvCaracterizaciones(rutaArchivoFiscalia);
        limpiarCsvCaracterizaciones(rutaArchivoContraloria);
        
        String[] nombres = {"alexander", "bianca", "carlos", "diana", "eduardo", "felicia", "gabriel", "helena", "isidro", "julieta", "kevin", "laura", "manuel", "nadia", "oscar", "patricia", "raul", "silvia", "tomas", "ursula", "victor", "xavier", "yadira", "zoraida", "alicia", "benjamin", "carmen", "daniel", "elsa", "federico", "graciela", "hugo", "iris", "jorge", "karina", "luis", "mariana", "noel", "olivia", "pablo", "quiana", "ricardo", "samuel", "teresa", "violeta", "walter", "yasmin", "zoe", "alberto", "barbara", "cristobal", "daniela", "elias", "fabiola", "guillermo", "hector", "ivana", "juan", "karla", "leandro", "marcos", "norma", "oscar", "patricia", "raul", "sonia", "teresa", "vicente", "wilfredo", "xavier", "yolanda", "zulema", "andres", "belan", "cesar", "dolores", "emilio", "francisco", "gabriela", "hugo", "ignacio", "juanita", "karen", "luisana", "montserrat", "nicolas", "olga", "pablo", "ricardo", "susana", "tatiana", "vicente", "wilson", "alfonso", "bruno", "claudia", "david", "eugenia", "fernando", "gema", "humberto", "isabela", "javier", "katerina", "lidia", "manuel", "nerea", "pedro", "rami", "sofia", "teresa", "urbano", "veronica", "walter", "xavier", "zulema", "alex", "blanca", "robinson", "diana", "esteban", "fabio", "gloria", "hugo", "ivan", "jose", "kira", "leonardo", "miriam", "nicolas", "olga", "pablo", "quico", "roberto", "sergio", "tomas", "ursula", "beronica", "wenceslao", "ximena", "yolanda", "zacarias", "ana", "felipe", "gerardo", "ivonne", "jorge", "kike", "lucia", "mario", "noelia", "pablo", "rosa", "samuel", "valeria", "yolanda", "zulema", "angela", "benito", "carmen", "daniel", "eliza", "federico", "gonzalo", "hugo", "isabel", "josue", "karla", "luis", "marta", "natividad", "oscar", "pilar", "ricardo", "sergio"};
        String[] apellidos = {"alvarez", "perez", "mendez", "rodriguez", "garcia", "martinez", "hernandez", "gonzalez", "lopez", "sanchez", "torres", "romero", "vargas", "cruz", "reyes", "bautista", "ruiz", "cabrera", "alvarado", "pinto", "jimenez", "gomez", "delgado", "reyes", "fuentes", "mora", "flores", "soto", "castro", "silva", "bustos", "sierra", "vega", "castillo", "navarro", "garcia", "pineda", "caro", "valenzuela", "delgado", "ramirez", "murillo", "rivera", "pardo", "reyes", "gonzalez", "hidalgo", "rivas", "perez", "gonzalez", "salazar", "torres", "aguilar", "lopez", "vasquez", "martinez", "hernandez", "gil", "rodriguez", "serrano", "ruiz", "vargas", "medina", "mendoza", "franco", "perez", "santa", "sanchez", "fuentes", "castillo", "quintero", "ramirez", "mora", "gomez", "juarez", "rodrigo", "moreno", "delgado", "torres", "pinto", "bautista", "silva", "alvarez", "alvarado", "torres", "sarmiento", "rojas", "cruz", "sanchez", "guzman", "diaz", "morales", "piedra", "vazquez", "valverde", "roa", "jimenez", "ramirez", "reyes", "castro", "medina", "montero", "rodrigo", "garcia", "fuentes", "cameron", "gonzalez", "hernandez", "marin", "romero", "soto", "alvarez", "ferrari", "pizarro", "gonzalez", "carrillo", "calderon", "moreno", "carrera", "cifuentes", "garcia", "marin", "torres", "soto", "reyes", "montoya", "gomez", "perez", "vargas", "herrera", "castro", "soto", "leon", "vanegas", "morales", "ferrer", "castillo", "delgado", "ramirez", "silva", "murillo", "rojas", "medina", "zapata", "henao", "sanchez", "quiroz", "mendoza", "pineda", "rodriguez", "flores", "romero", "gomez", "alvarado", "vega", "martinez", "bautista", "flores", "bernal", "jimenez", "pardo", "marquez", "pico", "gonzalez", "juarez", "hernandez", "salazar", "david", "torres", "salcedo", "rodrigo", "roberto", "alfaro", "jimenez", "perez", "gonzalez", "gil", "salas", "cruz", "ramirez", "gomez", "ruiz", "barrera", "cifuentes", "quintana", "bautista", "rodriguez", "andrade", "lopez", "martinez", "delgado", "vega", "rojas", "serna", "salas", "romero", "perez", "garcia", "vargas", "pineda", "montero", "martinez", "gomez", "lopez", "garcia", "murillo", "gonzalez", "garcia", "rojas", "fuentes", "delgado", "santa", "castro", "pinto", "quintero", "soto", "barrios", "carrillo", "reyes", "gonzalez", "vazquez", "henao", "caro", "marin", "soto", "garcia", "cifuentes", "pino", "rodriguez", "castillo", "reyes", "hinojosa", "guzman", "maron", "torres", "perez", "gonzalez", "martinez", "sarmiento", "delgado", "rojas", "roberto", "salazar", "lira", "montero", "vega", "pinto", "hinojosa", "vargas", "gonzalez", "rojas", "delgado", "hidalgo", "vega", "pino", "moreno", "torres", "castro"};
        
        String[] observacionesDisciplinarias = {"ObservacionArmada","ObservacionInpec","ObservacionMinsalud","ObservacionMininterior","Null"};
        String[] entidadesPublicas = {"Armada","Inpec","Policia","Minsalud","Mininterior", "Null"};
        String[] institicionesLegales = {"Contraloria", "Procuraduria","Fiscalia","Null"};
        
        List<String> listaSolicitanteConCaracterizacion = new ArrayList<>();
        Random random = new Random();
        
        listaSolicitanteConCaracterizacion.add("id_caracterizacion,cedula,nombre,entidadPublica,condecoraciones,hijosEntidadPublica,familiarPolicia,mayorEdadFamiliarPolicia,observacionesDisciplinarias,tipoInstitucionLegal,sanciones");

        for (int i = 1; i < 10001; i++) {

            int idCaracterizacion = i;
            String cedula = String.valueOf(100000 + random.nextInt(900000));
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            String entidadPublica = entidadesPublicas[random.nextInt(entidadesPublicas.length)];
            boolean condecoraciones = random.nextBoolean();
            boolean hijosEntidadPublica = random.nextBoolean();
            boolean familiarPolicia = random.nextBoolean();
            boolean mayorEdadFamiliarPolicia = random.nextBoolean();
            String observacionDisciplinaria = observacionesDisciplinarias[random.nextInt(observacionesDisciplinarias.length)];
            String institicionLegal = institicionesLegales[random.nextInt(institicionesLegales.length)];
            boolean sanciones = random.nextBoolean();
            // Agregar la listaDePersonasGenerada
            
            String persona = idCaracterizacion + "," + cedula + "," + nombre + " " + apellido + "," + entidadPublica + "," + condecoraciones + "," + hijosEntidadPublica + "," + familiarPolicia + "," + mayorEdadFamiliarPolicia + "," + observacionDisciplinaria + "," + institicionLegal + "," + sanciones;

            // Filtrar por tipoInstitucionLegal igual a "Procuraduria"
            if (institicionLegal.equalsIgnoreCase("Procuraduria")) {
                escribirCSVCaracterizaciones(rutaArchivoProcuraduria, persona);
            }
            
            if(institicionLegal.equalsIgnoreCase("Fiscalia")){
                escribirCSVCaracterizaciones(rutaArchivoFiscalia,persona);
            }
            if(institicionLegal.equalsIgnoreCase("Contraloria")){
                escribirCSVCaracterizaciones(rutaArchivoContraloria,persona);
            }
            
            listaSolicitanteConCaracterizacion.add(persona);
        }
        
        // Imprimir o guardar la lista generada
        for (String persona : listaSolicitanteConCaracterizacion) {
//            System.out.println(persona);
            escribirCSVCaracterizaciones(rutaArchivo,persona);
        }
    }
    
    public static void limpiarCsvCaracterizaciones(String rutaArchivo){
        try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
            System.out.println("El archivo se ha generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
        }
    }
    
    public static void escribirCSVCaracterizaciones(String rutaArchivo,String listaNuevosCotizantes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(listaNuevosCotizantes);
            bw.newLine(); // Agrega un salto de línea después de cada registro
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}