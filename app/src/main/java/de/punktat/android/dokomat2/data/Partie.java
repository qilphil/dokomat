package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Date;
import java.util.Locale;

@Entity
public class Partie {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="start_time")
    @TypeConverters({DateTypeConverter.class})
    private Date startTime;
    @ColumnInfo(name="end_time")
    @TypeConverters({DateTypeConverter.class})
    private Date endTime;
    @ColumnInfo(name="location")
    private String location;
    @ColumnInfo(name="spieler_anzahl")
    private int spielerAnzahl;
    @ColumnInfo(name="spieler1_id")
    private int spieler1Id;
    @ColumnInfo(name="spieler2_id")
    private int spieler2Id;
    @ColumnInfo(name="spieler3_id")
    private int spieler3Id;
    @ColumnInfo(name="spieler4_id")
    private int spieler4Id;

    @ColumnInfo(name="comment")
    private String comment;
    @ColumnInfo(name="finished")
    private Boolean finished;
    @Ignore
    public Partie() {

    }
    @Ignore
    public Partie(Date startTime, String location) {
        this.startTime = startTime;
        this.location = location;
    }
    @Ignore
    public Partie(Date startTime, Date endTime, String location, int spielerAnzahl, int spieler1Id, int spieler2Id, int spieler3Id, int spieler4Id, String comment, Boolean finished) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.spielerAnzahl = spielerAnzahl;
        this.spieler1Id = spieler1Id;
        this.spieler2Id = spieler2Id;
        this.spieler3Id = spieler3Id;
        this.spieler4Id = spieler4Id;
        this.comment = comment;
        this.finished = finished;
    }

    public Partie(int id, Date startTime, Date endTime, String location, int spielerAnzahl, int spieler1Id, int spieler2Id, int spieler3Id, int spieler4Id, String comment, Boolean finished) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.spielerAnzahl = spielerAnzahl;
        this.spieler1Id = spieler1Id;
        this.spieler2Id = spieler2Id;
        this.spieler3Id = spieler3Id;
        this.spieler4Id = spieler4Id;
        this.comment = comment;
        this.finished = finished;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public String getStartTimeString() {

        DateFormat df = new SimpleDateFormat("dd.mm.yyyy HH:mm:ss");


        return df.format(startTime);
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSpielerAnzahl() {
        return spielerAnzahl;
    }

    public void setSpielerAnzahl(int spielerAnzahl) {
        this.spielerAnzahl = spielerAnzahl;
    }

    public int getSpieler1Id() {
        return spieler1Id;
    }

    public void setSpieler1Id(int spieler1Id) {
        this.spieler1Id = spieler1Id;
    }

    public int getSpieler2Id() {
        return spieler2Id;
    }

    public void setSpieler2Id(int spieler2Id) {
        this.spieler2Id = spieler2Id;
    }

    public int getSpieler3Id() {
        return spieler3Id;
    }

    public void setSpieler3Id(int spieler3Id) {
        this.spieler3Id = spieler3Id;
    }

    public int getSpieler4Id() {
        return spieler4Id;
    }

    public void setSpieler4Id(int spieler4Id) {
        this.spieler4Id = spieler4Id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Boolean getFinished() {
        return finished;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }
}
