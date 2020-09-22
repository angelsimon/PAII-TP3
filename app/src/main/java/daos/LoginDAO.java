package daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import classes.DBHelper;
import models.LoginModel;

public class LoginDAO {

    public static Long insert(LoginModel reg, Context ctx){
        try{
            Long id;
            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            ContentValues valores = new ContentValues();
            valores.put("idusuario", reg.getIdUsuario());
            valores.put("fechahora", reg.getFechaYhora());
            id = db.insert("Logins", valores);
            db.cerrar();
            return id;
        }
        catch(SQLiteException ex){
            throw ex;
        }
    }

    public static ArrayList<LoginModel> getByUsuario(Long IDUsuario, Context ctx) {
        try {
            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            String[] columnas = new String[]{"*"};
            String[] whereParams = new String[]{String.valueOf(IDUsuario)};
            Cursor c = db.select("Logins",
                    columnas,
                    "idusuario = ?",
                    whereParams,
                    null,
                    null,
                    "id desc");

            ArrayList<LoginModel> lista = new ArrayList<LoginModel>();
            while (c.moveToNext()) {
                LoginModel log = new LoginModel();
                log.setId(c.getLong(c.getColumnIndexOrThrow("id")));
                log.setIdUsuario(c.getLong(c.getColumnIndexOrThrow("idusuario")));
                log.setFechaYhora(c.getLong(c.getColumnIndexOrThrow("fechahora")));
                lista.add(log);
            }
            c.close();
            db.cerrar();
            return lista;
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

}
