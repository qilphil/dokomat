package de.punktat.android.dokomat2.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Spieler {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name="first_name")
    private String firstName;
    @ColumnInfo(name="last_name")
    private String lastName;
    @ColumnInfo(name="name")
    private String Name;
    @ColumnInfo(name="short_name")
    private String shortName;
    @ColumnInfo(name="icon")
    private String Icon;

    @ColumnInfo(name="email")
    private String Email;
    @ColumnInfo(name="gender")
    private char gender;


    public Spieler() {

    }

    public Spieler(int id, String firstName, String lastName, String name, String shortName, String icon, String email, char gender) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        Name = name;
        this.shortName = shortName;
        Icon = icon;
        Email = email;
        this.gender = gender;
    }

    public Spieler(String firstName, String lastName, String name, String shortName, String email, char gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        Name = name;
        this.shortName = shortName;
        Email = email;
        this.gender = gender;
    }



    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getIcon() {
        return Icon;
    }

    public void setIcon(String icon) {
        Icon = icon;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
