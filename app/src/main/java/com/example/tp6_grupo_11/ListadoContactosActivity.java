package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tp6_grupo_11.database.ContactoDao;
import com.example.tp6_grupo_11.model.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ListadoContactosActivity extends BaseActivity {

    private ListView lvContactos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        lvContactos = findViewById(R.id.lvContactos);
    }

    // Se recarga cada vez que la pantalla vuelve a mostrarse, asi aparecen los contactos recien guardados
    @Override
    protected void onResume() {
        super.onResume();
        cargarContactos();
    }

    /** Lee los contactos de SQLite y los muestra como "NOMBRE APELLIDO - MAIL". */
    private void cargarContactos() {
        List<Contacto> contactos = new ContactoDao(this).obtenerTodos();

        List<String> filas = new ArrayList<>();
        for (Contacto c : contactos) {
            filas.add(c.getNombre() + " " + c.getApellido() + " \u2013 " + c.getEmail());
        }

        lvContactos.setAdapter(new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, filas));
    }
}