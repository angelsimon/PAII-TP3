package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import controllers.UsuarioController;
import models.UsuarioModel;

public class RegistrarActivity extends AppCompatActivity {
    private EditText txtNombreUsuario, txtMail, txtClave, txtRepetirClave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        bindControls();
    }

    private boolean validate(){

        if(!txtClave.getText().toString().equals(txtRepetirClave.getText().toString())){
            Toast.makeText(this, "Las claves deben ser idénticas.", Toast.LENGTH_LONG).show();
            return false;
        }

        return true;
    }

    private void bindControls(){
        this.txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario);
        this.txtMail = (EditText) findViewById(R.id.txtMail);
        this.txtClave = (EditText) findViewById(R.id.txtPassword);
        this.txtRepetirClave = (EditText) findViewById(R.id.txtRepetir);
    }

    private UsuarioModel bindData(){
        try {
            UsuarioModel obj = new UsuarioModel();
            obj.setNombreusuario(txtNombreUsuario.getText().toString());
            obj.setClave(txtClave.getText().toString());
            obj.setMail(txtMail.getText().toString());
            obj.setEstado(true);
            return obj;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public void btnRegistrar_Click(View view){
        try {
            if (UsuarioController.guardar(bindData(), this)){
                Toast.makeText(RegistrarActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(RegistrarActivity.this, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}