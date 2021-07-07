package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class nivel1 extends AppCompatActivity {

    public int ejercicio;
    private ImageView cuadro1;
    private ImageView cuadro2;
    private ImageView cuadro3;
    private ImageView cuadro4;
    public int []soncorrecto = {R.raw.buentrabajo,R.raw.exelente,R.raw.muybien,R.raw.perfecto};
    public int [] sonincorrecto = {R.raw.mmm,R.raw.ahmno,R.raw.tampoc};
    private int sonido_reproduccion;
    private int bandera;
    public int cuadsel=0;

    //public int tinco;//incorrecto 3 veces
    private int [] imagenes ={R.drawable.elefante,R.drawable.gallina,R.drawable.gato,R.drawable.pato
            ,R.drawable.perro,R.drawable.rana,R.drawable.baniera,R.drawable.cama,R.drawable.chimenea
            ,R.drawable.coche,R.drawable.jarra,R.drawable.lapiz,R.drawable.lupa,R.drawable.madera,R.drawable.miel
            ,R.drawable.monio,R.drawable.moto,R.drawable.nido,R.drawable.pan,R.drawable.raqueta,R.drawable.tomate
            ,R.drawable.vaca,R.drawable.zorro,
            R.drawable.corazon,R.drawable.oveja,R.drawable.abeja,R.drawable.gusano,
            R.drawable.toro,R.drawable.loro,R.drawable.ardilla,R.drawable.sombrilla,R.drawable.raton,R.drawable.mano};
    private int [] sonidos={R.raw.elefante,R.raw.gallina,R.raw.gato,R.raw.pato
            ,R.raw.perro,R.raw.rana,R.raw.baniera,R.raw.cama,R.raw.chimenea
            ,R.raw.coche,R.raw.jarra,R.raw.lapiz,R.raw.lupa,R.raw.madera,R.raw.miel
            ,R.raw.monio,R.raw.moto,R.raw.nido,R.raw.pan,R.raw.raqueta,R.raw.tomates
            ,R.raw.vaca,R.raw.zorro,
            R.raw.tcorazon,R.raw.tobeja,R.raw.tabeja,R.raw.tgusano,R.raw.ttoro,R.raw.tloro,R.raw.tardilla,R.raw.tsombrilla,R.raw.traton,R.raw.tmano};
    private TextView ejervie;
    private int correcto = 0;
    private int score=0;
    adminSqlite adm = new adminSqlite(this, "administrador",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel1);
        this.ejercicio=0;
        this.correcto = (int) Math.random()*4+1;
        System.out.println("Inicia en: "+correcto);
        ejervie = (TextView)findViewById(R.id.canejer);
        cuadro1=(ImageView)findViewById(R.id.marco1);
        cuadro2=(ImageView)findViewById(R.id.marco2);
        cuadro3=(ImageView)findViewById(R.id.marco3);
        cuadro4=(ImageView)findViewById(R.id.marco4);

        adm.primerUso();
        this.resetear();
    }
    public void sonidos(){
       MediaPlayer mp = MediaPlayer.create(this, sonidos[cuadsel]);
       mp.start();
    }
    public void menu (View view){
        Intent men = new Intent(this, MainActivity.class);
        startActivity(men);
    }
    public void felis (View view){
        Intent fel = new Intent(this, nivel4.class);
        startActivity(fel);
    }

    public void resetear (){

        int c1=0;
        int c2=0;
        int c3 =0;
        int c4=0;
        int seleccion;
        while (true){
            c1=(int)(Math.random()*33);
            c2=(int)(Math.random()*33);
            c3=(int)(Math.random()*33);
            c4=(int)(Math.random()*33);
            if(c1 !=c2&&c1!=c3&&c1!=c4&&c2 !=c3&&c2!=c4&&c3!=c4){
                break;
            }
        }


        ejercicio ++;
        String let = "";
        let = String.valueOf(ejercicio);
        ejervie.setText(let);
        cuadro1.setImageResource(imagenes[c1]);
        cuadro2.setImageResource(imagenes[c2]);
        cuadro3.setImageResource(imagenes[c3]);
        cuadro4.setImageResource(imagenes[c4]);
        correcto = (int)(Math.random()*4+1);
        cuadsel=0;
        if(correcto==1) {
            cuadsel = c1;
        }else if(correcto==2){
            cuadsel = c2;
            }else if (correcto==3){
            cuadsel = c3;
        }else{
            cuadsel = c4;
        }
        String[]nombre={"elefante",
        "gallina",
        "gato",
        "pato",
        "perro",
        "rana", "baniera","cama","chimenea","coche","jarra","lapiz","lupa","madera","miel","monio","moto","nido",
                "pan","raqueta","tomate","vaca","zorro","corazon","oveja","abeja","gusano","toro","loro","ardilla","sombrilla","raton","mano"};
        System.out.println("toca a :" + nombre[cuadsel]);
        this.sonidos();

    }
   /* public void comparacion(int cuadro){
        if (correcto==1){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            mp.start();
            System.out.println("Correcto!!");
            score++;
            //  adminSqlite adm = new adminSqlite(this, "administrador",null,1);
            adm.actualizarCorrecto(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.felis();
            this.resetear();
        }else{
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            System.out.println("incorrecto!!");
            adm.actualizarincorrecto(1);
        }

    }*/

    public void btn1 (View view){
        if (correcto==1){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            mp.start();
            System.out.println("Correcto!!");
            score++;
          //  adminSqlite adm = new adminSqlite(this, "administrador",null,1);
            adm.actualizarCorrecto(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //this.felis();
            this.resetear();
        }else{
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            System.out.println("incorrecto!!");
            adm.actualizarincorrecto(1);
        }
        System.out.println("score: "+score);


    }
    public void btn2 (View view){
        if (correcto==2){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            mp.start();
            System.out.println("Correcto!!");
            score++;
         //   adminSqlite adm = new adminSqlite(this, "administrador",null,1);
            adm.actualizarCorrecto(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.resetear();
        }else{
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            System.out.println("incorrecto!!");
            adm.actualizarincorrecto(1);
        }
        System.out.println("score: "+score);

    }
    public void btn3 (View view){
        if (correcto==3){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            mp.start();
            System.out.println("Correcto!!");
            score++;
          //  adminSqlite adm = new adminSqlite(this, "administrador",null,1);
            adm.actualizarCorrecto(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.resetear();
        }else{
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            System.out.println("incorrecto!!");
            adm.actualizarincorrecto(1);
        }
        System.out.println("score: "+score);

    }
    public void btn4 (View view){

        if (correcto==4){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            mp.start();
            System.out.println("Correcto!!");
            score++;
           // adminSqlite adm = new adminSqlite(this, "administrador",null,1);
            adm.actualizarCorrecto(1);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.resetear();    
        }else{
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            adm.actualizarincorrecto(1);
            System.out.println("incorrecto!!");
        }
        System.out.println("score: "+score);

    }
}