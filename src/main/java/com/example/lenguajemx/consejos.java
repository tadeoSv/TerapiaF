package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class consejos extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consejos);
    }
    public void menu (View view){
        Intent men = new Intent(this, MainActivity.class);
        startActivity(men);
    }

}