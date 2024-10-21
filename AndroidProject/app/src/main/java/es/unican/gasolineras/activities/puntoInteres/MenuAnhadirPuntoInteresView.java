package es.unican.gasolineras.activities.puntoInteres;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import es.unican.gasolineras.R;
import es.unican.gasolineras.repository.AppDatabase;
import es.unican.gasolineras.repository.IPuntosInteresDao;

public class MenuAnhadirPuntoInteresView extends AppCompatActivity implements IAnhadirPuntoInteresView {

    private EditText etNombre, etLatitud, etLongitud;
    private Button btnGuardar, btnCancelar;
    private AnhadirPuntoInteresPresenter presentador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anhadir_punto_interes);

        // Inicializar los elementos de la vista
        etNombre = findViewById(R.id.etNombre);
        etLatitud = findViewById(R.id.etLatitud);
        etLongitud = findViewById(R.id.etLongitud);
        btnGuardar = findViewById(R.id.buttonGuardar);
        btnCancelar = findViewById(R.id.buttonCancelar);

        // Inicializar el Presentador
        presentador = new AnhadirPuntoInteresPresenter(this);

        // Acción del botón Guardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nombre = obtenerNombre();
                String latitudStr = obtenerLatitud();
                String longitudStr = obtenerLongitud();
                presentador.onGuardarPuntoInteresClicked(nombre, latitudStr, longitudStr);
            }
        });

        // Acción del botón Cancelar
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cerrar la actividad si se cancela
            }
        });
    }

    // Métodos de la interfaz IAnhadirPuntoInteresView
    @Override
    public void mostrarMensaje(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String obtenerNombre() {
        return etNombre.getText().toString();
    }

    @Override
    public String obtenerLatitud() {
        return etLatitud.getText().toString();
    }

    @Override
    public String obtenerLongitud() {
        return etLongitud.getText().toString();
    }

    @Override
    public void cerrarVista() {
        finish(); // Cerrar la actividad
    }

    @Override
    public IPuntosInteresDao getPuntosInteresDAO() {
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "puntos-interes").allowMainThreadQueries().build();
        return db.puntosInteresDao();
    }
}