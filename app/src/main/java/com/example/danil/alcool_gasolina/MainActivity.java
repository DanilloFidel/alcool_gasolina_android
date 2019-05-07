package com.example.danil.alcool_gasolina;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editPrecoAlcool;
    private EditText editPrecoGas;
    private TextView textResultado;
    private static final String PREFERENCES_FILES = "ArquivoPreferencias";

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
        String melhorParaAbastecer = "";
        SharedPreferences preferences = getSharedPreferences(PREFERENCES_FILES, 0);
        SharedPreferences.Editor editor = preferences.edit();

        Double resultado = precoAlcool / precoGas;
        if( resultado >= 0.7){
            editor.putString("combustivel", "Gasolina");
        }else{
            editor.putString("combustivel", "Alcool");
        }
        editor.commit();
        melhorParaAbastecer = preferences.getString("combustivel", "nenhum valor calculado!");
        Toast.makeText(getApplicationContext(),"Recomendo usar " + melhorParaAbastecer + "!",Toast.LENGTH_SHORT).show();
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
