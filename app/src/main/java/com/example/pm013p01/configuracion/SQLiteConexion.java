package com.example.pm013p01.configuracion;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.pm013p01.tablas.Transacciones;

public class SQLiteConexion extends SQLiteOpenHelper {
    // Constructor de la clase para la base de datos
    public SQLiteConexion(Context context,
                          String dbname,
                          SQLiteDatabase.CursorFactory factory,
                          int version)
    {
        super(context, dbname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Creacion de las tablas de la db
        db.execSQL(Transacciones.CTPersonas); // Creando la tabla Personas



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        // Eliminamos la data y las tablas de la aplicacion
        db.execSQL(Transacciones.DropTPersona);
        onCreate(db);
    }

}