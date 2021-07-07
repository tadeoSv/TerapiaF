package com.example.lenguajemx;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class adminSqlite extends SQLiteOpenHelper{
    public adminSqlite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDatosMovil) {
        BaseDatosMovil.execSQL("CREATE TABLE palabras(codigo int primary key,palabra_mensionada text NOT NULL, palabra_correcta text )" );
        BaseDatosMovil.execSQL("CREATE TABLE juegos(id int, nombre TEXT,ejercicios_correctos int NOT NULL,ejercicios_incorrectos INT NOT NULL )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE juegos ");
        db.execSQL("DROP TABLE palabras ");
        this.onCreate(db);
    }

        public void primerUso(){
            SQLiteDatabase db = this.getWritableDatabase();
            String count = "SELECT count(*) FROM juegos";
            Cursor mcursor = db.rawQuery(count, null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            System.out.println("la cantidad de registros almacenado es "+ icount);
            if(icount == 0){
                System.out.println("**** primer uso ***************************************************");
                ContentValues registro = new ContentValues();
                registro.put("id",1);
                registro.put("nombre","nivel1");
                registro.put("ejercicios_correctos",.0);
                registro.put("ejercicios_incorrectos",0);
                db.insert("juegos", null, registro);

                ////////////////////////
                ContentValues registro2 = new ContentValues();
                registro2.put("id",2);
                registro2.put("nombre","nivel2");
                registro2.put("ejercicios_correctos",0);
                registro2.put("ejercicios_incorrectos",0);
                db.insert("juegos", null, registro2);
                ///////////////////
                ContentValues registro3 = new ContentValues();
                registro3.put("id",3);
                registro3.put("nombre","nivel3");
                registro3.put("ejercicios_correctos",0);
                registro3.put("ejercicios_incorrectos",0);
                db.insert("juegos", null, registro3);
                db.close();
            } else {
                System.out.println(" ----------ya ha sido usado antes************************");
            }
        }

    public ArrayList llenar_lv(){
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase database = this.getWritableDatabase();
        String q = "SELECT palabra_mensionada FROM palabras";
        Cursor registros = database.rawQuery(q,null);
        if(registros.moveToFirst()){
            do{
                lista.add(registros.getString(0));

            }while (registros.moveToNext());
        }
        System.out.println(" *************se llenó el array list******************");
        return lista;
    }

    public void actualizarCorrecto(int tipo){
        SQLiteDatabase database = this.getWritableDatabase();
        String cant = "SELECT ejercicios_correctos FROM juegos WHERE id ="+tipo;
        Cursor registro = database.rawQuery(cant,null);
        registro.moveToFirst();
        String regreso = registro.getString(0);
        int valor = Integer.parseInt(regreso);
        valor++;
        System.out.println("lo que te regreso es : "+regreso);
        ContentValues upd = new ContentValues();
            upd.put("ejercicios_correctos",valor);
        System.out.println("lo que se agregará es: "+valor);
            int can = database.update("juegos",upd, "id="+tipo,null);
            database.close();
    }

    public void actualizarincorrecto(int tipo){
        SQLiteDatabase database = this.getWritableDatabase();
        String cant = "SELECT ejercicios_incorrectos FROM juegos WHERE id ="+tipo;
        Cursor registro = database.rawQuery(cant,null);
        registro.moveToFirst();
        String regreso = registro.getString(0);
        int valor = Integer.parseInt(regreso);
        valor++;
        System.out.println("lo que te regreso es : "+regreso);
        ContentValues upd = new ContentValues();
        upd.put("ejercicios_incorrectos",valor);
        System.out.println("lo que se agregará es: "+valor);
        int can = database.update("juegos",upd, "id="+tipo,null);
        database.close();
    }
}
