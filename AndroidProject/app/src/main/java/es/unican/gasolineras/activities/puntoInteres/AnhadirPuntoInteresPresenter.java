package es.unican.gasolineras.activities.puntoInteres;

import androidx.room.Room;
import es.unican.gasolineras.model.PuntoInteres;
import es.unican.gasolineras.repository.AppDatabase;
import es.unican.gasolineras.repository.IPuntosInteresDao;

public class AnhadirPuntoInteresPresenter {

    private IAnhadirPuntoInteresView vista;
    private IPuntosInteresDao puntosInteresDao;

    public AnhadirPuntoInteresPresenter(IAnhadirPuntoInteresView vista) {
        this.vista = vista;
        this.puntosInteresDao = vista.getPuntosInteresDAO();
    }

    /**
     * Clase que actúa como el presentador en el patrón MVP para la vista de añadir un nuevo punto de interés.
     * Maneja la lógica de negocio relacionada con la validación de datos y el acceso a la base de datos
     * para insertar un nuevo punto de interés.
     */
    public void onGuardarPuntoInteresClicked(String nombre, String latitudStr, String longitudStr) {

        // Validar que los campos no estén vacíos
        if (nombre.isEmpty() || latitudStr.isEmpty() || longitudStr.isEmpty()) {
            vista.mostrarMensaje("Por favor, llene todos los campos");
            return;
        }
        if (puntosInteresDao.loadByName(nombre) != null) {
            vista.mostrarMensaje("Ya existe un punto de interés con ese nombre");
            return;
        }

        try {
            // Convertir las coordenadas a tipo double
            double latitud = Double.parseDouble(latitudStr);
            double longitud = Double.parseDouble(longitudStr);

            // Crear un nuevo punto de interés
            PuntoInteres nuevoPunto = new PuntoInteres();
            nuevoPunto.nombre = nombre;
            nuevoPunto.latitud = latitud;
            nuevoPunto.longitud = longitud;

            // Insertar el nuevo punto de interés en la base de datos
            puntosInteresDao.insertAll(nuevoPunto);

            vista.mostrarMensaje("Punto de interés guardado");
            vista.cerrarVista(); // Cerrar la vista después de guardar

        } catch (NumberFormatException e) {
            vista.mostrarMensaje("Por favor, ingresa valores numéricos válidos para latitud y longitud");
        }
    }
}