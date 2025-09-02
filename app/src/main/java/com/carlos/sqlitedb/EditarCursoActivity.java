package com.carlos.sqlitedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarCursoActivity extends AppCompatActivity {

    private EditText etCodigo, etCurso, etCarrera;
    private Button btnEditar;
    private BdAlumno bdAlumno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_curso);

        etCodigo = findViewById(R.id.etCodigo);
        etCurso = findViewById(R.id.etCurso);
        etCarrera = findViewById(R.id.etCarrera);
        btnEditar = findViewById(R.id.btnEditar);

        bdAlumno = new BdAlumno(this);

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String codigo = etCodigo.getText().toString();
                String curso = etCurso.getText().toString();
                String carrera = etCarrera.getText().toString();

                boolean actualizado = bdAlumno.editarCurso(codigo, curso, carrera);

                if (actualizado) {
                    Toast.makeText(EditarCursoActivity.this, "Curso actualizado", Toast.LENGTH_SHORT).show();
                    finish(); // volver a lista
                } else {
                    Toast.makeText(EditarCursoActivity.this, "No se encontró el curso", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
