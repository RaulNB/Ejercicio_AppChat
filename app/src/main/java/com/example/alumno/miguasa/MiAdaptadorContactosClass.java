package com.example.alumno.miguasa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by alumno on 25/10/17.
 */

public class MiAdaptadorContactosClass extends BaseAdapter{
    BBDDContactos agenda;
    SQLiteDatabase db;
    Cursor cursor;
    Context cont;

    public MiAdaptadorContactosClass(Context cont) {
        this.cont = cont;
        agenda = new BBDDContactos(cont, "agenda", null, 1);
        db = agenda.getWritableDatabase();
        cursor = db.rawQuery("SELECT nombre, telefono, imagen FROM contactos", null);
    }

    public String getNombre (int position) {
        cursor.moveToPosition(position);
        return cursor.getString(0);
    }

    public int getImagen (int position) {
        cursor.moveToPosition(position);
        return cursor.getInt(2);
    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }

    @Override
    public Object getItem(int position) {
        cursor.moveToPosition(position);
        return cursor;
    }

    @Override
    public long getItemId(int position) {
        cursor.moveToPosition(position);
        return cursor.getInt(1);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(cont);
            convertView = inflater.inflate(R.layout.plantilla_contacto, null);
        }
        cursor.moveToPosition(position);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.nombre);
        tvNombre.setText(cursor.getString(0));
        TextView tvTelefono = (TextView) convertView.findViewById(R.id.telefono);
        tvTelefono.setText(cursor.getString(1));
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imgContacto);
        imagen.setBackgroundResource(cursor.getInt(2));

        return convertView;
    }
}
