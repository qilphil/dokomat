package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface SpielDao {
    @Query("SELECT * FROM spiel")
    List<Spiel> getAll();

    @Query("SELECT * FROM spiel WHERE id IN (:spielIds)")
    List<Spiel> loadAllByIds(int[] spielIds);

    @Query("SELECT * FROM spiel WHERE partie_id = :partie_id ORDER BY sequence_nr")
    List<Spiel> findByPartie(int partie_id);


    @Insert
    void insertAll(Spiel... spiele);

    @Update
    void updateAll(Spiel... spiele);

    @Delete
    void delete(Spiel spiel);
}
