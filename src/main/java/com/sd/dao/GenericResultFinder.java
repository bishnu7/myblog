package com.sd.dao;


import com.sd.dao.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.sd.constants.AppConst.*;

public class GenericResultFinder<T>
{
    public List<T> findAll(Map<String, Object> params)
    {
        List<T> results = null;
        Connection connection = DbConnection.getDbConnection();
        try
        {
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
            results = resultSetMapper.mapResultSetToObjects(getRs(connection, params), (Class) params.get(CLASS));
            DbConnection.close(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return results;
    }

    public T find(Map<String, Object> params)
    {
        T result = null;
        Connection connection = DbConnection.getDbConnection();
        try
        {
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
            result = resultSetMapper.mapResultSetToObject(getRs(connection, params), (Class) params.get(CLASS));
            DbConnection.close(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }

    private ResultSet getRs(Connection connection, Map<String, Object> params)
    {
        PreparedStatement prepStmt;
        ResultSet rs = null;
        try
        {
            prepStmt = connection.prepareStatement(params.get(SQL).toString());
            Map<Integer, Object> prepStmtParams = (LinkedHashMap<Integer,Object>)(params.get(STMT_PARAMS));
            for(Map.Entry<Integer, Object> pair : prepStmtParams.entrySet())
            {
                if(pair.getValue() instanceof Integer)
                {
                    int val = (Integer)(pair.getValue());
                    prepStmt.setInt(pair.getKey(), val);
                }
                if(pair.getValue() instanceof String)
                {
                    String val = (String)(pair.getValue());
                    prepStmt.setString(pair.getKey(), val);
                }
            }
            rs = prepStmt.executeQuery();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return rs;
    }
}
