package br.senac.sp.victor.materialdesignexercicios;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoProduto;
import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoCliente;
import br.senac.sp.victor.materialdesignexercicios.fragmentos.FragmentoUsuario;

public class MainActivity extends AppCompatActivity {
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Menu menu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.navigation_view);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                if (menuItem.isChecked())
                    menuItem.setChecked(false);
                else menuItem.setChecked(true);

                drawerLayout.closeDrawers();
                //setMenuVisible(false,R.id.action_activity_sobre);
                MenuItem itemSobre = menu.findItem(R.id.action_activity_sobre);
                itemSobre.setVisible(false);
                switch (menuItem.getItemId()) {
                    case R.id.action_fragmento_produto:
                        FragmentoProduto f1 = new FragmentoProduto();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container, f1).commit();
                        return true;
                    case R.id.action_fragmento_cliente:
                        FragmentoCliente f2 = new FragmentoCliente();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container, f2).commit();
                        return true;
                    case R.id.action_fragmento_usuario:
                        FragmentoUsuario f3 = new FragmentoUsuario();
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.frag_container, f3).commit();
                        return true;
                    case R.id.action_activity_sobre:
                        Intent intent = new Intent(MainActivity.this, SobreActivity.class);
                        startActivity(intent);
                        return true;
                    case R.id.action_activity_home:
                        itemSobre.setVisible(true);

                        for (Fragment fragment : getSupportFragmentManager().getFragments()) {
                            getSupportFragmentManager().beginTransaction().remove(fragment).commit();
                        }
                        return true;
                }
                //setMenuVisible(true,R.id.action_activity_sobre);
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
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
