package de.punktat.android.dokomat2;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;



import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import de.punktat.android.dokomat2.data.AppDatabase;
import de.punktat.android.dokomat2.data.Partie;
import de.punktat.android.dokomat2.data.PartieDao;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class DBPartieUnitTest {
    private PartieDao mPartieDao;
    private AppDatabase mDb;

    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        mDb = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        mPartieDao = mDb.partieDao();
    }

    @After
    public void closeDb() {
        mDb.close();
    }


    @Test
    public void table_update() {
        Partie tPartie = new Partie(new Date(),"Dingles");
        mPartieDao.insertAll(tPartie);
        Partie foundPartie = mPartieDao.findAllNewestS().get(0);
        assertEquals(foundPartie.getLocation(), "Dingles");
        String CommentText = "TestComment";
        foundPartie.setComment(CommentText);
        mPartieDao.updateAll(foundPartie);
        Partie changedPartie = mPartieDao.loadAllByIds(new int []{foundPartie.getId()}).get(0);
        assertEquals(CommentText,changedPartie.getComment()) ;

    }

    @Test
    public void table_insert() {
        Partie tPartie = new Partie(new Date(),"Dingles");
        mPartieDao.insertAll(tPartie);
        Partie foundPartie = mPartieDao.findAllNewestS().get(0);
        assertEquals(foundPartie.getLocation(), "Dingles");

    }

    @Test
    public void table_delete() {
        Partie tPartie = new Partie(new Date(),"Dingles");
        mPartieDao.insertAll(tPartie);
        Partie foundPartie = mPartieDao.findAllNewestS().get(0);
        assertEquals(foundPartie.getLocation(), "Dingles");
        mPartieDao.delete(foundPartie);

        List<Partie> changedPartieer = mPartieDao.loadAllByIds(new int[]{foundPartie.getId()});
        assertEquals(0, changedPartieer.size());

    }

    @Test
    public void table_getAll() {
        int iCount = 100;
        Partie[] aPartie = new Partie[iCount];
        for (int i = 0; i < iCount; i++) {
            aPartie[i] = new Partie(new Date(),"Dingles"+i);
        }
        mPartieDao.insertAll(aPartie);
        List<Partie> foundParties = mPartieDao.findAllNewestS();
        assertEquals(foundParties.size(), iCount);

    }
}