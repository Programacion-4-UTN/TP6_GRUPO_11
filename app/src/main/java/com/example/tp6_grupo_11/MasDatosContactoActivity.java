package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp6_grupo_11.model.Contacto;
import androidx.core.content.IntentCompat;

public class MasDatosContactoActivity extends BaseActivity {

    private TextView tvDatosResumen;
    private Contacto contacto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mas_datos_contacto);

        tvDatosResumen = findViewById(R.id.tvDatosResumen);


        if (getIntent() != null && getIntent().hasExtra("contacto")) {
            //contacto = (Contacto) getIntent().getSerializableExtra("contacto");
            contacto = IntentCompat.getSerializableExtra(getIntent(), "contacto", Contacto.class);
            if (contacto != null) {
                mostrarDatosContacto();
            }
        } else {
            Toast.makeText(this, "No se recibieron datos del contacto", Toast.LENGTH_SHORT).show();
        }
    }

    private void mostrarDatosContacto() {
        String resumen = "Nombre: " + contacto.getNombre() + " " + contacto.getApellido() + "\n" +
                "Teléfono: " + contacto.getTelefono() + " (" + contacto.getTipoTelefono() + ")\n" +
                "Email: " + contacto.getEmail() + " (" + contacto.getTipoEmail() + ")\n" +
                "Dirección: " + contacto.getDireccion() + "\n" +
                "Fecha de Nacimiento: " + contacto.getFechaNacimiento();

        if (tvDatosResumen != null) {
            tvDatosResumen.setText(resumen);
        }
    }
}