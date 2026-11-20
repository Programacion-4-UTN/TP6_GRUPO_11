package com.example.tp6_grupo_11.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.tp6_grupo_11.model.Contacto;

import java.util.ArrayList;
import java.util.List;

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

    /**
     * Devuelve todos los contactos guardados, ordenados por apellido y nombre
     * (sin distinguir mayusculas). Si no hay ninguno, devuelve una lista vacia.
     */
    public List<Contacto> obtenerTodos() {
        List<Contacto> contactos = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        try (Cursor cursor = db.query(
                AdminSQLiteOpenHelper.TABLA_CONTACTOS,
                null, null, null, null, null,
                AdminSQLiteOpenHelper.COL_APELLIDO + " COLLATE NOCASE ASC, "
                        + AdminSQLiteOpenHelper.COL_NOMBRE + " COLLATE NOCASE ASC")) {

            while (cursor.moveToNext()) {
                contactos.add(new Contacto(
                        entero(cursor, AdminSQLiteOpenHelper.COL_ID),
                        texto(cursor, AdminSQLiteOpenHelper.COL_NOMBRE),
                        texto(cursor, AdminSQLiteOpenHelper.COL_APELLIDO),
                        texto(cursor, AdminSQLiteOpenHelper.COL_TELEFONO),
                        texto(cursor, AdminSQLiteOpenHelper.COL_TIPO_TELEFONO),
                        texto(cursor, AdminSQLiteOpenHelper.COL_EMAIL),
                        texto(cursor, AdminSQLiteOpenHelper.COL_TIPO_EMAIL),
                        texto(cursor, AdminSQLiteOpenHelper.COL_DIRECCION),
                        texto(cursor, AdminSQLiteOpenHelper.COL_FECHA_NACIMIENTO),
                        texto(cursor, AdminSQLiteOpenHelper.COL_ESTUDIOS),
                        texto(cursor, AdminSQLiteOpenHelper.COL_INTERESES),
                        entero(cursor, AdminSQLiteOpenHelper.COL_RECIBE_INFORMACION) == 1));
            }
        } finally {
            db.close();
        }

        return contactos;
    }

    // Lee una columna de texto del cursor por nombre
    private static String texto(Cursor cursor, String columna) {
        return cursor.getString(cursor.getColumnIndexOrThrow(columna));
    }

    // Lee una columna numerica del cursor por nombre
    private static int entero(Cursor cursor, String columna) {
        return cursor.getInt(cursor.getColumnIndexOrThrow(columna));
    }
}