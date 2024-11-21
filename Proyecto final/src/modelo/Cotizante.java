package modelo;

public class Cotizante implements Comparable<Cotizante> {
    private String nombre;
    private String id;
    private int semanasCotizadas;
    private String estadoSolicitud;

    public Cotizante(String nombre, String id, int semanasCotizadas, String estado) {
        this.nombre = nombre;
        this.id = id;
        this.semanasCotizadas = semanasCotizadas;
        this.estadoSolicitud = estado;
    }

    public String getEstado() {
        return estadoSolicitud;
    }

    @Override
    public int compareTo(Cotizante otro) {
        return Integer.compare(this.semanasCotizadas, otro.semanasCotizadas); // Comparar por semanas cotizadas
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", ID: " + id + ", Semanas Cotizadas: " + semanasCotizadas + ", Estado: " + estadoSolicitud;
    }
}
