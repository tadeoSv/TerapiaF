package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
    public void n1 (View view){
        Intent niv1 = new Intent(this, nivel1.class);
        startActivity(niv1);
    }

    public void n2 (View view){
        Intent niv2 = new Intent(this, nivel2.class);
        startActivity(niv2);
    }
    public void n3 (View view){
        Intent niv3 = new Intent(this, nivel3.class);
        startActivity(niv3);
    }
    public void n4 (View view){
        Intent niv4 = new Intent(this, nivel4.class);
        startActivity(niv4);
    }
    public void registro (View view){
        Intent reg = new Intent(this, Registro.class);
        startActivity(reg);
    }
    public void areaPadres (View view){
        Intent area = new Intent(this, AreaDePadres.class);
        startActivity(area);
    }
}