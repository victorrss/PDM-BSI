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
import br.senac.sp.victor.materialdesignexercicios.models.Produto;

public class FragmentoProduto extends Fragment {
    EditText etCodigoBarras;
    EditText etNome;
    EditText etMarca;
    EditText etCategoria;
    EditText etPrecoCusto;
    EditText etPrecoVenda;
    Produto p;

    public FragmentoProduto() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_produto, container, false);
        FloatingActionButton fabButton = (FloatingActionButton)v.findViewById(R.id.fabEdit);
        etCodigoBarras = (EditText)v.findViewById(R.id.etCodigoBarras);
        etNome = (EditText)v.findViewById(R.id.etNome);
        etMarca = (EditText)v.findViewById(R.id.etMarca);
        etCategoria = (EditText)v.findViewById(R.id.etCategoria);
        etPrecoCusto = (EditText)v.findViewById(R.id.etPrecoCusto);
        etPrecoVenda = (EditText)v.findViewById(R.id.etPrecoVenda);

        fabButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = new Produto();

                p.setCodigoBarras(etCodigoBarras.getText().toString());
                p.setNome(etNome.getText().toString());
                p.setMarca(etMarca.getText().toString());
                p.setCategoria(etCategoria.getText().toString());
                p.setPrecoCusto(etPrecoCusto.getText().toString());
                p.setPrecoVenda(etPrecoVenda.getText().toString());

                etCodigoBarras.setText("");
                etNome.setText("");
                etMarca.setText("");
                etCategoria.setText("");
                etPrecoCusto.setText("");
                etPrecoVenda.setText("");

                Snackbar.make(v, "Os dados foram salvos", Snackbar.LENGTH_LONG)
                        .setAction("Ver dados", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                etCodigoBarras.setText(p.getCodigoBarras());
                                etNome.setText(p.getNome());
                                etMarca.setText(p.getMarca());
                                etCategoria.setText(p.getCategoria());
                                etPrecoCusto.setText(p.getPrecoCusto());
                                etPrecoVenda.setText(p.getPrecoVenda());
                            }
                        }).show();
            }
        });
        return v;
    }
}
