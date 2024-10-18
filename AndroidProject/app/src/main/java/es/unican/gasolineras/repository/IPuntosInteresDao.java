package es.unican.gasolineras.repository;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

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

    // Buscar por id
    @Query("SELECT * FROM PuntoInteres WHERE idPuntoInteres IN (:PuntoInteresId)")
    PuntoInteres loadById(int PuntoInteresId);

    // Buscar por nombre
    @Query("SELECT * FROM PuntoInteres WHERE nombre IN (:NombrePuntoInteres)")
    PuntoInteres loadByName(String NombrePuntoInteres);

    // Buscar por latitud y longitud exactas
    @Query("SELECT * FROM PuntoInteres WHERE latitud = :lat AND longitud = :lon")
    PuntoInteres loadByLatLon(double lat, double lon);

    @Insert
    void insertAll(PuntoInteres... puntoInteres);

    @Delete
    void delete(PuntoInteres puntoInteres);
}