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
import br.senac.sp.victor.materialdesignexercicios.models.Usuario;

public class FragmentoUsuario extends Fragment {
    EditText etCPF;
    EditText etNome;
    EditText etEndereco;
    EditText etLogin;
    Usuario u;
    public FragmentoUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_usuario, container, false);
        FloatingActionButton fabButton = (FloatingActionButton)v.findViewById(R.id.fabEdit);
        etCPF = (EditText)v.findViewById(R.id.etCPF);
        etNome = (EditText)v.findViewById(R.id.etNome);
        etEndereco = (EditText)v.findViewById(R.id.etEndereco);
        etLogin = (EditText)v.findViewById(R.id.etLogin);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u = new Usuario();

                u.setCpf(etCPF.getText().toString());
                u.setNome(etNome.getText().toString());
                u.setEndereco(etEndereco.getText().toString());
                u.setLogin(etLogin.getText().toString());

                etCPF.setText("");
                etNome.setText("");
                etEndereco.setText("");
                etLogin.setText("");

                Snackbar.make(v, "Os dados foram salvos", Snackbar.LENGTH_LONG)
                        .setAction("Ver dados", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etCPF.setText(u.getCpf());
                                etNome.setText(u.getNome());
                                etEndereco.setText(u.getEndereco());
                                etLogin.setText(u.getLogin());
                            }
                        }).show();
            }
        });
        return v;
    }
}
