package fr.corbiko.wemouv.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.annotations.MarkerOptions;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;

import android.util.Log;
import android.widget.TextView;

import java.util.List;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.models.Establishment;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private TextView infos;
    private List<Establishment> estab;
    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Mapbox.getInstance(this, "pk.eyJ1Ijoid2Vtb3V2IiwiYSI6ImNqcGgxN21tZjBwYWgzcWxxZ242NHd5NjkifQ.dbudKtF6QmygxeD1osCvbA");
        setContentView(R.layout.activity_map);

        mapView = findViewById(R.id.mapView);
        mapView.onCreate(savedInstanceState);

        infos = findViewById(R.id.infos);
        estab = (List) getIntent().getParcelableArrayListExtra("list_estab");
        StringBuilder stringBuilder = new StringBuilder();
        for(Establishment elem : estab) {
            stringBuilder.append(elem.getName()).append("\n");
        }
        infos.setText(stringBuilder.toString());
        mapView.getMapAsync(this);
    }


    @Override
    public void onMapReady(MapboxMap mapboxMap) {
        for(Establishment elem : estab) {
            mapboxMap.addMarker(new MarkerOptions()
                    .position(new LatLng(elem.getCoord().getLongitude(), elem.getCoord().getLatitude()))
                    .title(elem.getName())
                    .snippet("subtitle example"));
        }
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
