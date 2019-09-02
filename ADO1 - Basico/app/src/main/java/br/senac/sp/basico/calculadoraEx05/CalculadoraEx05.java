package br.senac.sp.basico.calculadoraEx05;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import br.senac.sp.basico.R;

public class CalculadoraEx05 extends AppCompatActivity {
    private Button btnNum0, btnNum1, btnNum2, btnNum3, btnNum4, btnNum5, btnNum6, btnNum7, btnNum8, btnNum9;
    private Button btnOpAdicao, btnOpSubtracao, btnOpMultiplicacao, btnOpDivisao, btnOpIgual;
    private Button btnSeparadorDecimal;
    private EditText etVisor;

    private Double valor1,valor2;
    private String op;
    private boolean stock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora_ex05);

        etVisor = findViewById(R.id.etVisor);

        btnNum0 = findViewById(R.id.btnNum0);
        btnNum1 = findViewById(R.id.btnNum1);
        btnNum2 = findViewById(R.id.btnNum2);
        btnNum3 = findViewById(R.id.btnNum3);
        btnNum4 = findViewById(R.id.btnNum4);
        btnNum5 = findViewById(R.id.btnNum5);
        btnNum6 = findViewById(R.id.btnNum6);
        btnNum7 = findViewById(R.id.btnNum7);
        btnNum8 = findViewById(R.id.btnNum8);
        btnNum9 = findViewById(R.id.btnNum9);

        btnOpAdicao = findViewById(R.id.btnOpAdicao);
        btnOpSubtracao = findViewById(R.id.btnOpSubtracao);
        btnOpMultiplicacao = findViewById(R.id.btnOpMultiplicacao);
        btnOpDivisao = findViewById(R.id.btnOpDivisao);
        btnOpIgual = findViewById(R.id.btnOpIgual);

        btnSeparadorDecimal = findViewById(R.id.btnSeparadorDecimal);

        View.OnClickListener listener =  new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        };

        btnNum0.setOnClickListener(listener);
        btnNum1.setOnClickListener(listener);
        btnNum2.setOnClickListener(listener);
        btnNum3.setOnClickListener(listener);
        btnNum4.setOnClickListener(listener);
        btnNum5.setOnClickListener(listener);
        btnNum6.setOnClickListener(listener);
        btnNum7.setOnClickListener(listener);
        btnNum8.setOnClickListener(listener);
        btnNum9.setOnClickListener(listener);

        btnOpAdicao.setOnClickListener(listener);
        btnOpSubtracao.setOnClickListener(listener);
        btnOpMultiplicacao.setOnClickListener(listener);
        btnOpDivisao.setOnClickListener(listener);
        btnOpIgual.setOnClickListener(listener);

        btnSeparadorDecimal.setOnClickListener(listener);
    }
}
