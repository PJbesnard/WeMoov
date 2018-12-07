package fr.corbiko.wemouv.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import fr.corbiko.wemouv.R;

public class FavFragment extends Fragment {

    public FavFragment() {
        /* Constructeur vide mais doit etre la */
    }

    /* Méthode obligatoire (bien de le mettre) qui permet de creer l'activité */
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /* Méthode pour creer la vue du fragment */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav, container, false);
    }
}
