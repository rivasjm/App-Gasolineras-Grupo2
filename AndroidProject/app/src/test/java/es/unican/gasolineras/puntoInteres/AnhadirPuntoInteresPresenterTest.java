package es.unican.gasolineras.puntoInteres;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteException;

import androidx.test.core.app.ApplicationProvider;

import org.junit.Before;
import org.junit.Test;
import org.junit.function.ThrowingRunnable;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.robolectric.RobolectricTestRunner;

import es.unican.gasolineras.activities.puntoInteres.AnhadirPuntoInteresPresenter;
import es.unican.gasolineras.activities.puntoInteres.IAnhadirPuntoInteresContract;
import es.unican.gasolineras.activities.puntoInteres.IAnhadirPuntoInteresContract.View;
import es.unican.gasolineras.model.GasolinerasResponse;
import es.unican.gasolineras.model.PuntoInteres;
import es.unican.gasolineras.repository.AppDatabase;
import es.unican.gasolineras.repository.DbFunctions;
import es.unican.gasolineras.repository.IPuntosInteresDao;

@RunWith(RobolectricTestRunner.class)
public class AnhadirPuntoInteresPresenterTest {

    AppDatabase db;

    Context context = ApplicationProvider.getApplicationContext();

    private IAnhadirPuntoInteresContract.View vistaMock;
    private IPuntosInteresDao puntosInteresDao;
    private AnhadirPuntoInteresPresenter presenter;

    String nombreStr = "casa";
    String latitudStr = "43.462274";
    String longitudStr = "-3.809740";

    double latitud = Double.parseDouble(latitudStr);
    double longitud = Double.parseDouble(longitudStr);

    PuntoInteres p;

    @Before
    public void setUp() {

        vistaMock = mock(IAnhadirPuntoInteresContract.View.class);

        db = DbFunctions.generaBaseDatosPuntosInteres(context);
        puntosInteresDao = db.puntosInteresDao();
        //puntosInteresDao.delete(any(PuntoInteres.class));

        when(vistaMock.getPuntosInteresDAO()).thenReturn(puntosInteresDao);

        p = new PuntoInteres(nombreStr, latitud, longitud);

        presenter = new AnhadirPuntoInteresPresenter(vistaMock);
    }

    @Test
    public void TestOnGuardarPuntoInteresClicked() {

        // Caso valido
        presenter.onGuardarPuntoInteresClicked(nombreStr, latitudStr, longitudStr);
        PuntoInteres puntoCapturado = puntosInteresDao.loadByName(nombreStr);
        assertEquals(nombreStr, puntoCapturado.getNombre());
        assertEquals(latitud, puntoCapturado.getLatitud(), 0.0);
        assertEquals(longitud, puntoCapturado.getLongitud(), 0.0);
        verify(vistaMock).mostrarMensaje("Punto de interés guardado");

        // Caso no valido (Ya existe el punto de interes con ese nombre)
        presenter.onGuardarPuntoInteresClicked(nombreStr, "43.4733", "-3.80111");
        verify(vistaMock).mostrarMensaje("Ya existe un punto de interés con ese nombre");

        /* Caso no valido (Error acceso a BBDD)
        presenter.onGuardarPuntoInteresClicked("pabellon", "43.47578", "-3.76644");
        verify(vistaMock).mostrarMensaje("Ha ocurrido un error en la base de datos");*/

        puntosInteresDao.delete(puntoCapturado);

    }
}
