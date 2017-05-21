package com.example.pngweb.tallerbasededatos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Reportes extends AppCompatActivity {
    private ListView opcionesReport;
    private  String []opc;
    private ArrayAdapter adapter;
    private Intent i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reportes);

        opcionesReport=(ListView)findViewById(R.id.lstReportes);
        opc = getResources().getStringArray(R.array.opcionesReport);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        opcionesReport.setAdapter(adapter);
        opcionesReport.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        sombraBalcon();
                        break;
                    case 1:
                        aptMasCaro();
                        break;
                    case 2:
                        mayorTamaño();
                        break;
                    case 3:
                        promedio();
                        break;
                }
            }
        });

    }

    public void sombraBalcon(){
        String msjNuevoSombra, msjNuevoBalcon, mensaje = "";
        int cont=0;
        ArrayList<Apartamento> apt = Datos.listarConBalconySombra(getApplicationContext());
        for (int i=0; i<apt.size(); i++){
            msjNuevoSombra = apt.get(i).getSombra();
            msjNuevoBalcon = apt.get(i).getBalcon();
            if (msjNuevoBalcon.equalsIgnoreCase(getResources().getString(R.string.si)) && msjNuevoSombra.equalsIgnoreCase(getResources().getString(R.string.si)) ){
                cont+=1;
            }
        }
        mensaje = "Con Balcon y Sombra hay en Total: "+cont;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }

    public void aptMasCaro(){
        String aptMasCaro="", pisoApt = "", mensaje = "";
        ArrayList<Apartamento> apt = Datos.listarMasCaro(getApplicationContext());
        for (int i=0; i<apt.size(); i++){
            pisoApt = apt.get(i).getPiso();
        }
        mensaje = pisoApt;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }

    public void mayorTamaño(){
        String aptNomenc="", mtsApt = "", mensaje = "";
        ArrayList<Apartamento> apt = Datos.Mayortamanio(getApplicationContext());
        for (int i=0; i<apt.size(); i++) {
            aptNomenc = apt.get(i).getNome();
            mtsApt = apt.get(i).getMts();
        }
        mensaje = "Nomenclatura: "+aptNomenc+" "+" Medidas: "+" "+mtsApt;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }



    public void promedio(){
        String mensaje = "";
        int acum=0, suma =0, prom;

        ArrayList<Apartamento> apt = Datos.listarApartamentos(getApplicationContext());

        for (int i=0; i<apt.size(); i++){
            //piso = Integer.parseInt(apt.get(i).getPiso());
            suma = suma+Integer.parseInt(apt.get(i).getMts());

        }
        prom = suma/apt.size();
        mensaje = "Promedio: " +prom;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }

}
