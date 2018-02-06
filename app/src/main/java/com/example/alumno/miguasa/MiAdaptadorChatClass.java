package com.example.alumno.miguasa;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;

/**
 * Created by alumno on 16/11/17.
 */

public class MiAdaptadorChatClass extends BaseAdapter {
    BBDDContactos agenda;
    SQLiteDatabase db;
    Cursor cursor;
    Context cont;
    int tel;

    public MiAdaptadorChatClass (Context cont, int tel) {
        this.tel = tel;
        this.cont = cont;
        agenda = new BBDDContactos(cont, "agenda", null, 1);
        db = agenda.getWritableDatabase();
        cursor = db.rawQuery("SELECT mensaje, fecha, emisor FROM chats WHERE (receptor=" + tel + " AND emisor=600000000) OR (receptor=600000000 AND emisor=" + tel + ") ORDER BY fecha ASC", null);
    }

    public void refresh (String textoMsg) {
        if (!textoMsg.trim().equals("")) {
            java.util.Date fecha = new java.util.Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String fechaFormat = dateFormat.format(fecha);
            db.execSQL("INSERT INTO chats (emisor, fecha, mensaje, receptor) VALUES (600000000, '" + fechaFormat + "', '" + textoMsg + "', " + tel + ")");

            cursor = db.rawQuery("SELECT mensaje, fecha, emisor FROM chats WHERE (receptor=" + tel + " AND emisor=600000000) OR (receptor=600000000 AND emisor=" + tel + ") ORDER BY fecha ASC", null);
            this.notifyDataSetChanged();
        }
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
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(cont);
            convertView = inflater.inflate(R.layout.plantilla_chat, null);
        }
        cursor.moveToPosition(position);
        TextView tvMensaje = (TextView) convertView.findViewById(R.id.mensajeChat);
        tvMensaje.setText(cursor.getString(0));
        TextView tvFecha = (TextView) convertView.findViewById(R.id.fechaChat);
        tvFecha.setText(cursor.getString(1));
        LinearLayout llMensaje = (LinearLayout) convertView.findViewById(R.id.layoutMens);
        if (cursor.getInt(2) == 600000000) {
            tvMensaje.setGravity(Gravity.RIGHT);
            tvFecha.setGravity(Gravity.RIGHT);
            llMensaje.setBackgroundColor(Color.GREEN);
        }
        return convertView;
    }
}
