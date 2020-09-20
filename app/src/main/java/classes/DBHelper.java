package classes;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String createTablaUsuarios = "CREATE TABLE Usuarios (id INTEGER NOT NULL, nombreusuario TEXT NOT NULL UNIQUE, mail TEXT NOT NULL UNIQUE, clave TEXT NOT NULL, estado INTEGER NOT NULL DEFAULT 1, PRIMARY KEY(id AUTOINCREMENT));";
        String createTablaLogins = "CREATE TABLE Logins (id INTEGER NOT NULL, idusuario INTEGER NOT NULL, fechahora INTEGER NOT NULL, FOREIGN KEY(idusuario) REFERENCES Usuarios(id), PRIMARY KEY(id AUTOINCREMENT));";
        String createTablaEstacionamientos = "CREATE TABLE Estacionamientos (id INTEGER NOT NULL, idusuario INTEGER NOT NULL, patente TEXT NOT NULL, duracion INTEGER NOT NULL CHECK(duracion > 0), estado INTEGER NOT NULL DEFAULT 1, FOREIGN KEY(idusuario) REFERENCES Usuarios(id), PRIMARY KEY(id AUTOINCREMENT));";

        sqLiteDatabase.execSQL(createTablaUsuarios);
        sqLiteDatabase.execSQL(createTablaLogins);
        sqLiteDatabase.execSQL(createTablaEstacionamientos);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void abrir(){
        this.getWritableDatabase();
    }

    public void cerrar(){
        this.close();
    }

    public long insert(String tabla, ContentValues valores){
        return this.getWritableDatabase().insert(tabla, null, valores);
    }

    public int update(String tabla, ContentValues valores, String whereClause, String[] whereParameters){
        return this.getWritableDatabase().update(tabla, valores, whereClause, whereParameters);
    }

    public Cursor select(String tabla, String[] columnas, String whereClause, String[] whereParameters, String groupByClause, String havingClause, String orderByClause){
        Cursor cursor;
        cursor = this.getReadableDatabase().query(
                tabla,
                columnas,
                whereClause,
                whereParameters,
                groupByClause,
                havingClause,
                orderByClause
        );
        return cursor;
    }

    public boolean insert_dummy_user(){
        ContentValues valores = new ContentValues();
        valores.put("nombreusuario", "vaquita");
        valores.put("mail", "vaquita@michishito.com");
        valores.put("clave", "vaquita");
        valores.put("estado", 1);
        this.getWritableDatabase().insert("Usuarios", null, valores);
        return true;
    }

}
