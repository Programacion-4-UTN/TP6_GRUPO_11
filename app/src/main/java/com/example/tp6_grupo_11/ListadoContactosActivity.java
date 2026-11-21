package com.example.tp6_grupo_11;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;

import com.example.tp6_grupo_11.adapters.ContactoAdapter;
import com.example.tp6_grupo_11.database.ContactoDao;
import com.example.tp6_grupo_11.model.Contacto;

import java.util.ArrayList;
import java.util.List;

public class ListadoContactosActivity extends BaseActivity {

    private ListView lvContactos;
    private TextView tvSinContactos;
    private TextView tvCantidadContactos;

    private EditText etBuscarContacto;
    private List<Contacto> listaCompletaContactos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_contactos);

        lvContactos = findViewById(R.id.lvContactos);
        tvSinContactos = findViewById(R.id.tvSinContactos);
        tvCantidadContactos = findViewById(R.id.tvCantidadContactos);
        etBuscarContacto = findViewById(R.id.etBuscarContacto);
        etBuscarContacto.addTextChangedListener(new android.text.TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {

                filtrarContactos(s.toString());

            }
            @Override public void afterTextChanged(android.text.Editable s) {}
        });

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

    /** obtengo los contactos,  el texto insertado para buscar, y lo filtro segun eso  */
    private void cargarContactos() {
        listaCompletaContactos = new ContactoDao(this).obtenerTodos();
        String textoActual = (etBuscarContacto != null) ? etBuscarContacto.getText().toString() : "";
        filtrarContactos(textoActual);
    }

    private void filtrarContactos(String textoActual) {
        String filtro = textoActual.trim().toLowerCase();
        List<Contacto> filtrados = new ArrayList<>();

        for (Contacto c : listaCompletaContactos) {
            String nombreCompleto = (c.getNombre() + " " + c.getApellido()).toLowerCase();
            String email = (c.getEmail() != null) ? c.getEmail().toLowerCase() : "";
            if (filtro.isEmpty() || nombreCompleto.contains(filtro) || email.contains(filtro)) {
                filtrados.add(c);
            }
        }

        tvCantidadContactos.setText("Total de contactos: " + filtrados.size());
        lvContactos.setAdapter(new ContactoAdapter(this, filtrados));
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