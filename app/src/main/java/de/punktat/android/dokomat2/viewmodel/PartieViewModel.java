package de.punktat.android.dokomat2.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;

import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Partie;

public class PartieViewModel extends ViewModel {
    private MutableLiveData<List<Partie>> mPartien;
    public PartieViewModel() {
        super();

    }

    public LiveData<List<Partie>> getPartie(Context context) {
        if (mPartien == null) {
            mPartien = new MutableLiveData<List<Partie>>();
            loadUsers(context);
        }
        return mPartien;
    }

    private void loadUsers(Context context) {
        AppDatabase mDb=AppDatabase.ger(context);
        // Do an asyncronous operation to fetch users.
    }
}
