package com.sd.dao;

import org.skife.jdbi.v2.DBI;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import static com.sd.constants.AppConst.*;

public class JDBIConnection
{
    public static DBI getConnection()
    {
        return new DBI(getDataSource());
    }

    private static DataSource getDataSource()
    {
        Context ctx;
        DataSource ds = null;
        try
        {
            ctx = new InitialContext();
            ds = (DataSource) ctx.lookup(DB_PATH);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return ds;
    }
}
