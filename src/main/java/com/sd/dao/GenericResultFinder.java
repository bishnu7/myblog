package com.sd.dao;


import com.sd.dao.connection.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

public class GenericResultFinder<T>
{
    public T find(Map<String, Object> params)
    {
        T result = null;
        Connection connection = DbConnection.getDbConnection();
        PreparedStatement prepStmt;
        try
        {
            prepStmt = connection.prepareStatement(params.get("sql").toString());
            Map<Integer, Object> prepStmtParams = (LinkedHashMap<Integer,Object>)(params.get("stmtParams"));
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
            ResultSet rs = prepStmt.executeQuery();
            ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
            result = resultSetMapper.mapResultSetToObject(rs, (Class) params.get("class"));
            DbConnection.close(connection);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
