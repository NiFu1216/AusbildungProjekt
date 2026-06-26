package model;

public class Person {

    private String svnr;
    private String vorname;
    private String nachname;
    private String plz;
    private String ort;
    private String strasse;
    private String hausnummer;

    public Person(String svnr, String vorname, String nachname, String plz, String ort, String strasse, String hausnummer) {

        this.svnr = svnr;
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;

    }

    public String getSvnr(){ return svnr; }

    public String getVorname(){ return vorname; }

    public String getNachname(){ return nachname; }

    public String getPlz() { return plz; }

    public String getOrt() { return ort; }

    public String getStrasse() { return strasse; }

    public String getHausnummer() { return hausnummer; }
}