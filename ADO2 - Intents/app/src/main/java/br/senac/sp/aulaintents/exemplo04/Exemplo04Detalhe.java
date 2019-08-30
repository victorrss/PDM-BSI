package br.senac.sp.aulaintents.exemplo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aulaintents.R;

import java.text.DecimalFormat;

public class Exemplo04Detalhe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo04_detalhe);

        final Button btnAdicao = findViewById(R.id.btnAdicao);
        final Button btnSubtracao = findViewById(R.id.btnSubtracao);
        final Button btnMultiplicacao = findViewById(R.id.btnMultiplicacao);
        final Button btnDivisao = findViewById(R.id.btnDivisao);
        final Intent i = getIntent();
        final Double value1 = i.getDoubleExtra("valor1", 1);
        final Double value2 = i.getDoubleExtra("valor2", 1);

        btnAdicao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("resultado", calcular(value1, value2, "+"));
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnSubtracao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("resultado", calcular(value1, value2, "-"));
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnMultiplicacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("resultado", calcular(value1, value2, "*"));
                setResult(RESULT_OK, i);
                finish();
            }
        });

        btnDivisao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i.putExtra("resultado", calcular(value1, value2, "/"));
                setResult(RESULT_OK, i);
                finish();
            }
        });
    }

    String calcular(Double value1, Double value2, String op) {
        Double result = 0.0;

        if (op.equals("/") && value2 == 0.00) {
            // Util.showDialog("O valor 2 precisa ser maior que 0.", "Atenção!", Exemplo04Detalhe.this);
            return "ERRO";
        }

        // calculo
        if (op.equals("+"))
            result = value1 + value2;
        else if (op.equals("-"))
            result = value1 - value2;
        else if (op.equals("*"))
            result = value1 * value2;
        else
            result = value1 / value2;

        // impressão
        DecimalFormat df = new DecimalFormat("####0.00");
        return df.format(result);
    }
}
