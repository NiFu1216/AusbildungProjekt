package model;

import java.sql.Date;
import java.sql.Time;

public class Seminar {

    private Date datum;
    private Time uhrzeit;
    private String kursName;

    public Seminar() {}

    public Seminar(Date datum, Time uhrzeit, String kursName) {

        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.kursName = kursName;
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

    public String getKursName() {
        return kursName;
    }

    public void setKursName(String kursName) {
        this.kursName = kursName;
    }
}