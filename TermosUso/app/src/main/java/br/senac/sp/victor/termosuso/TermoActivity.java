package br.senac.sp.victor.termosuso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TermoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_termo);

        final SharedPreferences prefs = getSharedPreferences("termo", MODE_PRIVATE);
        String termo = prefs.getString("termo", null);

        if (termo != null) {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        }
        Button btnAceito = findViewById(R.id.btnAceito);

        btnAceito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("termo", "SIM");
                editor.apply();

                Intent intent = new Intent(TermoActivity.this, AppActivity.class);
                startActivity(intent);
            }
        });

    }
}
