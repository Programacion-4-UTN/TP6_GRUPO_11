package com.example.tp6_grupo_11.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "administracion_contactos.db";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLA_CONTACTOS = "contactos";
    public static final String COL_ID = "id";
    public static final String COL_NOMBRE = "nombre";
    public static final String COL_APELLIDO = "apellido";
    public static final String COL_TELEFONO = "telefono";
    public static final String COL_TIPO_TELEFONO = "tipo_telefono";
    public static final String COL_EMAIL = "email";
    public static final String COL_TIPO_EMAIL = "tipo_email";
    public static final String COL_DIRECCION = "direccion";
    public static final String COL_FECHA_NACIMIENTO = "fecha_nacimiento";
    public static final String COL_ESTUDIOS = "estudios";
    public static final String COL_INTERESES = "intereses";
    public static final String COL_RECIBE_INFORMACION = "recibe_informacion";

    private static final String CREATE_TABLE_CONTACTOS = "CREATE TABLE " + TABLA_CONTACTOS + " (" +
            COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COL_NOMBRE + " TEXT, " +
            COL_APELLIDO + " TEXT, " +
            COL_TELEFONO + " TEXT, " +
            COL_TIPO_TELEFONO + " TEXT, " +
            COL_EMAIL + " TEXT, " +
            COL_TIPO_EMAIL + " TEXT, " +
            COL_DIRECCION + " TEXT, " +
            COL_FECHA_NACIMIENTO + " TEXT, " +
            COL_ESTUDIOS + " TEXT, " +
            COL_INTERESES + " TEXT, " +
            COL_RECIBE_INFORMACION + " INTEGER" +
            ")";

    public AdminSQLiteOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_CONTACTOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLA_CONTACTOS);
        onCreate(db);
    }
}