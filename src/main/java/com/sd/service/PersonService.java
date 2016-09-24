package com.sd.service;

import com.sd.dao.DbConnection;
import com.sd.dto.PersonDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static com.sd.constants.AppConst.*;


public class PersonService implements Service
{
    public PersonDto findById(int id)
    {
        PersonDto personDto = new PersonDto();
        Connection connection = DbConnection.getDbConnection();
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery("SELECT * FROM user WHERE id = " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next())
            {
                personDto.setFirstName(rs.getString(FIRST_NAME));
                personDto.setLastName(rs.getString(LAST_NAME));
                personDto.setActive(rs.getBoolean(ACTIVE));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personDto;
    }
}
