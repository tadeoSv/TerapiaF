package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class Registro extends AppCompatActivity {
    private Spinner spinner1;
    private TextView tvinf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        tvinf = (TextView) findViewById(R.id.informativo);
       // spinner1 =(Spinner) findViewById(R.id.spinner);

        String [] edad = {"3","4","","5"};

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,edad);
        spinner1.setAdapter(adapter);
    }
    public void edad(View view){
        String eleccion = spinner1.getSelectedItem().toString();
        if(eleccion =="3"){
            tvinf.setText("Información: \n"+"El niño o niña agrupa objetos, como alimentos o vestimentas, identifica colores, Usa la mayoría de los sonidos del habla, pero puede confundir algunos sonidos más difíciles como l, r, s, su, ch, y, v, z. Estos sonidos pueden no manejarse correctamente hasta los 7 u 8 años. "+"\n"+"Su vocabulario: entre 900 y 1000 palabras.");
        }
    }

}