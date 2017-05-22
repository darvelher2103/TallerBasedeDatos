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
                        promedioDep();
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
        mensaje = getResources().getString(R.string.balconsombratotal)+": "+cont;
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
        mensaje = getResources().getString(R.string.nomec)+":"+aptNomenc+"\n"+getResources().getString(R.string.mts)+":"+" "+mtsApt;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }

    public void promedio(){
        String mensaje = "";
        String pisoUno;
        int contUno = 0, sumUno = 0, promUno, cont2 = 0, suma2 = 0, prom2 , cont3 = 0, suma3 = 0, prom3;
        ArrayList<Apartamento> apt = Datos.listarApartamentos(getApplicationContext());

        for (int i = 0; i < apt.size(); i++) {
            pisoUno = apt.get(i).getPiso();
            if (pisoUno.equalsIgnoreCase("Piso uno")) {
                sumUno = sumUno + Integer.parseInt(apt.get(i).getMts());
                contUno=contUno+1;
            }
        }

        promUno = sumUno / contUno;
        mensaje = "Promedio del tamaño del piso uno: " + promUno;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();

    }

    public void promedioDep(){
        String piso, mensaje = "";
        float cont=0, prom=0, suma =0, cont2=0, prom2=0, suma2 =0, cont3=0, prom3=0, suma3 =0,
        cont4=0, prom4=0, suma4 =0, cont5=0, prom5=0, suma5 =0;
        ArrayList<Apartamento> apt = Datos.listarConBalconySombra(getApplicationContext());
        for (int i=0; i<apt.size(); i++){
            piso = apt.get(i).getPiso();
            if (piso.equalsIgnoreCase(getResources().getString(R.string.piso1))){
                suma+=Integer.parseInt(apt.get(i).getMts());
                cont+=1;
            }
            if (piso.equalsIgnoreCase(getResources().getString(R.string.piso2))){
                suma2+=Integer.parseInt(apt.get(i).getMts());
                cont2+=1;
            }
            if (piso.equalsIgnoreCase(getResources().getString(R.string.piso3))){
                suma3+=Integer.parseInt(apt.get(i).getMts());
                cont3+=1;
            }
            if (piso.equalsIgnoreCase(getResources().getString(R.string.piso4))){
                suma4 +=Integer.parseInt(apt.get(i).getMts());
                cont4+=1;
            }
            if (piso.equalsIgnoreCase(getResources().getString(R.string.piso5))){
                suma5+=Integer.parseInt(apt.get(i).getMts());
                cont5+=1;
            }
        }
        if(cont>0)prom = suma/cont;
        if(cont2>0)prom2 = suma2/cont2;
        if(cont3>0)prom3 = suma3/cont3;
        if(cont4>0)prom4 = suma4/cont4;
        if(cont5>0)prom5 = suma5/cont5;
        mensaje = getResources().getString(R.string.piso1)+" "+prom+"\n"+
                 getResources().getString(R.string.piso2)+" "+prom2+"\n"+
                 getResources().getString(R.string.piso3)+" "+prom3+"\n"+
                 getResources().getString(R.string.piso4)+" "+prom4+"\n"+
                 getResources().getString(R.string.piso5)+" "+prom5;
        new AlertDialog.Builder(this).
                setMessage(mensaje).
                setCancelable(true).show();
    }

}
