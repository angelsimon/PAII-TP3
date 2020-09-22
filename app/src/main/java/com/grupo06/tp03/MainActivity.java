package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import classes.Validaciones;
import controllers.LoginController;
import controllers.UsuarioController;
import models.LoginModel;
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
    private void resetForm(){
        txtNombre.setText("");
        txtClave.setText("");
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
                        LoginModel login = new LoginModel();
                        login.setIdUsuario(user.getId());
                        login.setFechayHora(new Date());
                        LoginController loginController = new LoginController(this);
                        loginController.guardar(login);
                        Intent i = new Intent(this, AppActivity.class);
                        i.putExtra("Usuario", user);
                        startActivity(i);
                        resetForm();
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
        if (Validaciones.esVacio(txtNombre.getText().toString())){
            Toast.makeText(this, "El nombre de usuario no puede ser vacío", Toast.LENGTH_LONG).show();
            txtNombre.requestFocus();
            return false;
        }
        if(Validaciones.esVacio(txtClave.getText().toString())){
            Toast.makeText(this, "La clave no puede ser vacía", Toast.LENGTH_LONG).show();
            txtClave.requestFocus();
            return false;
        }
        return true;
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