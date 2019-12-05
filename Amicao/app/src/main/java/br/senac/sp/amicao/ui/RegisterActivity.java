package br.senac.sp.amicao.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.api.ApiCustomer;
import br.senac.sp.amicao.model.Customer;
import br.senac.sp.amicao.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RegisterActivity extends AppCompatActivity {
    private EditText etName;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etCPF;
    private EditText etCellphone;
    private EditText etTelephoneH;
    private EditText etTelephoneC;
    private Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etCPF = findViewById(R.id.etCPF);
        etCellphone = findViewById(R.id.etCellphone);
        etTelephoneH = findViewById(R.id.etTelephoneH);
        etTelephoneC = findViewById(R.id.etTelephoneC);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer c = new Customer();
                c.setNomeCompletoCliente(etEmail.getText().toString());
                c.setEmailCliente(etEmail.getText().toString());
                c.setSenhaCliente(etPassword.getText().toString());
                c.setCPFCliente(etCPF.getText().toString());
                c.setCelularCliente(etCellphone.getText().toString());
                c.setTelResidencialCliente(etTelephoneH.getText().toString());
                c.setTelComercialCliente(etTelephoneC.getText().toString());

                Retrofit retrofit = Util.getRetrofit();

                ApiCustomer api = retrofit.create(ApiCustomer.class);
                Call<Customer> call = api.register(c);

                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.isSuccessful()) {
                            Customer customer = response.body();

                            Toast toast= Toast.makeText(getApplicationContext(),"Conta criada realizada com sucesso, efetue o login",Toast.LENGTH_SHORT);
                            toast.setMargin(50,50);
                            toast.show();

                            // Retorna para a activity do construtor
                            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            Util.showDialog("Falha ao cadastrar, verifique os dados informados.", "Criar conta", RegisterActivity.this);
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Util.showDialog("Erro de Conexão", "Erro", RegisterActivity.this);
                        t.printStackTrace();
                    }
                });
            }
        });

    }
}
