package es.unican.gasolineras.activities.puntoInteres;

import es.unican.gasolineras.repository.IPuntosInteresDao;

/**
 * Interfaz que define la vista para la funcionalidad de añadir un punto de interés.
 * Esta interfaz especifica los métodos que la vista debe implementar para interactuar
 * con el presentador y mostrar información al usuario.
 *
 */
public interface IAnhadirPuntoInteresView {

    /**
     * Muestra un mensaje al usuario.
     *
     * @param mensaje El mensaje a mostrar.
     */
    public void mostrarMensaje(String mensaje);

    /**
     * Obtiene el nombre del punto de interés introducido por el usuario.
     *
     * @return El nombre del punto de interés.
     */
    public String obtenerNombre();

    /**
     * Obtiene la latitud del punto de interés introducida por el usuario.
     *
     * @return La latitud del punto de interés.
     */
    public String obtenerLatitud();

    /**
     * Obtiene la longitud del punto de interés introducida por el usuario.
     *
     * @return La longitud del punto de interés.
     */
    public String obtenerLongitud();

    /**
     * Cierra la actividad después de guardar el punto de interés.
     */
    public void cerrarVista(); // Para cerrar la actividad después de guardar

    /**
     * Obtiene el DAO de puntos de interés.
     *
     * @return El DAO de puntos de interés.
     */
    public IPuntosInteresDao getPuntosInteresDAO();
}