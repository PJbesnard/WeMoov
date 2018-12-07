package fr.corbiko.wemouv.activities;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.models.Establishment;

public class EtabClickActivity extends AppCompatActivity {

    /* Récupère l'etablissement passé dans l'activity */
   /* private final Establishment etablissement; */

   private ImageView iv;
   private TextView name, description;
   private int images_estab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etab_click);
        Establishment establishment = getIntent().getParcelableExtra("etab_infos");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // affiche bouton back
            getSupportActionBar().setDisplayShowHomeEnabled(true); // garde boutton back lors du défilement
            toolbar.setTitle(establishment.getName());
        }
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO definir une animation de retour
                finish(); // detruit l'activity et revient a l'activity précédente dans le pile
            }
        });
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        iv = findViewById(R.id.image);

        name.setText(establishment.getName());
        description.setText(establishment.getContent());

        images_estab = establishment.getImage1();
        iv.setImageResource(images_estab);
    }

    /* Affiche le menu dans le toolbar */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return super.onCreateOptionsMenu(menu);
    }


    /* Listener lors du clic sur un bouton du toolbar */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.favori:
                Toast.makeText(this, "Favori", Toast.LENGTH_SHORT).show();
                break;
            case R.id.share:
                Toast.makeText(this, "Share", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
