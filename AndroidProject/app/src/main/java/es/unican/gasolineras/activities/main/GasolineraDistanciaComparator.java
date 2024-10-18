package es.unican.gasolineras.activities.main;

import java.util.Comparator;

import es.unican.gasolineras.model.Gasolinera;
import es.unican.gasolineras.model.PuntoInteres;

public class GasolineraDistanciaComparator implements Comparator<Gasolinera> {

    private PuntoInteres punto;

    public GasolineraDistanciaComparator(PuntoInteres punto){
        this.punto = punto;
    }

    @Override
    public int compare(Gasolinera g1, Gasolinera g2) {
        return 0;
    }


}
