package br.senac.sp.victor.termosuso;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        verificarBotaoSecreto();
    }

    protected void onResume() {
        super.onResume();
        verificarBotaoSecreto();
    }

    private void verificarBotaoSecreto() {
        Button btnSecreto = findViewById(R.id.btnSecreto);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);

        boolean habilita = prefs.getBoolean("btn_secreto", false);

        btnSecreto.setVisibility(habilita ? View.VISIBLE : View.INVISIBLE);
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu_item_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return true;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
}
