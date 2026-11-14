package com.example.tp6_grupo_11;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

/** Activity base: aporta el menú y el título clickeable a toda pantalla que la extienda. */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        configurarTituloClickeable();
    }

    /** Reemplaza el título de la barra por un TextView que vuelve al inicio al tocarlo. */
    private void configurarTituloClickeable() {
        ActionBar barra = getSupportActionBar();
        if (barra == null) return;

        TextView titulo = new TextView(this);
        titulo.setText(R.string.app_name);
        titulo.setTextColor(Color.WHITE);
        titulo.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        titulo.setOnClickListener(v -> irAInicio());

        barra.setDisplayShowTitleEnabled(false); // oculta el título estándar
        barra.setDisplayShowCustomEnabled(true);
        barra.setCustomView(titulo);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_agregar) {
            navegarA(AgregarContactoActivity.class);
            return true;
        }
        if (id == R.id.menu_listar) {
            navegarA(ListadoContactosActivity.class);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void navegarA(Class<?> destino) {
        if (getClass().equals(destino)) return; // ya estamos en esa pantalla
        Intent intent = new Intent(this, destino);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        startActivity(intent);
    }

    /** Vuelve al inicio y cierra las pantallas que estaban encima. */
    private void irAInicio() {
        if (this instanceof InicioActivity) return; // ya estamos en el inicio
        Intent intent = new Intent(this, InicioActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        startActivity(intent);
    }
}