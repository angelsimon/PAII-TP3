package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;

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

    public void btnRegistrarse_Click(View view){
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
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