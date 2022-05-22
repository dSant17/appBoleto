package com.example.appboleto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText txtNombre;
    private EditText txtEdad;
    private EditText txtFecha;
    private Spinner spnDestino;
    private Spinner spnTipo;
    private EditText txtPrecio;
    private Button btnCalcular;
    private Button btnLimpiar;
    private TextView lblCostoTotal;

    Boleto ticket = new Boleto();
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        spnDestino = (Spinner) findViewById(R.id.destino);
        ArrayAdapter<String> Adaptador = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Destinos));
        spnDestino.setAdapter(Adaptador);

        spnTipo = (Spinner) findViewById(R.id.tipo);
        ArrayAdapter<String> Adaptador2 = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_expandable_list_item_1, getResources().getStringArray(R.array.Tipo));
        spnTipo.setAdapter(Adaptador2);

        btnCalcular = (Button) findViewById(R.id.calcular);
        txtNombre = (EditText) findViewById(R.id.nombre);
        txtEdad = (EditText) findViewById(R.id.edad);
        txtFecha = (EditText) findViewById(R.id.fecha);
        txtPrecio = (EditText) findViewById(R.id.precio);
        lblCostoTotal = (TextView) findViewById(R.id.costoTotal);


        btnCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spnDestino.getSelectedItem().toString().matches("Seleccione un destino")) {
                    Toast.makeText(MainActivity.this, "Seleccione un destino.", Toast.LENGTH_SHORT).show();
                } else if (spnTipo.getSelectedItem().toString().matches("Seleccione un tipo de boleto")) {
                    Toast.makeText(MainActivity.this, "Seleccione un tipo de boleto.", Toast.LENGTH_SHORT).show();
                } else if (txtNombre.getText().toString().matches("") || txtEdad.getText().toString().matches("") ||
                txtFecha.getText().toString().matches("") || txtPrecio.getText().toString().matches("")){
                    Toast.makeText(MainActivity.this, "Faltan algunos campos por llenar.", Toast.LENGTH_SHORT).show();
                } else {
                    String nombre = txtNombre.getText().toString();
                    int edad = Integer.parseInt(txtEdad.getText().toString());
                    String fecha = txtFecha.getText().toString();
                    float precio = Float.parseFloat(txtPrecio.getText().toString());
                    String destino = spnDestino.getSelectedItem().toString();
                    String tipoBoleto = spnTipo.getSelectedItem().toString();
                    int tipo = 0;
                    if (tipoBoleto.matches("Sencillo")) {
                        tipo = 1;
                    } else {
                        tipo = 2;
                    }
                    id = id + 1;
                    ticket = new Boleto(id, nombre, precio, tipo, fecha, destino);
                    String costo = ticket.imprimirBoleto(edad).toString();
                    lblCostoTotal.setText(costo);
                }
            }
        });

        btnLimpiar = (Button) findViewById(R.id.limpiar);
        btnLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtNombre.setText("");
                txtEdad.setText("");
                txtFecha.setText("");
                spnDestino.setSelection(0, true);
                spnTipo.setSelection(0, true);
                txtPrecio.setText("");
                lblCostoTotal.setText("");
            }
        });
    }
}