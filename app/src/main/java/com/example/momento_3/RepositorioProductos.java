package com.example.momento_3;

import java.util.ArrayList;

public class RepositorioProductos {
    private ArrayList<ProductoBase> lista = new ArrayList<>();

    public void agregar(ProductoBase p) { lista.add(p); }
    public ArrayList<ProductoBase> listar() { return lista; }
    public int getTotalRegistros() { return lista.size(); }

    public double getTotalRecaudado() {
        double total = 0;
        for (ProductoBase p : lista) total += p.calcularSubtotal();
        return total;
    }
    public void limpiar() { lista.clear(); }
}
