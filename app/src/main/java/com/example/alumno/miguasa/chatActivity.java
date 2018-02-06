package com.example.alumno.miguasa;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class chatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat);

        BBDDContactos agenda = new BBDDContactos(this, "agenda", null, 1);
        SQLiteDatabase db = agenda.getWritableDatabase();

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("TEXT_NOMBRE");
        int imagen = intent.getIntExtra("INT_IMAGEN", 0);
        int telefono = intent.getIntExtra("INT_TLF", 0);

        final MiAdaptadorChatClass adaptador = new MiAdaptadorChatClass(this, telefono);
        ListView lista = this.findViewById(R.id.listaMensajes);
        lista.setAdapter(adaptador);

        TextView nombreChat = (TextView) findViewById(R.id.nombreChat);
        nombreChat.setText(nombre);

        ImageView imagenChat = (ImageView) findViewById(R.id.imagenChat);
        imagenChat.setBackgroundResource(imagen);

        ImageButton botonEnviar = (ImageButton) this.findViewById(R.id.enviarBoton);
        botonEnviar.setOnClickListener(new AdapterView.OnClickListener() {
            @Override
            public void onClick (View v) {
                EditText etTexto = (EditText) findViewById(R.id.textoMensajeEnviar);
                String textoMsg = etTexto.getText().toString();
                adaptador.refresh(textoMsg);
                etTexto.setText("");
            }
        });
    }

}
