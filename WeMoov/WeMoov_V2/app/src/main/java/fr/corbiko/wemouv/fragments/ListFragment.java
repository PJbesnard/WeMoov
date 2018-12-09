package fr.corbiko.wemouv.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


import fr.corbiko.wemouv.activities.FiltreActivity;
import fr.corbiko.wemouv.activities.MapActivity;
import fr.corbiko.wemouv.R;
import fr.corbiko.wemouv.adapters.MyAdapter;
import fr.corbiko.wemouv.models.Establishment;

import static android.app.Activity.RESULT_OK;

public class ListFragment extends Fragment {

    private RecyclerView recyclerView;
    private RelativeLayout mapItem;
    private Button filtres;
    private static Bundle mBundleRecyclerViewState;
    private FirebaseFirestore database = FirebaseFirestore.getInstance();
    private CollectionReference establishmentRef = database.collection("establishment");
    private MyAdapter adapter;

    private List<Establishment> estab;

    // L'identifiant de notre requête vers filtre
    public final static int ID_FILTER = 0;
    // L'identifiant de la chaîne de caractères qui contient le résultat de l'intent
    public final static String BUTTONS = "RETOUR FILTRE";


    public ListFragment() {
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

        /*Toolbar toolbar = view.findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true); // affiche bouton back
            getSupportActionBar().setDisplayShowHomeEnabled(true); // garde boutton back lors du défilement

        }*/

        filtres = view.findViewById(R.id.filtres);
        filtres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO: FAIRE INTENT ENVOI ET RECEPTION DONNEE
                Intent intent = new Intent(getContext(), FiltreActivity.class);
                startActivityForResult(intent, ID_FILTER);
            }
        });

        mapItem = view.findViewById(R.id.map);
        mapItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* ENVPOYER LES DONNES ET CONTINUER ICI  */
               // Toast.makeText(getContext(), "Map clique", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MapActivity.class);
                intent.putParcelableArrayListExtra("list_estab", (ArrayList) estab);
                startActivity(intent);
            }
        });

      //  addEtabs();

        recyclerView = view.findViewById(R.id.my_recycler_view);

        recyclerView.setHasFixedSize(true); // optimise la taille de l'item par taille fixe

        /* définit l'agencement des items, ici avec un LinearLayout de façon verticale */
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        /* Aucune animation -> possible de creer,
         * ex: faire apparaitre item du haut vers le bas ou du bas vers le haut ou de droite a gauche etc */
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        if (adapter == null) {
            /* Requete qui trie par coordonnées */
            Query query = establishmentRef.orderBy("coord", Query.Direction.ASCENDING);

//            FirestoreRecyclerOptions<Establishment> options = new FirestoreRecyclerOptions.Builder<Establishment>()
//                    .setQuery(query, Establishment.class)
//                    .setLifecycleOwner(this)
//                    .build();
//
//            adapter = new EstablishmentAdapter(options);
            query.addSnapshotListener(new EventListener<QuerySnapshot>() {
                @Override
                public void onEvent(@javax.annotation.Nullable QuerySnapshot queryDocumentSnapshots, @javax.annotation.Nullable FirebaseFirestoreException e) {
                    if(e != null) {
                        return;
                    }
                    estab = queryDocumentSnapshots.toObjects(Establishment.class);
                    /* puis créer un MyAdapter, lui fournir notre liste d'etablissement.
                     * cet adapter servira à remplir notre recyclerview */
                    adapter = new MyAdapter(estab);
                    recyclerView.setAdapter(adapter);
                }
            });
        }
    }

    /* Méthode pour creer la vue du fragment */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        return view;
    }

    /* Méthode qui se lance lorsque ce fragment n'est plus visible mais que l'activité mère (MainActivity) existe toujours
     * On sauvegarde l'état du RecyclerView */
    @Override
    public void onPause() {
        super.onPause();
        mBundleRecyclerViewState = new Bundle();
        /* Stocke l'état du RecyclerView dans un colis */
        Parcelable savedRecyclerLayoutState = recyclerView.getLayoutManager().onSaveInstanceState();
        mBundleRecyclerViewState.putParcelable("ListFragment", savedRecyclerLayoutState);
    }


    /* Méthode qui se lance lorsque le fragment est re-visible
     * Par défaut le RecyclerView se recharge et raffiche les premiers éléments
     * Dans ce cas, on modifie la vue du RecyclerView par l'état sauvegardé précédement */
    @Override
    public void onResume() {
        super.onResume();
        if(mBundleRecyclerViewState != null) {
            /* Récupère le colis (l'état stocké) puis on modifie la vue du RecyclerView par l'état sauvegardé */
            Parcelable savedRecyclerLayoutState = mBundleRecyclerViewState.getParcelable("ListFragment");
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // On vérifie tout d'abord à quel intent on fait référence ici à l'aide de notre identifiant
        if (requestCode == ID_FILTER) {
            // On vérifie aussi que l'opération s'est bien déroulée
            if (resultCode == RESULT_OK) {
                // On affiche le bouton qui a été choisi
                Toast.makeText(getContext(), "Tu as cliqué sur le bouton " + data.getStringExtra(BUTTONS), Toast.LENGTH_SHORT).show();
            }
        }
    }



    /*  private void addEtabs() {
        Establishment estab1 = new Establishment("Le petit malin", "Joli bar", new GeoPoint(58.0, 58.0), "https://firebasestorage.googleapis.com/v0/b/wemouv-c638a.appspot.com/o/bar%2Fbar1.jpg?alt=media&token=c63776f2-babf-45f5-b1e1-8d1363a86980", "", "");
//        Establishment estab2 = new Establishment("A la pêche aux moules", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar1, 0, 0);
//        Establishment estab3 = new Establishment("Suzette fais moi des crêpes", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar2, 0, 0);
//        Establishment estab4 = new Establishment("Akouna ma tata", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar3, 0, 0);
//        Establishment estab5 = new Establishment("Corbi & Ko", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar4, 0, 0);
//        Establishment estab6 = new Establishment("Koolkool et Molokoloche", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar5, 0, 0);
//        Establishment estab7 = new Establishment("L'Apé-Rôt", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar6, 0, 0);
//        Establishment estab8 = new Establishment("Alcol'Eau", "Joli bar", new GeoPoint(58.0, 58.0), R.drawable.bar7, 0, 0);
//        establishments.add(estab1);
//        establishments.add(estab2);
//        establishments.add(estab3);
//        establishments.add(estab4);
//        establishments.add(estab5);
//        establishments.add(estab6);
//        establishments.add(estab7);
//        establishments.add(estab8);

        CollectionReference EstabRef = database.collection("establishment");
        // Create collection if not exist and add a document with ID name establishment
        EstabRef.add(estab1);
        // Just add a new document with ID name establishment
//        EstabRef.add(estab2);
//        EstabRef.add(estab3);
//        EstabRef.add(estab4);
//        EstabRef.add(estab5);
//        EstabRef.add(estab6);
//        EstabRef.add(estab7);
//        EstabRef.add(estab8);

        /*establishments.add(new Establishment("A la pêche aux moules", "Joli bar", 56.0, 56.0, R.drawable.bar1, 0, 0));
        establishments.add(new Establishment("Suzette fais moi des crêpes", "Joli bar", 56.0, 56.0, R.drawable.bar2, 0, 0));
        establishments.add(new Establishment("Akouna ma tata", "Joli bar", 56.0, 56.0, R.drawable.bar3, 0, 0));
        establishments.add(new Establishment("Corbi & Ko", "Joli bar", 56.0, 56.0, R.drawable.bar4, 0, 0));
        establishments.add(new Establishment("Koolkool et Molokoloche", "Joli bar", 56.0, 56.0, R.drawable.bar5, 0, 0));
        establishments.add(new Establishment("L'Apé-Rôt", "Joli bar", 56.0, 56.0, R.drawable.bar6, 0, 0));
        establishments.add(new Establishment("Alcol'Eau", "Joli bar", 56.0, 56.0, R.drawable.bar7, 0, 0));*/
    //}

}
