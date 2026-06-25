package dao;

import model.Seminar;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SeminarDAO {

    public List<String> getAlleKurse() {

        List<String> kurse =
                new ArrayList<>();

        String sql = "SELECT Name FROM Kurs ORDER BY Name";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {
                kurse.add(rs.getString("Name"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return kurse;
    }

    public List<String> getAlleSprachen() {

        List<String> sprachen = new ArrayList<>();

        String sql = "SELECT Bezeichnung FROM Sprache";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while(rs.next()) {
                sprachen.add(rs.getString("Bezeichnung"));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return sprachen;
    }

    public List<Seminar> findeSeminare(String kurs, String sprache) {

        List<Seminar> liste = new ArrayList<>();

        String sql =
                """
                SELECT DISTINCT
                       s.Datum,
                       s.Uhrzeit,
                       sk.Kurs_Name
                FROM Seminar s
    
                JOIN Seminar_Kurs sk
                    ON s.Datum = sk.Seminar_Datum
                   AND s.Uhrzeit = sk.Seminar_Uhrzeit
    
                JOIN Bildet_aus ba
                    ON ba.Seminar_Datum = s.Datum
                   AND ba.Seminar_Uhrzeit = s.Uhrzeit
    
                JOIN Haelt_ab h
                    ON h.Ausbilder = ba.Ausbilder
                   AND h.Kurs = sk.Kurs_Name
    
                WHERE sk.Kurs_Name = ?
                  AND h.Sprache = ?
                """;


        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){

            ps.setString(1, kurs);
            ps.setString(2, sprache);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {

                liste.add(new Seminar(
                                rs.getDate("Datum"),
                                rs.getTime("Uhrzeit"),
                                rs.getString("Kurs_Name"))
                );
            }

        } catch(Exception e){
            e.printStackTrace();
        }

        return liste;
    }
}