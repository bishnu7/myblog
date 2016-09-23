import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by sdahal on 9/23/2016.
 */
public class MyListener implements ServletContextListener
{
    private static final Logger LOGGER = Logger.getLogger(MyListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        LOGGER.debug("servlet has been initialized sfsdfsadfs");
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
        LOGGER.debug("servlet has been destroyed sfsdfsadfs");
    }
}
