package br.senac.sp.basico.checkbox;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import br.senac.sp.basico.util.Util;
import br.senac.sp.basico.R;

public class CheckboxActivity extends AppCompatActivity {
    private EditText etNome;
    private EditText etTelefone;
    private EditText etEmail;
    private EditText etWhatsApp;
    private EditText etSkype;
    private EditText etHangouts;
    private CheckBox cbTelefone;
    private CheckBox cbEmail;
    private CheckBox cbWhatsApp;
    private CheckBox cbSkype;
    private CheckBox cbHangouts;
    private Button btnMostrarMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkbox);

        etNome = findViewById(R.id.etNome);
        etTelefone = findViewById(R.id.etTelefone);
        etEmail = findViewById(R.id.etEmail);
        etWhatsApp = findViewById(R.id.etWhatsApp);
        etSkype = findViewById(R.id.etSkype);
        etHangouts = findViewById(R.id.etHangouts);
        cbTelefone = findViewById(R.id.cbTelefone);
        cbEmail = findViewById(R.id.cbEmail);
        cbWhatsApp = findViewById(R.id.cbWhatsApp);
        cbSkype = findViewById(R.id.cbSkype);
        cbHangouts = findViewById(R.id.cbHangouts);
        btnMostrarMsg = findViewById(R.id.btnMostrarMsg);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNome.getText().toString().trim().equals("")) {
                    Util.showDialog("Informe o nome!", "Atenção", CheckboxActivity.this);
                    return;
                }

                String msg = "Nome: " + etNome.getText() + "\n";
                msg += cbTelefone.isChecked() ? "Telefone: " + etTelefone.getText() + "\n" : "";
                msg += cbEmail.isChecked() ? "Email: " + etEmail.getText() + "\n" : "";
                msg += cbWhatsApp.isChecked() ? "WhatsApp: " + etWhatsApp.getText() + "\n" : "";
                msg += cbSkype.isChecked() ? "Skype: " + etSkype.getText() + "\n" : "";
                msg += cbHangouts.isChecked() ? "Hangouts: " + etHangouts.getText() + "\n" : "";
                msg += "\nPreferência de contato: ";
                msg += cbTelefone.isChecked() ? "\n - Telefone " : "";
                msg += cbEmail.isChecked() ? "\n - Email" : "";
                msg += cbWhatsApp.isChecked() ? "\n - WhatsApp" : "";
                msg += cbSkype.isChecked() ? "\n - Skype" : "";
                msg += cbHangouts.isChecked() ? "\n - Hangouts" : "";

                Util.showDialog(msg, "Dados", CheckboxActivity.this);
            };
        };
        btnMostrarMsg.setOnClickListener(listener);

        cbTelefone.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                etTelefone.setText("");
                etTelefone.setEnabled(b);
                if (b) etTelefone.requestFocus();
            }
        });

        cbEmail.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                etEmail.setText("");
                etEmail.setEnabled(b);
                if (b) etEmail.requestFocus();
            }
        });

        cbWhatsApp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                etWhatsApp.setText("");
                etWhatsApp.setEnabled(b);
                if (b) etWhatsApp.requestFocus();
            }
        });

        cbSkype.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                etSkype.setText("");
                etSkype.setEnabled(b);
                if (b) etSkype.requestFocus();
            }
        });

        cbHangouts.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                etHangouts.setText("");
                etHangouts.setEnabled(b);
                if (b) etHangouts.requestFocus();
            }
        });
    }
}
