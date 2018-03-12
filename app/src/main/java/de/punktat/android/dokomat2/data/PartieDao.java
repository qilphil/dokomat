package de.punktat.android.dokomat2.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface PartieDao {
    @Query("SELECT * FROM partie")
    List<Partie> getAll();

    @Query("SELECT * FROM partie WHERE id IN (:partieIds)")
    List<Partie> loadAllByIds(int[] partieIds);


    @Query("SELECT * FROM partie ORDER BY start_time DESC")
    List<Partie> findAllNewestS();

    @Query("SELECT * FROM partie ORDER BY start_time DESC")
    LiveData<List<Partie>> findAllNewestLD();


    @Insert
    void insertAll(Partie... partien);

    @Update
    void updateAll(Partie... partien);

    @Delete
    void delete(Partie partie);
}
