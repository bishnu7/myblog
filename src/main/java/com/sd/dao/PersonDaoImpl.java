package com.sd.dao;

import com.sd.dao.connection.DbConnection;
import com.sd.dto.PersonDto;
import com.sd.sql.PersonDaoSQL;

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
        PreparedStatement preparedStmt;
        try
        {
            preparedStmt = connection.prepareStatement(PersonDaoSQL.findById());
            preparedStmt.setInt(1, id);
            ResultSet rs = preparedStmt.executeQuery();
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
            prepStmt = connection.prepareStatement(PersonDaoSQL.findByCity());
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
