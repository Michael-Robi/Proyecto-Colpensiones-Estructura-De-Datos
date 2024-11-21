package Main;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GeneradorSolicitante {
    static String rutaDinamicaProyecto = System.getProperty("user.dir");
    static String rutaArchivo = rutaDinamicaProyecto+"\\src\\files\\solicitante.csv";
        
    public static void main(String[] args) {
        
        limpiarCsvSolicitantes(rutaArchivo);
        
        String[] nombres = {"alexander", "bianca", "carlos", "diana", "eduardo", "felicia", "gabriel", "helena", "isidro", "julieta", "kevin", "laura", "manuel", "nadia", "oscar", "patricia", "raul", "silvia", "tomas", "ursula", "victor", "xavier", "yadira", "zoraida", "alicia", "benjaman", "carmen", "daniel", "elsa", "federico", "graciela", "hugo", "iris", "jorge", "karina", "luis", "mariana", "noel", "olivia", "pablo", "quiana", "ricardo", "samuel", "teresa", "violeta", "walter", "yasmin", "zoe", "alberto", "barbara", "cristobal", "daniela", "elias", "fabiola", "guillermo", "hector", "ivana", "juan", "karla", "leandro", "marcos", "norma", "oscar", "patricia", "raul", "sonia", "teresa", "vicente", "wilfredo", "xavier", "yolanda", "zulema", "andres", "beto", "cesar", "dolores", "emilio", "francisco", "gabriela", "hugo", "ignacio", "juanita", "karen", "luisana", "montserrat", "nicolas", "olga", "pablo", "ricardo", "susana", "tatiana", "vicente", "wilson", "alfonso", "bruno", "claudia", "david", "eugenia", "fernando", "gema", "humberto", "isabela", "javier", "katerina", "lidia", "manuel", "nerea", "pedro", "rami", "sofia", "teresa", "urbano", "veronica", "walter", "xavier", "zulema", "alexander", "blanca", "carlos", "diana", "esteban", "fabio", "gloria", "hugo", "ivan", "josue", "kira", "leonardo", "miriam", "nicolas", "olga", "pablo", "quico", "roberto", "sergio", "tomas", "ursula", "veronica", "wenceslao", "ximena", "yolanda", "zacarias", "ana", "felipe", "gerardo", "ivonne", "jorge", "kike", "lucas", "mario", "noelia", "pablo", "rosa", "samuel", "valeria", "yolanda", "zulema", "angela", "benito", "carmen", "daniel", "eliza", "federico", "gonzalo", "hugo", "isabel", "jose", "karla", "luis", "marta", "natividad", "oscar", "pilar", "ricardo", "sergio"};
        String[] apellidos = {"alvarez", "perez", "mendez", "rodriguez", "garcia", "martinez", "hernandez", "gonzalez", "lopez", "sanchez", "torres", "romero", "vargas", "cruz", "reyes", "bautista", "ruiz", "cabrera", "alvarado", "pinto", "jimenez", "gomez", "delgado", "reyes", "fuentes", "mora", "flores", "soto", "castro", "silva", "bustos", "sierra", "vega", "castillo", "navarro", "garcia", "pineda", "caro", "valenzuela", "delgado", "ramirez", "murillo", "rivera", "pardo", "reyes", "gonzalez", "hidalgo", "rivas", "perez", "gonzalez", "salazar", "torres", "aguilar", "lopez", "vasquez", "martinez", "hernandez", "gil", "rodriguez", "serrano", "ruiz", "vargas", "medina", "mendoza", "franco", "perez", "santa", "sanchez", "fuentes", "castillo", "quintero", "ramirez", "mora", "gomez", "juarez", "rodrigo", "moreno", "delgado", "torres", "pinto", "bautista", "silva", "alvarez", "alvarado", "torres", "sarmiento", "rojas", "cruz", "sanchez", "guzman", "diaz", "morales", "piedra", "vazquez", "valverde", "roa", "jimenez", "ramirez", "reyes", "castro", "medina", "montero", "rodrigo", "garcia", "fuentes", "cameron", "gonzalez", "hernandez", "marin", "romero", "soto", "alvarez", "ferrari", "pizarro", "gonzalez", "carrillo", "calderon", "moreno", "carrera", "cifuentes", "garcia", "marin", "torres", "soto", "reyes", "montoya", "gomez", "perez", "vargas", "herrera", "castro", "soto", "leon", "perez", "morales", "ferrer", "castillo", "delgado", "ramirez", "silva", "murillo", "rojas", "medina", "zapata", "henao", "sanchez", "quiroz", "mendoza", "pineda", "rodriguez", "flores", "romero", "gomez", "alvarado", "vega", "martinez", "bautista", "flores", "bernal", "jimenez", "pardo", "marquez", "pico", "gonzalez", "juarez", "hernandez", "salazar", "david", "torres", "salcedo", "rodrigo", "roberto", "alfaro", "jimenez", "perez", "gonzalez", "gil", "salas", "cruz", "ramirez", "gomez", "ruiz", "barrera", "cifuentes", "quintana", "bautista", "ron", "andrade", "guadalupe", "martinez", "delgado", "vega", "rojas", "serna", "salas", "romero", "perez", "garcia", "vargas", "pineda", "montero", "martinez", "gomez", "lopez", "garcia", "murillo", "gonzalez", "garcia", "rojas", "fuentes", "delgado", "santa", "castro", "pinto", "quintero", "soto", "barrios", "carrillo", "reyes", "gonzalez", "vela", "doris", "caro", "marin", "soto", "garcia", "cifuentes", "pino", "rodriguez", "castillo", "reyes", "hinojosa", "guzman", "maron", "torres", "perez", "gonzalez", "martinez", "sarmiento", "delgado", "rojas", "roberto", "salazar", "lira", "montero", "vega", "pinto", "hinojosa", "vargas", "gonzalez", "rojas", "delgado", "hidalgo", "vega", "pino", "moreno", "restrepo", "castro"};
        String[] ciudades = {"Apartado", "Arauca", "Arauquita", "Armenia", "Barranquilla", "Bogota", "Bucaramanga", "Cali", "Cartagena", "Chia", "Cucuta", "Florencia", "Girardot", "Ibague", "Inirida", "Leticia", "Manizales", "Medellin", "Monteria", "Neiva", "Pasto", "Pereira", "Pitalito", "Popayan", "Quibdo", "Riohacha", "Santa Marta", "Santa Rosa de Cabal", "Sincelejo", "Soacha", "Tunja", "Valledupar", "Villavicencio", "Yopal"};

        String[] fondoDePensiones = {"Proteccion","Porvenir","Colfondos","Old Mutual","Fondo extranjero","Colpensiones","Null"};
        String[] entidadesPublicas = {"Armada","Inpec","Policia","Minsalud","Mininterior", "Null"};
        
        List<String> listaNuevosCotizantes = new ArrayList<>();
        Random random = new Random();
        
        listaNuevosCotizantes.add("id_cotizante,cedula,nombre,edad,genero,ciudad,semanasCotizadas,declaraRenta,fondoDePensiones,entidadPublica");

        for (int i = 1; i < 10001; i++) {

            int idCotizante = i;
            String cedula = String.valueOf(100000 + random.nextInt(900000));
            String nombre = nombres[random.nextInt(nombres.length)];
            String apellido = apellidos[random.nextInt(apellidos.length)];
            String edad = String.valueOf(20 + random.nextInt(41));
            String genero = (random.nextInt(2) == 0) ? "F" : "M";
            String ciudad = ciudades[random.nextInt(ciudades.length)];
            String semanasCotizadas = String.valueOf(20 + random.nextInt(41));
            boolean declaraRenta = random.nextBoolean();
            String fondoDePension = fondoDePensiones[random.nextInt(fondoDePensiones.length)];
            String entidadPublica = entidadesPublicas[random.nextInt(entidadesPublicas.length)];
            
            // Agregar la listaDePersonasGenerada
            listaNuevosCotizantes.add(idCotizante + "," +cedula + "," +nombre + " " + apellido + "," + edad + "," + genero + "," + ciudad + "," + semanasCotizadas + "," + declaraRenta + "," + fondoDePension + "," + entidadPublica);
        }
        
        // Imprimir o guardar la lista generada
        for (String persona : listaNuevosCotizantes) {
//            System.out.println(persona);
            escribirCSVSolicitantes(rutaArchivo,persona);
        }
    }
    
   public static void limpiarCsvSolicitantes(String rutaArchivo){
        try (FileWriter writer = new FileWriter(rutaArchivo, true)) {
            System.out.println("El archivo se ha generado correctamente.");
        } catch (IOException e) {
            System.err.println("Error al generar el archivo: " + e.getMessage());
        }
    }
    
    public static void escribirCSVSolicitantes(String rutaArchivo,String listaNuevosCotizantes) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write(listaNuevosCotizantes);
            bw.newLine(); // Agrega un salto de línea después de cada registro
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}