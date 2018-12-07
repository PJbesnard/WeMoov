package fr.corbiko.wemouv.adapters;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.models.Establishment;

public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {

    private List<Establishment> establishments;

    /* Constructeur prenant en entrée un ArrayList */
    public MyAdapter(List<Establishment> etabList) {
        this.establishments = etabList;
    }

    /* Méthode qui permet de créer les viewHolder
    *  et indiquer la vue à inflater (à partir des layout xml) */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int itemType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item,viewGroup,false);
        return new MyViewHolder(view, viewGroup.getContext());
    }

    /* Méthode qui permet de remplir une card avec le texte/image de chaque Establishment */
    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        Establishment myEtab = establishments.get(position);
        myViewHolder.display(myEtab);
    }

    /* Méthode qui retourne le nombre d'élément a afficher */
    @Override
    public int getItemCount() {
        return establishments.size();
    }

}
