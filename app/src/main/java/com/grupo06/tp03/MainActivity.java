package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import Classes.Validaciones;

import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
    }

<<<<<<< Updated upstream
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
=======
    private void btnIniciar_Click(){
        String sNombre, sClave;
        sNombre = String.valueOf(txtNombre);
>>>>>>> Stashed changes
    }

    private void bindControls(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
    }

}