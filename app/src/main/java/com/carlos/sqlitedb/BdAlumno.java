package com.carlos.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;                // <- android.database.Cursor (no java.sql)
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class BdAlumno extends SQLiteOpenHelper {

    private static final String NOMBRE_DB = "alumno.db";
    private static final int VERSION_DB = 1;
    private static final String TABLA_CURSOS =
            "CREATE TABLE cursos (codigo TEXT PRIMARY KEY, curso TEXT, carrera TEXT)";

    public BdAlumno(Context context) {
        super(context, NOMBRE_DB, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLA_CURSOS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS cursos");
        onCreate(db);
    }

    // AGREGAR CURSOS
    public void agregarCursos(String codigo, String curso, String carrera) {
        SQLiteDatabase bd = getWritableDatabase();
        if (bd != null) {
            ContentValues valores = new ContentValues();
            valores.put("codigo", codigo);
            valores.put("curso", curso);
            valores.put("carrera", carrera);
            bd.insert("cursos", null, valores);
            bd.close();
        }
    }

    // EDITAR CURSOS
    public boolean editarCurso(String codigo, String curso, String carrera) {
        SQLiteDatabase bd = getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("curso", curso);
        valores.put("carrera", carrera);
        int filasActualizadas = bd.update("cursos", valores, "codigo=?", new String[]{codigo});
        bd.close();
        return filasActualizadas > 0;
    }

    // ELIMINAR CURSOS
    public boolean eliminarCurso(String codigo) {
        SQLiteDatabase bd = getWritableDatabase();
        int filasEliminadas = bd.delete("cursos", "codigo=?", new String[]{codigo});
        bd.close();
        return filasEliminadas > 0;
    }

    // MOSTRAR CURSOS
    public Cursor obtenerCursos() {
        SQLiteDatabase bd = getReadableDatabase();
        return bd.rawQuery("SELECT * FROM cursos", null);
    }
}
