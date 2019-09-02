package br.senac.sp.basico.spinnerEx03;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import br.senac.sp.basico.util.Util;
import br.senac.sp.basico.R;

public class SpinnerEx03 extends AppCompatActivity {
    private EditText etNome;
    private EditText etTelefone;
    private Spinner spinnerTipoTelefone;
    private EditText etEmail;
    private Spinner spinnerTipoEmail;
    private Button btnMostrarMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_ex03);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        spinnerTipoTelefone = findViewById(R.id.spinnerTipoTelefone);
        etEmail = findViewById(R.id.etEmail);
        spinnerTipoEmail = findViewById(R.id.spinnerTipoEmail);
        btnMostrarMsg = findViewById(R.id.btnMostrarMsg);

        String tels[] = {"Selecione", "Residencial", "Comercial", "Celular", "Outro"};
        String emails[] = {"Selecione", "Pessoal", "Profissional", "Outro"};

        ArrayAdapter<String> spinnerTelArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, tels);
        spinnerTelArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoTelefone.setAdapter(spinnerTelArrayAdapter);

        ArrayAdapter<String> spinnerEmailArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, emails);
        spinnerEmailArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipoEmail.setAdapter(spinnerEmailArrayAdapter);


        btnMostrarMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validacao
                if (etNome.getText().toString().equals("") && etTelefone.getText().toString().equals("") && etEmail.getText().toString().equals("")) {
                    Util.showDialog("Digite um nome, telefone e e-mail", "Dados", SpinnerEx03.this);
                    return;
                }
                if (spinnerTipoTelefone.getSelectedItem().equals("Selecione")) {
                    Util.showDialog("Selecione um tipo de telefone", "Dados", SpinnerEx03.this);
                    return;
                }
                if (spinnerTipoEmail.getSelectedItem().equals("Selecione")) {
                    Util.showDialog("Selecione um tipo de e-mail", "Dados", SpinnerEx03.this);
                    return;
                }

                String mensagem = "Nome: " + etNome.getText().toString() + "\n";
                mensagem += "Telefone: " + etTelefone.getText().toString() + "\n";
                mensagem += "Telefone selecionado: " + spinnerTipoTelefone.getSelectedItem().toString() + "\n\n";
                mensagem += "E-mail: " + etEmail.getText().toString() + "\n";
                mensagem += "E-mail selecionado: " + spinnerTipoEmail.getSelectedItem().toString();
                Util.showDialog(mensagem, "Dados", SpinnerEx03.this);
            }
        });
    }
}
