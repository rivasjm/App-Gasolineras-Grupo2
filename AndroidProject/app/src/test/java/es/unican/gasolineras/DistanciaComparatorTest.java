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
    private Gasolinera auxCercana;


    private PuntoInteres universidad;

    @Before
    public void inicializa(){

        //creo gasolineras necesarias

        cercana = new Gasolinera();
        cercana.setId("GasolineraPaco");
        cercana.setDireccion("Sardinero");
        cercana.setLatitud(43.47618775921668);
        cercana.setLongitud(-3.7933535145721233);

        lejana = new Gasolinera();
        lejana.setId("GasolineraMario");
        lejana.setDireccion("Torrelavega");
        lejana.setLatitud(43.356608665447474);
        lejana.setLongitud(-4.046146566530483);

        auxCercana = new Gasolinera();
        auxCercana.setId("GasolineraJaime");
        auxCercana.setLatitud(43.47618775921668);
        auxCercana.setLongitud(-3.7933535145721233);

        //creo un punto de interes
        universidad = new PuntoInteres();
        universidad.nombre = "Universidad";
        universidad.latitud = 43.47122194796555;
        universidad.longitud = -3.800797786772268;

        comparadorDistancia = new GasolineraDistanciaComparator(universidad);
    }

    @Test
    public void testComparadorDistancia(){

        //caso que la primera gasolinera esta mas cerca
        assertEquals(comparadorDistancia.compare(cercana, lejana), 1);

        //caso que la primera gasolinera esta mas lejos
        assertEquals(comparadorDistancia.compare(lejana, cercana), -1);

        //caso que esten a la misma distancia
        assertEquals(comparadorDistancia.compare(cercana, auxCercana), 0);
    }
}
