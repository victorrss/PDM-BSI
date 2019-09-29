package br.senac.sp.victor.materialdesignexercicios.fragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import br.senac.sp.victor.materialdesignexercicios.R;
import br.senac.sp.victor.materialdesignexercicios.models.Cliente;

public class FragmentoCliente extends Fragment {
    EditText etDocumento;
    EditText etNome;
    EditText etEndereco;
    EditText etEmail;
    EditText etContato;
    Cliente c;

    public FragmentoCliente() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_cliente, container, false);
        FloatingActionButton fabButton = (FloatingActionButton)v.findViewById(R.id.fabEdit);
        etDocumento = (EditText)v.findViewById(R.id.etDocumento);
        etNome = (EditText)v.findViewById(R.id.etNome);
        etEndereco = (EditText)v.findViewById(R.id.etEndereco);
        etEmail = (EditText)v.findViewById(R.id.etEmail);
        etContato = (EditText)v.findViewById(R.id.etContato);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c = new Cliente();

                c.setDocumento(etDocumento.getText().toString());
                c.setNome(etNome.getText().toString());
                c.setEndereco(etEndereco.getText().toString());
                c.setEmail(etEmail.getText().toString());
                c.setContato(etContato.getText().toString());

                etDocumento.setText("");
                etNome.setText("");
                etEndereco.setText("");
                etEmail.setText("");
                etContato.setText("");

                Snackbar.make(v, "Os dados foram salvos", Snackbar.LENGTH_LONG)
                        .setAction("Ver dados", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etDocumento.setText(c.getDocumento());
                                etNome.setText(c.getNome());
                                etEndereco.setText(c.getEndereco());
                                etEmail.setText(c.getEmail());
                                etContato.setText(c.getContato());
                            }
                        }).show();
            }
        });
        return v;
    }
}
