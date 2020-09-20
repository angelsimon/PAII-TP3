package controllers;

import android.content.Context;

import java.util.ArrayList;

import daos.EstacionamientoDAO;
import daos.UsuarioDAO;
import models.EstacionamientoModel;
import models.UsuarioModel;

public class EstacionamientoController {
    private Context ctx;

    public EstacionamientoController(Context context){
        ctx = context;
    }

    public boolean guardar(EstacionamientoModel reg){
        try{
            return EstacionamientoDAO.insert(reg, ctx) >= 0;
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public Boolean delete(Long ID){
        try{
            return EstacionamientoDAO.delete(ID, ctx);

        }
        catch(Exception ex) {
            return false;
        }
    }

    public ArrayList<EstacionamientoModel> getByUsuario(Long IDUsuario){
        try{
            ArrayList<EstacionamientoModel> lista = EstacionamientoDAO.getByUsuario(IDUsuario, ctx);
            return lista;
        }
        catch(Exception ex) {
            return null;
        }
    }

}
