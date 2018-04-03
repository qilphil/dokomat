package de.punktat.android.dokomat2.viewmodel;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import de.punktat.android.dokomat2.BasicApp;
import de.punktat.android.dokomat2.DataRepository;
import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Partie;

public class PartieViewModel extends AndroidViewModel {
    private LiveData<List<Partie>> mPartien;
    public PartieViewModel(@NonNull Application application, DataRepository repository) {
        super(application);


        mPartien = repository.getProducts();

    }
    public PartieViewModel(@NonNull Application application, DataRepository repository, int mPartieId) {
        super(application);


        mPartien = repository.loadPartien(new int[] {mPartieId});

    }
    public LiveData<List<Partie>> getPartie(Context context, Application application) {
        if (mPartien == null) {
            mPartien = new MutableLiveData<List<Partie>>();
            loadUsers(context,application);
        }
        return mPartien;
    }

    private void loadUsers(Context context,Application application) {
        AppDatabase mDb = AppDatabase.getInstance(context,((BasicApp) application).getmAppExecutors());
        // Do an asyncronous operation to fetch users.
    }
    public static class Factory extends ViewModelProvider.NewInstanceFactory {

        @NonNull
        private final Application mApplication;

        private final int mProductId;

        private final DataRepository mRepository;

        public Factory(@NonNull Application application, int productId) {
            mApplication = application;
            mProductId = productId;
            mRepository = ((BasicApp) application).getRepository();
        }

        @Override
        public <T extends ViewModel> T create(Class<T> modelClass) {
            //noinspection unchecked
            return (T) new PartieViewModel(mApplication, mRepository, mProductId);
        }
    }
}
