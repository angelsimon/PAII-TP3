package com.grupo06.tp03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegistrarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
    }

    public void btnRegistrar_Click(View view){
        try {
            Toast.makeText(RegistrarActivity.this, "Auch!", Toast.LENGTH_SHORT).show();
        }
        catch(Exception e){

        }
    }

}