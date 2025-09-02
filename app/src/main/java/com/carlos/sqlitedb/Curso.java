package com.carlos.sqlitedb;

public class Curso {
    private String codigo;
    private String nombre;
    private String carrera;

    public Curso(String codigo, String nombre, String carrera) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.carrera = carrera;
    }

    public String getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public String getCarrera() { return carrera; }
}
