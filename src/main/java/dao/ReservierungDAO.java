package dao;

import model.Reservierung;
import model.ReservierungsAnzeige;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReservierungDAO {

    public void reservieren(Reservierung r) throws SQLException {

        String sql =
                """
                INSERT INTO Reservierung
                (
                    Reservierungsnummer,
                    Teilnehmer,
                    Seminar_Datum,
                    Seminar_Uhrzeit
                )
                VALUES
                (?,?,?,?)
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){

            ps.setInt(1, r.getNummer());
            ps.setString(2, r.getTeilnehmer());
            ps.setDate(3, r.getDatum());
            ps.setTime(4, r.getUhrzeit());

            ps.executeUpdate();
        }
    }

    public int naechsteNummer() throws SQLException {

        String sql =
                """
                SELECT
                COALESCE(
                    MAX(
                        Reservierungsnummer
                    ),
                    0
                ) + 1 AS id
                FROM Reservierung
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ){
            rs.next();
            return rs.getInt("id");
        }
    }

    public List<Reservierung> alleReservierungen() throws SQLException {

        List<Reservierung> liste = new ArrayList<>();

        String sql = "SELECT * FROM Reservierung";

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ){
            while(rs.next()){
                liste.add(new Reservierung(
                                rs.getInt("Reservierungsnummer"),
                                rs.getString("Teilnehmer"),
                                rs.getDate("Seminar_Datum"),
                                rs.getTime("Seminar_Uhrzeit"))
                );
            }
        }
        return liste;
    }

    public List<ReservierungsAnzeige> alleReservierungenMitNamen() throws SQLException {

        List<ReservierungsAnzeige> liste = new ArrayList<>();

        String sql =
                """
                SELECT
                    r.Reservierungsnummer,
                    p.Vorname,
                    p.Nachname,
                    r.Seminar_Datum,
                    r.Seminar_Uhrzeit,
                    sk.Kurs_Name
                FROM Reservierung r
    
                JOIN Teilnehmer t
                    ON r.Teilnehmer =
                       t.SVNr
    
                JOIN Person p
                    ON t.SVNr =
                       p.SVNr
    
                JOIN Seminar_Kurs sk
                    ON sk.Seminar_Datum =
                       r.Seminar_Datum
    
                   AND sk.Seminar_Uhrzeit =
                       r.Seminar_Uhrzeit
    
                ORDER BY
                    r.Reservierungsnummer
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ){
            while(rs.next()){
                liste.add(
                        new ReservierungsAnzeige(
                                rs.getInt("Reservierungsnummer"),
                                rs.getString("Vorname"),
                                rs.getString("Nachname"),
                                rs.getString("Seminar_Datum"),
                                rs.getString("Seminar_Uhrzeit"),
                                rs.getString("Kurs_Name"))
                );
            }
        }
        return liste;
    }
}