import com.sd.dao.JDBIConnection;
import com.sd.dao.PersonDao;
import com.sd.dto.PersonDto;
import org.apache.log4j.Logger;
import org.skife.jdbi.v2.DBI;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Logger LOG = Logger.getLogger(HelloServlet.class);
        LOG.debug("executing do get post");

        DBI connection = JDBIConnection.getConnection();

        PersonDao personDao = connection.open(PersonDao.class);

        personDao.insert("Rakesh", "Sharma", true);

        List<PersonDto> persons = personDao.findAll();

        for(PersonDto personDto : persons)
        {
            System.out.println(personDto.getFirstName());
        }

    }
}
