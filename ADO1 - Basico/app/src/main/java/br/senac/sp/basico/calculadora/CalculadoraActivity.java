package br.senac.sp.basico.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import java.text.DecimalFormat;

import br.senac.sp.basico.util.Util;
import br.senac.sp.basico.R;

public class CalculadoraActivity extends AppCompatActivity {
    private EditText etValor1;
    private EditText etValor2;
    private Button btnCalcular;
    private RadioButton rbAdicao;
    private RadioButton rbSubtracao;
    private RadioButton rbMultiplicacao;
    private RadioButton rbDivisao;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        etValor1 = findViewById(R.id.etValor1);
        etValor2 = findViewById(R.id.etValor2);
        btnCalcular = findViewById(R.id.btnCalcular);
        rbAdicao = findViewById(R.id.rbAdicao);
        rbSubtracao = findViewById(R.id.rbSubtracao);
        rbMultiplicacao = findViewById(R.id.rbMultiplicacao);
        rbDivisao = findViewById(R.id.rbDivisao);
        tvResultado = findViewById(R.id.tvResultado);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // variaveis
                Double value1 = Util.toDouble(etValor1.getText().toString());
                Double value2 = Util.toDouble(etValor2.getText().toString());
                Double result = 0.0;

                // validações
                if (value1 == null || value2 == null) {
                    Util.showDialog("Informe os valores corretamente.", "Atenção!", CalculadoraActivity.this);
                    return;
                }

                if (!rbAdicao.isChecked() && !rbSubtracao.isChecked() && !rbMultiplicacao.isChecked() && !rbDivisao.isChecked()) {
                    Util.showDialog("Informe a operação matemática corretamente.", "Atenção!", CalculadoraActivity.this);
                    return;
                }

                if (rbDivisao.isChecked() && value2 == 0.00) {
                    Util.showDialog("O valor 2 precisa ser maior que 0.", "Atenção!", CalculadoraActivity.this);
                    return;
                }

                // calculo
                if (rbAdicao.isChecked())
                    result = value1 + value2;
                else if (rbSubtracao.isChecked())
                    result = value1 - value2;
                else if (rbMultiplicacao.isChecked())
                    result = value1 * value2;
                else
                    result = value1 / value2;

                // impressão
                DecimalFormat df = new DecimalFormat("####0.00");
                tvResultado.setText(df.format(result));
            }
        };
        btnCalcular.setOnClickListener(listener);
    }
}
