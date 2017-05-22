package com.example.pngweb.tallerbasededatos;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {
    private Spinner pisoOpciones;
    private String []opc;
    private ArrayAdapter adapter;
    private EditText cajaPrecio;
    private EditText cajanome;
    private EditText cajamts;
    private RadioButton rdSiBalcon;
    private RadioButton rdNoBalcon;
    private RadioButton rdSiSombra;
    private RadioButton rdNoSombra;
    private Apartamento apt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        pisoOpciones = (Spinner)findViewById(R.id.cboPisos);
        opc = getResources().getStringArray(R.array.pisos);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        pisoOpciones.setAdapter(adapter);
        rdSiBalcon=(RadioButton) findViewById(R.id.rdsib);
        rdNoBalcon=(RadioButton) findViewById(R.id.rdnob);
        rdSiSombra=(RadioButton) findViewById(R.id.rdsis);
        rdNoSombra=(RadioButton) findViewById(R.id.rdnos);
        cajaPrecio=(EditText)findViewById(R.id.txtPrecio);
        cajamts=(EditText)findViewById(R.id.txtmstti);
        cajanome=(EditText)findViewById(R.id.txtnome);
    }

    public void guardar(View v){
        String piso, precio, balcon, sombra, mts, nome;
        if (validarTodo()){
            if(validarNomenclatura()){
                if (validarPisosNum()) {
                    piso = pisoOpciones.getSelectedItem().toString();
                    if (rdSiBalcon.isChecked()) balcon = getResources().getString(R.string.si);
                    else balcon = getResources().getString(R.string.no);

                    if (rdSiSombra.isChecked()) sombra = getResources().getString(R.string.si);
                    else sombra = getResources().getString(R.string.no);

                    mts = cajamts.getText().toString();
                    precio = cajaPrecio.getText().toString();
                    nome = cajanome.getText().toString();

                    apt = new Apartamento(piso,precio,balcon,sombra,mts,nome);
                    apt.guardar(getApplicationContext());

                    new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.saveexitoso)).setCancelable(true).show();
                    limpiar();
                }
            }
        }
    }

    public boolean validarPisosNum(){
        ArrayList<Apartamento> apto=Datos.listarApartamentos(getApplicationContext());
        String piso=pisoOpciones.getSelectedItem().toString();
        String  nomen="";
        int cont=0;
        for (int i=0;i<apto.size();i++){
            if (apto.get(i).getPiso().equals(piso))cont=cont+1;
            nomen = apto.get(i).getNome();
        }
        if (cont>=3){
            Toast.makeText(getApplicationContext(),getResources().getString(R.string.topepiso),
                    Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void limpiar(){
        cajaPrecio.setText("");
        cajamts.setText("");
        cajanome.setText("");
        rdSiBalcon.setChecked(true);
        rdNoBalcon.setChecked(false);
        rdSiSombra.setChecked(true);
        rdNoSombra.setChecked(false);
        cajaPrecio.requestFocus();
    }

    public boolean validarTodo(){

        if(cajaPrecio.getText().toString().isEmpty()) {
            cajaPrecio.setError(getResources().getString(R.string.digiteprecio));
            cajaPrecio.requestFocus();
            return false;
        }
        if(cajanome.getText().toString().isEmpty()) {
            cajanome.setError(getResources().getString(R.string.digitenomenc));
            cajanome.requestFocus();
            return false;
        }
        if(cajamts.getText().toString().isEmpty()) {
            cajamts.setError(getResources().getString(R.string.digitemedida));
            cajamts.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarNomenclatura(){
        ArrayList<Apartamento> apt = Datos.listarApartamentos(getApplicationContext());
        String nomenc = cajanome.getText().toString();
        for (int i = 0; i<apt.size(); i++ ){
            if (apt.get(i).getNome().equalsIgnoreCase(cajanome.getText().toString()) && apt.get(i).getPiso().equalsIgnoreCase(pisoOpciones.getSelectedItem().toString().trim())){
                cajanome.setError(getResources().getString(R.string.nomecexistente));
                cajanome.requestFocus();
                return false;
            }
        }
        return true;
    }

}
