package br.senac.sp.aulaintents.exemplo02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.aulaintents.R;

public class Exemplo02 extends AppCompatActivity {
    Button btnIr;
    EditText etURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exemplo02);
        btnIr = findViewById(R.id.btnIr);
        etURL = findViewById(R.id.etURL);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = etURL.getText().toString();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + url));
                startActivity(intent);
            }
        };
        btnIr.setOnClickListener(listener);
    }
}