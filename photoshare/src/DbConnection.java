package photoshare;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;


public class DbConnection {
    
    public static Connection getConnection() throws RuntimeException {
        Connection conn = null;
        try {
            Context ctx = new InitialContext();
            if (ctx == null) {
                throw new RuntimeException("No Context");
            }
            DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/postgres");
            if (ds == null) {
                throw new RuntimeException("Datasource not found");
            }
            conn = ds.getConnection();
            if (conn == null) {
                throw new RuntimeException("Could not get connection");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e.getMessage());
        }
        return conn;
    }
}
