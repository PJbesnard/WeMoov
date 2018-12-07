package fr.corbiko.wemouv.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.activities.EtabClickActivity;
import fr.corbiko.wemouv.models.Establishment;

public class EstablishmentViewHolder extends RecyclerView.ViewHolder {

    private final Context context;
    private final TextView name, distance;
    private final ImageView images;

    /* itemView est la vue correspondante à 1 card */
    public EstablishmentViewHolder(View itemView, Context context) {
        super(itemView);

        this.context = context;

        /* Récupère nos elements de la vue pour les manipuler */
        name =  itemView.findViewById(R.id.title_card);
        distance = itemView.findViewById(R.id.distance_card);
        images = itemView.findViewById(R.id.image_card);
    }


    /* Méthode qui permet de remplir la card en fonction d'un Establishment */
    public void display(final Establishment myEtab){
        name.setText(myEtab.getName());
        distance.setText("500 m");
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
