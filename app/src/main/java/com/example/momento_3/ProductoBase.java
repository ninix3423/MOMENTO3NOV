package com.example.momento_3;

public abstract class ProductoBase {
    private String nombre;
    private double precioBase;
    private int cantidad;

    public ProductoBase(String nombre, double precioBase, int cantidad) {
        setNombre(nombre);
        setPrecioBase(precioBase);
        setCantidad(cantidad);
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty())
            throw new IllegalArgumentException("Nombre vacío");
        this.nombre = nombre.trim();
    }

    public double getPrecioBase() { return precioBase; }
    public void setPrecioBase(double precioBase) {
        if (precioBase <= 0) throw new IllegalArgumentException("Precio debe ser > 0");
        this.precioBase = precioBase;
    }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        if (cantidad <= 0) throw new IllegalArgumentException("Cantidad debe ser > 0");
        this.cantidad = cantidad;
    }

    // Método abstracto que cada hija especializa
    public abstract double calcularPrecioFinal();

    public double calcularSubtotal() {
        return calcularPrecioFinal() * cantidad;
    }

    public String getFicha() {
        return nombre + " | Cant: " + cantidad + " | Total: $" + String.format("%.2f", calcularSubtotal());
    }
}
