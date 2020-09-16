package daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import classes.DBHelper;
import models.UsuarioModel;

public class UsuarioDAO {
    public static Long insert(UsuarioModel reg, Context ctx){
        try{
            Long id;
            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            ContentValues valores = new ContentValues();
            valores.put("nombreusuario", reg.getNombreusuario());
            valores.put("mail", reg.getMail());
            valores.put("clave", reg.getClave());
            valores.put("estado", reg.getEstado());
            id = db.insert("Usuarios", valores);
            db.cerrar();
            return id;
        }
        catch(SQLiteException ex){
            throw ex;
        }
    }

    public static ArrayList<UsuarioModel> get(String username, Context ctx) {
        try {

            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            String[] columnas = new String[]{"*"};
            String[] whereParams = new String[]{username};
            Cursor c = db.select("Usuarios",
                    columnas,
                    "nombreusuario = ?",
                    whereParams,
                    null,
                    null,
                    null);

            ArrayList<UsuarioModel> lista = new ArrayList<UsuarioModel>();
            while (c.moveToNext()) {
                UsuarioModel usuario = new UsuarioModel();
                usuario.setId(c.getLong(c.getColumnIndexOrThrow("id")));
                usuario.setNombreusuario(c.getString(c.getColumnIndexOrThrow("nombreusuario")));
                usuario.setMail(c.getString(c.getColumnIndexOrThrow("mail")));
                usuario.setClave(c.getString(c.getColumnIndexOrThrow("clave")));
                //usuario.setEstado(Boolean. c.getLong(c.getColumnIndexOrThrow("estado")));
                lista.add(usuario);
            }
            c.close();
            db.cerrar();
            return lista;
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

    public static UsuarioModel get(UsuarioModel reg, Context ctx){
        try{
            String [] columnas = new String [] {"*"};
            String whereClause = "nombreusuario = ? AND clave = ? AND estado = 1";
            String [] whereParams = new String [] {reg.getNombreusuario().toString(), reg.getClave().toString()};

            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            Cursor c = db.select("Usuarios", columnas, whereClause, whereParams, null, null, null);
            while(c.moveToNext()){
                reg.setId(c.getLong(c.getColumnIndexOrThrow("id")));
                reg.setMail(c.getString(c.getColumnIndexOrThrow("mail")));
            }
            c.close();
            db.cerrar();
            return reg;
        }
        catch(SQLiteException e){
            throw e;
        }
    }

}
