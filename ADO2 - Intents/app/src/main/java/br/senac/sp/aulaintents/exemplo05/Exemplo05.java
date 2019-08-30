package br.senac.sp.aulaintents.exemplo05;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.aulaintents.R;
import br.senac.sp.aulaintents.exemplo01.Exemplo01;
import br.senac.sp.aulaintents.exemplo02.Exemplo02;
import br.senac.sp.aulaintents.exemplo03.Exemplo03;
import br.senac.sp.aulaintents.exemplo04.Exemplo04;

public class Exemplo05 extends AppCompatActivity {
    private Button btnEx01;
    private Button btnEx02;
    private Button btnEx03;
    private Button btnEx04;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo05);

        btnEx01 = findViewById(R.id.btnEx01);
        btnEx02 = findViewById(R.id.btnEx02);
        btnEx03 = findViewById(R.id.btnEx03);
        btnEx04 = findViewById(R.id.btnEx04);

        btnEx01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exemplo05.this, Exemplo01.class));
            }
        });

        btnEx02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exemplo05.this, Exemplo02.class));
            }
        });

        btnEx03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exemplo05.this, Exemplo03.class));
            }
        });

        btnEx04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Exemplo05.this, Exemplo04.class));
            }
        });

    }
}
