package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

public class AgregarContactoActivity extends BaseActivity {

    private EditText etNombre, etApellido, etTelefono;
    private Spinner spTelefono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        // VINCULA LOS CONTORLES
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        spTelefono = findViewById(R.id.spTelefono);

        // CARGA DEL SPINNER CON LOS DATOS REQUERIDOS
        String[] opcionesSpinner = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opcionesSpinner
        );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTelefono.setAdapter(adapterSpinner);
    }
}