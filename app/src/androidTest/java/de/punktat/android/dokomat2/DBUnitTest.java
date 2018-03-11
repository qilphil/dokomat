package de.punktat.android.dokomat2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Spieler;
import de.punktat.android.dokomat2.data.SpielerDao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DBUnitTest {
    private SpielerDao mSpielerDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mSpielerDao = mDb.spielerDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }


    @Test
    public void table_update() {
        String spielerName = "spielerNAme2";
        String newEmail = "spieler@example.com";
        Spieler tSpieler = new Spieler("FirstNAme", "LastName", spielerName, "FL", "test@email.com", 'm');
        mSpielerDao.insertAll(tSpieler);
        Spieler foundSpieler = mSpielerDao.findByName(spielerName);
        assertEquals(foundSpieler.getEmail(), tSpieler.getEmail());

        foundSpieler.setEmail(newEmail);
        mSpielerDao.updateAll(foundSpieler);
        Spieler changedSpieler = mSpielerDao.findByName(spielerName);
        assertEquals(changedSpieler.getEmail(), newEmail);

    }

    @Test
    public void table_insert() {
        String spielerName = "spielerNAme";
        Spieler tSpieler = new Spieler("FirstNAme", "LastName", spielerName, "FL", "test@email.com", 'm');
        mSpielerDao.insertAll(tSpieler);
        Spieler foundSpieler = mSpielerDao.findByName(spielerName);
        assertEquals(foundSpieler.getEmail(), tSpieler.getEmail());

    }

    @Test
    public void table_delete() {
        String spielerName = "spielerNAme3";
        Spieler tSpieler = new Spieler("FirstNAme", "LastName", spielerName, "FL", "test@email.com", 'm');
        mSpielerDao.insertAll(tSpieler);
        Spieler foundSpieler = mSpielerDao.findByName(spielerName);
        mSpielerDao.delete(foundSpieler);

        Spieler changedSpieler = mSpielerDao.findByName(spielerName);
        assertNull(changedSpieler);

    }

    @Test
    public void table_getAll() {
        String spielerName = "spielerName";
        int iCount = 100;
        Spieler[] aSpieler = new Spieler[iCount];
        for (int i = 0; i < iCount; i++) {
            aSpieler[i] = new Spieler("FirstNAme", "LastName", spielerName + i, "FL", "test@email.com", 'm');
        }
        mSpielerDao.insertAll(aSpieler);
        List<Spieler> foundSpieler = mSpielerDao.findAllByName(spielerName + "%");
        assertEquals(foundSpieler.size(), iCount);

    }
}