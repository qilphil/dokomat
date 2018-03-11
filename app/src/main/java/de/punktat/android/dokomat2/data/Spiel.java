package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

@Entity
public class Spiel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="partie_id")
    private int partieId;
    @ColumnInfo(name="runde")
    private int runde;
    @ColumnInfo(name="geber")
    private int geber;
    @ColumnInfo(name="sequence_nr")
    private int sequenceNr;
    @ColumnInfo(name="vorbehalte")
    private String vorbehalte;
    @ColumnInfo(name="solo")
    private Boolean solo;
    @ColumnInfo(name="spiel_time")
    @TypeConverters({DateTypeConverter.class})
    private Date spielTime;
    @ColumnInfo(name="punkte1")
    private int punkte1;
    @ColumnInfo(name="punkte2")
    private int punkte2;
    @ColumnInfo(name="punkte3")
    private int punkte3;
    @ColumnInfo(name="punkte4")
    private int punkte4;
    @Ignore
    Spiel() {

    }
    @Ignore
    public Spiel(int partieId, int runde, int geber, int sequenceNr) {
        this.partieId = partieId;
        this.runde = runde;
        this.geber = geber;
        this.sequenceNr = sequenceNr;
    }

    public Spiel(int id, int partieId, int runde, int geber, int sequenceNr, String vorbehalte, Boolean solo, Date spielTime, int punkte1, int punkte2, int punkte3, int punkte4) {
        this.id = id;
        this.partieId = partieId;
        this.runde = runde;
        this.geber = geber;
        this.sequenceNr = sequenceNr;
        this.vorbehalte = vorbehalte;
        this.solo = solo;
        this.spielTime = spielTime;
        this.punkte1 = punkte1;
        this.punkte2 = punkte2;
        this.punkte3 = punkte3;
        this.punkte4 = punkte4;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPartieId() {
        return partieId;
    }

    public void setPartieId(int partieId) {
        this.partieId = partieId;
    }

    public int getRunde() {
        return runde;
    }

    public void setRunde(int runde) {
        this.runde = runde;
    }

    public int getGeber() {
        return geber;
    }

    public void setGeber(int geber) {
        this.geber = geber;
    }

    public int getSequenceNr() {
        return sequenceNr;
    }

    public void setSequenceNr(int sequenceNr) {
        this.sequenceNr = sequenceNr;
    }

    public String getVorbehalte() {
        return vorbehalte;
    }

    public void setVorbehalte(String vorbehalte) {
        this.vorbehalte = vorbehalte;
    }

    public Boolean getSolo() {
        return solo;
    }

    public void setSolo(Boolean solo) {
        this.solo = solo;
    }

    public Date getSpielTime() {
        return spielTime;
    }

    public void setSpielTime(Date spielTime) {
        this.spielTime = spielTime;
    }

    public int getPunkte1() {
        return punkte1;
    }

    public void setPunkte1(int punkte1) {
        this.punkte1 = punkte1;
    }

    public int getPunkte2() {
        return punkte2;
    }

    public void setPunkte2(int punkte2) {
        this.punkte2 = punkte2;
    }

    public int getPunkte3() {
        return punkte3;
    }

    public void setPunkte3(int punkte3) {
        this.punkte3 = punkte3;
    }

    public int getPunkte4() {
        return punkte4;
    }

    public void setPunkte4(int punkte4) {
        this.punkte4 = punkte4;
    }
}
