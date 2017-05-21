package com.example.pngweb.tallerbasededatos;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import java.util.ArrayList;

public class Registrar extends AppCompatActivity {
    private Spinner pisoo;
    private  String []opc;
    private ArrayAdapter adapter;
    private EditText cajaPrecio;
    private EditText cajanome;
    private EditText cajamts;
    private RadioButton rdSib;
    private RadioButton rdNob;
    private RadioButton rdSis;
    private RadioButton rdNos;
    private Apartamento a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        pisoo = (Spinner)findViewById(R.id.cboPisos);
        opc = getResources().getStringArray(R.array.pisos);
        adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,opc);
        pisoo.setAdapter(adapter);
        rdSib=(RadioButton) findViewById(R.id.rdsib);
        rdNob=(RadioButton) findViewById(R.id.rdnob);
        rdSis=(RadioButton) findViewById(R.id.rdsis);
        rdNos=(RadioButton) findViewById(R.id.rdnos);
        cajaPrecio=(EditText)findViewById(R.id.txtPrecio);
        cajamts=(EditText)findViewById(R.id.txtmstti);
        cajanome=(EditText)findViewById(R.id.txtnome);
    }

    public void guardar(View v){
        String piso,precio,balcon,sombra,mts,nome;
        if (validarNomenclatura()){
            if(validarTodo()){
                piso = pisoo.getSelectedItem().toString();
                precio=cajaPrecio.getText().toString();
                if(rdSib.isChecked()) balcon = getResources().getString(R.string.si);
                else balcon = getResources().getString(R.string.no);

                if(rdSis.isChecked()) sombra = getResources().getString(R.string.si);
                else sombra = getResources().getString(R.string.no);
                mts = cajamts.getText().toString();
                nome=cajanome.getText().toString();
                a = new Apartamento(piso,precio,balcon,sombra,mts,nome);
                a.guardar(getApplicationContext());
                new AlertDialog.Builder(this).setMessage("Apartamento Registrado Exitosamente").setCancelable(true).show();
                limpiar();
            }
        }
    }

    public void limpiar(){
        cajaPrecio.setText("");
        cajamts.setText("");
        cajanome.setText("");
        rdSib.setChecked(true);
        rdNob.setChecked(false);
        rdSis.setChecked(true);
        rdNos.setChecked(false);
        cajaPrecio.requestFocus();
    }

    public boolean validarTodo(){

        if(cajaPrecio.getText().toString().isEmpty()) {
            cajaPrecio.setError("Digite el Precio");
            cajaPrecio.requestFocus();
            return false;
        }

        if(cajamts.getText().toString().isEmpty()) {
            cajamts.setError("Digite la medida en mts2");
            cajamts.requestFocus();
            return false;
        }
        if(cajanome.getText().toString().isEmpty()) {
            cajanome.setError("Digite la nomeclatura");
            cajanome.requestFocus();
            return false;
        }
        return true;
    }

    public boolean validarNomenclatura(){
        ArrayList<Apartamento> apt = Datos.listarApartamentos(getApplicationContext());
        String nomenc = cajanome.getText().toString();
        for (int i = 0; i<apt.size(); i++ ){
            if (apt.get(i).getNome().equalsIgnoreCase(cajanome.getText().toString()) && apt.get(i).getPiso().equalsIgnoreCase(pisoo.getSelectedItem().toString().trim())){
                cajanome.setError("La nomenclatura ya existe");
                cajanome.requestFocus();
                return false;
            }
        }
        return true;
    }

}
