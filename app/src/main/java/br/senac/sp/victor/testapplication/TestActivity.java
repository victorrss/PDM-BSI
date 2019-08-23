package br.senac.sp.victor.testapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import br.senac.sp.victor.R;

public class TestActivity extends AppCompatActivity {
    EditText txtNome;
    EditText txtSobrenome;
    Button btn;
    RadioButton rbMasc;
    RadioButton rbFem;
    RadioButton rbCasado;
    RadioButton rbSolteiro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        txtNome = findViewById(R.id.etNome);
        txtSobrenome = findViewById(R.id.etSobrenome);
        btn = findViewById(R.id.btnSaudar);
        rbMasc = findViewById(R.id.rbMasc);
        rbFem = findViewById(R.id.rbFem);
        rbCasado = findViewById(R.id.rbCasado);
        rbSolteiro = findViewById(R.id.rbSolteiro);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = txtNome.getText().toString();
                String sobrenome = txtSobrenome.getText().toString();

                if (nome.trim().equals("")) {
                    showDialog("Você precisa digitar um nome","Erro");
                    return;
                }

                if (sobrenome.trim().equals("")) {
                    showDialog("Você precisa digitar um sobrenome","Erro");
                    return;
                }

                if (!rbMasc.isChecked() && !rbFem.isChecked()) {
                    showDialog( "Você precisa selecionar um sexo","Erro");
                    return;
                }

                if (!rbCasado.isChecked() && !rbSolteiro.isChecked()) {
                    showDialog("Você precisa selecionar um estado civil","Erro");
                    return;
                }
                String artigo = rbMasc.isChecked() ? "o" : "a";
                String pronome = rbMasc.isChecked() ? "Sr." : "Sra.";

                String estadoCivil = rbCasado.isChecked() ? "Casad" : "Solteir";

                showDialog("Olá " + pronome + " " + nome + " " + sobrenome +
                        ", "+artigo+" " + pronome.toLowerCase() + " é " + estadoCivil.toLowerCase() + artigo + ".","Bem-vind" + artigo);

            }
        };
        btn.setOnClickListener(listener);
    }

    private void showDialog(String message, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(TestActivity.this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
