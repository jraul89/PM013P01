package com.example.pm013p01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    TextView txtresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtresultado = (TextView) findViewById(R.id.txtresultado);

        Bundle resultado = getIntent().getExtras();
        txtresultado.setText(resultado.getString("nombre"));


    }
}