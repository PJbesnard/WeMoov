package fr.corbiko.wemouv.activities;


import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.squareup.picasso.Picasso;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.fragments.ListFragment;
import fr.corbiko.wemouv.models.Establishment;

public class EtabClickActivity extends AppCompatActivity {

    private boolean b;
   private ImageView iv;
   private TextView name, description;
   private String images_estab;
   private FloatingActionButton favori;
    private MapView mapView;

   private Establishment establishment;
   private String state_favori;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoid2Vtb3V2IiwiYSI6ImNqcGgxN21tZjBwYWgzcWxxZ242NHd5NjkifQ.dbudKtF6QmygxeD1osCvbA");
        setContentView(R.layout.activity_etab_click);

        b = false;

        establishment = getIntent().getParcelableExtra("etab_infos");
        state_favori = getIntent().getStringExtra("state_favori");

        mapView = findViewById(R.id.mapViewEstab);
        mapView.onCreate(savedInstanceState);

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

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        iv = findViewById(R.id.image);
        favori = findViewById(R.id.favori);

        name.setText(establishment.getName());
        description.setText(establishment.getContent());
        if(state_favori.equals("not_favori")) {
            favori.setImageResource(R.drawable.not_favori);
            favori.setTag("not_favori");
        }
        else {
            favori.setImageResource(R.drawable.is_favori);
            favori.setTag("is_favori");
        }

        images_estab = establishment.getImage1();
        Picasso.with(this)
                .load(images_estab)
                .fit()
                .centerCrop()
                .into(iv);

        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(favori.getTag().equals("not_favori")) {
                    favori.setImageResource(R.drawable.is_favori);
                    favori.setTag("is_favori");
                    Toast.makeText(EtabClickActivity.this, "Ajouté aux Favoris", Toast.LENGTH_SHORT).show();
                }
                else {
                    favori.setImageResource(R.drawable.not_favori);
                    favori.setTag("not_favori");
                    Toast.makeText(EtabClickActivity.this, "Retiré des Favoris", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(MapboxMap mapboxMap) {
                /* Centre map sur l'etablissement */
                mapboxMap.setCameraPosition(new CameraPosition.Builder()
                    .target(new LatLng(establishment.getCoord().getLongitude(), establishment.getCoord().getLatitude()))
                    .zoom(12.5)
                    .build());
                /* Ajoute le marqueur de l'etablissement */
                mapboxMap.addMarker(new MarkerOptions()
                        .position(new LatLng(establishment.getCoord().getLongitude(), establishment.getCoord().getLatitude())));
            }
        });
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
            case R.id.share:
                Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = "Je te partage cet établissement !\n Nom: " + establishment.getName()
                        + "\nDescription: " + establishment.getContent()
                        + "\nPhone: " + establishment.getPhone();
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Partage etablisseement - WeMouv");
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
                startActivity(Intent.createChooser(sharingIntent, "Partager via"));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        state_favori = favori.getTag().toString();
        Intent intent = new Intent(this, ListFragment.class);
        intent.putExtra("state_favori", favori.getTag().toString());
        finish();
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        mapView.onSaveInstanceState(outState);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
