package com.carlos.sqlitedb;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.Intent;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtCodigo, edtCarrera, edtCurso;
    private Button btnAgregar, btnEditar, btnEliminar, btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edtCodigo = findViewById(R.id.edtCodigo);
        edtCarrera = findViewById(R.id.edtCarrera);
        edtCurso = findViewById(R.id.edtCurso);
        btnAgregar = findViewById(R.id.btnAgregar);
        btnEditar = findViewById(R.id.btnEditar);
        btnEliminar = findViewById(R.id.btnEliminar);
        btnMostrar = findViewById(R.id.btnMostrar);

        final BdAlumno alumno = new BdAlumno(getApplicationContext());

        // BOTÓN AGREGAR
        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alumno.agregarCursos(
                        edtCodigo.getText().toString(),
                        edtCurso.getText().toString(),
                        edtCarrera.getText().toString()
                );
                Toast.makeText(MainActivity.this, "SE AGREGO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
            }
        });

        // BOTÓN EDITAR
        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean actualizado = alumno.editarCurso(
                        edtCodigo.getText().toString(),
                        edtCurso.getText().toString(),
                        edtCarrera.getText().toString()
                );

                if(actualizado){
                    Toast.makeText(MainActivity.this, "CURSO ACTUALIZADO", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "NO SE PUDO ACTUALIZAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // BOTÓN ELIMINAR
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean eliminado = alumno.eliminarCurso(edtCodigo.getText().toString());

                if(eliminado){
                    Toast.makeText(MainActivity.this, "CURSO ELIMINADO", Toast.LENGTH_SHORT).show();
                    edtCodigo.setText("");
                    edtCurso.setText("");
                    edtCarrera.setText("");
                } else {
                    Toast.makeText(MainActivity.this, "NO SE PUDO ELIMINAR", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // BOTÓN MOSTRAR
        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ListaCursosActivity.class);
                startActivity(intent);
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}
