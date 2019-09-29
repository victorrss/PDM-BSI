package br.senac.sp.victor.materialdesignexercicios;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoProduto;
import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoCliente;
import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoUsuario;

public class TabPageAdapter extends FragmentStatePagerAdapter {
    private List<Fragment> fragmentos = new ArrayList<Fragment>();
    private List<String> titulos = new ArrayList<String>();

    public TabPageAdapter(FragmentManager fm) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        fragmentos.add(new FragmentoProduto());
        titulos.add("Produto");

        fragmentos.add(new FragmentoCliente());
        titulos.add("Cliente");

        fragmentos.add(new FragmentoUsuario());
        titulos.add("Usuário");
    }

    @Override
    public Fragment getItem(int position) {
        return this.fragmentos.get(position);
    }

    @Override
    public int getCount() {
        return this.fragmentos.size();
    }

    public CharSequence getPageTitle(int position){
        return titulos.get(position);
    }
}
