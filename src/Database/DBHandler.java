package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Deze klasse heb ik aangemaakt omdat het openen en sluiten van een connectie
 * niet enkel voor de DBStudent klasse zou zijn, maar voor alle klasse in de
 * persistence layer.
 *
 * @author stevmert
 */
public class DBHandler {

    private static final String DB_NAME = "db2022_12";
    private static final String DB_PASS = "635d11c7dd46f";

    private static Connection connection;


    public static Connection getConnection() throws DBException {
        if(connection != null)
            return connection;
        else
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");

                String protocol = "jdbc";
                String subProtocol = "mysql";
                String myDatabase = "//pdbmbook.com/" + DB_NAME;
                String URL = protocol + ":" + subProtocol + ":" + myDatabase
                        + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
                String URL2 = "jdbc:mysql://pdbmbook.com:3306/db2022_12";

                connection = DriverManager.getConnection(URL2, DB_NAME, DB_PASS);
                return connection;
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                closeConnection(connection);
                throw new DBException(sqle);
            } catch (ClassNotFoundException cnfe) {
                cnfe.printStackTrace();
                closeConnection(connection);
                throw new DBException(cnfe);
            } catch (Exception ex) {
                ex.printStackTrace();
                closeConnection(connection);
                throw new DBException(ex);
            }
    }

    public static void closeConnection(Connection con) {
        try {
            if(con != null)
                con.close();
        } catch (SQLException sqle) {
            //do nothing
        }
    }
}
