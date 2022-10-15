package com.example.pm013p01;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.pm013p01.configuracion.Personas;
import com.example.pm013p01.configuracion.SQLiteConexion;
import com.example.pm013p01.tablas.Transacciones;

import java.util.ArrayList;
import java.util.SortedMap;

public class ActivityCombo extends AppCompatActivity {
/*Variables globales*/

    SQLiteConexion conexion;
    Spinner spersonas;
    EditText nombres, apellidos, correo;
    ArrayList<Personas> lista;
    ArrayList<String> listString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);


        conexion = new SQLiteConexion(this, Transacciones.NameDatabase, null, 1);
        spersonas = (Spinner)  findViewById(R.id.spersonas);
        nombres = (EditText)  findViewById(R.id.txtnombre_ac);
        apellidos = (EditText)  findViewById(R.id.txtapellido_ac);
        correo = (EditText)  findViewById(R.id.txtcorreo_ac);

        ObtenerPersonas();
        ArrayAdapter<CharSequence> adp = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listString);
        spersonas.setAdapter(adp);

        spersonas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                try {
                    nombres.setText(lista.get(i).getNombres());
                    apellidos.setText(lista.get(i).getApellidos());
                    correo.setText(lista.get(i).getCorreo());
                }catch (Exception e)
                {

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void ObtenerPersonas() {
        SQLiteDatabase db = conexion.getReadableDatabase();
        Personas listapersonas = null;
        lista = new ArrayList<Personas>();

        Cursor cursor = db.rawQuery("SELECT * FROM "+ Transacciones.TbPersonas, null);
        while(cursor.moveToNext()){
            listapersonas = new Personas();
            listapersonas.setId(cursor.getInt(0));
            listapersonas.setNombres(cursor.getString(1));
            listapersonas.setApellidos(cursor.getString(2));
            listapersonas.setEdad(cursor.getInt(3));
            listapersonas.setCorreo(cursor.getString(4));

            lista.add(listapersonas);
        }

        cursor.close();

        fillcombo();


    }

    private void fillcombo() {
        listString = new ArrayList<String>();

        for (int i=0; i< lista.size(); i++){
            listString.add(lista.get(i).getNombres() + " | "+
                    lista.get(i).getApellidos() + " | "+
                    lista.get(i).getCorreo());

        }
    }
}