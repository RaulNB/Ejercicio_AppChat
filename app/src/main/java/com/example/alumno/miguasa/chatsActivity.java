package com.example.alumno.miguasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by alumno on 8/11/17.
 */

public class chatsActivity extends Activity{
    private MiAdaptadorChatsClass adaptador = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chats);

        Intent inicio = new Intent(this, inicioActivity.class);
        startActivity(inicio);

        adaptador = new MiAdaptadorChatsClass(this);
        ListView listChats = this.findViewById(R.id.chatsDisponibles);
        listChats.setAdapter(adaptador);

        listChats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(), chatActivity.class);
                intent.putExtra("TEXT_NOMBRE", adaptador.getNombre(position));
                intent.putExtra("INT_IMAGEN", adaptador.getImagen(position));
                intent.putExtra("INT_TLF", (int)id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        adaptador.refresh();
    }

    public void onClickChats (View v) {
        Intent intent = new Intent(this, contactosActivity.class);
        startActivity(intent);
    }

    public void onClickClose (View v) {
        finish();
    }
}
