package com.example.loginproject.Dtos;

public class PresupuestoDto {
    private String nombre;
    private double monto;
    private boolean activo;
    public PresupuestoDto(String nombre, double monto, boolean activo) {
        this.nombre = nombre;
        this.monto = monto;
        this.activo = activo;
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
}
