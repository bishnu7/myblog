package com.sd.dao;

import com.sd.dao.connection.DbConnection;
import com.sd.dto.PersonDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PersonDaoImpl implements PersonDao
{
    public PersonDto findById(int id)
    {
        PersonDto personDto = new PersonDto();
        Connection connection = DbConnection.getDbConnection();

        String sql = "SELECT " +
                        "u.id as id, " +
                        "u.first_name as firstName, " +
                        "u.last_name as lastName, " +
                        "u.active as active, " +
                        "(SELECT c.name FROM city c WHERE c.id = u.city_id) as city " +
                     "FROM " +
                        "user u " +
                     "WHERE " +
                        "u.id = ?";
        PreparedStatement preparedStmt;
        try
        {
            preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
            System.out.println(sql);
            while(rs.next())
            {
                personDto.setId(rs.getInt("id"));
                personDto.setFirstName(rs.getString("firstName"));
                personDto.setLastName(rs.getString("lastName"));
                personDto.setActive(rs.getBoolean("active"));
                personDto.setCity(rs.getString("city"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return personDto;
    }
}
