package com.example.alumno.miguasa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class contactosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contactos);

        Intent intent = getIntent();

        final MiAdaptadorContactosClass adaptador = new MiAdaptadorContactosClass(this);
        ListView listContactos = this.findViewById(R.id.contactosDisponibles);
        listContactos.setAdapter(adaptador);

        listContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

    public void onClickAgenda (View v) {
        finish();
    }
}