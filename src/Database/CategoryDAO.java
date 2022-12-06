package Database;

import Model.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {

    public Category getCategory(int stuNum)  {
        //MOET GELINKT WORDEN AAN OWNER, MOET ER IN DE CATEGORY DATABASETABEL DAN OOK GEEN KOLOM USERID ZIJN FSO?
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT number, name, fullTime, graduate, summary "
                    + "FROM Students "
                    + "WHERE number = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,stuNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String name, summary;
            int number;
            boolean fullTime, graduate;

            if (srs.next()) {
                number = srs.getInt("number");
                name = srs.getString("name");

                fullTime = srs.getBoolean("fullTime");
                graduate = srs.getBoolean("graduate");
                summary = srs.getString("summary");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Student student = new Student(name, number, fullTime, graduate, summary);
            return student;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveCategory(Category category)  {
        Connection con = null;
        try {
            //Doet gelijk iets nutteloos?
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT categoryName "
                    + "FROM category "
                    + "WHERE categoryName = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1,category.getCategoryName());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                //nutteloos? idem causeklasse
                String sqlUpdate = "UPDATE category " +
                        "SET categoryName = ? ," +
                        "WHERE categoryName = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, s.getName());
                stmt2.setBoolean(2,s.isFullTime());
                stmt2.setBoolean(3, s.isGraduate());
                stmt2.setString(4, s.getSummary());
                stmt2.setInt(5, s.getNumber());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into category "
                        + "(categoryName) "
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

    public ArrayList<Category> getAllPossibleCategories()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT DISTINCT categoryName"
                    + "FROM category";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Category> categories = new ArrayList<Category>();
            while (srs.next())
                categories.add(getCategory(srs.getInt("categoryName")));
            return categories;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteCategory(Category category)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM category "
                    + "WHERE categoryName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,category.getCategoryName());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}