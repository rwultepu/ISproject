package Database;


import Model.Category;
import Model.Cause;

import java.sql.*;
import java.util.ArrayList;

public class CauseDAO {


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

    public void saveCause (Cause cause)  {
        Connection con = null;
        try {
            //Doet gelijk iets nutteloos?
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT causeName "
                    + "FROM cause "
                    + "WHERE causeName = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1,cause.getCauseName());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE cause " +
                        "SET causeName = ? ," +
                        "WHERE causeName = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, s.getName());
                stmt2.setBoolean(2,s.isFullTime());
                stmt2.setBoolean(3, s.isGraduate());
                stmt2.setString(4, s.getSummary());
                stmt2.setInt(5, s.getNumber());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into cause "
                        + "(causeName) "
                        + "VALUES (?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, category.getCategoryName());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
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
