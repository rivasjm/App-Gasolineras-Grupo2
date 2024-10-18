package es.unican.gasolineras;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.unican.gasolineras.activities.main.GasolineraDistanciaComparator;
import es.unican.gasolineras.model.Gasolinera;
import es.unican.gasolineras.model.PuntoInteres;

public class DistanciaComparatorTest {

    private GasolineraDistanciaComparator comparadorDistancia;

    private Gasolinera cercana;
    private Gasolinera lejana;

    private PuntoInteres universidad;

    @Before
    public void inicializa(){

        cercana = new Gasolinera();
        cercana.setId("GasolineraPaco");
        cercana.setDireccion("Calle Los Castros");

        lejana = new Gasolinera();
        lejana.setId("GasolineraMario");
        lejana.setDireccion("Calle Honduras");

        //creo un punto de interes
        universidad = new PuntoInteres();
        universidad.nombre = "Universidad";
        universidad.latitud = 43.47122194796555;
        universidad.longitud = -3.800797786772268;

        comparadorDistancia = new GasolineraDistanciaComparator(universidad);
    }

    @Test
    public void testComparadorDistancia(){

        assertEquals(comparadorDistancia.compare(cercana, lejana), 0);
    }
}
