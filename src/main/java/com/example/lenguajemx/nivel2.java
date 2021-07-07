package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.ImageView;
import android.widget.Toast;
import java.util.ArrayList;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class nivel2 extends AppCompatActivity {

    private SpeechRecognizer sr;
    private static final String TAG = "MyStt3Activity";
    TextView tvlec;
    private int correcto = 1;
    private int score=0;
    public int ejercicio;
    private TextView ejervie;
    private ImageView cuadro1;
    public int bandera =0;
    String[]nombre={"elefante",
            "gallina",
            "gato",
            "pato",
            "perro",
            "rana","bañera","cama","chimenea","coche","jarra","lápiz","lupa","madera","miel","moño","moto","nido",
            "pan","raqueta","tomates","vaca","zorro","corazon","oveja","abeja","gusano","toro","loro","ardilla","sombrilla","raton","mano"};
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    public int []soncorrecto = {R.raw.buentrabajo,R.raw.exelente,R.raw.muybien,R.raw.perfecto};
    public int [] sonincorrecto = {R.raw.mmm,R.raw.ahmno,R.raw.tampoc};
    private int [] imagenes ={R.drawable.elefante,R.drawable.gallina,R.drawable.gato,R.drawable.pato
            ,R.drawable.perro,R.drawable.rana,R.drawable.baniera,R.drawable.cama,R.drawable.chimenea
            ,R.drawable.coche,R.drawable.jarra,R.drawable.lapiz,R.drawable.lupa,R.drawable.madera,R.drawable.miel
            ,R.drawable.monio,R.drawable.moto,R.drawable.nido,R.drawable.pan,R.drawable.raqueta,R.drawable.tomate
            ,R.drawable.vaca,R.drawable.zorro,R.drawable.corazon,R.drawable.oveja,R.drawable.abeja,R.drawable.gusano,R.drawable.toro,R.drawable.loro,R.drawable.ardilla};
    adminSqlite adm = new adminSqlite(this, "administrador",null,1);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel2);
        tvlec=(TextView)findViewById(R.id.traductor);
        this.ejercicio=0;
        //this.correcto = (int) Math.random()*4+1;
        System.out.println("Inicia en: "+correcto);
        ejervie = (TextView)findViewById(R.id.canejer3);
        cuadro1=(ImageView)findViewById(R.id.marcosoy);

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        //sr.setRecognitionListener(new listener());
        this.inicio();
        adm.primerUso();
    }

    public void menu (View view){
        Intent men = new Intent(this, MainActivity.class);
        startActivity(men);
    }

    public void inicio(){
        int c1=0;
        int seleccion;

            c1=(int)(Math.random()*23);
            bandera=c1;


        ejercicio ++;
        String let = "";
        let = String.valueOf(ejercicio);
        ejervie.setText(let);
        cuadro1.setImageResource(imagenes[c1]);

        String[]nombre1={"elefante",
                "gallina",
                "gato",
                "pato",
                "perro",
                "rana","bañera","cama","chimenea","coche","jarra","lápiz","lupa","madera","miel","monio","moto","nido",
                "pan","raqueta","tomate","vaca","zorro"};
        System.out.println("toca a :" + nombre1[correcto]);
        try {
            Thread.sleep(1500);
            this.sonidos();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.Hablar();
    }

    public void sonidos (){

        MediaPlayer mp = MediaPlayer.create(this, R.raw.quesoy);
        mp.start();


    }
    public void comparacion(){

    String resut = (String)tvlec.getText();
        System.out.println("la comparación es con "+nombre[bandera]);
        // aquí se hace la comparación para determinar si la palabra es correcta o incorrecta
    if ((resut.toLowerCase()).equals(nombre[bandera])){
        int aleatorio = (int)(Math.random()*4);
        MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
        mp.start();
        System.out.println("Correcto!!");
        //Actualizamos la base de datos los valores correctos
        adm.actualizarCorrecto(2);// el numero 2 hace referencia al nivel
        score++;


        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.inicio();
    }else{
        int aleatorio = (int)(Math.random()*2);
        MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
        mp.start();
        System.out.println("incorrecto!!");
        //Actualizamos la base de datos los valores incorrectos
        adm.actualizarincorrecto(2);
    }
        tvlec.setText("");
    }

    // traducción de las palabras mensionadas obtenidas con el reconocimiento de voz
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    tvlec.setText(strSpeech2Text);
                     String imp = (String)tvlec.getText();
                    // System.out.println("esto es lo que escuchó!! " +imp);
                    this.registrar();
                    this.comparacion();
                }
                break;
            default:
                break;
        }
    }

    public void registrar(){
        adminSqlite admin = new adminSqlite(this, "administrador",null,1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();
        if(BaseDeDatos != null) {
            String mencionado = tvlec.getText().toString();
            mencionado = mencionado+" / "+nombre[bandera];
            ContentValues registro = new ContentValues();
            registro.put("palabra_mensionada",mencionado);
            //registro.put("palabra_mensionada",nombre[bandera]);
            BaseDeDatos.insert("palabras", null, registro);


        } else{
            Toast.makeText(this,"NO HAY BASE DE DATOS",Toast.LENGTH_SHORT).show();
        }

       /*if(!mencionado.isEmpty()){
           this.Hablar(View view);

       }*/
        BaseDeDatos.close();
    }

    public void Hablar(View view){

        Intent intentActionRecognizeSpeech = new Intent(
                RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // Configura el Lenguaje (Español-México)
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intentActionRecognizeSpeech.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,"voice.recognition.test");
        intentActionRecognizeSpeech.putExtra(
                RecognizerIntent.EXTRA_LANGUAGE_MODEL, "es-MX");
        try {
            startActivityForResult(intentActionRecognizeSpeech,
                    RECOGNIZE_SPEECH_ACTIVITY);
            sr.startListening(intentActionRecognizeSpeech);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "Tu dispositivo no soporta el reconocimiento por voz",
                    Toast.LENGTH_SHORT).show();
        }

    }

}