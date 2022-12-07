package Database;

import Model.Transaction;

import java.sql.*;
import java.util.ArrayList;

public class TransactionDAO {

    public Transaction getTransaction(int transactionID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID, clothingID, dateOfTransaction , amountToCause"
                    + "FROM transaction "
                    + "WHERE transactionID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,transactionID);

            ResultSet srs = stmt.executeQuery();
            String shipmentMethod;
            int reviewProduct, reviewService, userID, clothingID;
            String startDate, endDate, dateOfTransaction;
            String causeName;
            double amountToCause;

            if (srs.next()) {
                shipmentMethod = srs.getString("shipmentMethod");
                reviewProduct = srs.getInt("reviewProduct");
                reviewService = srs.getInt("reviewService");
                startDate = srs.getString("startDate");
                endDate = srs.getString("endDate");
                causeName = srs.getString("causeName");
                userID = srs.getInt("userID");
                clothingID = srs.getInt("clothingID");
                dateOfTransaction = srs.getString("dateOfTransaction");
                amountToCause = srs.getDouble("amountToCause");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Transaction transaction = new Transaction(startDate,endDate,transactionID,shipmentMethod,reviewProduct,reviewService,causeName,userID,clothingID,dateOfTransaction, amountToCause);
            return transaction;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void save(Transaction transaction)  {
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
                String sqlUpdate = "UPDATE transaction " +
                        "SET transactionID = ? ," +
                        " shipmentMethod = ? , " +
                        " reviewProduct = ? , " +
                        " reviewService = ? , " +
                        " startDate = ? , " +
                        " endDate = ? , " +
                        " causeName = ? , " +
                        " userID = ? , " +
                        " clothingID = ? , " +
                        " dateOfTransaction = ? , " +
                        "amountToCause = ? ," +
                        "WHERE transactionID = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, transaction.getShipmentMethod());
                stmt2.setInt(2,transaction.getReviewProduct());
                stmt2.setInt(3, transaction.getReviewService());
                stmt2.setString(4, transaction.getStartDate());
                stmt2.setString(5, transaction.getEndDate());
                stmt2.setString(6, transaction.getCauseName());
                stmt2.setInt(7, transaction.getUserID());
                stmt2.setInt(8, transaction.getClothingID());
                stmt2.setString(9, transaction.getDateOfTransaction());
                stmt2.setDouble(10, transaction.getAmountToCause());
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into transaction "
                        + "(shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID, clothingID,dateOfTransaction, amountToCause) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, transaction.getShipmentMethod());
                insertStm.setInt(2,transaction.getReviewProduct());
                insertStm.setInt(3,transaction.getReviewService());
                insertStm.setString(4, transaction.getStartDate());
                insertStm.setString(5, transaction.getEndDate());
                insertStm.setString(6,transaction.getCauseName());
                insertStm.setInt(7,transaction.getUserID());
                insertStm.setInt(8,transaction.getClothingID());
                insertStm.setString(9, transaction.getDateOfTransaction());
                insertStm.setDouble(10, transaction.getAmountToCause());
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