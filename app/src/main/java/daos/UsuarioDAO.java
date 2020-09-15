package daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteException;

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
}
