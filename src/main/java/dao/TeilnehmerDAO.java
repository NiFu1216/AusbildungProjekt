package dao;

import model.Teilnehmer;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeilnehmerDAO {

    public List<Teilnehmer> getAlleTeilnehmer() {

        List<Teilnehmer> liste = new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM Teilnehmer
                ORDER BY Kundennummer
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ){

            while(rs.next()) {
                liste.add(new Teilnehmer(rs.getString("SVNr"), rs.getInt("Kundennummer")));
            }

        } catch(Exception e) {
            e.printStackTrace();
        }

        return liste;
    }
}