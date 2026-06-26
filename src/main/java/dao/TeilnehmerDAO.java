package dao;

import model.Person;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TeilnehmerDAO {

    public List<Person> getAllePersonen() {

        List<Person> liste = new ArrayList<>();

        String sql =
                """
                SELECT *
                FROM Person
                ORDER BY Nachname, Vorname
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
            while(rs.next()){
                liste.add(
                        new Person (
                                rs.getString("SVNr"),
                                rs.getString("Vorname"),
                                rs.getString("Nachname"),
                                rs.getString("Postleitzahl"),
                                rs.getString("Ort"),
                                rs.getString("Strasse"),
                                rs.getString("Hausnummer"))
                );
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
        return liste;
    }

    public Person findePerson(String svnr){

        String sql =
                """
                SELECT *
                FROM Person
                WHERE SVNr = ?
                """;

        try(
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ){
            ps.setString(1, svnr);
            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                return new Person(
                        rs.getString("SVNr"),
                        rs.getString("Vorname"),
                        rs.getString("Nachname"),
                        rs.getString("Postleitzahl"),
                        rs.getString("Ort"),
                        rs.getString("Strasse"),
                        rs.getString("Hausnummer"));
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }
}