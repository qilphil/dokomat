package de.punktat.android.dokomat2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Spiel;
import de.punktat.android.dokomat2.data.SpielDao;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DBSpielUnitTest {
    private SpielDao mSpielDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mSpielDao = mDb.spielDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }


    @Test
    public void table_update() {
        String spielerName = "spielerNAme2";
        String newEmail = "spieler@example.com";
        Spiel tSpiel = new Spiel(1, 1, 1, 1);
        mSpielDao.insertAll(tSpiel);
        Spiel foundSpiel = mSpielDao.findByPartie(1).get(0);
        assertEquals(foundSpiel.getSequenceNr(), 1);

        foundSpiel.setPunkte1(2);
        mSpielDao.updateAll(foundSpiel);
        Spiel changedSpieler = mSpielDao.findByPartie(1).get(0);
        assertEquals(changedSpieler.getPunkte1(), 2);

    }

    @Test
    public void table_insert() {
        String spielerName = "spielerNAme2";
        String newEmail = "spieler@example.com";
        Spiel tSpiel = new Spiel(1, 1, 1, 1);
        mSpielDao.insertAll(tSpiel);
        Spiel foundSpiel = mSpielDao.findByPartie(1).get(0);
        assertEquals(foundSpiel.getSequenceNr(), 1);

    }

    @Test
    public void table_delete() {
        String spielerName = "spielerNAme2";
        String newEmail = "spieler@example.com";
        Spiel tSpiel = new Spiel(1, 1, 1, 1);
        mSpielDao.insertAll(tSpiel);
        Spiel foundSpiel = mSpielDao.findByPartie(1).get(0);
        assertEquals(foundSpiel.getSequenceNr(), 1);
        mSpielDao.delete(foundSpiel);

        List<Spiel> changedSpieler = mSpielDao.loadAllByIds(new int[]{foundSpiel.getId()});
        assertEquals(0, changedSpieler.size());

    }

    @Test
    public void table_getAll() {
        String spielerName = "spielerName";
        int iCount = 100;
        Spiel[] aSpiel = new Spiel[iCount];
        for (int i = 0; i < iCount; i++) {
            aSpiel[i] = new Spiel(1,i/4+1,i%4+1,i);
        }
        mSpielDao.insertAll(aSpiel);
        List<Spiel> rSpiel = mSpielDao.findByPartie(1);
        assertEquals(rSpiel.size(), iCount);

    }
}