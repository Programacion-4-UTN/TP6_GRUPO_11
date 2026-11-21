package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import com.example.tp6_grupo_11.adapters.ContactoAdapter;
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

        // Evento al tocar un contacto de la lista: muestra el popup con el detalle completo
        lvContactos.setOnItemClickListener((parent, view, position, id) -> {
            Contacto contactoSeleccionado = (Contacto) parent.getItemAtPosition(position);
            if (contactoSeleccionado != null) {
                mostrarPopupDetalle(contactoSeleccionado);
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
        lvContactos.setAdapter(new ContactoAdapter(this, contactos));
    }

    private String armarDetalleContacto(Contacto c) {
        return "Nombre: " + c.getNombre() + " " + c.getApellido() + "\n"
                + "Teléfono: " + c.getTelefono() + " (" + (c.getTipoTelefono() != null ? c.getTipoTelefono() : "") + ")\n"
                + "Email: " + c.getEmail() + " (" + (c.getTipoEmail() != null ? c.getTipoEmail() : "") + ")\n"
                + "Dirección: " + c.getDireccion() + "\n"
                + "Fecha Nacimiento: " + c.getFechaNacimiento() + "\n"
                + "Nivel de Estudios: " + c.getEstudios() + "\n"
                + "Intereses: " + c.getIntereses() + "\n"
                + "Recibe Información: " + (c.isRecibeInformacion() ? "Sí" : "No");
    }

    private void mostrarPopupDetalle(Contacto contacto) {
        new AlertDialog.Builder(this)
                .setTitle("Detalle del Contacto")
                .setMessage(armarDetalleContacto(contacto))
                .setPositiveButton("Cerrar", (dialog, which) -> dialog.dismiss())
                .show();
    }
}