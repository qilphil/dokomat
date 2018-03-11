package de.punktat.android.dokomat2;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class PartieListeActivityFragment extends Fragment {

    public PartieListeActivityFragment() {
    }
    @Override
    public void  onResume() {
        super.onResume();
        // Inflate the menu; this adds items to the action bar if it is present.
        FloatingActionButton fab = (FloatingActionButton) getActivity().findViewById(R.id.fab);
        fab.show();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_partie_liste, container, false);
    }
}
