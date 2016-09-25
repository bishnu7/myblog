package com.sd.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Sam Dahal
 */
public class ResultSetMapper<T> {
    private void setProperty(Object clazz, String fieldName, Object columnValue) {
        Field field = null;
        try
        {
            field = clazz.getClass().getDeclaredField(fieldName);
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
            field.setAccessible(true);
        try
        {
            field.set(clazz, columnValue);
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public List<T> mapRersultSetToObject(ResultSet rs, Class clazz)
    {
        List<T> output = null;
        try
        {
            if(rs != null)
            {
                    ResultSetMetaData resultSetMetaData = rs.getMetaData();

                    Field[] fields = clazz.getDeclaredFields();

                    while(rs.next())
                    {
                        T bean = (T) clazz.newInstance();
                        for(int i = 0; i < resultSetMetaData.getColumnCount(); i++)
                        {
                            String columnName = resultSetMetaData.getColumnLabel(i + 1);
                            Object columnVal = rs.getObject(i + 1);

                            for(Field field : fields)
                            {
                                if(field.getName().equalsIgnoreCase(columnName) && columnVal != null)
                                {
                                    this.setProperty(bean, field.getName(), columnVal);
                                    break;
                                }
                            }
                        }

                        if(output == null)
                            output = new ArrayList<T>();
                        output.add(bean);
                    }

                }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return output;
    }
}
