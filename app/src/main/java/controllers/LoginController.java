package controllers;

import android.content.Context;

import java.util.ArrayList;

import daos.EstacionamientoDAO;
import daos.LoginDAO;
import models.EstacionamientoModel;
import models.LoginModel;

public class LoginController {
    private Context ctx;

    public LoginController(Context context){
        this.ctx = context;
    }

    public boolean guardar(LoginModel reg){
        try{
            return LoginDAO.insert(reg, ctx) >= 0;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public ArrayList<LoginModel> getLast5ByUsuario(Long IDUsuario){
        try{
            ArrayList<LoginModel> lista = LoginDAO.getByUsuario(IDUsuario, ctx);
            return lista;
        }
        catch(Exception ex) {
            return null;
        }
    }

}
