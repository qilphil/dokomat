package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SpielerDao {
    @Query("SELECT * FROM spieler")
    List<Spieler> getAll();
    @Query("SELECT * FROM spieler WHERE id IN (:userIds)")
    List<Spieler> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM spieler WHERE "
            + "name LIKE :name LIMIT 1")
    Spieler findByName(String name);

    @Query("SELECT * FROM spieler WHERE name LIKE :namePat")
    List<Spieler> findAllByName(String namePat);

    @Insert
    void insertAll(Spieler... spielers);

    @Update
    void updateAll(Spieler... spielers);

    @Delete
    void delete(Spieler spieler);
}
