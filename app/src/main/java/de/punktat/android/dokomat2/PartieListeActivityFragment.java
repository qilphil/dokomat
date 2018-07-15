package de.punktat.android.dokomat2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Partie;

/**
 * A placeholder fragment containing a simple view.
 */
public class PartieListeActivityFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private PartieAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    public PartieListeActivityFragment() {
    }

    private AppDatabase mDb;

    @Override
    public void onResume() {
        super.onResume();
        Activity myAct = getActivity();
        if (myAct != null) {
            mDb = AppDatabase.getInstance(myAct.getApplicationContext(), ((BasicApp) myAct.getApplication()).getmAppExecutors());
        }

        // Inflate the menu; this adds items to the action bar if it is present.
        FloatingActionButton fab = myAct.findViewById(R.id.fab);
        fab.show();
        mRecyclerView = myAct.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new PartieAdapter(Partie -> {

        });

        new AsyncTask<Void, Void, List<Partie>>() {
            @Override
            protected List<Partie> doInBackground(Void... params) {
                return mDb.partieDao().findAllNewestS();

            }

            @Override
            protected void onPostExecute(List<Partie> partieData) {
                mAdapter.setPartieList(partieData);
            }
        }.execute();


        mRecyclerView.setAdapter(mAdapter);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_partie_liste, container, false);
    }
}
