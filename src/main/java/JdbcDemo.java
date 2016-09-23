import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * Created by sdahal on 9/23/2016.
 */
public class JdbcDemo {
    public static void main(String main)
    {
        DataSource dataSource = null;
        try
        {
            Context ctx = new InitialContext();
            dataSource = (DataSource)ctx.lookup("java:comp/env/jdbc/sdtest");
        }
        catch (NamingException e)
        {
            // who cares???
        }

    }
}
