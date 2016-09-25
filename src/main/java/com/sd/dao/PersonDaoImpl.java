package com.sd.dao;

import com.sd.dao.connection.DbConnection;
import com.sd.dto.PersonDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

        try
        {
            connection.close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }

        return personDto;
    }

    public List<PersonDto> findByCity(String city) {
        List<PersonDto> personDtos = new ArrayList<PersonDto>();
        Connection connection = DbConnection.getDbConnection();
        Integer cityId = null;
        PreparedStatement prepStmt;
        try
        {
            prepStmt = connection.prepareStatement("SELECT id FROM city WHERE name = ?");
            prepStmt.setString(1, city);
            ResultSet rs = prepStmt.executeQuery();
            if(rs.next())
            {
                cityId = rs.getInt("id");
            }
            prepStmt.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        try
        {
            String sql = "SELECT " +
                            "u.id as id, " +
                            "u.first_name as firstName, " +
                            "u.last_name as lastName, " +
                            "u.active as active, " +
                            "(SELECT c.name FROM city c WHERE u.city_id = c.id) as city " +
                        "FROM " +
                            "user u " +
                        "WHERE " +
                            "u.city_id = ?";
            prepStmt = connection.prepareStatement(sql);
            prepStmt.setInt(1, cityId);
            ResultSet rs = prepStmt.executeQuery();
            while (rs.next())
            {
                PersonDto personDto = new PersonDto();
                personDto.setId(rs.getInt("id"));
                personDto.setFirstName(rs.getString("firstName"));
                personDto.setLastName(rs.getString("lastName"));
                personDto.setActive(rs.getBoolean("active"));
                personDto.setCity(rs.getString("city"));
                personDtos.add(personDto);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return personDtos;
    }
}
