package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast; // <-- [AGREGADO]

import com.example.tp6_grupo_11.adapters.ContactoAdapter; // <-- [AGREGADO]
import com.example.tp6_grupo_11.database.ContactoDao;
import com.example.tp6_grupo_11.model.Contacto;

import java.util.List;

public class ListadoContactosActivity extends BaseActivity {

    private ListView lvContactos;
    private TextView tvSinContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        lvContactos = findViewById(R.id.lvContactos);
        tvSinContactos = findViewById(R.id.tvSinContactos);

        // Muestra automáticamente tvSinContactos cuando la lista está vacía
        lvContactos.setEmptyView(tvSinContactos);

        // [AGREGADO] Evento al tocar un contacto de la lista (listo para implementar el detalle)
        lvContactos.setOnItemClickListener((parent, view, position, id) -> {
            Contacto contactoSeleccionado = (Contacto) parent.getItemAtPosition(position);
            if (contactoSeleccionado != null) {
                // TODO: Mostrar todos los datos de 'contactoSeleccionado' en un popup o en pantalla
                Toast.makeText(this, "Seleccionado: " + contactoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Se recarga cada vez que la pantalla vuelve a mostrarse, asi aparecen los contactos recien guardados
    @Override
    protected void onResume() {
        super.onResume();
        cargarContactos();
    }

    /** Lee los contactos de SQLite y los carga en el ListView mediante ContactoAdapter. */
    private void cargarContactos() {
        List<Contacto> contactos = new ContactoDao(this).obtenerTodos();
        lvContactos.setAdapter(new ContactoAdapter(this, contactos)); // <-- [ACTUALIZADO]
    }
}