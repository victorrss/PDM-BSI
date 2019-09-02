package br.senac.sp.basico.testapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import br.senac.sp.basico.R;
import br.senac.sp.basico.util.Util;

// Contempla até o ex. nro. 3
public class TestActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etSobrenome;
    private Button btnSaudar;
    private RadioButton rbMasc;
    private RadioButton rbFem;
    private RadioButton rbCasado;
    private RadioButton rbSolteiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        etNome = findViewById(R.id.etNome);
        etSobrenome = findViewById(R.id.etSobrenome);
        btnSaudar = findViewById(R.id.btnSaudar);
        rbMasc = findViewById(R.id.rbMasc);
        rbFem = findViewById(R.id.rbFem);
        rbCasado = findViewById(R.id.rbCasado);
        rbSolteiro = findViewById(R.id.rbSolteiro);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = etNome.getText().toString();
                String sobrenome = etSobrenome.getText().toString();

                if (nome.trim().equals("")) {
                    Util.showDialog("Você precisa digitar um nome", "Atenção", TestActivity.this);
                    return;
                }

                if (sobrenome.trim().equals("")) {
                    Util.showDialog("Você precisa digitar um sobrenome", "Atenção", TestActivity.this);
                    return;
                }

                if (!rbMasc.isChecked() && !rbFem.isChecked()) {
                    Util.showDialog("Você precisa selecionar um sexo", "Atenção", TestActivity.this);
                    return;
                }

                if (!rbCasado.isChecked() && !rbSolteiro.isChecked()) {
                    Util.showDialog("Você precisa selecionar um estado civil", "Atenção", TestActivity.this);
                    return;
                }
                String artigo = rbMasc.isChecked() ? "o" : "a";
                String pronome = rbMasc.isChecked() ? "Sr." : "Sra.";
                String estadoCivil = rbCasado.isChecked() ? "Casad" : "Solteir";

                Util.showDialog("Olá " + pronome + " " + nome + " " + sobrenome +
                        ", " + artigo + " " + pronome.toLowerCase() + " é " + estadoCivil.toLowerCase() + artigo + ".", "Bem-vind" + artigo, TestActivity.this);
            }
        };
        btnSaudar.setOnClickListener(listener);
    }


}
