package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;
import androidx.core.content.IntentCompat;

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

        btnGuardar.setOnClickListener(v -> {
            // TODO: Validar que se haya seleccionado al menos un estudio y un interes,
            // completar el objeto 'contacto' y guardarlo en SQLite con ContactoDao.
            Toast.makeText(this, "Pendiente: validar y guardar contacto", Toast.LENGTH_SHORT).show();
        });

        if (contacto == null) {
            Toast.makeText(this, "No se recibieron datos del contacto", Toast.LENGTH_SHORT).show();
        }
    }

    /** Nivel de estudios elegido, o "" si todavia no se eligio ninguno. */
    public String getNivelEstudios() {
        return nivelEstudios;
    }
}