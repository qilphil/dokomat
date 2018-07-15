package de.punktat.android.dokomat2.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.Date;
import java.util.List;

import de.punktat.android.dokomat2.BasicApp;
import de.punktat.android.dokomat2.threads.AppExecutors;


@Database(entities = {Spieler.class, Spiel.class, Partie.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase sInstance;
    private static final String DATABASE_NAME = "DokoMat2";
    private final MutableLiveData<Boolean> mIsDatabaseCreated = new MutableLiveData<>();


    private static AppDatabase buildDatabase(final Context appContext,
                                             final AppExecutors executors) {
        return Room.databaseBuilder(appContext, AppDatabase.class, DATABASE_NAME)
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        executors.diskIO().execute(() -> {
                            // Add a delay to simulate a long-running operation
                            addDelay();
                            // Generate the data for pre-population
                            AppDatabase database = AppDatabase.getInstance(appContext, executors);

                            sInstance.preFillData(database);

                            // notify that the database was created and it's ready to be used
                            database.setDatabaseCreated();
                        }
                        );

                    }
                }).build();

    }

    private void setDatabaseCreated() {
        mIsDatabaseCreated.postValue(true);
    }

    public static AppDatabase getInstance(final Context context, final AppExecutors executors) {
        if (sInstance == null) {
            synchronized (AppDatabase.class) {
                if (sInstance == null) {
                    sInstance = buildDatabase(context.getApplicationContext(), executors);
                    sInstance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return sInstance;
    }

    private void updateDatabaseCreated(final Context context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDatabaseCreated();
        }
    }

    private static void addDelay() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException ignored) {
        }
    }

    public abstract SpielerDao spielerDao();

    public abstract PartieDao partieDao();

    public abstract SpielDao spielDao();

    public static void preFillData(AppDatabase db) {
        SpielerDao sd = db.spielerDao();
        Spieler[] newSpieler = new Spieler[]{
                new Spieler("Philipp", "Lutz", "Phil", "PL", "atpunkt@punktat.de", 'm'),
                new Spieler("Markus", "Eickmann", "Eiger", "ME", "markus@eickmann.de", 'm'),
                new Spieler("Birgit", "Eickmann", "Birgit", "BE", "birgit@punktat.de", 'f'),
                new Spieler("Andreas", "Nitsche", "Nitsche", "AN", "mim@mcmurksens.de", 'm')
        };
        sd.insertAll(newSpieler);
        List<Spieler> allSpieler = sd.getAll();
        int newPartieCount = 6;
        int off = 0;
        Partie[] newPartien = new Partie[newPartieCount];
        Date now = new Date();
        Date now2 = new Date();
        for (int i = 0; i < newPartieCount; i++) {
            now.setDate((now.getDay() - 1) % 28 + 1);
            now2.setDate((now2.getDay() - 1) % 28 + 1);
            now2.setHours(now.getHours() + 3);

            newPartien[i] = new Partie(now, now2, "Dingle's", 4,
                    GetNextPlayerId(allSpieler, off++),
                    GetNextPlayerId(allSpieler, off++),
                    GetNextPlayerId(allSpieler, off++),
                    GetNextPlayerId(allSpieler, off++),
                    "Kommentar 1", true);

        }
        db.partieDao().insertAll(newPartien);


    }
    public LiveData<Boolean> getDatabaseCreated() {
        return mIsDatabaseCreated;
    }
    private static int GetNextPlayerId(List<Spieler> aSpieler, int off) {
        Spieler selSPiel = aSpieler.get(off % aSpieler.size());
        return selSPiel.getId();


    }
}
