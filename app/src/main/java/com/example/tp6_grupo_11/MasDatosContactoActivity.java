package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.Toast;

import androidx.core.content.IntentCompat;

import com.example.tp6_grupo_11.model.Contacto;

public class MasDatosContactoActivity extends BaseActivity {

    // Contacto recibido del formulario del Ejercicio 1 (se guardara junto con los datos nuevos)
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos_contacto);

        if (getIntent() != null && getIntent().hasExtra("contacto")) {
            contacto = IntentCompat.getSerializableExtra(getIntent(), "contacto", Contacto.class);
        }

        if (contacto == null) {
            Toast.makeText(this, "No se recibieron datos del contacto", Toast.LENGTH_SHORT).show();
        }
    }
}