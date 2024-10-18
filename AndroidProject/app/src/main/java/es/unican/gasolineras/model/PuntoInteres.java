package es.unican.gasolineras.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import lombok.Getter;
import lombok.Setter;

/**
 * La clase {@code PuntoInteres} representa una entidad que modela un punto de interés
 * geográfico, con información sobre su nombre, latitud y longitud.
 * Está anotada con {@link Entity} para ser utilizada en una base de datos Room, con un
 * índice único en el campo {@code nombre}, lo que garantiza que no haya duplicados en este campo.
 */
@Getter
@Setter
@Entity(indices = {@Index(value = {"nombre"}, unique = true)})
public class PuntoInteres {
    @PrimaryKey
    protected int idPuntoInteres;

    @ColumnInfo(name = "nombre")
    protected String nombre;

    @ColumnInfo(name = "latitud")
    protected double latitud;

    @ColumnInfo(name = "longitud")
    protected double longitud;

    /**
     * Constructor vacío requerido por Room para instanciar la entidad sin parámetros.
     */
    public PuntoInteres() {
    }

    /**
     * Constructor que permite inicializar un objeto {@code PuntoInteres} con todos sus atributos.
     *
     * @param nombre El nombre del punto de interés.
     * @param latitud La latitud geográfica del punto de interés.
     * @param longitud La longitud geográfica del punto de interés.
     */
    public PuntoInteres(String nombre, double latitud, double longitud) {
        this.nombre = nombre;
        this.latitud = latitud;
        this.longitud = longitud;
    }
}