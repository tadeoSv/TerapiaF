package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AreaDePadres extends AppCompatActivity {
    public TextView palMen;
    public TextView nivel1;
    public TextView nivel2;
    public TextView nivel3;
    public TextView incn1;
    public TextView incn2;
    public TextView incn3;
    ListView lv;
    ArrayList lista;
    ArrayAdapter adaptador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adminSqlite admin = new adminSqlite(this,"administrador",null,1);
        admin.primerUso();
        SQLiteDatabase bd = admin.getWritableDatabase();
        setContentView(R.layout.activity_area_de_padres);
       lv = (ListView)findViewById(R.id.listamen);
       lista = admin.llenar_lv();
       adaptador = new ArrayAdapter(this, android.R.layout.simple_spinner_item,lista);
       lv.setAdapter(adaptador);
       nivel1 = (TextView)findViewById(R.id.ecn1);
        nivel2 = (TextView)findViewById(R.id.ecn2);
        nivel3 = (TextView)findViewById(R.id.ecn3);
        incn1 = (TextView)findViewById(R.id.einn1);
        incn2 = (TextView)findViewById(R.id.einn2);
        incn3 = (TextView)findViewById(R.id.einn3);

        Cursor tvn1 = bd.rawQuery("SELECT ejercicios_correctos,ejercicios_incorrectos FROM juegos where id=1",null);
        tvn1.moveToFirst();
        nivel1.setText(tvn1.getString(0));
        incn1.setText(tvn1.getString(1));

        bd.close();
        this.estNiv2();
      // palMen = (TextView)findViewById(R.id.palabra);
      //  this.consultar();
    }

    public void estNiv2(){

        adminSqlite admin = new adminSqlite(this,"administrador",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        System.out.println("//////////*//*/*     entra   ********************");
        Cursor tvn2 = bd.rawQuery("SELECT ejercicios_correctos,ejercicios_incorrectos FROM juegos where id=2",null);
        tvn2.moveToFirst();
        nivel2.setText(tvn2.getString(0));
        incn2.setText(tvn2.getString(1));
        bd.close();
        this.estNiv3();
    }

    public void estNiv3(){

        adminSqlite admin = new adminSqlite(this,"administrador",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor tvn3 = bd.rawQuery("SELECT ejercicios_correctos,ejercicios_incorrectos FROM juegos where id=3",null);
        tvn3.moveToFirst();
        nivel3.setText(tvn3.getString(0));
        incn3.setText(tvn3.getString(1));
        bd.close();
    }

    public void consejos (View view){
        Intent con = new Intent(this, consejos.class);
        startActivity(con);
    }

    public void consultar(){
        adminSqlite admin = new adminSqlite(this,"administrador",null,1);
        SQLiteDatabase bd = admin.getWritableDatabase();
            Cursor tabla = bd.rawQuery("SELECT palabra_mensionada,palabra_correcta FROM palabras ",null);
            if (tabla.moveToFirst()) {
                palMen.setText(tabla.getString(0));
               // bd.close();
            } else {
                Toast.makeText(this, "No hay datos", Toast.LENGTH_SHORT).show();

            }


     //   }
        bd.close();
    }
    public void menu (View view){
        Intent men = new Intent(this, MainActivity.class);
        startActivity(men);
    }
}