import com.sd.dao.PersonDao;
import com.sd.dao.PersonDaoImpl;
import com.sd.dto.PersonDto;
import org.apache.log4j.Logger;

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

        PersonDao personDao = new PersonDaoImpl();

        PersonDto personDto = personDao.findById(1);

        List<PersonDto> personDtos = personDao.findByCity("Chicago");

        for(PersonDto personDto1 : personDtos)
        {
            System.out.println("print findbyCity " + personDto1);
        }

        System.out.println("Executing findByID " + personDto);

    }
}
