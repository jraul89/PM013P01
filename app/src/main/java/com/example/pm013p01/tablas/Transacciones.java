package com.example.pm013p01.tablas;

public class Transacciones {
    // Nombre de la base de datos
    public  static String NameDatabase = "PM01DB";

    /* Creacion de las tablas de la BD */
    public static final String TbPersonas = "personas";

    /* Campos de la tabla personas */
    public static final String id = "id";
    public static final String nombres = "nombres";
    public static final String apellidos = "apellidos";
    public static final String edad = "edad";
    public static final String correo = "correo";

    // DDL =  Data Definition Language
    public static  final String CTPersonas = "CREATE TABLE personas (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "nombres TEXT NOT NULL," +
            "apellidos TEXT NOT NULL," +
            "edad INTEGER NOT NULL," +
            "correo TEXT NOT NULL" +
            ");";

    public static final String DropTPersona = "DROP TABLE IF EXISTS personas;";

    /* CRUD = Create, Read, Update, Delete */

    // Read
    public static final String GetPersonas = "Select * FROM " + Transacciones.TbPersonas;

}
