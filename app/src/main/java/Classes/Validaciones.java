package Classes;

public class Validaciones {

    public boolean Vacio(String text){
        Boolean resultado = false;
        if (text.length() == 0){
            resultado = true;
        }
        return resultado;
    }
}
