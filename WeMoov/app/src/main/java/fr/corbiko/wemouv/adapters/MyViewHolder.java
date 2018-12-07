package fr.corbiko.wemouv.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.activities.EtabClickActivity;
import fr.corbiko.wemouv.models.Establishment;

public class MyViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView name, distance;
    private final ImageView images, favori;

    /* itemView est la vue correspondante à 1 card */
    public MyViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;

        /* Récupère nos elements de la vue pour les manipuler */
        name =  itemView.findViewById(R.id.title_card);
        distance = itemView.findViewById(R.id.distance_card);
        images = itemView.findViewById(R.id.image_card);
        favori = itemView.findViewById(R.id.favori);

    }

    /* Méthode qui permet de remplir la card en fonction d'un Establishment */
    public void display(final Establishment myEtab){
        name.setText(myEtab.getName());
        distance.setText("500 m");
        images.setImageResource(myEtab.getImage1());

        favori.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, myEtab.getName(), Toast.LENGTH_SHORT).show();
                if(favori.getTag().equals("not_favori")) {
                    favori.setImageResource(R.drawable.is_favori);
                    favori.setTag("is_favori");
                }
                else {
                    favori.setImageResource(R.drawable.not_favori);
                    favori.setTag("not_favori");
                }
            }
        });

        distance.setText("à 500 m");
        distance.setText("500 m");
        images.setImageResource(myEtab.getImage1());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EtabClickActivity.class);
                intent.putExtra("etab_infos", myEtab);
                context.startActivity(intent);
            }
        });
    }


}