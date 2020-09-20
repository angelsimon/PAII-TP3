package daos;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;

import classes.DBHelper;
import models.EstacionamientoModel;
import models.UsuarioModel;

public class EstacionamientoDAO {

    public static Long insert(EstacionamientoModel reg, Context ctx){
        try{
            Long id;
            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            ContentValues valores = new ContentValues();
            valores.put("idusuario", reg.getIdUsuario());
            valores.put("patente", reg.getPatente());
            valores.put("duracion", reg.getDuracion());
            valores.put("estado", true);
            id = db.insert("Estacionamientos", valores);
            db.cerrar();
            return id;
        }
        catch(SQLiteException ex){
            throw ex;
        }
    }
    public static Boolean delete(Long ID, Context ctx){
        try{
            int rows;
            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            ContentValues valores = new ContentValues();
            valores.put("estado", false);
            String[] whereParameters = {String.valueOf(ID)};
            rows = db.update("Estacionamientos", valores, "id = ?", whereParameters);
            db.cerrar();
            return rows == 1;
        }
        catch(SQLiteException ex){
            throw ex;
        }
    }

    public static ArrayList<EstacionamientoModel> getByUsuario(Long IDUsuario, Context ctx) {
        try {

            DBHelper db = new DBHelper(ctx, "tp3.db", null, 1);
            db.abrir();
            String[] columnas = new String[]{"*"};
            String[] whereParams = new String[]{String.valueOf(IDUsuario)};
            Cursor c = db.select("Estacionamientos",
                    columnas,
                    "idusuario = ?",
                    whereParams,
                    null,
                    null,
                    "id desc");

            ArrayList<EstacionamientoModel> lista = new ArrayList<EstacionamientoModel>();
            while (c.moveToNext()) {
                EstacionamientoModel park = new EstacionamientoModel();
                park.setId(c.getLong(c.getColumnIndexOrThrow("id")));
                park.setIdUsuario(c.getLong(c.getColumnIndexOrThrow("idusuario")));
                park.setDuracion(c.getLong(c.getColumnIndexOrThrow("duracion")));
                park.setPatente(c.getString(c.getColumnIndexOrThrow("patente")));
                park.setEstado(c.getLong(c.getColumnIndexOrThrow("estado")) == 1);
                lista.add(park);
            }
            c.close();
            db.cerrar();
            return lista;
        } catch (SQLiteException ex) {
            throw ex;
        }
    }

}
