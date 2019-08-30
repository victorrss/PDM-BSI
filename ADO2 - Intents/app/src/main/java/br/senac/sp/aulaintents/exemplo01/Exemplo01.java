package br.senac.sp.aulaintents.exemplo01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aulaintents.R;


public class Exemplo01 extends AppCompatActivity {
    Button btnIr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo01);

        btnIr = findViewById(R.id.btnIr);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Exemplo01.this, Exemplo01Detalhe.class);
                startActivity(intent);
            }
        };
        btnIr.setOnClickListener(listener);
    }

}