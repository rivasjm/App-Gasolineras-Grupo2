package es.unican.gasolineras.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import es.unican.gasolineras.model.PuntoInteres;

/**
 * La interfaz {@code IPuntosInteresDao} define las operaciones de acceso a datos (DAO)
 * para la entidad {@link PuntoInteres}. Esta interfaz proporciona métodos para interactuar
 * con la base de datos, como insertar, eliminar y consultar puntos de interés.
 * Anotada con {@link Dao}, esta interfaz es utilizada por Room para generar automáticamente
 * el código necesario para las operaciones de base de datos.
 */
@Dao
public interface IPuntosInteresDao {
    @Query("SELECT * FROM PuntoInteres")
    List<PuntoInteres> getAll();

    /**
     * Busca un punto de interés por su ID.
     * @param PuntoInteresId id del punto a buscar.
     * @return el punto de interés con ese ID, o null si no se encuentra.
     */
    @Query("SELECT * FROM PuntoInteres WHERE idPuntoInteres IN (:PuntoInteresId)")
    PuntoInteres loadById(int PuntoInteresId);

    /**
     * Busca un punto de interés por su nombre.
     * @param NombrePuntoInteres nombre del punto a buscar.
     * @return el punto de interés con ese nombre, o null si no se encuentra.
     */
    @Query("SELECT * FROM PuntoInteres WHERE nombre IN (:NombrePuntoInteres)")
    PuntoInteres loadByName(String NombrePuntoInteres);

    /**
     * Busca un punto de interés por su latitud y longitud.
     * @param lat la latitud del punto a buscar.
     * @param lon la longitud del punto a buscar.
     * @return el punto de interés con esa latitud y longitud, o null si no se encuentra.
     */
    @Query("SELECT * FROM PuntoInteres WHERE latitud = :lat AND longitud = :lon")
    PuntoInteres loadByLatLon(double lat, double lon);

    /**
     * Inserta un nuevo punto de interés en la base de datos.
     *
     * @param puntoInteres El objeto PuntoInteres a insertar.
     */
    @Insert
    void insertAll(PuntoInteres... puntoInteres);

    /**
     * Elimina un punto de interés de la base de datos.
     *
     * @param puntoInteres El objeto PuntoInteres a eliminar.
     */
    @Delete
    void delete(PuntoInteres puntoInteres);
}