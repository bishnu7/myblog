package com.sd.sql;


public class PersonDaoSQL
{
    public static String findByCity()
    {
        return  "SELECT " +
                       "u.id as id, " +
                       "u.first_name as firstName, " +
                       "u.last_name as lastName, " +
                       "u.active as active, " +
                       "(SELECT c.name FROM city c WHERE u.city_id = c.id) as city " +
                "FROM " +
                       "user u " +
                "WHERE " +
                        "u.city_id = ?";
    }

    public static String findById()
    {
        return "SELECT " +
                     "u.id as id, " +
                     "u.first_name as firstName, " +
                     "u.last_name as lastName, " +
                     "u.active as active, " +
                     "(SELECT c.name FROM city c WHERE c.id = u.city_id) as city " +
                "FROM " +
                     "user u " +
                "WHERE " +
                    "u.id = ?";
    }
}
