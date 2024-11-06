package com.example.loginproject.Models;

public class GastoDia {
    private String nombreArticulo;
    private double precio;
    private int cantidad;
    private double subTotal;
    private String idPresupuesto;
    public GastoDia() {
    }
    public GastoDia(String nombreArticulo, double precio, int cantidad, double subTotal, String idPresupuesto) {
        this.nombreArticulo = nombreArticulo;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
        this.idPresupuesto = idPresupuesto;
    }
    @Override
    public String toString() {
        return "GastoDia{" +
                "nombreArticulo='" + nombreArticulo + '\'' +
                ", precio=" + precio +
                ", cantidad=" + cantidad +
                ", subTotal=" + subTotal +
                ", idPresupuesto='" + idPresupuesto + '\'' +
                '}';
    }
    public String getNombreArticulo() {
        return nombreArticulo;
    }
    public void setNombreArticulo(String nombreArticulo) {
        this.nombreArticulo = nombreArticulo;
    }
    public double getPrecio() {
        return precio;
    }
    public void setPrecio(double precio) {
        this.precio = precio;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public double getSubTotal() {
        return subTotal;
    }
    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
    public String getIdPresupuesto() {
        return idPresupuesto;
    }
    public void setIdPresupuesto(String idPresupuesto) {
        this.idPresupuesto = idPresupuesto;
    }
}
