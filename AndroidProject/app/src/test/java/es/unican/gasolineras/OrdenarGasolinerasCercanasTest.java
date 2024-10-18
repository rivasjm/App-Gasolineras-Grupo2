package es.unican.gasolineras;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import es.unican.gasolineras.activities.main.IMainContract;
import es.unican.gasolineras.activities.main.MainPresenter;
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
    private Gasolinera gasolineraLejana;

    private PuntoInteres universidad;

    @Before
    public void inicializa(){

        //inicializo los mocks
        MockitoAnnotations.openMocks(this);

        //creo los objetos necesarios

        //creo las gasolineras
        gasolineraCercana = new Gasolinera();
        gasolineraCercana.setId("GasolineraPaco");
        gasolineraCercana.setDireccion("Calle Los Castros");

        gasolineraLejana = new Gasolinera();
        gasolineraLejana.setId("GasolineraMario");
        gasolineraLejana.setDireccion("Calle Honduras");

        //creo la lista de gasolineras que voy a mockear
        List<Gasolinera> listaGasolineras = new ArrayList<>();
        listaGasolineras.add(gasolineraCercana);
        listaGasolineras.add(gasolineraLejana);


        //creo un punto de interes
        universidad = new PuntoInteres();
        universidad.nombre = "Universidad";
        universidad.latitud = 43.47122194796555;
        universidad.longitud = -3.800797786772268;


        when(mockPuntoInteres.loadByName("Universidad")).thenReturn(universidad);


        doAnswer(invocation -> {
            ICallBack callBack = invocation.getArgument(0);
            callBack.onSuccess(listaGasolineras);
            return null;
        }).when(mockGasolineras).requestGasolineras(any(ICallBack.class), any(String.class));

        when(mockVista.getGasolinerasRepository()).thenReturn(mockGasolineras);

        sut = new MainPresenter();
    }

    @Test
    public void testOrdenaGasolinerasMasCercanas(){

        sut.init(mockVista);


    }
}
