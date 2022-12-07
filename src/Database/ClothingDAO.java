package Database;


import Model.Category;
import Model.Clothing;

import java.sql.*;
import java.util.ArrayList;

public class ClothingDAO {
    public Clothing getClothing(int clothingID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT price, userID, categoryName, description "
                    + "FROM clothing "
                    + "WHERE clothingID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,clothingID);


            ResultSet srs = stmt.executeQuery();
            double price;
            String categoryName, description;
            int userID;

            if (srs.next()) {
                price = srs.getDouble("price");
                userID = srs.getInt("userID");
                categoryName = srs.getString("category");
                description = srs.getString("description");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Clothing clothing = new Clothing(clothingID,price,userID,categoryName,description);
            return clothing;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveClothing(Clothing clothing)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT clothingID "
                    + "FROM clothing "
                    + "WHERE clothingID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,clothing.getClothingID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE clothing " +
                        "SET price = ? ," +
                        " userID = ? " +
                        " categoryName = ? " +
                        " description = ? " +
                        "WHERE clothingID = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setDouble(1, clothing.getPrice());
                stmt2.setInt(2, clothing.getUserID());
                stmt2.setString(3, clothing.getCategoryName());
                stmt2.setString(4, clothing.getDescription());
                stmt2.setInt(5, clothing.getClothingID());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into clothing "
                        + "(clothingID, price, userID, categoryName,description) "
                        + "VALUES (?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, clothing.getClothingID());
                insertStm.setInt(2, clothing.getUserID());
                insertStm.setDouble(3, clothing.getPrice());
                insertStm.setString(4,clothing.getCategoryName());
                insertStm.setString(5,clothing.getDescription());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Clothing> getAllClothing()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT clothingID "
                    + "FROM clothing";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Clothing> clothings = new ArrayList<Clothing>();
            while (srs.next())
                clothings.add(getClothing(srs.getInt("clothingID")));
            return clothings;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteClothing(Clothing clothing)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM clothing "
                    + "WHERE clothingID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,clothing.getClothingID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}