package com.example.lenguajemx;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class nivel3 extends AppCompatActivity {
    private static final int RECOGNIZE_SPEECH_ACTIVITY = 1;
    public int contador=0;
    public TextView conView;
    public TextView ttrad;
    public ImageView imagenP;
    public ImageView imagenSec1;
    public ImageView imagenSec2;
    public ImageView imagenSec3;
    private SpeechRecognizer sr;
    int [] imagenPrin ={R.drawable.gato,R.drawable.pato,R.drawable.corazon,R.drawable.oveja,R.drawable.abeja,R.drawable.gusano,
            R.drawable.toro,R.drawable.loro,R.drawable.ardilla,R.drawable.sombrilla,R.drawable.raton,R.drawable.mano};
    public int select =0;
    public int []soncorrecto = {R.raw.buentrabajo,R.raw.exelente,R.raw.muybien,R.raw.perfecto};
    public int [] sonincorrecto = {R.raw.mmm,R.raw.ahmno,R.raw.tampoc};
    public int [] sonrimas ={R.raw.elgato,R.raw.elpato,R.raw.corazon,R.raw.obeja,R.raw.abeja,R.raw.gusano,R.raw.toro,R.raw.loro,R.raw.ardilla,R.raw.sombrilla,R.raw.raton,R.raw.mano};
    String [] nombre={"elefante",
            "gallina", "gato", "pato", "perro", "rana","bañera","cama","chimenea","coche","jarra","lápiz","lupa","madera","miel","moño","moto","nido",
            "pan","raqueta","tomates","vaca","zorro","corazón","oveja","abeja","gusano","toro","loro","ardilla","sombrilla","ratón","mano"};
    String [] nomImagPrin ={"gato","pato","corazón","oveja","abeja","gusano","toro","loro","ardilla","sombrilla","ratón","mano"};
    adminSqlite adm = new adminSqlite(this, "administrador",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nivel3);
        ttrad = (TextView)findViewById(R.id.Rec);
        imagenP = (ImageView)findViewById(R.id.Cuadro_principal);
        imagenSec1 = (ImageView)findViewById(R.id.prop1);
        imagenSec2 = (ImageView)findViewById(R.id.prop2);
        imagenSec3 = (ImageView)findViewById(R.id.prop3);
        conView = (TextView)findViewById(R.id.contEjer);
        sr = SpeechRecognizer.createSpeechRecognizer(this);
        this.imagenPrincipal();
        adm.primerUso();
    }

    public void menu (View view){
        Intent men = new Intent(this, MainActivity.class);
        startActivity(men);
    }

    public void imagenPrincipal(){
        select = (int) (Math.random()*12);
        System.out.println(" -------------------------   !!! esta es la imagen principal " +select+" imagen "+ nomImagPrin[select] );
        imagenP.setImageResource(imagenPrin[select]);
        this.imagenesSecundarias();
        MediaPlayer mp = MediaPlayer.create(this, R.raw.conquerimma);
        mp.start();
        contador++;
        String valor =String.valueOf(contador);
        conView.setText(valor);
        try {
            Thread.sleep(1800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        MediaPlayer mpp = MediaPlayer.create(this, sonrimas[select]);
        mpp.start();
    }


    public void imagenesSecundarias(){
        int [] imagenSec ={R.drawable.elefante,R.drawable.gallina,R.drawable.gato,R.drawable.pato //imagenes secundarias
                ,R.drawable.perro,R.drawable.rana,R.drawable.baniera,R.drawable.cama,R.drawable.chimenea
                ,R.drawable.coche,R.drawable.jarra,R.drawable.lapiz,R.drawable.lupa,R.drawable.madera,R.drawable.miel
                ,R.drawable.monio,R.drawable.moto,R.drawable.nido,R.drawable.pan,R.drawable.raqueta,R.drawable.tomate
                ,R.drawable.vaca,R.drawable.zorro,R.drawable.corazon,R.drawable.oveja,R.drawable.abeja,R.drawable.gusano,
                R.drawable.toro,R.drawable.loro,R.drawable.ardilla,R.drawable.sombrilla,R.drawable.raton,R.drawable.mano};
        while (true) {
    int rand = (int) (Math.random() * 33);
    int rand3 = (int) (Math.random() * 33);
    int rand2 = (int) (Math.random() * 33);

          
            //aquí se seleccionan las imágenes evitando repeticiones con base a sus nombres y seleccionando una que tomando las dos últimas letras, rimen
            if(((nombre[rand].substring((nombre[rand].length()-2),(nombre[rand].length())).equals(nomImagPrin[select].substring((nomImagPrin[select].length()-2),nomImagPrin[select].length())))
            || (nombre[rand2].substring(nombre[rand2].length()-2,nombre[rand2].length()).equals(nomImagPrin[select].substring(nomImagPrin[select].length()-2,nomImagPrin[select].length()))) ||
            (nombre[rand3].substring(nombre[rand3].length()-2,nombre[rand3].length()).equals(nomImagPrin[select].substring(nomImagPrin[select].length()-2,nomImagPrin[select].length())))) &&
                    ((rand != rand2) && (rand != rand3) && (rand2 != rand3)&&((imagenPrin[select]!=imagenSec[rand])&&(imagenPrin[select]!=imagenSec[rand2])&&(imagenPrin[select]!=(imagenSec[rand3]))))){
        System.out.println(" ************  imagen secundaria es "+ rand +"  " +rand2 +"  "+rand3+"******");
        System.out.println("primera condicion   "+ nombre[rand]+" con "+nomImagPrin[select]);
        System.out.println("segunda condicion   "+ nombre[rand2]+" con "+nomImagPrin[select]);
        System.out.println("tercera condicion   "+ nombre[rand3]+" con "+nomImagPrin[select]);
        imagenSec1.setImageResource(imagenSec[rand]);
        imagenSec2.setImageResource(imagenSec[rand2]);
        imagenSec3.setImageResource(imagenSec[rand3]);
        break;
    }

        }

    }

    public void comparacion(){
    String captura = (String) ttrad.getText();
        //se compara lo que fue pronunciado con la imagen principal
        if(captura.substring((captura.length()-2),captura.length()).equals(nomImagPrin[select].substring(nomImagPrin[select].length()-2,nomImagPrin[select].length()))){
            int aleatorio = (int)(Math.random()*4);
            MediaPlayer mp = MediaPlayer.create(this, soncorrecto[aleatorio]);
            adm.actualizarCorrecto(3);
            mp.start();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.imagenPrincipal();
        } else {
            int aleatorio = (int)(Math.random()*2);
            MediaPlayer mp = MediaPlayer.create(this, sonincorrecto[aleatorio]);
            mp.start();
            adm.actualizarincorrecto(3);
        }
    }

    /******************** traducción *********************/

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case RECOGNIZE_SPEECH_ACTIVITY:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> speech = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    String strSpeech2Text = speech.get(0);
                    ttrad.setText(strSpeech2Text);
                    String imp = (String)ttrad.getText();
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
            String mencionado = ttrad.getText().toString();
            mencionado = mencionado+" / "+nomImagPrin[select]+" / RIMAS ";
            ContentValues registro = new ContentValues();
            registro.put("palabra_mensionada",mencionado);

            BaseDeDatos.insert("palabras", null, registro);


        } else{
            Toast.makeText(this,"NO HAY BASE DE DATOS",Toast.LENGTH_SHORT).show();
        }


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