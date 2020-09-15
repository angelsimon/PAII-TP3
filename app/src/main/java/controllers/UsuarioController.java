package controllers;

import android.content.Context;

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
}
