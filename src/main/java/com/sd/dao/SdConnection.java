package com.sd.dao;

import com.sd.exception.ConnectionFailException;
import org.apache.log4j.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import static com.sd.constants.AppConst.*;


public class SdConnection
{
    private static final Logger LOGGER = Logger.getLogger(SdConnection.class);

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
}
