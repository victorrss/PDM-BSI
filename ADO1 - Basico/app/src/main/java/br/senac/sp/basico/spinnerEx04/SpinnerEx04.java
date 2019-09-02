package br.senac.sp.basico.spinnerEx04;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import br.senac.sp.basico.R;
import br.senac.sp.basico.spinnerEx03.SpinnerEx03;
import br.senac.sp.basico.util.Util;

public class SpinnerEx04 extends AppCompatActivity {
    private Spinner spinnerOpcao, spinnerGenero;
    private Button btnMostrarMsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_ex04);

        spinnerOpcao = findViewById(R.id.spinnerOpcao);
        spinnerGenero = findViewById(R.id.spinnerGenero);
        btnMostrarMsg = findViewById(R.id.btnMostrarMsg);

        String opcoes[] = {"Selecione", "Filme", "Música", "Livro", "Jogo"};
        final String filmes[] = {"Selecione", "Matrix", "Rei Leão", "Planeta dos Macacos", "Tropa de Elite", "Outro"};
        final String musicas[] = {"Selecione", "Oceaco - Djavan", "Chega de Saudade - João Gilberto", "Metamorfose Ambulante - Raul Seixas", "Imagine - John Lennon", "Asa Branca - Luiz Gonzaga", "Outra"};
        final String livros[] = {"Selecione", "O cortiço, de Aluísio Azevedo (1890)", "Dom Casmurro, de Machado de Assis (1899)", "São Bernardo, de Graciliano Ramos (1934)", "Outro"};
        final String jogos[] = {"Selecione", "CS:GO", "Need for Speed", "FIFA 20", "Free Fire", "PUBG", "Outro"};


        ArrayAdapter<String> spinnerOpcaoArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opcoes);
        spinnerOpcaoArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerOpcao.setAdapter(spinnerOpcaoArrayAdapter);

        spinnerOpcao.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                spinnerGenero.setEnabled(true);
                ArrayAdapter<String> spinnerGeneroArrayAdapter = null;
                switch (i) {
                    case 0: // Selecione
                        String generos[] = {};
                        spinnerGenero.setEnabled(false);
                        spinnerGeneroArrayAdapter = new ArrayAdapter<String>(SpinnerEx04.this, android.R.layout.simple_spinner_item, generos);
                        break;
                    case 1: // Filme
                        spinnerGeneroArrayAdapter = new ArrayAdapter<String>(SpinnerEx04.this, android.R.layout.simple_spinner_item, filmes);
                        break;
                    case 2: // Musica
                        spinnerGeneroArrayAdapter = new ArrayAdapter<String>(SpinnerEx04.this, android.R.layout.simple_spinner_item, musicas);
                        break;
                    case 3: // Livro
                        spinnerGeneroArrayAdapter = new ArrayAdapter<String>(SpinnerEx04.this, android.R.layout.simple_spinner_item, livros);
                        break;
                    case 4: // Jogo
                        spinnerGeneroArrayAdapter = new ArrayAdapter<String>(SpinnerEx04.this, android.R.layout.simple_spinner_item, jogos);
                        break;
                }
                spinnerGeneroArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerGenero.setAdapter(spinnerGeneroArrayAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnMostrarMsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validacao
                if (spinnerOpcao.getSelectedItem().equals("Selecione")) {
                    Util.showDialog("Selecione uma opção", "Dados", SpinnerEx04.this);
                    return;
                }
                if (spinnerGenero.getSelectedItem().equals("Selecione")) {
                    Util.showDialog("Selecione um gênero", "Dados", SpinnerEx04.this);
                    return;
                }

                String mensagem = "";
                mensagem += "Opção selecionada: " + spinnerOpcao.getSelectedItem().toString() + "\n\n";
                mensagem += "Gênero selecionado: " + spinnerGenero.getSelectedItem().toString();
                Util.showDialog(mensagem, "Dados", SpinnerEx04.this);
            }
        });
    }
}
