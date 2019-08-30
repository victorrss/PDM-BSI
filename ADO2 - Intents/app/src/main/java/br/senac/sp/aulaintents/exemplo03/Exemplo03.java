package br.senac.sp.aulaintents.exemplo03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aulaintents.R;

public class Exemplo03 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo03);

        final Button btnIr = findViewById(R.id.btnIr);
        final EditText etParam = findViewById(R.id.etParam);
        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i =  new Intent(Exemplo03.this, Exemplo03Detalhe.class);
                i.putExtra("param", etParam.getText().toString());
                startActivity(i);
            }
        };
        btnIr.setOnClickListener(listener);
    }
}
