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

package de.punktat.android.dokomat2;

import android.app.Application;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.threads.AppExecutors;


/**
 * Android Application class. Used for accessing singletons.
 */
public class BasicApp extends Application {

    public AppExecutors getmAppExecutors() {
        return mAppExecutors;
    }

    private AppExecutors mAppExecutors;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppExecutors = new AppExecutors();
    }

    public AppDatabase getDatabase() {
        return AppDatabase.getInstance(this, mAppExecutors);
    }
    public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }
    /*public DataRepository getRepository() {
        return DataRepository.getInstance(getDatabase());
    }*/
}
