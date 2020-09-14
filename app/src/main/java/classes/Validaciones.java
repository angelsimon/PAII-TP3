package classes;

import android.widget.EditText;

public class Validaciones {

    private EditText campo;
    private String text;

    public Validaciones(){

    }

    public boolean Vacio(String text){
        Boolean resultado = false;
        if (text.length() == 0){
            resultado = true;
        }
        return resultado;
    }
}
