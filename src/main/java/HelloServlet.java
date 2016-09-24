import com.sd.service.PersonService;
import com.sd.service.Service;
import com.sd.service.ServiceFactory;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HelloServlet extends HttpServlet
{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        final Logger LOG = Logger.getLogger(HelloServlet.class);
        LOG.debug("executing do get post");

        Service personService = ServiceFactory.getService("personService", PersonService.class);
        LOG.debug(personService.findById(1));

    }
}
