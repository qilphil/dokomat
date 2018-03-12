/*
 * Copyright 2017, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.punktat.android.dokomat2.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import de.punktat.android.dokomat2.BasicApp;
import de.punktat.android.dokomat2.data.Partie;

public class PartieListViewModel extends AndroidViewModel {

    // MediatorLiveData can observe other LiveData objects and react on their emissions.
    private final MediatorLiveData<List<Partie>> mObservablePartien;

    public PartieListViewModel(Application application) {
        super(application);

        mObservablePartien = new MediatorLiveData<>();
        // set by default null, until we get data from the database.
        mObservablePartien.setValue(null);

        LiveData<List<Partie>> Partien = ((BasicApp) application).getDatabase()
                .partieDao().findAllNewestLD();

        // observe the changes of the Partien from the database and forward them
        mObservablePartien.addSource(Partien, mObservablePartien::setValue);
    }

    /**
     * Expose the LiveData Partien query so the UI can observe it.
     */
    public LiveData<List<Partie>> getPartien() {
        return mObservablePartien;
    }
}
