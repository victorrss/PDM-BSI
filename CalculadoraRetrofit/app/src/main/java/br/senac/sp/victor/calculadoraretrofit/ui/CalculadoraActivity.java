package br.senac.sp.victor.calculadoraretrofit.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

import br.senac.sp.victor.calculadoraretrofit.R;
import br.senac.sp.victor.calculadoraretrofit.interfaces.ApiCalculadora;
import br.senac.sp.victor.calculadoraretrofit.models.Calculadora;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CalculadoraActivity extends AppCompatActivity {
    private EditText etValor01;
    private EditText etValor02;
    private RadioButton rbSoma;
    private RadioButton rbSubtracao;
    private RadioButton rbMultiplicacao;
    private RadioButton rbDivisao;
    private Button btnCalcular;
    private TextView tvResultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);

        etValor01 = findViewById(R.id.etValor01);
        etValor02 = findViewById(R.id.etValor02);
        rbSoma = findViewById(R.id.rbSoma);
        rbSubtracao = findViewById(R.id.rbSubtracao);
        rbMultiplicacao = findViewById(R.id.rbMultiplicacao);
        rbDivisao = findViewById(R.id.rbDivisao);
        btnCalcular = findViewById(R.id.btnCalcular);
        tvResultado = findViewById(R.id.tvResultado);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double num1, num2 = null;
                try{
                    num1 = Double.parseDouble(etValor01.getText().toString());
                    num2 = Double.parseDouble(etValor02.getText().toString());
                } catch (Exception e){
                    showDialog("Digite um valor númerico nos campos valor 01 e/ou valor 02", "Erro");
                    return;
                }

                if(!rbDivisao.isChecked() && !rbMultiplicacao.isChecked() &&
                        !rbSoma.isChecked() && !rbSubtracao.isChecked()){
                    showDialog("Selecione uma operação","Erro");
                    return;
                }

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://fabiohenriqueaf.online/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiCalculadora api = retrofit.create(ApiCalculadora.class);
               Call<Calculadora> call = api.getObject(num1,num2);

                Callback<Calculadora>  callback = new Callback<Calculadora>() {
                    @Override
                    public void onResponse(Call<Calculadora> call, Response<Calculadora> response) {
                        Calculadora result = response.body();
                        if (rbDivisao.isChecked()){
                            tvResultado.setText(result.getDiv().toString());
                        }
                        else if (rbMultiplicacao.isChecked()){
                            tvResultado.setText(result.getMult().toString());
                        }
                        else if (rbSoma.isChecked()){
                            tvResultado.setText(result.getSoma().toString());
                        }
                        else if (rbSubtracao.isChecked()){
                            tvResultado.setText(result.getSub().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<Calculadora> call, Throwable t) {
                        showDialog("Erro de conexão","Erro");
                        t.printStackTrace();
                    }
                };
                call.enqueue(callback);
            }
        };
        btnCalcular.setOnClickListener(listener);
    }

    private void showDialog(String val, String title){
        AlertDialog.Builder builder = new AlertDialog.Builder(CalculadoraActivity.this);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
