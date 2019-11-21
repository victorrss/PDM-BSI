package br.senac.sp.amicao.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import br.senac.sp.amicao.R;
import br.senac.sp.amicao.util.Util;

public class MainActivity extends AppCompatActivity {
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private ProductListFragment f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.navigation_view);

        f = new ProductListFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frag_container, f, ProductListFragment.class.getName())
                .commit();
        navView.setCheckedItem(R.id.action_product_list);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();

                switch (menuItem.getItemId()) {
                    case R.id.action_product_list:
                        ProductListFragment f = new ProductListFragment();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container, f).commit();
                        return true;
                }
                return false;

            }
        });
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.openDrawer, R.string.closeDrawer);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);

        actionBarDrawerToggle.syncState();
    }

    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        else if (item.getItemId() == R.id.action_filter_category) {
            CategoryListFragment c = new CategoryListFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frag_container, c).commit();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Carrega o menu]
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);

        ProductListFragment nomeFragment = (ProductListFragment) getSupportFragmentManager()
                .findFragmentByTag(ProductListFragment.class.getName());
        if (nomeFragment != null && !nomeFragment.isVisible()) {
            menu.removeItem(R.id.action_filter_category);
        }
        //Pega o Componente.
        SearchView mSearchView = (SearchView) menu.findItem(R.id.search_view).getActionView();

        // exemplos de utilização:
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Util.searchTerm = query;
                f.callApis();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        mSearchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                Util.searchTerm = null;
                f.callApis();
                return false;
            }
        });
        return true;
    }

}
