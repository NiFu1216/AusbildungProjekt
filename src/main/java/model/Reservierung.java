package model;

import java.sql.Date;
import java.sql.Time;

public class Reservierung {

    private int nummer;

    private String teilnehmer;

    private Date datum;

    private Time uhrzeit;

    public Reservierung() {}

    public Reservierung(
            int nummer,
            String teilnehmer,
            Date datum,
            Time uhrzeit) {

        this.nummer = nummer;
        this.teilnehmer = teilnehmer;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public String getTeilnehmer() {
        return teilnehmer;
    }

    public void setTeilnehmer(String teilnehmer) {
        this.teilnehmer = teilnehmer;
    }

    public Date getDatum() {
        return datum;
    }

    public void setDatum(Date datum) {
        this.datum = datum;
    }

    public Time getUhrzeit() {
        return uhrzeit;
    }

    public void setUhrzeit(Time uhrzeit) {
        this.uhrzeit = uhrzeit;
    }
}