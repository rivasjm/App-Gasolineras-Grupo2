package es.unican.gasolineras.activities.puntoInteres;

import es.unican.gasolineras.repository.IPuntosInteresDao;

public interface IAnhadirPuntoInteresView {
    public void mostrarMensaje(String mensaje);
    public String obtenerNombre();
    public String obtenerLatitud();
    public String obtenerLongitud();
    public void cerrarVista(); // Para cerrar la actividad después de guardar
    public IPuntosInteresDao getPuntosInteresDAO();
}