package br.senac.sp.aulaintents.exemplo03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.aulaintents.R;

public class Exemplo03Detalhe extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo03_detalhe);
        final TextView tvParam = findViewById(R.id.tvParam);
        Intent intent = getIntent();
        String param = intent.getStringExtra("param");
        tvParam.setText(param);
    }
}
