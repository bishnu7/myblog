package com.sd.dao;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Sam Dahal
 */
public class ResultSetMapper<T>
{

    private void setProperty(Object clazz, String fieldName, Object columnValue)
    {
        Field field = null;
        try
        {
            field = clazz.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(clazz, columnValue);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public T mapResultSetToObject(ResultSet rs, Class clazz)
    {
        T bean = null;
        try
        {
            bean = (T)(clazz.newInstance());
            if(rs != null)
            {
                Field[] fields = clazz.getDeclaredFields();
                while (rs.next())
                {
                    setClassFieldFromRsColumn(rs, fields, bean);
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return bean;
    }

    public List<T> mapResultSetToObjects(ResultSet rs, Class clazz)
    {
        List<T> output = null;
        try
        {
            if(rs != null)
            {
                Field[] fields = clazz.getDeclaredFields();
                while(rs.next())
                {
                    T bean = (T) clazz.newInstance();
                    setClassFieldFromRsColumn(rs, fields, bean);
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

    private void setClassFieldFromRsColumn(ResultSet rs, Field[] fields, T bean)
    {
        try
        {
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int i = 0; i < rsmd.getColumnCount(); i++)
            {
                String columnName = rsmd.getColumnLabel(i + 1);
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

        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}