package es.unican.gasolineras.activities.main;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.gasolineras.model.Gasolinera;
import es.unican.gasolineras.model.PuntoInteres;
import es.unican.gasolineras.repository.ICallBack;
import es.unican.gasolineras.repository.IGasolinerasRepository;
import es.unican.gasolineras.repository.IPuntosInteresDAO;


public class OrdenarGasolinerasCercanasTest {

    @Mock
    private static IPuntosInteresDAO mockPuntoInteres;

    @Mock
    private static IGasolinerasRepository mockGasolineras;

    @Mock
    private static IMainContract.View mockVista;

    private static MainPresenter sut;

    private Gasolinera gasolineraCercana;
    private Gasolinera gasolineraNeutra;
    private Gasolinera gasolineraLejana;
    private Gasolinera gasolineraMuylejana;

    private PuntoInteres universidad;

    private List<Gasolinera> listaGasolineras;

    @Before
    public void inicializa(){

        //inicializo los mocks
        MockitoAnnotations.openMocks(this);

        //creo los objetos necesarios

        //creo las gasolineras
        gasolineraCercana = new Gasolinera();
        gasolineraCercana.setId("GasolineraPaco");
        gasolineraCercana.setDireccion("Sardinero");
        gasolineraCercana.setLatitud(43.47618775921668);
        gasolineraCercana.setLongitud(-3.7933535145721233);

        gasolineraLejana = new Gasolinera();
        gasolineraLejana.setId("GasolineraMario");
        gasolineraLejana.setDireccion("Torrelavega");
        gasolineraLejana.setLatitud(43.356608665447474);
        gasolineraLejana.setLongitud(-4.046146566530483);

        gasolineraNeutra = new Gasolinera();
        gasolineraNeutra.setId("GasolineraLuis");
        gasolineraNeutra.setDireccion("Bezana");
        gasolineraNeutra.setLatitud(43.406608665447474);
        gasolineraNeutra.setLongitud(-4.0);

        gasolineraMuylejana= new Gasolinera();
        gasolineraMuylejana.setId("GasolineraJuan");
        gasolineraMuylejana.setDireccion("America");
        gasolineraMuylejana.setLatitud(0.0);
        gasolineraMuylejana.setLongitud(0.0);


        //creo un punto de interes
        universidad = new PuntoInteres();
        universidad.nombre = "Universidad";
        universidad.latitud = 43.47122194796555;
        universidad.longitud = -3.800797786772268;

        when(mockPuntoInteres.loadByName("Universidad")).thenReturn(universidad);


        sut = new MainPresenter();
    }

    @Test
    public void testOrdenaGasolinerasMasCercanas2Gasos(){

        //creo la lista de gasolineras que voy a mockear
        listaGasolineras = new ArrayList<>();
        listaGasolineras.add(gasolineraLejana);
        listaGasolineras.add(gasolineraCercana);

        doAnswer(invocation -> {
            ICallBack callBack = invocation.getArgument(0);
            callBack.onSuccess(listaGasolineras);
            return null;
        }).when(mockGasolineras).requestGasolineras(any(ICallBack.class), any(String.class));

        when(mockVista.getGasolinerasRepository()).thenReturn(mockGasolineras);

        //creo capturacion de la lista de las gasolineras ya ordenadas
        ArgumentCaptor<List<Gasolinera>> captor = ArgumentCaptor.forClass(List.class);

        //inicializo
        sut.init(mockVista);

        //llamo al metodo que pruebo
        sut.ordenarGasolinerasCercanasPtoInteres(universidad);

        //capturo la lista
        verify(mockVista, times(2)).showStations(captor.capture());

        //verifico que la lista este bien ordenada
        List<Gasolinera> listaCapturada = captor.getValue();
        assertEquals(gasolineraCercana, listaCapturada.get(0));
        assertEquals(gasolineraLejana, listaCapturada.get(1));
    }

    @Test
    public void testOrdenaGasolinerasMasCercanasMuchasGasos() {

        //creo la lista de gasolineras que voy a mockear
        listaGasolineras = new ArrayList<>();
        listaGasolineras.add(gasolineraLejana);
        listaGasolineras.add(gasolineraCercana);
        listaGasolineras.add(gasolineraMuylejana);
        listaGasolineras.add(gasolineraNeutra);

        doAnswer(invocation -> {
            ICallBack callBack = invocation.getArgument(0);
            callBack.onSuccess(listaGasolineras);
            return null;
        }).when(mockGasolineras).requestGasolineras(any(ICallBack.class), any(String.class));

        when(mockVista.getGasolinerasRepository()).thenReturn(mockGasolineras);

        doAnswer(invocation -> {
            ICallBack callBack = invocation.getArgument(0);
            callBack.onSuccess(listaGasolineras);
            return null;
        }).when(mockGasolineras).requestGasolineras(any(ICallBack.class), any(String.class));

        when(mockVista.getGasolinerasRepository()).thenReturn(mockGasolineras);

        //creo capturacion de la lista de las gasolineras ya ordenadas
        ArgumentCaptor<List<Gasolinera>> captor = ArgumentCaptor.forClass(List.class);

        //inicializo
        sut.init(mockVista);

        //llamo al metodo que pruebo
        sut.ordenarGasolinerasCercanasPtoInteres(universidad);

        //capturo la lista
        verify(mockVista, times(2)).showStations(captor.capture());

        //verifico que la lista este bien ordenada
        List<Gasolinera> listaCapturada = captor.getValue();
        assertEquals(gasolineraCercana, listaCapturada.get(0));
        assertEquals(gasolineraNeutra, listaCapturada.get(1));
        assertEquals(gasolineraLejana, listaCapturada.get(2));
        assertEquals(gasolineraMuylejana, listaCapturada.get(3));
    }
}
