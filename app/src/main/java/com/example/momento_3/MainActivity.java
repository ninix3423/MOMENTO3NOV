package com.example.momento_3;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private EditText edtNombre, edtPrecio, edtCantidad, edtMarca;
    private RadioGroup rgTipo;
    private CheckBox cbSeguro;
    private TextView txtResumen;
    private RepositorioProductos repo = new RepositorioProductos();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNombre = findViewById(R.id.edtNombre);
        edtPrecio = findViewById(R.id.edtPrecio);
        edtCantidad = findViewById(R.id.edtCantidad);
        edtMarca = findViewById(R.id.edtMarca);
        rgTipo = findViewById(R.id.rgTipo);
        cbSeguro = findViewById(R.id.cbSeguro);
        txtResumen = findViewById(R.id.txtResumen);

        findViewById(R.id.btnGuardar).setOnClickListener(v -> guardarVenta());
        findViewById(R.id.btnLimpiar).setOnClickListener(v -> limpiar());
    }

    private void guardarVenta() {
        String nombre = edtNombre.getText().toString().trim();
        String precioStr = edtPrecio.getText().toString().trim();
        String cantStr = edtCantidad.getText().toString().trim();

        if (nombre.isEmpty() || precioStr.isEmpty() || cantStr.isEmpty()) {
            Toast.makeText(this, "Completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            double precio = Double.parseDouble(precioStr);
            int cant = Integer.parseInt(cantStr);
            String marca = edtMarca.getText().toString().trim();

            ProductoBase p;
            if (rgTipo.getCheckedRadioButtonId() == R.id.rbSmartphone) {
                p = new Smartphone(nombre, precio, cant, marca);
            } else {
                p = new Accesorio(nombre, precio, cant, cbSeguro.isChecked());
            }
            repo.agregar(p);
            Toast.makeText(this, "Venta registrada!", Toast.LENGTH_SHORT).show();
            mostrarResumen();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private void mostrarResumen() {
        StringBuilder sb = new StringBuilder("VENTAS:\n");
        for (ProductoBase p : repo.listar()) sb.append(p.getFicha()).append("\n");
        sb.append("\nTotal registros: ").append(repo.getTotalRegistros());
        sb.append("\nTotal recaudado: $").append(String.format("%.2f", repo.getTotalRecaudado()));
        txtResumen.setText(sb.toString());
    }

    private void limpiar() {
        edtNombre.setText(""); edtPrecio.setText(""); edtCantidad.setText(""); edtMarca.setText("");
        cbSeguro.setChecked(false);
        Toast.makeText(this, "Campos limpios", Toast.LENGTH_SHORT).show();
    }
}