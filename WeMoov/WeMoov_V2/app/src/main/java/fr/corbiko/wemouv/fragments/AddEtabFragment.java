package fr.corbiko.wemouv.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.corbiko.wemouv.R;

public class AddEtabFragment extends Fragment {



    public AddEtabFragment() {
        /* Constructeur vide mais doit etre car Fragment */
    }

    /* Méthode obligatoire (bien de le mettre) qui permet de creer l'activité */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    /* Quand la vue est créée */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    /* Méthode pour creer la vue du fragment */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_etab, container, false);
        return view;
    }

}