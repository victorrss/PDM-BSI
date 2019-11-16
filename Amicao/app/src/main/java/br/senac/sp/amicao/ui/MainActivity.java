package br.senac.sp.amicao.ui;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import br.senac.sp.amicao.R;

public class MainActivity extends AppCompatActivity {
    private NavigationView navView;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer);
        navView = findViewById(R.id.navigation_view);

        ProductListFragment f = new ProductListFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag_container, f).commit();
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
        /*
        else if (item.getItemId() == R.id.action_config) {
            Intent intent = new Intent(MainActivity.this, ConfiguracaoActivity.class);
            startActivity(intent);
            return true;
        }
         */
        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Carrega o menu
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return true;
    }

}
