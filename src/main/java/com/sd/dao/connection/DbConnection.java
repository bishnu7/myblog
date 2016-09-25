package com.sd.dao.connection;

import com.sd.exception.ConnectionFailException;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static com.sd.constants.AppConst.*;

public class DbConnection
{
    private static final Logger LOGGER = Logger.getLogger(DbConnection.class);

    public static Connection getDbConnection()
    {
        Context ctx;
        DataSource ds;
        Connection connection = null;

        try
        {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(DB_PATH);
            connection = ds.getConnection();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        if(connection == null)
            throw new ConnectionFailException("Unable to get connection from datasource");

        LOGGER.debug("connection created sucessfully...");

        return connection;
    }

    public static void close(Connection connection)
    {
        if(connection != null)
        {
            try
            {
                connection.close();
                LOGGER.debug("Connection closed successfully");
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
