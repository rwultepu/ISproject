package Database;

import Model.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO {

    public Transaction getTransaction(int transactionID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID, clothingID "
                    + "FROM transaction "
                    + "WHERE transactionID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,transactionID);

            ResultSet srs = stmt.executeQuery();
            String shipmentMethod;
            int reviewProduct, reviewService, userID, clothingID;
            Date startDate, endDate;
            String causeName;

            if (srs.next()) {
                shipmentMethod = srs.getString("shipmentMethod");
                reviewProduct = srs.getInt("reviewProduct");
                reviewService = srs.getInt("reviewService");
                startDate = srs.getDate("startDate");
                endDate = srs.getDate("endDate");
                causeName = srs.getString("causeName");
                userID = srs.getInt("userID");
                clothingID = srs.getInt("clothingID");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Transaction transaction = new Transaction();
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void save(Transaction transaction)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT transactionID "
                    + "FROM transaction "
                    + "WHERE transactionID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,transaction.getTransactionID());
            // moet nog id klasse van Britt...
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE transactionID " +
                        "SET shipmentMethod = ? ," +
                        " reviewProduct = ? , " +
                        " reviewService = ? , " +
                        " startDate = ? , " +
                        " endDate = ? , " +
                        " causeName = ? , " +
                        " userID = ? , " +
                        " clothingID = ? , " +
                        "WHERE transactionID = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, transaction.getShipmentMethod());
                stmt2.setBoolean(2,transaction.getReviewProduct());
                stmt2.setBoolean(3, transaction.getReviewService());
                stmt2.setString(4, transaction.getStartDate());
                stmt2.setInt(5, transaction.getEndDate());
                // als die set dingen moeten ook nog aangepast worden
                stmt2.setInt(6, transaction.getcauseName());
                stmt2.setInt(7, transaction.getUserID());
                stmt2.setInt(8, transaction.getClothingID());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into transaction "
                        + "(shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID, clothingID) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, transaction.getShipmentMethod());
                insertStm.setString(2,transaction.getReviewProduct());
                insertStm.setBoolean(3,transaction.getReviewService());
                insertStm.setBoolean(4,transaction.getStartDate());
                insertStm.setString(5,transaction.getEndDate());
                insertStm.setString(6,transaction.getcauseName());
                insertStm.setInt(7,transaction.getUserID());
                insertStm.setInt(7,transaction.getclothingID());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Transaction> getAllTransactions()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT transactionID "
                    + "FROM transaction";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Transaction> transactions = new ArrayList<Transaction>();
            while (srs.next())
                transactions.add(getTransaction(srs.getInt("transactionID")));
            return transactions;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteTransaction(Transaction transaction)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM transaction "
                    + "WHERE transactionID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,transaction.getTransactionID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


}