package com.example.loginproject.Models;

import java.io.Serializable;

public class Presupuesto implements Serializable {
    private String id;
    private String nombre;
    private double monto;
    private boolean activo;

    public Presupuesto(String nombre, double monto, boolean activo) {
        this.nombre = nombre;
        this.monto = monto;
        this.activo = activo;
    }

    public Presupuesto() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Presupuesto{" +
                ", nombre='" + nombre + '\'' +
                ", monto=" + monto +
                ", activo=" + activo +
                '}';
    }
}