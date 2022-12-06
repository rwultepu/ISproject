package Database;


import Model.Cause;

import java.sql.*;
import java.util.ArrayList;

public class CauseDAO {
    public static void createCauseTable() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `cause` (\n"
                    + "  `causeName` varchar(50) NOT NULL,\n"
                    + "  PRIMARY KEY (`causeName`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<String> getAllPossibleCauses()  {
        //MOET HIER GEEN METHODE IN DIE DUPLICATES VERWIJDERT? Heb 'DISTINCT' toegevoegd bij select hiervoor?

        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT DISTINCT causeName "
                    + "FROM cause";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<String> causes = new ArrayList<>();
            while (srs.next())
                causes.add(srs.getString("causeName"));
            return causes;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }


    //DeleteCause, als we deze houden moeten we ook een saveCause toevoegen!!
    public void deleteCause(Cause cause)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM cause "
                    + "WHERE causeName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cause.getCauseName());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}
