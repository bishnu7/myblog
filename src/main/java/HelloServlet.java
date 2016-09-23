import com.sd.dao.DaoMapifier;
import com.sd.dao.SdConnection;
import org.apache.log4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Logger LOG = Logger.getLogger(HelloServlet.class);
        LOG.debug("executing do get post");

        Connection connection = SdConnection.getDbConnection();
        Statement stmt = null;
        try
        {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try
        {
            DaoMapifier mapifier = new DaoMapifier();
            mapifier.addParam("getUser", "getUser");
            mapifier.addParam("name", "Sam Dahal");
            ResultSet rs = stmt.executeQuery(mapifier.getQueryString());

            while (rs.next())
            {
                String name = rs.getString("name");
                LOG.debug(name);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
