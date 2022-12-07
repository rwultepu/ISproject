package Database;

import Model.Renter;

import java.sql.*;
import java.util.ArrayList;

public class RenterDAO {

    public Renter getRenter(int userID) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT firstName, lastName, email, phoneNumber, userBirth, streetName, StreetNumber, city, zipCode "
                    + "FROM renter "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1, userID);

            ResultSet srs = stmt.executeQuery();
            String firstName, lastName, email, phoneNumber, streetName, city;
            String userBirth;
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
            Renter renter = new Renter(firstName, lastName, email, phoneNumber, userBirth, streetName, streetNumber, city, zipCode);
            return renter;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public static void saveRenter(Renter renter) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT userID "
                    + "FROM renter "
                    + "WHERE userID = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1, renter.getUserID());
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
                stmt2.setString(2, renter.getLastName());
                stmt2.setString(3, renter.getEmail());
                stmt2.setString(4, renter.getPhoneNumber());
                stmt2.setString(5, renter.getUserBirth());
                //Deze getters missen ook nog
                stmt2.setString(6, renter.getStreetName());
                stmt2.setInt(7, renter.getStreetNumber());
                stmt2.setString(8, renter.getCity());
                stmt2.setInt(9, renter.getZipCode());

                stmt2.setInt(10, renter.getUserID());

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
                insertStm.setString(2, renter.getLastName());
                insertStm.setInt(3, renter.getUserID());
                insertStm.setString(4, renter.getEmail());
                insertStm.setString(5, renter.getPhoneNumber());
                insertStm.setString(6, renter.getUserBirth());
                //Deze getters missen ook nog
                insertStm.setString(7, renter.getStreetName());
                insertStm.setInt(8, renter.getStreetNumber());
                insertStm.setString(9, renter.getCity());
                insertStm.setInt(10, renter.getZipCode());

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
                renters.add(getRenter(srs.getInt("userID")));
            return renters;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public static void deleteRenter(Renter renter) {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql = "DELETE FROM renter "
                    + "WHERE userID = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, renter.getUserID());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}

