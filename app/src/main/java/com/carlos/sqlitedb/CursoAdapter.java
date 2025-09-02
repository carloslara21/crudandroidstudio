package com.carlos.sqlitedb;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.ViewHolder> {

    private ArrayList<Curso> listaCursos;

    public CursoAdapter(ArrayList<Curso> listaCursos) {
        this.listaCursos = listaCursos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Curso curso = listaCursos.get(position);
        holder.txtCurso.setText(curso.getNombre());
        holder.txtCodigo.setText(curso.getCodigo());
        holder.txtCarrera.setText(curso.getCarrera());
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCurso, txtCodigo, txtCarrera;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCurso = itemView.findViewById(R.id.txtCurso);
            txtCodigo = itemView.findViewById(R.id.txtCodigo);
            txtCarrera = itemView.findViewById(R.id.txtCarrera);
        }
    }
}
