package es.unican.gasolineras.puntoInteres;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

import es.unican.gasolineras.activities.puntoInteres.AnhadirPuntoInteresPresenter;
import es.unican.gasolineras.activities.puntoInteres.IAnhadirPuntoInteresView;
import es.unican.gasolineras.model.GasolinerasResponse;
import es.unican.gasolineras.model.PuntoInteres;
import es.unican.gasolineras.repository.IPuntosInteresDao;

@RunWith(RobolectricTestRunner.class)
public class AnhadirPuntoInteresPresenterTest {

    private IAnhadirPuntoInteresView vistaMock;
    private IPuntosInteresDao puntosInteresDaoMock;
    private AnhadirPuntoInteresPresenter presenter;

    String nombreStr = "casa";
    String latitudStr = "43.462274";
    String longitudStr = "-3.809740";

    double latitud = Double.parseDouble(latitudStr);
    double longitud = Double.parseDouble(longitudStr);

    PuntoInteres p1 = new PuntoInteres();

    @Before
    public void setUp() {

        vistaMock = mock(IAnhadirPuntoInteresView.class);
        puntosInteresDaoMock = mock(IPuntosInteresDao.class);

        p1.setNombre(nombreStr);
        p1.setLatitud(latitud);
        p1.setLongitud(longitud);

        when(puntosInteresDaoMock.loadByName(nombreStr)).thenReturn(p1);

        when(vistaMock.getPuntosInteresDAO()).thenReturn(puntosInteresDaoMock);

        presenter = new AnhadirPuntoInteresPresenter(vistaMock);
    }

    @Test
    public void TestOnGuardarPuntoInteresClicked() {

        // Caso valido
        presenter.onGuardarPuntoInteresClicked(nombreStr, latitudStr, longitudStr);
        verify(puntosInteresDaoMock).insertAll(p1);
        verify(vistaMock).mostrarMensaje("Punto de interés guardado");

        // Caso no valido (Ya existe el punto de interes)
        presenter.onGuardarPuntoInteresClicked(nombreStr, latitudStr, longitudStr);
        verify(vistaMock).mostrarMensaje("Ya existe un punto de interés con ese nombre");

        // Caso no valido (Error acceso a BBDD)

    }
}
