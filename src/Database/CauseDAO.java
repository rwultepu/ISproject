package Database;


import Model.Cause;

import java.sql.*;
import java.util.ArrayList;

public class CauseDAO {
    public static void createCauseTable() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE `cause` (\n"
                    + "  `causeName` varchar(50) NOT NULL,\n"
                    + "  PRIMARY KEY (`causeName`)\n"
                    + ") ENGINE=InnoDB DEFAULT CHARSET=utf8mb3";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Cause getCause(int stuNum)  {
        // Ik snap ni goe hoe de owner hier gelinkt wordt aan de cause
        // getCause dacht ik dan dadde vertrekt van een owner en zo naar cause gaat ma die zijn gelijk niecht gelinkt in de mysqlworkbench...
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql1 = "SELECT number, name, fullTime, graduate, summary "
                    + "FROM Students "
                    + "WHERE number = ?";
            PreparedStatement stmt = con.prepareStatement(sql1);
            stmt.setInt(1,stuNum);

            // let op de spatie na 'summary' en 'Students' in voorgaande SQL
            ResultSet srs = stmt.executeQuery();
            String name, summary;
            int number;
            boolean fullTime, graduate;

            if (srs.next()) {
                number = srs.getInt("number");
                name = srs.getString("name");

                fullTime = srs.getBoolean("fullTime");
                graduate = srs.getBoolean("graduate");
                summary = srs.getString("summary");
            } else {// we verwachten slechts 1 rij...
                return null;
            }
            Student student = new Student(name, number, fullTime, graduate, summary);
            return student;
        } catch (Exception ex) {
            ex.printStackTrace();
            DBHandler.closeConnection(con);
            return null;
        }
    }

    public void save(Cause cause)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            // doet gelijk iets nutteloos?
            String sqlSelect = "SELECT causeName "
                    + "FROM cause "
                    + "WHERE causeName = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setString(1,cause.getCauseName());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                // GEBT NIECHT IETS OM TE UPDATEN HIER DENKK WANT HET ENIGE ELEMENT VAN CAUSE IS CAUSENAME...   ??
                String sqlUpdate = "UPDATE cause " +
                        "SET causeName = ? ," +
                        "WHERE number = ?";
                PreparedStatement stmt2 = con.prepareStatement(sqlUpdate);
                stmt2.setString(1, s.getName());
                stmt2.setBoolean(2,s.isFullTime());
                stmt2.setBoolean(3, s.isGraduate());
                stmt2.setString(4, s.getSummary());
                stmt2.setInt(5, s.getNumber());
                stmt2.executeUpdate();
            } else {
                // INSERT
                // DIT GAAT WEL!!

                String sqlInsert = "INSERT into cause "
                        + "(causeName) "
                        + "VALUES (?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setString(1, cause.getCauseName());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Cause> getAllPossibleCauses()  {
        //MOET HIER GEEN METHODE IN DIE DUPLICATES VERWIJDERT? Heb 'DISTINCT' toegevoegd bij select hiervoor?

        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT DISTINCT causeName "
                    + "FROM cause";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Cause> causes = new ArrayList<Cause>();
            while (srs.next())
                causes.add(getCause(srs.getInt("causeName")));
            return causes;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteCause(Cause cause)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM cause "
                    + "WHERE causeName = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1,cause.getCauseName());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

}
