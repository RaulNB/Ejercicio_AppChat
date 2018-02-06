package com.example.alumno.miguasa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by alumno on 6/11/17.
 */

public class BBDDContactos extends SQLiteOpenHelper {
    String sqlCreateContactos = "CREATE TABLE contactos (nombre TEXT, telefono INT, sexo CHAR, imagen INT)";
    String sqlCreateChats = "CREATE TABLE chats (emisor INT, fecha DATETIME, mensaje CHAR, receptor INT)";

    public BBDDContactos (Context cont, String nombre, CursorFactory factory, int version) {
        super(cont, nombre, factory, version);
    }

    @Override
    public void onCreate (SQLiteDatabase db) {
        db.execSQL(sqlCreateContactos);
        db.execSQL(sqlCreateChats);

        if (db != null) {
            db.execSQL("INSERT INTO contactos (nombre, telefono, sexo, imagen) VALUES ('Artorias', 685878787, 'H', " + R.drawable.artorias + ")");
            db.execSQL("INSERT INTO contactos (nombre, telefono, sexo, imagen) VALUES ('Solaire', 685234587, 'H', " + R.drawable.solaire + ")");
            db.execSQL("INSERT INTO contactos (nombre, telefono, sexo, imagen) VALUES ('Waifu', 685874347, 'M', " + R.drawable.guardiana + ")");

            db.execSQL("INSERT INTO chats (emisor, fecha, mensaje, receptor) VALUES (685878787, '2017-11-8 9:35:37', 'HOLA :)', 600000000)");
            db.execSQL("INSERT INTO chats (emisor, fecha, mensaje, receptor) VALUES (685878787, '2017-11-8 9:36:01', 'Que tal?', 600000000)");
            db.execSQL("INSERT INTO chats (emisor, fecha, mensaje, receptor) VALUES (685234587, '2017-11-6 7:24:25', 'Praise The Sun', 600000000)");
            db.execSQL("INSERT INTO chats (emisor, fecha, mensaje, receptor) VALUES (600000000, '2017-11-8 9:36:40', 'Bien, y que tal por el abismo?', 685878787)");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS Contactos");
        db.execSQL(sqlCreateContactos);
        db.execSQL(sqlCreateChats);
    }
}
