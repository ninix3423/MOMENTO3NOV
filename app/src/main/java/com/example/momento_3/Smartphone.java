package com.example.momento_3;

public class Smartphone extends ProductoBase {
    private String marca;

    public Smartphone(String nombre, double precioBase, int cantidad, String marca) {
        super(nombre, precioBase, cantidad);
        this.marca = marca;
    }

    @Override
    public double calcularPrecioFinal() {
        // Descuento por volumen: si compra 3 o más, 10% descuento
        // Si es iPhone/Samsung, recargo 5% por garantía premium
        double precio = getPrecioBase();
        if (getCantidad() >= 3) precio = precio * 0.90;
        if (marca.equalsIgnoreCase("Apple") || marca.equalsIgnoreCase("Samsung"))
            precio = precio * 1.05;
        return precio;
    }
}
