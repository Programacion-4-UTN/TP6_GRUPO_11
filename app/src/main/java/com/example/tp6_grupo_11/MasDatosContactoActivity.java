package com.example.tp6_grupo_11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.core.content.IntentCompat;

import com.example.tp6_grupo_11.database.ContactoDao;
import com.example.tp6_grupo_11.model.Contacto;

public class MasDatosContactoActivity extends BaseActivity {

    // Contacto recibido del formulario del Ejercicio 1 (se guardara junto con los datos nuevos)
    private Contacto contacto;

    // Nivel de estudios elegido en el RadioGroup ("" mientras no se haya elegido ninguno)
    private String nivelEstudios = "";
    private CheckBox cbDeporte, cbMusica, cbArte, cbTecnologia;
    private Switch swRecibirInfo;
    private Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos_contacto);

        if (getIntent() != null && getIntent().hasExtra("contacto")) {
            contacto = IntentCompat.getSerializableExtra(getIntent(), "contacto", Contacto.class);
        }

        // SELECTOR DE ESTUDIOS: guarda el texto de la opcion elegida cada vez que cambia
        RadioGroup rgEstudios = findViewById(R.id.rgEstudios);
        rgEstudios.setOnCheckedChangeListener((grupo, idSeleccionado) -> {
            RadioButton elegido = grupo.findViewById(idSeleccionado);
            nivelEstudios = (elegido != null) ? elegido.getText().toString() : "";
        });

        cbDeporte = findViewById(R.id.cbDeporte);
        cbMusica = findViewById(R.id.cbMusica);
        cbArte = findViewById(R.id.cbArte);
        cbTecnologia = findViewById(R.id.cbTecnologia);
        swRecibirInfo = findViewById(R.id.swRecibirInfo);
        btnGuardar = findViewById(R.id.btnGuardar);

        // Cambia la etiqueta del Switch entre "Sí" y "No" al cambiar su estado
        swRecibirInfo.setOnCheckedChangeListener((buttonView, isChecked) -> {
            swRecibirInfo.setText(isChecked ? "Sí" : "No");
        });

        btnGuardar.setOnClickListener(v -> guardarContacto());

        if (contacto == null) {
            Toast.makeText(this, "No se recibieron datos del contacto", Toast.LENGTH_SHORT).show();
        }
    }

    private void guardarContacto() {
        if (contacto == null) {
            Toast.makeText(this, "Error: no hay datos base del contacto para guardar.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 1. Validar que se haya seleccionado al menos una opcion de estudios
        if (nivelEstudios.isEmpty()) {
            Toast.makeText(this, "Por favor, seleccioná un nivel de estudios.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 2. Validar que se haya seleccionado al menos un interes
        String interesesSeleccionados = getIntereses();
        if (interesesSeleccionados.isEmpty()) {
            Toast.makeText(this, "Por favor, seleccioná al menos un interés.", Toast.LENGTH_SHORT).show();
            return;
        }

        // 3. Completar el objeto Contacto con los datos del Ejercicio 2
        contacto.setEstudios(nivelEstudios);
        contacto.setIntereses(interesesSeleccionados);
        contacto.setRecibeInformacion(swRecibirInfo.isChecked());

        // 4. Guardar en SQLite mediante ContactoDao
        ContactoDao contactoDao = new ContactoDao(this);
        long id = contactoDao.insertar(contacto);

        if (id != -1) {
            Toast.makeText(this, "Contacto guardado correctamente.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, InicioActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Error al guardar el contacto en la base de datos.", Toast.LENGTH_SHORT).show();
        }
    }

    /** Nivel de estudios elegido, o "" si todavia no se eligio ninguno. */
    public String getNivelEstudios() {
        return nivelEstudios;
    }

    /** Intereses seleccionados separados por coma, o "" si no se eligio ninguno. */
    public String getIntereses() {
        StringBuilder intereses = new StringBuilder();
        if (cbDeporte.isChecked()) {
            intereses.append(cbDeporte.getText().toString());
        }
        if (cbMusica.isChecked()) {
            if (intereses.length() > 0) intereses.append(", ");
            intereses.append(cbMusica.getText().toString());
        }
        if (cbArte.isChecked()) {
            if (intereses.length() > 0) intereses.append(", ");
            intereses.append(cbArte.getText().toString());
        }
        if (cbTecnologia.isChecked()) {
            if (intereses.length() > 0) intereses.append(", ");
            intereses.append(cbTecnologia.getText().toString());
        }
        return intereses.toString();
    }
}