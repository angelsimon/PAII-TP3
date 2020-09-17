package controllers;

import android.content.Context;

import java.util.ArrayList;

import daos.UsuarioDAO;
import models.UsuarioModel;

public class UsuarioController {

    public static boolean guardar(UsuarioModel reg, Context ctx){
        try{
            return UsuarioDAO.insert(reg, ctx) >= 0;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public static UsuarioModel getByUsername(String username, Context ctx){
        try{
            ArrayList<UsuarioModel> lista = UsuarioDAO.get(username, ctx);
            if (lista.size() == 1){
                return lista.get(0);
            }
        }
        catch(Exception ex) {
            return null;
        }
        return null;
    }

    public static UsuarioModel usuarioExiste(UsuarioModel reg, Context ctx){
        try{
            return UsuarioDAO.get(reg, ctx);
        }
        catch(Exception e){
            throw e;
        }
    }

}
