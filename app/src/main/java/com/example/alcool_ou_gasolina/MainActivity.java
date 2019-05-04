package com.example.alcool_ou_gasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editPrecoAlcool;
    private EditText editPrecoGas;
    private TextView textResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editPrecoAlcool = findViewById(R.id.input_alcool);
        editPrecoGas = findViewById(R.id.input_gas);
        textResultado = findViewById(R.id.resultado);
    }

    public void processarValores(View view){
        String precoAlcool = editPrecoAlcool.getText().toString();
        String precoGas = editPrecoGas.getText().toString();
        Boolean camposValidos = this.validarCampos(precoAlcool ,precoGas);
        if(camposValidos){
            this.calcularMelhorPreco(precoAlcool, precoGas);
        }else{
            textResultado.setText("Preencha os dois campos!");
        }
    }

    public void calcularMelhorPreco(String alcool, String gas){
        Double precoAlcool = Double.parseDouble(alcool);
        Double precoGas = Double.parseDouble(gas);

        Double resultado = precoAlcool / precoGas;
        if( resultado >= 0.7){
            textResultado.setText("Recomendo usar Gasolina!");
        }else{
            textResultado.setText("Recomendo usar Alcool!");
        }
    }

    public Boolean validarCampos(String alcool, String gas){
        Boolean validados = true;

        if(alcool == null || alcool.equals("")){
            validados = false;
        }else if (gas == null || gas.equals("")){
            validados = false;
        }

        return validados;
    }

}
