package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


@Database(entities = {Spieler.class,Spiel.class,Partie.class} , version = 1 )
public abstract class AppDatabase extends RoomDatabase {
    public abstract SpielerDao spielerDao();
    public abstract PartieDao partieDao();
    public abstract SpielDao spielDao();
}
