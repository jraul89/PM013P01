package com.example.pm013p01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pm013p01.configuracion.Personas;
import com.example.pm013p01.configuracion.SQLiteConexion;
import com.example.pm013p01.tablas.Transacciones;

import java.util.ArrayList;

public class ActivityList extends AppCompatActivity {
    ListView listperson;
    SQLiteConexion conexion;
    ArrayList<Personas> lista; // Arreglo de la clase Personas
    ArrayList<String> listaconcatenada;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        conexion = new SQLiteConexion(this, Transacciones.NameDatabase,null,1);

        listperson = (ListView) findViewById(R.id.listperson);

        GetlistPerson();

        ArrayAdapter adp = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listaconcatenada);
        listperson.setAdapter(adp);

        listperson.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "Elemento (" + i+1 + "): " + listaconcatenada.get(i).toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void GetlistPerson() {
        SQLiteDatabase db = conexion.getReadableDatabase(); // Base de datos en modo lectura

        Personas listpersonas = null;

        lista = new ArrayList<Personas>(); // Lista de Objetos del tipo personas

        Cursor cursor = db.rawQuery(Transacciones.GetPersonas, null);

        while (cursor.moveToNext()) {
            listpersonas = new Personas();
            listpersonas.setId(cursor.getInt(0));
            listpersonas.setNombres(cursor.getString(1));
            listpersonas.setApellidos(cursor.getString(2));
            listpersonas.setEdad(cursor.getInt(3));
            listpersonas.setCorreo(cursor.getString(4));

            lista.add(listpersonas);
        }

        cursor.close();

        LlenarLista();

    }

    private void LlenarLista() {
        listaconcatenada = new ArrayList<>();

        for (int i=0; i < lista.size(); i++) {
            listaconcatenada.add(lista.get(i).getNombres() + " " +
                    lista.get(i).getApellidos() + " - " +
                    lista.get(i).getCorreo());
        }
    }
}