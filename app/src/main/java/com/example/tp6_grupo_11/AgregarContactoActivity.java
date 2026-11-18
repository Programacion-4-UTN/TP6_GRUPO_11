package com.example.tp6_grupo_11;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tp6_grupo_11.model.Contacto;
import com.example.tp6_grupo_11.util.FechaTextWatcher;
import com.example.tp6_grupo_11.util.ValidadorContacto;

public class AgregarContactoActivity extends BaseActivity {

    private EditText etNombre, etApellido, etTelefono, etEmail, etDireccion, etFechaNacimiento;
    private Spinner spTelefono, spEmail;
    private Button btnContinuar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_contacto);

        // VINCULA LOS CONTROLES
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etTelefono = findViewById(R.id.etTelefono);
        spTelefono = findViewById(R.id.spTelefono);
        etEmail = findViewById(R.id.etEmail);
        spEmail = findViewById(R.id.spEmail);
        etDireccion = findViewById(R.id.etDireccion);
        etFechaNacimiento = findViewById(R.id.etFechaNacimiento);
        btnContinuar = findViewById(R.id.btnContinuar);

        // CARGA DE SPINNERS CON LOS DATOS REQUERIDOS
        String[] opcionesSpinner = {"Casa", "Trabajo", "Móvil"};
        ArrayAdapter<String> adapterSpinner = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                opcionesSpinner
        );
        adapterSpinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTelefono.setAdapter(adapterSpinner);
        spEmail.setAdapter(adapterSpinner);

        // FORMATEO AUTOMÁTICO DE FECHA (dd/mm/aaaa)
        etFechaNacimiento.addTextChangedListener(new FechaTextWatcher(etFechaNacimiento));

        // EVENTO BOTÓN CONTINUAR
        btnContinuar.setOnClickListener(v -> procesarFormulario());
    }

    private void procesarFormulario() {

    }
}