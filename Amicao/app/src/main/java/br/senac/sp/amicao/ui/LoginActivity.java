package br.senac.sp.amicao.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.api.ApiCustomer;
import br.senac.sp.amicao.model.Customer;
import br.senac.sp.amicao.util.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {
    private EditText etEmail;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnRegister;
    private String classBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        final Intent i = getIntent();
        this.classBack = i.getStringExtra("classBack");

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer auth = new Customer();
                auth.setEmailCliente(etEmail.getText().toString());
                auth.setSenhaCliente(etPassword.getText().toString());

                Retrofit retrofit = Util.getRetrofit();

                ApiCustomer api = retrofit.create(ApiCustomer.class);
                Call<Customer> call = api.login(auth);

                call.enqueue(new Callback<Customer>() {
                    @Override
                    public void onResponse(Call<Customer> call, Response<Customer> response) {
                        if (response.isSuccessful()) {
                            Customer customer = response.body();

                            // Salva o status de logado na preferencia compartilhada
                            SharedPreferences.Editor editor = Util.getPreference(LoginActivity.this).edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.putInt("currentCustomerId", customer.getIdCliente());
                            editor.apply();

                            Util.customerLoggedIn = customer;

                            // Retorna para a activity do construtor
                            Intent intent = new Intent(LoginActivity.this, getClassBack());
                            startActivity(intent);
                        } else {
                            Util.showDialog("Login ou senha inválida!", "Login", LoginActivity.this);
                        }
                    }

                    @Override
                    public void onFailure(Call<Customer> call, Throwable t) {
                        Util.showDialog("Erro de Conexão", "Erro", LoginActivity.this);
                        t.printStackTrace();
                    }
                });
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    private Class getClassBack() {
        switch (this.classBack) {
            case "main":
                return MainActivity.class;
            case "cart":
                return CartActivity.class;
            default:
                return MainActivity.class;
        }
    }
}
