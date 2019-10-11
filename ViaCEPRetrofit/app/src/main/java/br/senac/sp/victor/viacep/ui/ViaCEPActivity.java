package br.senac.sp.victor.viacep.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import br.senac.sp.victor.viacep.R;
import br.senac.sp.victor.viacep.interfaces.ApiViaCEP;
import br.senac.sp.victor.viacep.models.ViaCEP;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ViaCEPActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viacep);

        final EditText etCEP = findViewById(R.id.etCEP);
        final Button btnBuscar = findViewById(R.id.btnBuscar);
        final EditText etLogradouro = findViewById(R.id.etLogradouro);
        final EditText etComplemento = findViewById(R.id.etComplemento);
        final EditText etBairro = findViewById(R.id.etBairro);
        final EditText etLocalidade = findViewById(R.id.etLocalidade);
        final EditText etUF = findViewById(R.id.etUF);
        final EditText etUnidade = findViewById(R.id.etUnidade);
        final EditText etIBGE = findViewById(R.id.etIBGE);
        final EditText etGIA = findViewById(R.id.etGIA);

        etLogradouro.setKeyListener(null);
        etComplemento.setKeyListener(null);
        etBairro.setKeyListener(null);
        etLocalidade.setKeyListener(null);
        etUF.setKeyListener(null);
        etUnidade.setKeyListener(null);
        etIBGE.setKeyListener(null);
        etGIA.setKeyListener(null);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String cep = etCEP.getText().toString();


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://viacep.com.br/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ApiViaCEP api = retrofit.create(ApiViaCEP.class);
                Call<ViaCEP> call = api.getCEP(cep);

                Callback<ViaCEP> callback = new Callback<ViaCEP>() {
                    @Override
                    public void onResponse(Call<ViaCEP> call, Response<ViaCEP> response) {
                        ViaCEP result = response.body();

                        etLogradouro.setText(result.getLogradouro());
                        etComplemento.setText(result.getComplemento());
                        etBairro.setText(result.getBairro());
                        etLocalidade.setText(result.getLocalidade());
                        etUF.setText(result.getUf());
                        etUnidade.setText(result.getUnidade());
                        etIBGE.setText(result.getIbge());
                        etGIA.setText(result.getGia());
                    }

                    @Override
                    public void onFailure(Call<ViaCEP> call, Throwable t) {
                        showDialog("Erro de conexão", "Erro");
                        t.printStackTrace();
                    }
                };
                call.enqueue(callback);
            }
        };
        btnBuscar.setOnClickListener(listener);
    }

    private void showDialog(String val, String title) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ViaCEPActivity.this);
        builder.setMessage(val);
        builder.setTitle(title);
        builder.setCancelable(false);
        builder.setPositiveButton("OK", null);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
