package model;

public class ReservierungsAnzeige {

    private int nummer;

    private String vorname;

    private String nachname;

    private String datum;

    private String uhrzeit;

    private String kurs;

    public ReservierungsAnzeige(int nummer, String vorname, String nachname, String datum, String uhrzeit, String kurs) {
        this.nummer = nummer;
        this.vorname = vorname;
        this.nachname = nachname;
        this.datum = datum;
        this.uhrzeit = uhrzeit;
        this.kurs = kurs;
    }

    public int getNummer() {
        return nummer;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getDatum() {
        return datum;
    }

    public String getUhrzeit() {
        return uhrzeit;
    }

    public String getKurs() {
        return kurs;
    }
}