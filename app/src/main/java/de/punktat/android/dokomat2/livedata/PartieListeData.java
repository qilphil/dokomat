package de.punktat.android.dokomat2.livedata;

import android.arch.lifecycle.LiveData;

import de.punktat.android.dokomat2.data.Partie;
import de.punktat.android.dokomat2.data.PartieDao;

public class PartieListeData extends LiveData<Partie> {

private PartieDao mPartieManager;

    @Override
    protected void onActive() {
        mPartieManager.getAll(mListener);
    }

    @Override
    protected void onInactive() {
        mPartieManager.removeUpdates(mListener);
    }
}
