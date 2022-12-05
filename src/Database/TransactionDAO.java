package Database;

import Model.Cause;
import Model.Review;
import Model.Transaction;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class TransactionDAO {
    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `transaction` (\n"
                    + "  `transactionID` int NOT NULL,\n"
                    + "  `shipmentMethod` varchar(45) NOT NULL,\n"
                    + "  `reviewProduct` int NOT NULL,\n"
                    + "  `reviewService` int NOT NULL,\n"
                    + "  `startDate` date NOT NULL,\n"
                    + "  `endDate` date NOT NULL,\n"
                    + "  `causeName` varchar(50) NOT NULL,\n"
                    + "  `userID` int NOT NULL,\n"
                    + "  PRIMARY KEY (`transactionID`),\n"
                    + "  KEY `causename_idx` (`causeName`),\n"
                    + "  KEY `userID_idx` (`userID`),\n"
                    + "  CONSTRAINT `transaction.causename` FOREIGN KEY (`causeName`) REFERENCES `cause` (`causeName`) ON DELETE RESTRICT ON UPDATE CASCADE,\n"
                    + "  CONSTRAINT `transaction.userid` FOREIGN KEY (`userID`) REFERENCES `renter` (`userID`) ON DELETE RESTRICT ON UPDATE CASCADE\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Transaction getTransaction(int transactionID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID "
                    + "FROM transaction "
                    + "WHERE transactionID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,transactionID);

            ResultSet srs = stmt.executeQuery();
            ShipmentMethod  shipmentMethod;
                // Moet in transaction nog gedeclareerd worden als enum...
            Review reviewProduct, reviewService;
                // Ik denk dat hier ok?
            LocalDate startDate, endDate;
            String causeName, userID;

            if (srs.next()) {
                shipmentMethod = srs.getInt("shipmentMethod");
                    //??
                reviewProduct = srs.getString("reviewProduct");
                reviewService = srs.getBoolean("reviewService");
                    //??
                startDate = srs.getDate("startDate");
                endDate = srs.getDate("endDate");
                    //LocalDate vs Date: wat is het verschil?
                causeName = srs.getString("causeName");
                userID = srs.getString("userID");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Transaction transaction = new Transaction();
            // MOET NOG INGEVULD WORDEN AFH VAN WAT BRITT IN HAAR IMPLEMENTATIE STEEKT VAN TRANSACTION...
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
                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into transaction "
                        + "(shipmentMethod, reviewProduct, reviewService, startDate, endDate, causeName, userID) "
                        + "VALUES (?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, transaction.getShipmentMethod());
                insertStm.setString(2,transaction.getReviewProduct());
                insertStm.setBoolean(3,transaction.getReviewService());
                insertStm.setBoolean(4,transaction.getStartDate());
                insertStm.setString(5,transaction.getEndDate());
                insertStm.setString(6,transaction.getcauseName());
                insertStm.setString(7,transaction.getUserID());
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