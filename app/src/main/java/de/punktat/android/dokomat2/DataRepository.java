package de.punktat.android.dokomat2;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Partie;

/**
 * Repository handling the work with products and comments.
 */
public class DataRepository {

    private static DataRepository sInstance;

    private final AppDatabase mDatabase;
    private MediatorLiveData<List<Partie>> mObservablePartien;

    private DataRepository(final AppDatabase database) {
        mDatabase = database;
        mObservablePartien = new MediatorLiveData<>();

        mObservablePartien.addSource(mDatabase.partieDao().findAllNewestLD(),
                productEntities -> {
                    if (mDatabase.getDatabaseCreated().getValue() != null) {
                        mObservablePartien.postValue(productEntities);
                    }
                });
    }

    public static DataRepository getInstance(final AppDatabase database) {
        if (sInstance == null) {
            synchronized (DataRepository.class) {
                if (sInstance == null) {
                    sInstance = new DataRepository(database);
                }
            }
        }
        return sInstance;
    }

    /**
     * Get the list of products from the database and get notified when the data changes.
     */
    public LiveData<List<Partie>> getProducts() {
        return mObservablePartien;
    }

    public LiveData<Partie> loadPartie(final int productId) {
        return mDatabase.partieDao().loadById(productId);
    }

    public LiveData<List<Partie>> loadPartie(final int[] productId) {
        return mDatabase.partieDao().loadAllByIdsLD(productId);
    }

    public LiveData<Partie> getPartie(int mPartieId) {
        return mDatabase.partieDao().loadById(mPartieId);
    }

    public LiveData<List<Partie>> loadPartien(int[] mPartieId) {
        return mDatabase.partieDao().loadAllByIdsLD(mPartieId);
    }

    // public LiveData<List<CommentEntity>> loadComments(final int productId) {
    //   return mDatabase.commentDao().loadComments(productId);
    // }
}
