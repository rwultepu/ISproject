package Database;

import Model.Owner;

import java.sql.*;
import java.util.ArrayList;

public class OwnerDAO {

    public Owner getOwner(int userID)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT firstName, lastName, email, phoneNumber, userBirth, streetName, StreetNumber, city, zipCode, causeName, selectedPercentageToCauseOfOwner"
                    + "FROM owner "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,userID);

            ResultSet srs = stmt.executeQuery();
            String firstName, lastName, email, phoneNumber, streetName, city, causeName;
            String userBirth;
            int streetNumber , zipCode;
            double selectedPercentageToCauseOfOwner;

            if (srs.next()) {
                firstName = srs.getString("firstName");
                lastName = srs.getString("lastName");
                email = srs.getString("email");
                phoneNumber = srs.getString("phoneNumber");
                userBirth = srs.getString("userBirth");
                streetName = srs.getString("streetName");
                city = srs.getString("city");
                causeName = srs.getString("causeName");
                streetNumber = srs.getInt("streetNumber");
                zipCode = srs.getInt("zipCode");
                selectedPercentageToCauseOfOwner = srs.getDouble("selectedPercentageToCauseOfOwner");


            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Owner owner = new Owner(firstName,lastName,email,phoneNumber,userBirth,streetName,streetNumber,city,zipCode,causeName,selectedPercentageToCauseOfOwner);
            return owner;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void saveOwner(Owner owner)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT userID "
                    + "FROM owner "
                    + "WHERE userID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,owner.getUserID());
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
                        " selectedPercentageToCauseOfOwner = ? " +
                        "WHERE userID = ?";


                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, owner.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                stmt2.setString(2, owner.getLastName());
                stmt2.setString(3, owner.getEmail());
                stmt2.setString(4, owner.getPhoneNumber());
                stmt2.setString(5,  owner.getUserBirth());
                //Deze getters missen ook nog
                stmt2.setString(6, owner.getStreetName());
                stmt2.setInt(7, owner.getStreetNumber());
                stmt2.setString(8, owner.getCity());
                stmt2.setInt(9, owner.getZipCode());
                //kwni goe wak me die Cause moet
                stmt2.setString(10, owner.getCauseName());
                stmt2.setDouble(11, owner.getSelectedPercentageToCauseOfOwner());

                stmt2.setInt(12, owner.getUserID());

                stmt2.executeUpdate();
            } else {
                // INSERT

                String sqlInsert = "INSERT into owner "
                        + "(firstName,lastName,userID,email,phoneNumber,userBirth,streetName,streetNumber,city,zipCode,causeName,selectedPercentageToCauseOfOwner) "
                        + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, owner.getFirstName());
                //Er moet bij owner een getFirstName en getLastName komen zodat hier kan setten...
                insertStm.setString(2, owner.getLastName());
                insertStm.setInt(3, owner.getUserID());
                insertStm.setString(4, owner.getEmail());
                insertStm.setString(5, owner.getPhoneNumber());
                insertStm.setString(6, owner.getUserBirth());
                //Deze getters missen ook nog
                insertStm.setString(7, owner.getStreetName());
                insertStm.setInt(8, owner.getStreetNumber());
                insertStm.setString(9, owner.getCity());
                insertStm.setInt(10, owner.getZipCode());
                //kwni goe wak me die Cause moet
                insertStm.setString(11, owner.getCauseName());
                insertStm.setDouble(12, owner.getSelectedPercentageToCauseOfOwner());

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
                owners.add(getOwner(srs.getInt("userID")));
            return owners;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static void deleteOwner(Owner owner)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM owner "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,owner.getUserID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}