package com.sd.service;


public class ServiceFactory
{

    public static  <T> T getService(String serviceName, Class<T> type)
    {
        if("personService".equals(serviceName))
        {
            return type.cast(new PersonService());
        }

        return null;
    }
}
