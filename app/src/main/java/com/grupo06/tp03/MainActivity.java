package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.widget.EditText;
import android.widget.Toast;

import classes.Validaciones;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtClave;
    private Validaciones validador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
        validador = new Validaciones();
    }

    private void btnIniciar_Click(){
        /*
        if (validador.Vacio(txtNombre)){
            Toast.makeText(this, this.getString(R.string.campo_vacio), Toast.LENGTH_LONG).show();
        }
         */
    }

    private void bindControls(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
    }

}