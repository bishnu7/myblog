package com.sd.util;

import java.util.Map;

/**
 * Author : Sam Dahal
 */
public class SQLUtil
{
    public static String createSelect(String tableName, Map<String, Object> columns)
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ");
        sb.append(tableName);
        if(columns != null)
        {
            sb.append(" WHERE ");
            sb.append(getColumns(columns));
        }
        return sb.toString();
    }

    public static String getColumns(Map<String, Object> columns)
    {
        StringBuilder query = new StringBuilder();
        int size = columns.entrySet().size();
        int counter = 1;
        for(Map.Entry<String, Object> entry : columns.entrySet())
        {
            counter ++;
            String key = entry.getKey();
            Object val = entry.getValue();
            if(key != null)
            {
                query.append(key + " ");
            }
            if(val != null)
            {
                query.append("= ");
                if(val instanceof Boolean)
                {
                    boolean valisTrue = (Boolean)(val);
                    if(valisTrue)
                    {
                        query.append("'1'");
                    }
                    else
                    {
                        query.append("'0'");
                    }
                }
                else
                {
                    query.append("'" + val + "'");
                }
            }

            if(key != null && val != null)
            {
                if(size != (counter - 1))
                    query.append(" AND ");
            }
        }
        return query.toString();
    }
}
