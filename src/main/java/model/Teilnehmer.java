package model;

public class Teilnehmer {

    private String svnr;
    private int kundennummer;

    public Teilnehmer() {}

    public Teilnehmer(
            String svnr,
            int kundennummer) {

        this.svnr = svnr;
        this.kundennummer = kundennummer;
    }

    public String getSvnr() {
        return svnr;
    }

    public void setSvnr(String svnr) {
        this.svnr = svnr;
    }

    public int getKundennummer() {
        return kundennummer;
    }

    public void setKundennummer(int kundennummer) {
        this.kundennummer = kundennummer;
    }
}