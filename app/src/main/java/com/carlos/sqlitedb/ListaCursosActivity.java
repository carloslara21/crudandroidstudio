package com.carlos.sqlitedb;

import android.database.Cursor;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListaCursosActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;
    private BdAlumno bdAlumno;
    private ArrayList<Curso> listaCursos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cursos);

        recyclerView = findViewById(R.id.recyclerViewCursos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bdAlumno = new BdAlumno(this);
        listaCursos = new ArrayList<>();

        // Inicializamos el adapter
        cursoAdapter = new CursoAdapter(listaCursos);
        recyclerView.setAdapter(cursoAdapter);

        // Primera carga de datos
        cargarCursos();
    }

    private void cargarCursos() {
        listaCursos.clear();
        Cursor cursor = bdAlumno.obtenerCursos();

        if (cursor.moveToFirst()) {
            do {
                listaCursos.add(new Curso(
                        cursor.getString(0), // codigo
                        cursor.getString(1), // curso
                        cursor.getString(2)  // carrera
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        cursoAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Refrescar lista cuando volvemos desde Editar/Eliminar
        cargarCursos();
    }
}
