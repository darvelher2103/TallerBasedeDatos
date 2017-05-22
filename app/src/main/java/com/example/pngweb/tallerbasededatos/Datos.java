package com.example.pngweb.tallerbasededatos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Datos {
    private static Apartamento apartamento;

    public static ArrayList<Apartamento> listarApartamentos(Context contexto) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        SQLiteDatabase db;
        String sql, piso, precio, balcon, sombra, mts, nome;


        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();
        //cursor

        sql = "SELECT * FROM Apartamentos";
        Cursor c = db.rawQuery(sql, null);


        //recorremos el cursor
        if (c.moveToFirst()) {
            do {
                piso = c.getString(0);
                precio = c.getString(1);
                balcon = c.getString(2);
                sombra = c.getString(3);
                mts = c.getString(4);
                nome = c.getString(5);
                apartamento = new Apartamento(piso, precio, balcon, sombra, mts, nome);
                apartamentos.add(apartamento);
            } while (c.moveToNext());
        }
        db.close();

        return apartamentos;
    }

    public static ArrayList<Apartamento> listarConBalconySombra(Context contexto) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        SQLiteDatabase db;
        String sql, piso, precio, balcon, sombra, mts, nome;


        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();
        //cursor

        sql = "SELECT * FROM Apartamentos WHERE balcon = 'Si' AND sombra ='Si'";
        Cursor c = db.rawQuery(sql, null);


        //recorremos el cursor
        if (c.moveToFirst()) {
            do {
                piso = c.getString(0);
                precio = c.getString(1);
                balcon = c.getString(2);
                sombra = c.getString(3);
                mts = c.getString(4);
                nome = c.getString(5);
                apartamento = new Apartamento(piso, precio, balcon, sombra, mts, nome);
                apartamentos.add(apartamento);
            } while (c.moveToNext());
        }
        db.close();

        return apartamentos;
    }

    public static ArrayList<Apartamento> listarMasCaro(Context contexto) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
    SQLiteDatabase db;
    String sql, piso, precio, balcon, sombra, mts, nome;


    ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
    db = aux.getReadableDatabase();
    //cursor

    sql = "SELECT * FROM Apartamentos  ORDER BY precio DESC  LIMIT 1 ";
    Cursor c = db.rawQuery(sql, null);


    //recorremos el cursor
        if (c.moveToFirst()) {
        do {
            piso = c.getString(0);
            precio = c.getString(1);
            balcon = c.getString(2);
            sombra = c.getString(3);
            mts = c.getString(4);
            nome = c.getString(5);
            apartamento = new Apartamento(piso, precio, balcon, sombra, mts, nome);
            apartamentos.add(apartamento);
        } while (c.moveToNext());
    }
        db.close();

        return apartamentos;
}

    public static ArrayList<Apartamento> Mayortamanio(Context contexto) {
        ArrayList<Apartamento> apartamentos = new ArrayList<>();
        SQLiteDatabase db;
        String sql, piso, precio, balcon, sombra, mts, nome;


        ApartamentosSQLiteOpenHelper aux = new ApartamentosSQLiteOpenHelper(contexto, "DBApartamentos", null, 1);
        db = aux.getReadableDatabase();
        //cursor

        sql = "SELECT * FROM Apartamentos  ORDER BY mts DESC  LIMIT 1 ";
        Cursor c = db.rawQuery(sql, null);


        //recorremos el cursor
        if (c.moveToFirst()) {
            do {
                piso = c.getString(0);
                precio = c.getString(1);
                balcon = c.getString(2);
                sombra = c.getString(3);
                mts = c.getString(4);
                nome = c.getString(5);
                apartamento = new Apartamento(piso, precio, balcon, sombra, mts, nome);
                apartamentos.add(apartamento);
            } while (c.moveToNext());
        }
        db.close();

        return apartamentos;
    }

}