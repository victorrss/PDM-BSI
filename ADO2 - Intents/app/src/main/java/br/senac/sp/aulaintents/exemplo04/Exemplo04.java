package br.senac.sp.aulaintents.exemplo04;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.aulaintents.R;
import br.senac.sp.aulaintents.util.Util;

public class Exemplo04 extends AppCompatActivity {
    private Button btnCalcular;
    private EditText etValue1;
    private EditText etValue2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo04);
        btnCalcular = findViewById(R.id.btnCalcular);
        etValue1 = findViewById(R.id.etValue1);
        etValue2 = findViewById(R.id.etValue2);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Double value1 = Util.toDouble(etValue1.getText().toString());
                Double value2 = Util.toDouble(etValue2.getText().toString());
                // validações
                if (value1 == null || value2 == null) {
                    Util.showDialog("Informe os valores corretamente.", "Atenção!", Exemplo04.this);
                    return;
                }

                Intent i = new Intent(Exemplo04.this, Exemplo04Detalhe.class);
                i.putExtra("valor1", value1);
                i.putExtra("valor2", value2);
                startActivityForResult(i, 1);
            }
        };
        btnCalcular.setOnClickListener(listener);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        final TextView tvResultado = findViewById(R.id.tvResultado);
        // Verifica de quem veio a resposta
        if (requestCode == 1) {
            // Se foi resposta de sucesso
            if (resultCode == RESULT_OK) {
                String result = data.getStringExtra("resultado");
                tvResultado.setText(result);
            }
        }
    }
}
