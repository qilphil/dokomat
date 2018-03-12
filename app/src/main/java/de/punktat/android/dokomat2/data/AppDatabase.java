package de.punktat.android.dokomat2.data;

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

    public synchronized static AppDatabase get(Context context) {
        if (sInstance == null) {
            sInstance = buildDatabase(context);
        }
        return sInstance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "DokoMat")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        sInstance.preFillData();
                    }
                }).build();

    }

    public static AppDatabase getInstance(BasicApp basicApp, AppExecutors mAppExecutors) {
        return sInstance;
    }

    public abstract SpielerDao spielerDao();

    public abstract PartieDao partieDao();

    public abstract SpielDao spielDao();

    private void preFillData() {
        SpielerDao sd = spielerDao();
        List<Spieler> checkList = sd.getAll();
        if (checkList.size() < 4) {
            Spieler[] newSpieler = new Spieler[]{
                    new Spieler("Philipp", "Lutz", "Phil", "PL", "atpunkt@punktat.de", 'm'),
                    new Spieler("Markus", "Eickmann", "Eiger", "ME", "markus@eickmann.de", 'm'),
                    new Spieler("Birgit", "Eickmann", "Birgit", "BE", "birgit@punktat.de", 'f'),
                    new Spieler("Andreas", "Nitsche", "Nitsche", "AN", "mim@mcmurksens.de", 'm')
            };
            sd.insertAll(newSpieler);
        }
        List<Spieler> allSpieler=sd.getAll();
        PartieDao pd= partieDao();
        List<Partie> checkPartien = pd.findAllNewestS();
        if (checkPartien.size()<3){
            int newPartieCount=6;
            int off=0;
            Partie[] newPartien = new Partie[newPartieCount];
            Date now = new Date();
            Date now2 = new Date();
            for (int i=0;i<newPartieCount;i++) {
                now.setDate((now.getDay()-1)%28+1);
                now2.setDate((now2.getDay()-1)%28+1);
                now2.setHours(now.getHours()+3);

                newPartien[i]=new Partie(now, now2,"Dingle's",4,
                        GetNextPlayerId(allSpieler,off++),
                        GetNextPlayerId(allSpieler,off++),
                        GetNextPlayerId(allSpieler,off++),
                        GetNextPlayerId(allSpieler,off++),
                        "Kommentar 1",true);

            }
            partieDao().insertAll(newPartien);

        }

    }

    private int GetNextPlayerId(List<Spieler> aSpieler,int off){
        Spieler selSPiel=aSpieler.get(off%aSpieler.size());
        return selSPiel.getId();


    }
}
