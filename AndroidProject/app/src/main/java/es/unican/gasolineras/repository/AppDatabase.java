package es.unican.gasolineras.repository;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import es.unican.gasolineras.model.PuntoInteres;

@Database(entities = {PuntoInteres.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract IPuntosInteresDAO puntosInteresDao();
}