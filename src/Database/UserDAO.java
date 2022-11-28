package Database;

import model.Student;

        import java.sql.*;
        import java.util.ArrayList;

public class userDAO {
    public static void createTables() throws DBException {
        try {
            // dit maakt de tabellen aan, de relaties moeten nog wel gelegd
            // worden via phpmyadmin
            Connection con = DBHandler.getConnection();
            Statement stmt = con.createStatement();
            String sql = "CREATE TABLE Students ("
                    + "number int(11) NOT NULL, "
                    + "name varchar(50) NOT NULL, "
                    + "fullTime boolean NOT NULL, "
                    + "graduate boolean NOT NULL, "
                    + "summary varchar(50) NOT NULL, "
                    + "PRIMARY KEY (number)" + ")";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Student getStudent(int stuNum)  {
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

    public void save(Student s)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();

            String sqlSelect = "SELECT number "
                    + "FROM Students "
                    + "WHERE number = ? ";

            PreparedStatement stmt = con.prepareStatement(sqlSelect);
            stmt.setInt(1,s.getNumber());
            ResultSet srs = stmt.executeQuery();
            if (srs.next()) {

                // UPDATE
                String sqlUpdate = "UPDATE Students " +
                        "SET name = ? ," +
                        " fullTime = ? , " +
                        "graduate = ?, summary = ? " +
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

                String sqlInsert = "INSERT into Students "
                        + "(number, name, fullTime, graduate, summary) "
                        + "VALUES (?,?,?,?,?)";
                //System.out.println(sql);
                PreparedStatement insertStm = con.prepareStatement(sqlInsert);
                insertStm.setInt(1, s.getNumber());
                insertStm.setString(2,s.getName());
                insertStm.setBoolean(3,s.isFullTime());
                insertStm.setBoolean(4,s.isGraduate());
                insertStm.setString(5,s.getSummary());
                insertStm.executeUpdate();
            }
        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }

    public ArrayList<Student> getStudents()  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT number "
                    + "FROM Students";
            ResultSet srs = stmt.executeQuery(sql);
            ArrayList<Student> studenten = new ArrayList<Student>();
            while (srs.next())
                studenten.add(getStudent(srs.getInt("number")));
            return studenten;
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
        return null;
    }

    public void deleteStudent(Student student)  {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            String sql ="DELETE FROM Students "
                    + "WHERE number = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1,student.getNumber());

            stmt.executeUpdate();
        } catch (DBException dbe) {
            dbe.printStackTrace();

        } catch (Exception ex) {
            ex.printStackTrace();

        }
    }


    public ArrayList<Student> getGraduates() throws DBException {
        Connection con = null;
        try {
            con = DBHandler.getConnection();
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);

            String sql = "SELECT number "
                    + "FROM Students "
                    + "WHERE graduate=" + true;
            ResultSet srs = stmt.executeQuery(sql);

            ArrayList<Student> studenten = new ArrayList<Student>();
            while (srs.next())
                studenten.add(getStudent(srs.getInt("number")));
            return studenten;
        } catch (DBException dbe) {
            dbe.printStackTrace();
            throw dbe;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new DBException(ex);
        }

    }


}