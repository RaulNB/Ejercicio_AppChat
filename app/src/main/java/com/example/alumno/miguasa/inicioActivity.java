package com.example.alumno.miguasa;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class inicioActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inicio);
    }

    public void onClickInicio (View v) {
        finish();
    }
}
