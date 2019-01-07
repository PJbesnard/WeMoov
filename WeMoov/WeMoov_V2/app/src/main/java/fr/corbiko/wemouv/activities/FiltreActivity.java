package fr.corbiko.wemouv.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.fragments.ListFragment;

public class FiltreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView image_date_today, image_date_tommorow, image_date_week, image_date_week2,
            image_event_type_afterwork, image_event_type_bar, image_event_type_boite, image_event_type_festival,
            style_electro, style_techno, style_house, style_pop_rock, style_jazz, style_hiphop, style_disco, style_soul;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtre);
        // test

        /* Recuperer les cardview */
        image_date_today = findViewById(R.id.image_date_today);
        image_date_tommorow = findViewById(R.id.image_date_tommorow);
        image_date_week = findViewById(R.id.image_date_week);
        image_date_week2 = findViewById(R.id.image_date_week2);

        image_event_type_afterwork = findViewById(R.id.image_event_type_afterwork);
        image_event_type_bar = findViewById(R.id.image_event_type_bar);
        image_event_type_boite = findViewById(R.id.image_event_type_boite);
        image_event_type_festival = findViewById(R.id.image_event_type_festival);

        style_electro = findViewById(R.id.style_electro);
        style_techno = findViewById(R.id.style_techno);
        style_house = findViewById(R.id.style_house);
        style_pop_rock = findViewById(R.id.style_pop_rock);
        style_jazz = findViewById(R.id.style_jazz);
        style_hiphop = findViewById(R.id.style_hiphop);
        style_disco = findViewById(R.id.style_disco);
        style_soul = findViewById(R.id.style_soul);
        // ###########################################

        /* Click Listener */
        image_date_today.setOnClickListener(this);
        image_date_tommorow.setOnClickListener(this);
        image_date_week.setOnClickListener(this);
        image_date_week2.setOnClickListener(this);

        image_event_type_afterwork.setOnClickListener(this);
        image_event_type_bar.setOnClickListener(this);
        image_event_type_boite.setOnClickListener(this);
        image_event_type_festival.setOnClickListener(this);

        style_electro.setOnClickListener(this);
        style_techno.setOnClickListener(this);
        style_house.setOnClickListener(this);
        style_pop_rock.setOnClickListener(this);
        style_jazz.setOnClickListener(this);
        style_hiphop.setOnClickListener(this);
        style_disco.setOnClickListener(this);
        style_soul.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        Intent result = new Intent();
        result.putExtra(ListFragment.BUTTONS, "1");
        setResult(RESULT_OK, result);
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_date_today:
                Toast.makeText(this, image_date_today.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_date_tommorow:
                Toast.makeText(this, image_date_tommorow.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_date_week:
                Toast.makeText(this, image_date_week.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_date_week2:
                Toast.makeText(this, image_date_week2.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_event_type_afterwork:
                Toast.makeText(this, image_event_type_afterwork.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_event_type_bar:
                Toast.makeText(this, image_event_type_bar.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_event_type_boite:
                Toast.makeText(this, image_event_type_boite.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.image_event_type_festival:
                Toast.makeText(this, image_event_type_festival.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_electro:
                Toast.makeText(this, style_electro.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_techno:
                Toast.makeText(this, style_techno.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_house:
                Toast.makeText(this, style_house.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_pop_rock:
                Toast.makeText(this, style_pop_rock.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_jazz:
                Toast.makeText(this, style_jazz.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_hiphop:
                Toast.makeText(this, style_hiphop.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_disco:
                Toast.makeText(this, style_disco.getText(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.style_soul:
                Toast.makeText(this, style_soul.getText(), Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
