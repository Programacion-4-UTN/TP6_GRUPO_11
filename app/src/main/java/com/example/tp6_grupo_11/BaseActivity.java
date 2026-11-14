package com.example.tp6_grupo_11;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

/** Activity base: aporta el menú de navegación superior a toda pantalla que la extienda. */
public abstract class BaseActivity extends AppCompatActivity {

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
}