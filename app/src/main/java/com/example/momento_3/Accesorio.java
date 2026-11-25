package com.example.momento_3;

public class Accesorio extends ProductoBase {
    private boolean conSeguro;

    public Accesorio(String nombre, double precioBase, int cantidad, boolean conSeguro) {
        super(nombre, precioBase, cantidad);
        this.conSeguro = conSeguro;
    }

    @Override
    public double calcularPrecioFinal() {
        // Si lleva seguro, + $15.000. Si compra 5 o más, 15% descuento
        double precio = getPrecioBase();
        if (conSeguro) precio += 15000;
        if (getCantidad() >= 5) precio = precio * 0.85;
        return precio;
    }
}
