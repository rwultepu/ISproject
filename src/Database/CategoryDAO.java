package Database;

import Model.Category;

import java.sql.*;
import java.util.ArrayList;

public class CategoryDAO {

    public Category getCategory(String categoryName)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT categoryName "
                    + "FROM category "
                    + "WHERE categoryName = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setString(1,categoryName);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();

            if (srs.next()) {
                categoryName = srs.getString("categoryName");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Category category = new Category(categoryName);
            return category;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveCategory(Category category)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT categoryName "
                    + "FROM category "
                    + "WHERE categoryName = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1,category.getCategoryName());
            ResultSet srs = stmt.executeQuery();

            String sqlInsert = "INSERT into category "
                    + "(categoryName) "
                    + "VALUES (?)";
            //System.out.println(sql);
            PreparedStatement insertStm = con.prepareStatement(sqlInsert);
            insertStm.setString(1, category.getCategoryName());
            insertStm.executeUpdate();
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