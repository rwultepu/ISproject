package Database;

import Model.Cause;
import Model.Owner;

import java.sql.*;
import java.util.ArrayList;

public class OwnerDAO {
    public static void createOwnerTable() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `owner` ("
                    + "`userid` int NOT NULL,"
                    + "`firstName` varchar(50) NOT NULL,"
                    + "`lastName` varchar(50) NOT NULL,"
                    + "`email` varchar(50) NOT NULL,"
                    + "`streetName` varchar(50) NOT NULL,"
                    + "`streetNumber` int NOT NULL,"
                    + "`city` varchar(50) NOT NULL,"
                    + "`zipCode` int NOT NULL,"
                    + "`phonenumber` varchar(50) DEFAULT NULL,"
                    + "`userBirth` date NOT NULL,"
                    + "`selected%tocause` int NOT NULL,"
                    + "`causeName` varchar(45) NOT NULL,"
                    + "PRIMARY KEY (`userid`),"
                    + "KEY `causename_idx` (`causeName`),"
                    + "CONSTRAINT `causename` FOREIGN KEY (`causeName`) REFERENCES `cause` (`causeName`) ON UPDATE CASCADE\n" +
                    ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            //MOET DIT LAATSTE ER WEL BIJSTAAN?
            // als je dit wegdoet, ) moet er sws blijven!

            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Owner getOwner(String userID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT firstname, lastname, email, phonenumber, userbirth, streetName, StreetNumber, city, zipcode, cause, pledge "
                    + "FROM owner "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setString(1,userID);

            ResultSet srs = stmt.executeQuery();
            String firstName, lastName, email, phoneNumber, userBirth, streetName, city;
            Cause cause;
            int streetNumber , zipCode;
            double pledge;

            if (srs.next()) {
                firstName = srs.getString("firstName");
                lastName = srs.getString("lastName");
                email = srs.getString("email");
                phoneNumber = srs.getString("phoneNumber");
                userBirth = srs.getString("userBirth");
                streetName = srs.getString("streetName");
                city = srs.getString("city");

                cause = ;
                //???

                streetNumber = srs.getInt("streetNumber");
                zipCode = srs.getInt("zipCode");

                pledge = srs.getDouble("pledge");


            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Owner owner = new Owner(firstName,lastName,userID,email,phoneNumber,userBirth,streetName,streetNumber,city,zipCode,cause,pledge);
            return owner;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void saveOwner(Owner owner)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT userID "
                    + "FROM owner "
                    + "WHERE userID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1,owner.getUserID());
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
                        " zipCode = ? , " +
                        " cause = ? , " +
                        " pledge = ? " +
                        "WHERE userID = ?";


                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, owner.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                stmt2.setString(2, owner.isLastName());
                stmt2.setString(3, owner.getEmail());
                stmt2.setString(4, owner.getPhoneNumber());
                stmt2.setString(5, owner.getUserBirth());
                //Deze getters missen ook nog
                stmt2.setString(6, owner.getStreetName());
                stmt2.setString(7, owner.getStreetNumber());
                stmt2.setString(8, owner.getCity());
                stmt2.setString(9, owner.getZipCode());
                //kwni goe wak me die Cause moet
                stmt2.setCause(10, owner.getCause());
                stmt2.setString(11, owner.getPledge());

                stmt2.setString(12, owner.getUserID());

                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into owner "
                        + "(firstName,lastName,userID,email,phoneNumber,userBirth,streetName,streetNumber,city,zipCode,cause,pledge) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, owner.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                insertStm.setString(2, owner.isLastName());
                insertStm.setString(3, owner.getUserID());
                insertStm.setString(4, owner.getEmail());
                insertStm.setString(5, owner.getPhoneNumber());
                insertStm.setString(6, owner.getUserBirth());
                //Deze getters missen ook nog
                insertStm.setString(7, owner.getStreetName());
                insertStm.setString(8, owner.getStreetNumber());
                insertStm.setString(9, owner.getCity());
                insertStm.setString(10, owner.getZipCode());
                //kwni goe wak me die Cause moet
                insertStm.setCause(11, owner.getCause());
                insertStm.setString(12, owner.getPledge());

                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Owner> getAllOwners()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT userID "
                    + "FROM owner";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Owner> owners = new ArrayList<Owner>();
            while (srs.next())
                owners.add(getOwner(srs.getString("userID")));
            return owners;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteOwner(Owner owner)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM owner "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,owner.getUserID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}