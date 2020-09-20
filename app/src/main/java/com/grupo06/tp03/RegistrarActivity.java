package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import classes.Validaciones;
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
        if (Validaciones.esVacio(txtNombreUsuario.getText().toString())){
            Toast.makeText(this, "El nombre de usuario no puede ser vacío", Toast.LENGTH_LONG).show();
            return false;
        }
        if (!Validaciones.esMailValido(txtMail.getText().toString())){
            Toast.makeText(this, "El mail no es correcto", Toast.LENGTH_LONG).show();
            return false;
        }
        if(!Validaciones.sonIguales(txtClave.getText().toString(), txtRepetirClave.getText().toString())){
            Toast.makeText(this, "Las claves deben ser idénticas", Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    private void bindControls(){
        this.txtNombreUsuario = (EditText) findViewById(R.id.txtNombreUsuario_mc);
        this.txtMail = (EditText) findViewById(R.id.txtMail_mc);
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
            if (this.validate()) {
                if (UsuarioController.guardar(bindData(), this)) {
                    Toast.makeText(RegistrarActivity.this, "Usuario creado", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    if (UsuarioController.getByUsername(txtNombreUsuario.getText().toString(), this) != null) {
                        Toast.makeText(RegistrarActivity.this, this.getString(R.string.ya_existe_usuario), Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(RegistrarActivity.this, "No se pudo crear el usuario", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

}