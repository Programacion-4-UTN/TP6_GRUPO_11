package com.example.tp6_grupo_11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.example.tp6_grupo_11.model.Contacto;
public class ContactoDao {

    private final AdminSQLiteOpenHelper dbHelper;

    public ContactoDao(Context context) {
        this.dbHelper = new AdminSQLiteOpenHelper(context);
    }

    public long insertar(Contacto contacto) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        long idInsertado = -1;

        try {
            ContentValues values = new ContentValues();
            values.put(AdminSQLiteOpenHelper.COL_NOMBRE, contacto.getNombre());
            values.put(AdminSQLiteOpenHelper.COL_APELLIDO, contacto.getApellido());
            values.put(AdminSQLiteOpenHelper.COL_TELEFONO, contacto.getTelefono());
            values.put(AdminSQLiteOpenHelper.COL_TIPO_TELEFONO, contacto.getTipoTelefono());
            values.put(AdminSQLiteOpenHelper.COL_EMAIL, contacto.getEmail());
            values.put(AdminSQLiteOpenHelper.COL_TIPO_EMAIL, contacto.getTipoEmail());
            values.put(AdminSQLiteOpenHelper.COL_DIRECCION, contacto.getDireccion());
            values.put(AdminSQLiteOpenHelper.COL_FECHA_NACIMIENTO, contacto.getFechaNacimiento());
            values.put(AdminSQLiteOpenHelper.COL_ESTUDIOS, contacto.getEstudios());
            values.put(AdminSQLiteOpenHelper.COL_INTERESES, contacto.getIntereses());
            // En SQLite los booleanos se guardan como INTEGER (1 = true, 0 = false)
            values.put(AdminSQLiteOpenHelper.COL_RECIBE_INFORMACION, contacto.isRecibeInformacion() ? 1 : 0);

            idInsertado = db.insert(AdminSQLiteOpenHelper.TABLA_CONTACTOS, null, values);
        } finally {
            db.close();
        }

        return idInsertado;
    }
}