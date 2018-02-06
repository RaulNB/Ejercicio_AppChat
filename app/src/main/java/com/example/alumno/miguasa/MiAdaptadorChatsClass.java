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
 * Created by alumno on 8/11/17.
 */

public class MiAdaptadorChatsClass extends BaseAdapter{
    BBDDContactos agenda;
    SQLiteDatabase db;
    Cursor cursor;
    Context cont;

    public MiAdaptadorChatsClass(Context cont) {
        this.cont = cont;
        agenda = new BBDDContactos(cont, "agenda", null, 1);
        db = agenda.getWritableDatabase();
        cursor = db.rawQuery("SELECT nombre, mensaje, imagen, fecha, telefono FROM contactos, chats WHERE (contactos.telefono=chats.emisor OR contactos.telefono=chats.receptor) AND (receptor=600000000 OR emisor=600000000) AND fecha=(SELECT MAX(fecha) FROM chats WHERE telefono=emisor OR telefono=receptor) ORDER BY fecha DESC", null);
    }

    public void refresh (){
        cursor = db.rawQuery("SELECT nombre, mensaje, imagen, fecha, telefono FROM contactos, chats WHERE (contactos.telefono=chats.emisor OR contactos.telefono=chats.receptor) AND (receptor=600000000 OR emisor=600000000) AND fecha=(SELECT MAX(fecha) FROM chats WHERE telefono=emisor OR telefono=receptor) ORDER BY fecha DESC", null);
        notifyDataSetChanged();
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
        return cursor.getInt(4);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(cont);
            convertView = inflater.inflate(R.layout.plantilla_chats, null);
        }
        cursor.moveToPosition(position);
        TextView tvNombre = (TextView) convertView.findViewById(R.id.nombre);
        tvNombre.setText(cursor.getString(0));
        TextView tvMensaje = (TextView) convertView.findViewById(R.id.ultimoMensaje);
        tvMensaje.setText(cursor.getString(1));
        ImageView imagen = (ImageView) convertView.findViewById(R.id.imgContacto);
        imagen.setBackgroundResource(cursor.getInt(2));
        TextView tvFecha = (TextView) convertView.findViewById(R.id.fecha);
        tvFecha.setText(cursor.getString(3));

        return convertView;
    }
}