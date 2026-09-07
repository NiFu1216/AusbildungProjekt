package dao;

import model.Person;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class KundenDAO {

    public List<Person> sucheKunden(String suchbegriff) {
        List<Person> liste = new ArrayList<>();
        String sql = """
                SELECT * FROM person 
                WHERE LOWER(nachname) LIKE ? OR LOWER(vorname) LIKE ? OR svnr LIKE ?
                ORDER BY nachname, vorname
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            String term = "%" + (suchbegriff == null ? "" : suchbegriff.toLowerCase()) + "%";
            ps.setString(1, term);
            ps.setString(2, term);
            ps.setString(3, term);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                liste.add(mapResultSetToPerson(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Person getKundeBySvnr(String svnr) {
        String sql = "SELECT * FROM person WHERE svnr = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, svnr);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToPerson(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean kundenAnlegen(Person p) {
        String sqlPerson = """
                INSERT INTO person (svnr, vorname, nachname, postleitzahl, ort, strasse, hausnummer)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sqlPerson)) {

            ps.setString(1, p.getSvnr());
            ps.setString(2, p.getVorname());
            ps.setString(3, p.getNachname());
            ps.setString(4, p.getPlz());
            ps.setString(5, p.getOrt());
            ps.setString(6, p.getStrasse());
            ps.setString(7, p.getHausnummer());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean kundenBearbeiten(Person p) {
        String sql = """
                UPDATE person 
                SET vorname = ?, nachname = ?, postleitzahl = ?, ort = ?, strasse = ?, hausnummer = ?
                WHERE svnr = ?
                """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, p.getVorname());
            ps.setString(2, p.getNachname());
            ps.setString(3, p.getPlz());
            ps.setString(4, p.getOrt());
            ps.setString(5, p.getStrasse());
            ps.setString(6, p.getHausnummer());
            ps.setString(7, p.getSvnr());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    private Person mapResultSetToPerson(ResultSet rs) throws SQLException {
        return new Person(
                rs.getString("svnr"),
                rs.getString("vorname"),
                rs.getString("nachname"),
                rs.getString("postleitzahl"),
                rs.getString("ort"),
                rs.getString("strasse"),
                rs.getString("hausnummer")
        );
    }
}