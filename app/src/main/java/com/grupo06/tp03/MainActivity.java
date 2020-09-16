package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Objects;

import classes.Validaciones;
import controllers.UsuarioController;
import models.UsuarioModel;

public class MainActivity extends AppCompatActivity {

    private EditText txtNombre, txtClave;
    //private Validaciones validador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindControls();
        //validador = new Validaciones();
    }

    public void btnRegistrarse_Click(View view){
        Intent i = new Intent(this, RegistrarActivity.class);
        startActivity(i);
    }

    public void btnIniciar_Click(View view){
        /*
        if (validador.Vacio(txtNombre)){

        }
         */
            try{
                if(validate()) {
                    UsuarioModel user = UsuarioController.usuarioExiste(bindData(), this);
                    if(!(user.getId() == -1)){
                        Intent i = new Intent(this, EstacionamientoActivity.class);
                        startActivity(i);
                    }
                    else{
                        Toast.makeText(this, this.getString(R.string.no_existe_usuario), Toast.LENGTH_LONG).show();
                    }
                }
            }
            catch(Exception e){
                e.getMessage();
            }
    }

    private boolean validate(){
    //ToDo
    //Ver de usar la clase Validaciones
        if ((txtNombre.getText().toString().trim() == "") || (txtClave.getText().toString().trim() == "")){
            Toast.makeText(this, this.getString(R.string.campos_vacios), Toast.LENGTH_LONG).show();
            return false;
        }
        else{
            return true;
        }
    }

    private void bindControls(){
        txtNombre = (EditText) findViewById(R.id.txtNombre);
        txtClave = (EditText) findViewById(R.id.txtClave);
    }

    private UsuarioModel bindData(){
        try{
            UsuarioModel reg = new UsuarioModel();
            reg.setId(new Long(-1));
            reg.setNombreusuario(txtNombre.getText().toString());
            reg.setMail(null);
            reg.setClave(txtClave.getText().toString());
            //reg.setEstado(true); // para buscar solamente usuarios activos
            return reg;
        }
        catch(Exception e){
            throw e;
        }
    }

}