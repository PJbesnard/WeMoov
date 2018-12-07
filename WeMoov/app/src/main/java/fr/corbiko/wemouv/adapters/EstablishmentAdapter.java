package fr.corbiko.wemouv.adapters;

import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;


import java.util.ArrayList;

import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.models.Establishment;

public class EstablishmentAdapter extends FirestoreRecyclerAdapter<Establishment, EstablishmentViewHolder> {

    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EstablishmentAdapter(@NonNull FirestoreRecyclerOptions<Establishment> options) {
        super(options);
    }

    /* Méthode qui permet de remplir une card avec le texte/image de chaque Establishment */
    @Override
    protected void onBindViewHolder(@NonNull EstablishmentViewHolder holder, int position, @NonNull Establishment model) {
        holder.display(model);

    }


    /* Méthode qui permet de créer les viewHolder
     *  et indiquer la vue à inflater (à partir des layout xml) */
    @NonNull
    @Override
    public EstablishmentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item,viewGroup,false);
        return new EstablishmentViewHolder(view, viewGroup.getContext());
    }

}
