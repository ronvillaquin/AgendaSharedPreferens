package com.example.agendasharedpreferens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText caja_nombre, caja_descripcion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        caja_nombre = (EditText)findViewById(R.id.caja_nombre);
        caja_descripcion = (EditText)findViewById(R.id.caja_descripcion);

    }

    public void btn_guardar(View vista){
        String nombre = caja_nombre.getText().toString();
        String descripcion = caja_descripcion.getText().toString();

        // creamos el objeto para guardar mediante shared preferens
        SharedPreferences recuperar_preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);
        SharedPreferences.Editor obj_editor = recuperar_preferencias.edit();
        // en este caso para poder eferecencias va a ser los datos del contacto mediante la variable string nombre
        obj_editor.putString(nombre, descripcion);
        obj_editor.commit();

        Toast.makeText(this, "Guardado con exito", Toast.LENGTH_LONG).show();
    }

    public void btn_buscar(View vista){
        String nombre = caja_nombre.getText().toString();

        // creamos el shred preferenc de nuevo para recuperar lo que ya se guardo en las preferencias
        SharedPreferences recuperar_preferencias = getSharedPreferences("agenda", Context.MODE_PRIVATE);

        // con esto recuperamos lo q ya tiene guardado shared preferens
        String desripcion = recuperar_preferencias.getString(nombre, "");

        if (desripcion.length()==0){
            Toast.makeText(this, "No se encontro Registro", Toast.LENGTH_LONG).show();
        }else{
            // si consigue datos le vamos a decir que imprima en la caja descripcion lo que encontro
            caja_descripcion.setText(desripcion);
        }
    }

    public void btn_limpiar(View vista){
        caja_nombre.setText("");
        caja_descripcion.setText("");
    }
}
