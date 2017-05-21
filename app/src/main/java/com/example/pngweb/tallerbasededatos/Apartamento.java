package com.example.pngweb.tallerbasededatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;


public class Apartamento {
        private  String piso;
        private  String precio;
        private  String balcon;
        private  String sombra;
        private String mts;
        private  String nome;

    public Apartamento(String piso, String precio, String balcon, String sombra, String mts, String nome) {
        this.piso = piso;
        this.precio = precio;
        this.balcon = balcon;
        this.sombra = sombra;
        this.mts = mts;
        this.nome = nome;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getBalcon() {
        return balcon;
    }

    public void setBalcon(String balcon) {
        this.balcon = balcon;
    }

    public String getSombra() {
        return sombra;
    }

    public void setSombra(String sombra) {
        this.sombra = sombra;
    }

    public String getMts() {
        return mts;
    }

    public void setMts(String mts) {
        this.mts = mts;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public  void guardar (Context contexto){
        //declaraos las variables
        SQLiteDatabase db;
        String sql;


        //abrimos la conexión de la db en modo de escritura

        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto,"DBApartamentos",null,1);
        db = aux.getWritableDatabase();

        //insertar version 1
         sql = "INSERT INTO Apartamentos values('"
                +this.getPiso()+"','"
                +this.getPrecio()+"','"
                +this.getBalcon()+"','"
                +this.getSombra()+"','"
                +this.getMts()+"','"
                +this.getNome()+"')";
        db.execSQL(sql);



        //importante siempre tener control de las conexiones sql
        db.close();
    }

}

