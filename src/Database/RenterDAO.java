package Database;

import Model.Renter;

import java.sql.*;
import java.util.ArrayList;

public class RenterDAO {
    public static void createRenterTable() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `renter` ("
                    + "`userID` int NOT NULL,"
                    + "`firstName` varchar(50) NOT NULL,"
                    + "`lastName` varchar(50) NOT NULL,"
                    + "`email` varchar(50) NOT NULL,"
                    + "`streetName` varchar(50) NOT NULL,"
                    + "`streetNumber` int NOT NULL,"
                    + "`city` varchar(50) NOT NULL,"
                    + "`zipCode` int NOT NULL,"
                    + "`phoneNumber` varchar(50) DEFAULT NULL,"
                    + "`userBirth` date NOT NULL,"
                    + "PRIMARY KEY (`userid`),"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            //MOET DIT LAATSTE ER WEL BIJSTAAN?
            // als je dit wegdoet, ) moet er sws blijven!

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Renter getRenter(String userID) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT firstName, lastName, email, phoneNumber, userBirth, streetName, StreetNumber, city, zipCode "
                    + "FROM renter "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setString(1, userID);

            ResultSet srs = stmt.executeQuery();
            String firstName, lastName, email, phoneNumber, userBirth, streetName, city;
            int streetNumber, zipCode;

            if (srs.next()) {
                firstName = srs.getString("firstName");
                lastName = srs.getString("lastName");
                email = srs.getString("email");
                phoneNumber = srs.getString("phoneNumber");
                userBirth = srs.getString("userBirth");
                streetName = srs.getString("streetName");
                city = srs.getString("city");

                streetNumber = srs.getInt("streetNumber");
                zipCode = srs.getInt("zipCode");

            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Renter renter = new Renter(firstName, lastName, userID, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
            return renter;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveRenter(Renter renter) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT userID "
                    + "FROM renter "
                    + "WHERE iserID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1, renter.getUserID());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE owner " +
                        "SET firstName = ? ," +
                        " lastName = ? , " +
                        " email = ? " +
                        " phoneNumber = ? , " +
                        " userBirth = ? , " +
                        " streetName = ? , " +
                        " streetNumber = ? , " +
                        " city = ? , " +
                        " zipCode = ? " +
                        "WHERE userID = ?";


                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, renter.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                stmt2.setString(2, renter.isLastName());
                stmt2.setString(3, renter.getEmail());
                stmt2.setString(4, renter.getPhoneNumber());
                stmt2.setString(5, renter.getUserBirth());
                //Deze getters missen ook nog
                stmt2.setString(6, renter.getStreetName());
                stmt2.setString(7, renter.getStreetNumber());
                stmt2.setString(8, renter.getCity());
                stmt2.setString(9, renter.getZipCode());

                stmt2.setString(10, renter.getUserID());

                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into renter "
                        + "(firstName,lastName,userID,email,phoneNumber,userBirth,streetName,streetNumber,city,zipCode) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, renter.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                insertStm.setString(2, renter.isLastName());
                insertStm.setString(3, renter.getUserID());
                insertStm.setString(4, renter.getEmail());
                insertStm.setString(5, renter.getPhoneNumber());
                insertStm.setString(6, renter.getUserBirth());
                //Deze getters missen ook nog
                insertStm.setString(7, renter.getStreetName());
                insertStm.setString(8, renter.getStreetNumber());
                insertStm.setString(9, renter.getCity());
                insertStm.setString(10, renter.getZipCode());

                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Renter> getAllRenters() {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT userID "
                    + "FROM renter";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Renter> renters = new ArrayList<Renter>();
            while (srs.next())
                renters.add(getRenter(srs.getString("userID")));
            return renters;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteRenter(Renter renter) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM renter "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, renter.getUserID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

