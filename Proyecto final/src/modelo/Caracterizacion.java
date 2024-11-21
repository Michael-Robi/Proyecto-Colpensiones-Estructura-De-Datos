package modelo;

public class Caracterizacion {
    private int idCaracterizacion;
    private int cedula;
    private String nombre,entidadPublica;
    private boolean condecoraciones;
    private boolean hijosEntidadPublica;
    private boolean familiaPolicia;
    private boolean mayorEdadFamiliarPolicia;
    private boolean observacionesDisciplinarias;
    private String tipoInstitucionLegal;
    private boolean sanciones;

    public Caracterizacion(int idCaracterizacion, int cedula, String nombre, String entidadPublica, boolean condecoraciones, 
            boolean hijosEntidadPublica, boolean familiaPolicia, boolean mayorEdadFamiliarPolicia, 
            boolean observacionesDisciplinarias, String tipoInstitucionLegal, boolean sanciones) {
        this.idCaracterizacion = idCaracterizacion;
        this.cedula = cedula;
        this.nombre = nombre;
        this.entidadPublica = entidadPublica;
        this.condecoraciones = condecoraciones;
        this.hijosEntidadPublica = hijosEntidadPublica;
        this.familiaPolicia = familiaPolicia;
        this.mayorEdadFamiliarPolicia = mayorEdadFamiliarPolicia;
        this.observacionesDisciplinarias = observacionesDisciplinarias;
        this.tipoInstitucionLegal = tipoInstitucionLegal;
        this.sanciones = sanciones;
    }

    public int getIdCaracterizacion() { return idCaracterizacion; }

    public int getCedula() { return cedula; }

    public String getNombre() { return nombre; }

    public String getEntidadPublica() { return entidadPublica; }

    public boolean isCondecoraciones() { return condecoraciones; }

    public boolean isHijosEntidadPublica() { return hijosEntidadPublica; }

    public boolean isFamiliaPolicia() { return familiaPolicia; }

    public boolean isMayorEdadFamiliarPolicia() { return mayorEdadFamiliarPolicia; }

    public boolean isObservacionesDisciplinarias() { return observacionesDisciplinarias; }

    public String getTipoInstitucionLegal() { return tipoInstitucionLegal; }

    public boolean isSanciones() { return sanciones; }
}
