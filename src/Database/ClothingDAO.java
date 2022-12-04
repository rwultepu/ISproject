package Database;


import Model.Category;
import Model.Clothing;

import java.sql.*;
import java.util.ArrayList;

public class ClothingDAO {
    public static void createClothingTable() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `clothing` ("
                    + "`clothingID` int NOT NULL,"
                    + "`price` float NOT NULL,"
                    + "`userID` int NOT NULL,"
                    + "PRIMARY KEY (number)" + ")"
                    + "PRIMARY KEY (`clothingID`),"
                    + "KEY `userID_idx` (`userID`),\n"
                    + "  KEY `categoryName_idx` (`categoryName`),\n"
                    + "  CONSTRAINT `categoryname` FOREIGN KEY (`categoryName`) REFERENCES `category` (`categoryName`) ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `userID` FOREIGN KEY (`userID`) REFERENCES `owner` (`userid`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Clothing getClothing(String clothingID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT price, category "
                    + "FROM clothing "
                    + "WHERE clothingID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setString(1,clothingID);


            ResultSet srs = stmt.executeQuery();
            double price;
            Category category;

            if (srs.next()) {
                price = srs.getDouble("price");
                category = srs.getCategory("category");
                // category zelfde probleem als cause bij owner

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Clothing clothing = new Clothing(clothingID,price,category);
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
            stmt.setString(1,clothing.getClothingID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE clothing " +
                        "SET price = ? ," +
                        " category = ? " +
                        "WHERE clothingID = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setDouble(1, clothing.getPrice());
                stmt2.setCategory(2, clothing.getCategory());
                //...
                stmt2.setString(3, clothing.getClothingID());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into clothing "
                        + "(clothingID, price, category) "
                        + "VALUES (?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, clothing.getClothingID());
                insertStm.setDouble(2, clothing.getPrice());
                insertStm.setCategory(3,clothing.getCategory());
                //...
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Clothing> getAllClothings()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT clothingID "
                    + "FROM clothing";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Clothing> clothings = new ArrayList<Clothing>();
            while (srs.next())
                clothings.add(getClothing(srs.getString("clothingID")));
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
            stmt.setString(1,clothing.getClothingID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}