package fr.corbiko.wemouv.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.fragments.ListFragment;
import fr.corbiko.wemouv.fragments.FavFragment;
import fr.corbiko.wemouv.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Récupère element de la vue */
        BottomNavigationView navigation = findViewById(R.id.navigation);
        /* if(user == admin) */
            // navigation.inflateMenu(R.menu.navigation_organiser);
        /* sinon rien faire - garde menu de base */
        navigation.inflateMenu(R.menu.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        /* Affiche ListFragment par défaut au démarage de l'Activity */
        showFragment(new ListFragment());
    }


    /*  Listener du menu du bas de l'appli (BottomNavigationView) */
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.list:
                    showFragment(new ListFragment());
                    return true;
                case R.id.favori:
                    showFragment(new FavFragment());
                    return true;
                case R.id.settings:
                    showFragment(new ProfileFragment());
                    return true;
                case R.id.add:
                    showFragment(new ProfileFragment());
                    return true;
            }
            return false;
        }
    };

    private void showFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.content, fragment)
                .commit();
    }
}
